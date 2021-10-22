package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class KardexServiceImp implements KardexService{
	
	@Inject
	private KardexRepository kardexDao;
	
	@Inject
	private ArticuloRepository articuloDao;
	
	@Override
	public ResponseRs listKardex(Map<String, Object> params, Integer page, Integer size) {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			List<Kardex> kardexes = kardexDao.findByParams(params, page, size); 
			response.setValue(kardexes);
			response.setTotal(kardexes.size());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public byte[] getReporteGeneralPdf(Map<String,Object> params) {
		List<Kardex> kardexes = new ArrayList<Kardex>();
		
		/** pdf **/
		try {
			Articulo articulo = articuloDao.findById(params.get("codigoArticulo").toString());
			kardexes = kardexDao.findByParams(params, null, null);
			
			byte file[] = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os);
			PdfDocument pdf = new PdfDocument(writer);
			
			Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);
			
            PdfTO pdfTO = new PdfTO();
            pdfTO.setTitle("REGISTRO DE GARANTÍAS MOBILIARIAS");
            pdfTO.setSubtitle("MOVIMIENTOS POR ARTÍCULO");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(3,4,5));
            pdfTO.setSections(sets);
            
            fields.put("start", params.get("fechaInicio").toString());
            fields.put("end", params.get("fechaFin").toString());
            fields.put("user", params.get("usuario").toString());
            fields.put("code", params.get("codigoArticulo").toString());
            fields.put("name", articulo.getDescripcion());	
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("Referencia");
            header.add("Entrada");
            header.add("Salida");
            header.add("Existencia");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(kardexes);
            
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
	public ResponseRs getKardex(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(kardexDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	public KardexRepository getKardexDao() {
		return kardexDao;
	}

	public void setKardexDao(KardexRepository kardexDao) {
		this.kardexDao = kardexDao;
	}

	public ArticuloRepository getArticuloDao() {
		return articuloDao;
	}

	public void setArticuloDao(ArticuloRepository articuloDao) {
		this.articuloDao = articuloDao;
	}
}
