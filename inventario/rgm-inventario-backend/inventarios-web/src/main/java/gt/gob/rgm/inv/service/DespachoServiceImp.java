package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
import gt.gob.rgm.inv.dao.DespachoRepository;
import gt.gob.rgm.inv.dao.KardexRepository;
import gt.gob.rgm.inv.dao.RequisicionRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.Despacho;
import gt.gob.rgm.inv.model.DetalleDespacho;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.model.Requisicion;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class DespachoServiceImp implements DespachoService {

	@Inject
	private DespachoRepository despachoDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Inject
	private KardexRepository kardexDao;
	
	@Inject
	private RequisicionRepository requisicionDao;
	
	@Override
	public ResponseRs listDespachos(Map<String, Object> filter, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(despachoDao.findWithFilter(filter, page, size));
			response.setTotal(despachoDao.countWithFilter(filter).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getDespacho(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(despachoDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	@Transactional
	public ResponseRs addDespacho(Despacho despacho) {
		ResponseRs response = new ResponseRs();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			LocalDate currentDate = LocalDate.now();			
			Integer corr = despachoDao.findAllByYear(String.valueOf(LocalDate.now().getYear())).size()+1;
			despacho.setCorrelativo("DES-" + corr + "-" + currentDate.getYear());
			
			despacho.setEstado(MessagesInv.ACTIVO);
			despachoDao.save(despacho);
			
			Requisicion requisicion = requisicionDao.findById(despacho.getRequisicionId().longValue());
			despacho.setRequisicion(requisicion);
			for(DetalleDespacho detalle: despacho.getDetalle()) {
				/** disminuye stock **/
				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("codigo", detalle.getCodigoArticulo());
				Articulo articulo = articuloDao.findWithFilter(filtro, null, null).get(0);
				detalle.setArticulo(articulo);
				
				/** guarda kardex **/
				Kardex kardex = new Kardex();
				kardex.setCodigoArticulo(detalle.getCodigoArticulo());
				StringBuffer referencia = new StringBuffer();
				referencia.append("[DESPACHO]:").append(despacho.getCorrelativo());				
				referencia.append("[FECHA_DES]:").append(dateFormat.format(despacho.getFecha()));
				referencia.append("[NO_REQ]:").append(despacho.getRequisicion().getCorrelativo());
				referencia.append("[SOLICITANTE]:").append(despacho.getRequisicion().getSolicitante().getNombre());
				referencia.append("[MOTIVO]:").append(despacho.getObservaciones());
				kardex.setReferencia(referencia.toString());
				kardex.setSalida(BigDecimal.valueOf(articulo.getStock() != null ? articulo.getStock() : 0));
				
				articulo.setStock((articulo.getStock() != null ? articulo.getStock() : 0) - detalle.getCantidad().longValue());
				kardex.setExistencia(BigDecimal.valueOf(articulo.getStock()));
								
				kardex.setFecha(new Timestamp(despacho.getFecha().getTime()));
				articuloDao.update(articulo);
				kardexDao.save(kardex);
				
				/** Actualizo requisicion **/
				requisicion.setEstado("D");
				requisicionDao.update(requisicion);
			}
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(despacho);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	@Transactional
	public ResponseRs updateDespacho(Long id, Despacho despacho) {
		ResponseRs response = new ResponseRs();
		try {
			Despacho vDespacho = despachoDao.findById(id);
			if(vDespacho != null) {	
				if(despacho.getContador()!=null)
					vDespacho.setContador(despacho.getContador());
				
				if(despacho.getCorrelativo()!=null)
					vDespacho.setCorrelativo(despacho.getCorrelativo());
				
				if(despacho.getFecha()!=null)
					vDespacho.setFecha(despacho.getFecha());
				
				if(despacho.getObservaciones()!=null)
					vDespacho.setObservaciones(despacho.getObservaciones());
				
				if(despacho.getEstado()!=null)
					vDespacho.setEstado(despacho.getEstado());
				
				despachoDao.update(vDespacho);
				response.setCode(0L);
				response.setReason(MessagesInv.OK); 
				response.setValue(vDespacho);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Despacho")
						.replace("%id%", despacho.getDespachoId().toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseRs deleteDespacho(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Despacho vDespacho = despachoDao.findById(id);
			if(vDespacho != null) {
				vDespacho.setEstado(MessagesInv.INACTIVO);
				despachoDao.update(vDespacho);
				response.setCode(0L);
				response.setReason(MessagesInv.OK); 
				response.setValue(vDespacho);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Despacho")
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
		
		List<Despacho> despachos = new ArrayList<Despacho>();		
		
		/** pdf **/
		try {
			despachos = despachoDao.findByParams(params);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("DESPACHOS GENERADOS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(4,5));
            pdfTO.setSections(sets);
            
            fields.put("start",params.get("fechaInicio").toString());
            fields.put("end",params.get("fechaFin").toString());
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("No. Despacho");
            header.add("Fecha");
            header.add("No. Requisicion");
            header.add("Observaciones");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(despachos);
            
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
	
	public byte[] getReporteDespachoPdf(Map<String,Object> params) {
		
		Despacho despacho = new Despacho();		
		
		/** pdf **/
		try {
			despacho = despachoDao.findById((Long) params.get("id"));	
			
			Collections.sort(despacho.getDetalle(), DetalleDespacho.COMPARE_BY_CODE);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("DESPACHO DE ARTÍCULOS DE ALMACEN");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(2,5,6,9));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("no",despacho.getCorrelativo());
            fields.put("user",params.get("usuario").toString());
            fields.put("observations",despacho.getObservaciones());
            fields.put("requisition", despacho.getRequisicion().getCorrelativo());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(3);
            
            List<String> header = new ArrayList<String>();
            header.add("Código");
            header.add("Descripción");
            header.add("Cantidad Despachada");                        
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(despacho.getDetalle());
            
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

	public DespachoRepository getDespachoDao() {
		return despachoDao;
	}

	public void setDespachoDao(DespachoRepository despachoDao) {
		this.despachoDao = despachoDao;
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
