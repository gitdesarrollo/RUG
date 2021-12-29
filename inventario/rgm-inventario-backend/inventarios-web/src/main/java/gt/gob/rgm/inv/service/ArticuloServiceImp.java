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

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import gt.gob.rgm.inv.dao.ArticuloRepository;
import gt.gob.rgm.inv.dao.KardexRepository;
import gt.gob.rgm.inv.dao.MarcaRepository;
import gt.gob.rgm.inv.dao.ProveedorRepository;
import gt.gob.rgm.inv.dao.TipoArticuloRepository;
import gt.gob.rgm.inv.dao.UnidadMedidaRepository;
import gt.gob.rgm.inv.domain.Inventario;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class ArticuloServiceImp implements ArticuloService {

	@Inject
	private ArticuloRepository articuloDao;
	
	@Inject
	private MarcaRepository marcaDao;
	
	@Inject
	private ProveedorRepository proveedorDao;
	
	@Inject
	private TipoArticuloRepository tipoArticuloDao;
	
	@Inject
	private UnidadMedidaRepository unidadMedidaDao;
	
	@Inject
	private KardexRepository kardexDao;
	
	@Override
	public ResponseRs listArticulos(Map<String, Object> filter, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);		
			
			Map<String, Object> articuloFilter = new HashMap<String, Object>();
			if(filter.get("texto")!=null) articuloFilter.put("texto", filter.get("texto"));
									
			List<Articulo> articulos = articuloDao.findWithFilter(articuloFilter, page, size);		
			
			List<Inventario> inventarios = new ArrayList<Inventario>();
			
			for(Articulo articulo : articulos) {
				
				filter.put("codigoArticulo", articulo.getCodigo());
				List<Kardex> kardex = kardexDao.findByParams(filter, null, null);
				
				Inventario inventario = new Inventario();
				inventario.setCodigo(articulo.getCodigo());
				inventario.setDescripcion(articulo.getDescripcion());
				inventario.setMarca(articulo.getMarca().getNombre());
				inventario.setTipo(articulo.getTipoArticulo().getNombre());
				inventario.setUnidad(articulo.getUnidadMedida().getNombre());
				inventario.setCodigoBarras(articulo.getCodigoBarras());
				inventario.setMarcaId(articulo.getMarcaId().intValue());
				inventario.setProveedorId(articulo.getProveedorId().intValue());
				inventario.setTipoArticuloId(articulo.getTipoArticuloId().intValue());
				inventario.setUnidadMedidaId(articulo.getUnidadMedidaId().intValue());
				inventario.setMinimoExistencia(articulo.getMinimoExistencia());
				inventario.setPerecedero(articulo.getPerecedero());
				inventario.setValor(articulo.getValor());
				
				// finds de los saldos				
				if(kardex.size()>0) {
                                        //corellana: Segun reunion con Morse y Lic. Tirsa a la existencia inicial se le restan las salidas y se le suman las entradas 
					inventario.setInicial(MessagesInv.numberNotNull(kardex.get(0).getExistencia()) - MessagesInv.numberNotNull(kardex.get(0).getSalida()) + MessagesInv.numberNotNull(kardex.get(0).getEntrada()));
					inventario.setIngreso(kardexDao.findValueByParams(filter, "ingreso"));
					inventario.setSalida(kardexDao.findValueByParams(filter, "salida"));
					inventario.setExistencia(kardex.get(kardex.size()-1).getExistencia().longValue());
				} else {
					inventario.setInicial(articulo.getStock());
					inventario.setIngreso(0L);
					inventario.setSalida(0L);
					inventario.setExistencia(articulo.getStock());
				}
				
				inventarios.add(inventario);
			}
			
			response.setValue(inventarios);
			if(filter.get("texto")!=null) articuloFilter.put("texto", filter.get("texto"));
			response.setTotal(articuloDao.countWithFilter(articuloFilter).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getArticulo(String id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(articuloDao.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addArticulo(Articulo articulo) {
		ResponseRs response = new ResponseRs();
		
		try {
			articulo.setEstado(MessagesInv.ACTIVO);
			articuloDao.save(articulo);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			// obtener las relaciones
			articulo.setMarca(marcaDao.findById(articulo.getMarcaId().intValue()));
			articulo.setProveedor(proveedorDao.findById(articulo.getProveedorId().intValue()));
			articulo.setTipoArticulo(tipoArticuloDao.findById(articulo.getTipoArticuloId().intValue()));
			articulo.setUnidadMedida(unidadMedidaDao.findById(articulo.getUnidadMedidaId().intValue()));
			response.setValue(articulo);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateArticulo(String id, Articulo articulo) {
		ResponseRs response = new ResponseRs();
		try {
			Articulo vArticulo = articuloDao.findById(id);
			if(vArticulo != null) {
				
				if(articulo.getCodigoBarras()!=null)
					vArticulo.setCodigoBarras(articulo.getCodigoBarras());
				
				if(articulo.getDescripcion()!=null)
					vArticulo.setDescripcion(articulo.getDescripcion());
				
				if(articulo.getFechaVencimiento()!=null)
					vArticulo.setFechaVencimiento(articulo.getFechaVencimiento());
				
				if(articulo.getMinimoExistencia()!=null)
					vArticulo.setMinimoExistencia(articulo.getMinimoExistencia());
				
				if(articulo.getPerecedero()!=null)
					vArticulo.setPerecedero(articulo.getPerecedero());
				
				if(articulo.getValor()!=null)
					vArticulo.setValor(articulo.getValor());
				
				if(articulo.getStock()!=null)
					vArticulo.setStock(articulo.getStock());
				
				if(articulo.getCorrelativo()!=null)
					vArticulo.setCorrelativo(articulo.getCorrelativo());
				
				if(articulo.getMarcaId()!=null)
					vArticulo.setMarcaId(articulo.getMarcaId());
				
				if(articulo.getTipoArticuloId()!=null)
					vArticulo.setTipoArticuloId(articulo.getTipoArticuloId());
				
				if(articulo.getUnidadMedidaId()!=null)
					vArticulo.setUnidadMedidaId(articulo.getUnidadMedidaId());
				
				if(articulo.getProveedorId()!=null)
					vArticulo.setProveedorId(articulo.getProveedorId());
				
				articuloDao.update(vArticulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vArticulo);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Articulo")
						.replace("%id%", id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs deleteArticulo(String id) {
		ResponseRs response = new ResponseRs();
		try {
			Articulo vArticulo = articuloDao.findById(id);
			if(vArticulo != null) {
				vArticulo.setEstado(MessagesInv.INACTIVO);
				articuloDao.update(vArticulo);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Articulo")
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
	public byte[] getInventarioGeneralPdf(Map<String,Object> params) {
		/** pdf **/
		try {
			List<String> kardexes = new ArrayList<String>();
			//PdfTO pdfTO = new PdfTO();
			
			kardexes = kardexDao.findByParams(params);
			
			List<Inventario> articulos = new ArrayList<Inventario>();
			
			for(String kardex : kardexes) {
				Articulo articulo = new Articulo();
				articulo = articuloDao.findById(kardex);
				
				if(articulo.getEstado().equalsIgnoreCase("A")) {
					Inventario inventario = new Inventario();
					inventario.setCodigo(articulo.getCodigo());
					inventario.setDescripcion(articulo.getDescripcion());
					inventario.setMarca(articulo.getMarca().getNombre());
					inventario.setTipo(articulo.getTipoArticulo().getNombre());
					inventario.setUnidad(articulo.getUnidadMedida().getNombre());
					
					// finds de los saldos
					params.put("codigoArticulo", kardex);
					inventario.setInicial(kardexDao.findValueByParams(params, "inicial_reporte")); //anteriormente se enviaba parametro inicial, de ser necesario se puede regresar.
					inventario.setIngreso(kardexDao.findValueByParams(params, "ingreso"));
					inventario.setSalida(kardexDao.findValueByParams(params, "salida"));
					inventario.setExistencia(kardexDao.findValueByParams(params, "final"));
				
					articulos.add(inventario);
				}
			}
			
			Collections.sort(articulos, Inventario.COMPARE_BY_CODE);
				
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("INVENTARIO GENERAL DE ARTÍCULOS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(1));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(8);
            
            List<String> header = new ArrayList<String>();
            header.add("Código Artículo");
            header.add("Descripción");
            header.add("Familia");
            header.add("Marca");
            header.add("Saldo Inicial");
            header.add("Ingresos");
            header.add("Salidas");
            header.add("Existencia");
            
            pdfTO.setHeaders(header);
            
            pdfTO.setValues(articulos);
            
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
	
	@Override
	public byte[] getReporteMinimos(Map<String,Object> params) {
		
		List<Articulo> articulos = new ArrayList<Articulo>();		
		
		articulos = articuloDao.findReporteMinimos(params);
		
		Collections.sort(articulos, Articulo.COMPARE_BY_CODE);
		
		/** pdf **/
		try {
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("REPORTE MÍNIMO DE EXISTENCIAS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(1));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(7);
            
            List<String> header = new ArrayList<String>();
            header.add("Código Artículo");
            header.add("Descripción");
            header.add("Familia");
            header.add("Marca");
            header.add("Proveedor");
            header.add("Existencia");
            header.add("Mínimo Existencia");            
            
            pdfTO.setHeaders(header);
            
            pdfTO.setValues(articulos);
            
            PdfUtils.createDocument(document, pdfTO);
			
            document.close();
			file = os.toByteArray();

			return file;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/** getters and setters **/
	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}

}
