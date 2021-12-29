package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

import gt.gob.rgm.inv.dao.ArticuloRepository;
import gt.gob.rgm.inv.dao.DetalleIngresoRepository;
import gt.gob.rgm.inv.dao.IngresoRepository;
import gt.gob.rgm.inv.dao.KardexRepository;
import gt.gob.rgm.inv.dao.TipoIngresoRepository;
import gt.gob.rgm.inv.dao.UsuarioRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.DetalleIngreso;
import gt.gob.rgm.inv.model.Ingreso;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class IngresoServiceImp implements IngresoService {

	@Inject
	private IngresoRepository ingresoDao;
	
	@Inject
	private DetalleIngresoRepository detalleIngresoDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Inject
	private KardexRepository kardexDao;
	
	@Inject
	private TipoIngresoRepository tipoIngresoDao;
	
	@Inject
	private UsuarioRepository usuarioDao;
	
	@Override
	public ResponseRs listIngresos(Map<String, Object> filter, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(ingresoDao.findWithFilter(filter, page, size));
			response.setTotal(ingresoDao.countWithFilter(filter).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(ingresoDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addIngreso(Ingreso ingreso) {
		ResponseRs response = new ResponseRs();
		
		try {
			LocalDate currentDate = LocalDate.now();			
			Integer corr = ingresoDao.findAllByYear(String.valueOf(LocalDate.now().getYear())).size()+1;
			ingreso.setCorrelativo("ING-" + corr + "-" + currentDate.getYear());
			
			ingreso.setEstado(MessagesInv.SOLICITUD);
			ingresoDao.save(ingreso);
						
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			// obtener las relaciones
			ingreso.setSolicitante(usuarioDao.findById(ingreso.getUsuarioId().intValue()));
			List<DetalleIngreso> detalleConRelaciones = new ArrayList<>();
			for(DetalleIngreso detalle: ingreso.getDetalle()) {
				detalle.setArticulo(articuloDao.findById(detalle.getCodigoArticulo()));
				detalleConRelaciones.add(detalle);
			}
			ingreso.setDetalle(detalleConRelaciones);
			response.setValue(ingreso);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateIngreso(Long id, Ingreso ingreso) {
		ResponseRs response = new ResponseRs();
		try {
			Ingreso vIngreso = ingresoDao.findById(id);
			if(vIngreso != null) {			
				
				if(ingreso.getFecha()!=null) 
					vIngreso.setFecha(ingreso.getFecha());
				
				if(ingreso.getObservaciones()!=null)
					vIngreso.setObservaciones(ingreso.getObservaciones());
				
				if(ingreso.getReferencia()!=null) {
					vIngreso.setReferencia(ingreso.getReferencia());
				}				
				
				if(ingreso.getEstado() !=null) {
					vIngreso.setEstado(ingreso.getEstado());
				}
				ingresoDao.update(vIngreso);
				
				if(ingreso.getEstado() !=null && ingreso.getEstado().equalsIgnoreCase(MessagesInv.ACTIVO)){
					// obtener el tipo de ingreso
					ingreso.setTipoIngreso(tipoIngresoDao.findById(ingreso.getTipoIngresoId().longValue()));
					for(DetalleIngreso detalle: ingreso.getDetalle()) {
						// si cambio la cantidad hay que actualizarla
						DetalleIngreso vDetalle = detalleIngresoDao.findById(detalle.getDetalleIngresoId());
						if(vDetalle.getCantidad() != detalle.getCantidad()) {
							vDetalle.setCantidad(detalle.getCantidad());
						}
						
						/** aumenta stock **/
						Map<String, Object> filtro = new HashMap<String, Object>();
						filtro.put("codigo", detalle.getCodigoArticulo());
						Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);
						
						/** guarda kardex **/
						Kardex kardex = new Kardex();
						kardex.setCodigoArticulo(detalle.getCodigoArticulo());
						StringBuffer referencia = new StringBuffer();
						referencia.append("[TIPO INGRESO]:").append(ingreso.getTipoIngreso().getNombre());				
						referencia.append("[DOC-REF]:").append(ingreso.getReferencia());
						referencia.append("[NO_DOC]:").append(ingreso.getCorrelativo());
						kardex.setReferencia(referencia.toString());
						kardex.setEntrada(BigDecimal.valueOf(articulo.getStock() != null ? articulo.getStock() : 0));
						
						articulo.setStock((articulo.getStock() != null ? articulo.getStock() : 0) + detalle.getCantidad().longValue());
						
						kardex.setExistencia(BigDecimal.valueOf(articulo.getStock()));
						kardex.setFecha(new Timestamp(ingreso.getFecha().getTime()));
						articuloDao.update(articulo);
						kardexDao.save(kardex);
					}
				}
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vIngreso);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Ingreso")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	public ResponseRs deleteIngreso(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Ingreso vIngreso = ingresoDao.findById(id);
			if(vIngreso != null) {
				vIngreso.setEstado(MessagesInv.INACTIVO);
				ingresoDao.update(vIngreso);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vIngreso);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Ingreso")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public byte[] getReporteGeneralPdf(Map<String,Object> params) {
		
		List<Ingreso> ingresos = new ArrayList<Ingreso>();	
		
		/** pdf **/
		try {
			ingresos = ingresoDao.findByParams(params);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("INGRESOS GENERADOS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(4,5));
            pdfTO.setSections(sets);
            
            fields.put("start",params.get("fechaInicio").toString());
            fields.put("end",params.get("fechaFin").toString());
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(5);
            
            List<String> header = new ArrayList<String>();
            header.add("No. Ingreso");
            header.add("Fecha");
            header.add("Referencia");
            header.add("Tipo Ingreso");
            header.add("Usuario");    
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(ingresos);
            
            PdfUtils.createDocument(document, pdfTO);
			
			document.close();
			file = os.toByteArray();
			
			return file;
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public byte[] getReporteIngresoPdf(Map<String,Object> params) {
		
		Ingreso ingreso = new Ingreso();		
		
		/** pdf **/
		try {
			ingreso = ingresoDao.findById((Long) params.get("id"));		
			
			Collections.sort(ingreso.getDetalle(), DetalleIngreso.COMPARE_BY_CODE);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("INGRESO DE ARTÍCULOS A ALMACEN");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(2,5,6,7));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("no",ingreso.getCorrelativo());
            fields.put("user",params.get("usuario").toString());
            fields.put("observations",ingreso.getObservaciones());
            fields.put("type",ingreso.getTipoIngreso().getNombre());
            fields.put("reference",ingreso.getReferencia());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("Código");
            header.add("Descripción");
            header.add("Cantidad");
            header.add("Fecha Vencimiento");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(ingreso.getDetalle());
            
            PdfUtils.createDocument(document, pdfTO);
            	
			/**  footer **/
            document.add(new Paragraph("\n\n\n"));
			Table table = new Table(2, true);
	        table.setWidth(UnitValue.createPercentValue(100));
	        table.setHorizontalAlignment(HorizontalAlignment.CENTER);	        
	        table.setBorder(Border.NO_BORDER);	       
	        table.addCell(MessagesInv.createTextCell("_________________________________"));
	        table.addCell(MessagesInv.createTextCell("_________________________________"));
	        table.addCell(MessagesInv.createTextCell(params.get("usuario").toString()));
	        table.addCell(MessagesInv.createTextCell("Vo Bo"));	 
	        document.add(table);
			
			document.close();
			file = os.toByteArray();
			
			return file;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public IngresoRepository getIngresoDao() {
		return ingresoDao;
	}

	public void setIngresoDao(IngresoRepository ingresoDao) {
		this.ingresoDao = ingresoDao;
	}

	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}

	public KardexRepository getKardexDao() {
		return kardexDao;
	}

	public void setKardexDao(KardexRepository kardexDao) {
		this.kardexDao = kardexDao;
	}

}
