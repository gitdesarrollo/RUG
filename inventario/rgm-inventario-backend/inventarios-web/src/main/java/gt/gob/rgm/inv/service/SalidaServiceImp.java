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
import gt.gob.rgm.inv.dao.KardexRepository;
import gt.gob.rgm.inv.dao.SalidaRepository;
import gt.gob.rgm.inv.dao.UsuarioRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.DetalleSalida;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.model.Salida;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class SalidaServiceImp implements SalidaService {

	@Inject
	private SalidaRepository salidaDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Inject
	private KardexRepository kardexDao;
	
	@Inject
	private UsuarioRepository usuarioDao;
	
	@Override
	public ResponseRs listSalidas(Map<String, Object> filter, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(salidaDao.findWithFilter(filter, page, size));
			response.setTotal(salidaDao.countWithFilter(filter).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getSalida(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(salidaDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs addSalida(Salida salida) {
		ResponseRs response = new ResponseRs();
		
		try {
			LocalDate currentDate = LocalDate.now();
			Integer corr = salidaDao.findAllByYear(String.valueOf(LocalDate.now().getYear())).size()+1;
			salida.setCorrelativo("SAL-" + corr + "-" + currentDate.getYear());
						
			salida.setEstado(MessagesInv.SOLICITUD);
			salidaDao.save(salida);
			
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			// obtener las relaciones
			salida.setSolicitante(usuarioDao.findById(salida.getUsuarioId().intValue()));
			List<DetalleSalida> detalleConRelaciones = new ArrayList<>();
			for(DetalleSalida detalle: salida.getDetalle()) {
				detalle.setArticulo(articuloDao.findById(detalle.getCodigoArticulo()));
				detalleConRelaciones.add(detalle);
			}
			salida.setDetalle(detalleConRelaciones);
			response.setValue(salida);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs updateSalida(Long id, Salida salida) {
		ResponseRs response = new ResponseRs();
		try {
			Salida vSalida = salidaDao.findById(id);
			if(vSalida != null) {			
				
				if(salida.getFecha()!=null) 
					vSalida.setFecha(salida.getFecha());
				
				if(salida.getObservaciones()!=null)
					vSalida.setObservaciones(salida.getObservaciones());
				
				if(salida.getEstado() != null)
					vSalida.setEstado(salida.getEstado());
				
				salidaDao.update(vSalida);
				
				if(salida.getEstado() != null && vSalida.getEstado().equalsIgnoreCase(MessagesInv.ACTIVO)) {
					for(DetalleSalida detalle: salida.getDetalle()) {
						/** disminuye stock **/
						Map<String, Object> filtro = new HashMap<String, Object>();
						filtro.put("codigo", detalle.getCodigoArticulo());
						Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);
						
						/** guarda kardex **/
						Kardex kardex = new Kardex();
						kardex.setCodigoArticulo(detalle.getCodigoArticulo());
						StringBuffer referencia = new StringBuffer();
						referencia.append("[TIPO SALIDA]:").append(salida.getTipoSalida().getNombre());				
						referencia.append("[DOC-REF]:").append(salida.getObservaciones());
						referencia.append("[NO_DOC]:").append(salida.getCorrelativo());
						kardex.setReferencia(referencia.toString());
						kardex.setSalida(BigDecimal.valueOf(articulo.getStock() != null ? articulo.getStock() : 0));
						
						articulo.setStock((articulo.getStock() != null ? articulo.getStock() : 0) - detalle.getCantidad().longValue());
						
						kardex.setExistencia(BigDecimal.valueOf(articulo.getStock()));
						kardex.setFecha(new Timestamp(salida.getFecha().getTime()));
						articuloDao.update(articulo);
						kardexDao.save(kardex);
					}
				}
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Salida")
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
	public ResponseRs deleteSalida(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Salida vSalida = salidaDao.findById(id);
			if(vSalida != null) {
				vSalida.setEstado(MessagesInv.INACTIVO);
				salidaDao.update(vSalida);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vSalida);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Salida")
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
		List<Salida> salidas = new ArrayList<Salida>();
		
		/** pdf **/
		try {
			salidas = salidaDao.findByParams(params);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("SALIDAS  GENERADAS");
            
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(4,5));
            pdfTO.setSections(sets);
            
            fields.put("start",params.get("fechaInicio").toString());
            fields.put("end",params.get("fechaFin").toString());
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(5);
            
            List<String> header = new ArrayList<String>();
            header.add("No. Salida");
            header.add("Fecha");            
            header.add("Observaciones");
            header.add("Tipo Salida");
            header.add("Usuario");
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(salidas);
            
            PdfUtils.createDocument(document, pdfTO);
            
			document.close();
			file = os.toByteArray();
			
			return file;
			
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public byte[] getReporteSalidaPdf(Map<String,Object> params) {
		
		Salida salida = new Salida();		
		
		/** pdf **/
		try {
			salida = salidaDao.findById((Long) params.get("id"));	
			
			Collections.sort(salida.getDetalle(), DetalleSalida.COMPARE_BY_CODE);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("SALIDA DE ARTÍCULOS DE ALMACEN");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(2,5,6,8));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("no",salida.getCorrelativo());
            fields.put("user",params.get("usuario").toString());
            fields.put("observations",salida.getObservaciones());
            fields.put("type",salida.getTipoSalida().getNombre());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(3);
            
            List<String> header = new ArrayList<String>();
            header.add("Código");
            header.add("Descripción");
            header.add("Cantidad");                        
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(salida.getDetalle());
            
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

	public SalidaRepository getSalidaDao() {
		return salidaDao;
	}

	public void setSalidaDao(SalidaRepository salidaDao) {
		this.salidaDao = salidaDao;
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
