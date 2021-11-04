package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import gt.gob.rgm.inv.dao.DetalleRequisicionRepository;
import gt.gob.rgm.inv.dao.RequisicionRepository;
import gt.gob.rgm.inv.dao.UsuarioRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.DetalleRequisicion;
import gt.gob.rgm.inv.model.Requisicion;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;
import java.util.stream.Collectors;

@Stateless
public class RequisicionServiceImp implements RequisicionService {

	@Inject
	private RequisicionRepository requisicionDao;
	
	@Inject
	private UsuarioRepository usuarioDao;
	
	@Inject
	private DetalleRequisicionRepository detalleRequisicionDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Override
	public ResponseRs listRequisiciones(Map<String, Object> params, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			List<Requisicion> requisiciones = requisicionDao.findByParams(params, page, size); 
			response.setValue(requisiciones);
			response.setTotal(requisicionDao.countByParams(params).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getRequisicion(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(requisicionDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	@Transactional
	public ResponseRs addRequisicion(Requisicion requisicion) {
		ResponseRs response = new ResponseRs();
		
		try {
			LocalDate currentDate = LocalDate.now();
			Integer corr = requisicionDao.findAllByYear(String.valueOf(LocalDate.now().getYear())).size()+1;
			requisicion.setCorrelativo("REQ-" + corr + "-" + currentDate.getYear());
			
			requisicion.setEstado(MessagesInv.SOLICITUD);
			requisicionDao.save(requisicion);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			// obtener las relaciones
			requisicion.setSolicitante(usuarioDao.findById(requisicion.getUsuarioId().intValue()));
			List<DetalleRequisicion> nuevoDetalle = new ArrayList<>();
			for(DetalleRequisicion detalle : requisicion.getDetalle()) {
				detalle.setArticulo(articuloDao.findById(detalle.getCodigoArticulo()));
				nuevoDetalle.add(detalle);
			}
			requisicion.setDetalle(nuevoDetalle);
			response.setValue(requisicion);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	@Transactional
	public ResponseRs updateRequisicion(Long id, Requisicion requisicion) {
		ResponseRs response = new ResponseRs();
		try {
			Requisicion vRequisicion = requisicionDao.findById(id);
			if(vRequisicion != null) {			
				
				if(requisicion.getFecha()!=null) 
					vRequisicion.setFecha(requisicion.getFecha());
				
				if(requisicion.getObservaciones()!=null)
					vRequisicion.setObservaciones(requisicion.getObservaciones());
				
				if(requisicion.getComentario()!=null) {
					vRequisicion.setComentario(requisicion.getComentario());
				}
				
				if(requisicion.getEstado()!=null) {
					vRequisicion.setEstado(requisicion.getEstado());
				}
				// actualizar detalle
				int index = 0;
				for(DetalleRequisicion detalle : requisicion.getDetalle()) {
                                        DetalleRequisicion vDetalle =null;
                                        
                                        if (detalle.getDetalleRequisionId()!=null)
                                            vDetalle = detalleRequisicionDao.findById(detalle.getDetalleRequisionId());
                                         
                                            
					if(vDetalle != null) {
						if(detalle.getCantidadAprobada() != null) {
							vDetalle.setCantidadAprobada(detalle.getCantidadAprobada());
						}                                                                                             
                                                
                                                vDetalle.setCantidad(detalle.getCantidad());
                                               // vDetalle.setCodigoArticulo(detalle.getCodigoArticulo());
                                                vDetalle.setArticulo(articuloDao.findById(vDetalle.getCodigoArticulo()));
                                                
						vRequisicion.getDetalle().set(index, vDetalle);
					}
                                        else{
                                            detalle.setArticulo(articuloDao.findById(detalle.getCodigoArticulo()));
                                            detalle.setRequisicionId(String.valueOf( requisicion.getRequisicionId()));
                                            vRequisicion.getDetalle().add(detalle);
                                        }
					index++;
				}
                                                 
                                if (requisicion.getArticulosEliminados()!= null ){
                                    if (requisicion.getArticulosEliminados().size()>0)
                                    {
                                        List<DetalleRequisicion> requisicion_detalle = vRequisicion.getDetalle().stream().filter(o -> !requisicion.getArticulosEliminados().contains(o.getDetalleRequisionId())).collect(Collectors.toList());
                                        vRequisicion.setDetalle(requisicion_detalle);
                                    }
                                }
				
				requisicionDao.update(vRequisicion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vRequisicion);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Requisicion")
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
	@Transactional
	public ResponseRs deleteRequisicion(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Requisicion vRequisicion = requisicionDao.findById(id);
			if(vRequisicion != null) {
				vRequisicion.setEstado(MessagesInv.INACTIVO);
				requisicionDao.update(vRequisicion);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vRequisicion);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Requisicion")
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
		
		List<Requisicion> requisiciones = new ArrayList<Requisicion>();		
		
		/** pdf **/
		try {
			requisiciones = requisicionDao.findByParams(params, null, null);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("REQUISICIONES GENERADAS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(4,5));
            pdfTO.setSections(sets);
            
            fields.put("start",params.get("fechaInicio").toString());
            fields.put("end",params.get("fechaFin").toString());
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("No. Requisición");
            header.add("Fecha");
            header.add("Nombre");
            header.add("Observaciones");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(requisiciones);
            
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
	
	public byte[] getReporteRequisicionPdf(Map<String,Object> params) {
		
		Requisicion requisicion = new Requisicion();		
		
		/** pdf **/
		try {
			requisicion = requisicionDao.findById((Long) params.get("idRequisicion"));	
			
			Collections.sort(requisicion.getDetalle(), DetalleRequisicion.COMPARE_BY_CODE);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("REQUISICIÓN DE ARTÍCULOS A ALMACEN");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(2,5,6));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("no",requisicion.getCorrelativo());
            fields.put("user",params.get("usuario").toString());
            fields.put("observations",requisicion.getObservaciones());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("Código");
            header.add("Descripción");
            header.add("Cantidad");
            header.add("Cantidad Aprobada");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(requisicion.getDetalle());
            
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
	
	public RequisicionRepository getRequisicionDao() {
		return requisicionDao;
	}

	public void setRequisicionDao(RequisicionRepository requisicionDao) {
		this.requisicionDao = requisicionDao;
	}

	public UsuarioRepository getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioRepository usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public DetalleRequisicionRepository getDetalleRequisicionDao() {
		return detalleRequisicionDao;
	}

	public void setDetalleRequisicionDao(DetalleRequisicionRepository detalleRequisicionDao) {
		this.detalleRequisicionDao = detalleRequisicionDao;
	}

}
