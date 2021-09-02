package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import gt.gob.rgm.inv.dao.ProveedorRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Proveedor;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class ProveedorServiceImp implements ProveedorService {

	@Inject
	private ProveedorRepository proveedorDao;
	
	@Override
	public ResponseRs listProveedores() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(proveedorDao.findAllProveedores());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getProveedor(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(proveedorDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addProveedor(Proveedor proveedor) {
		ResponseRs response = new ResponseRs();
		
		try {
			proveedor.setEstado(MessagesInv.ACTIVO);
			proveedorDao.save(proveedor);
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(proveedor);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateProveedor(Long id, Proveedor proveedor) {
		ResponseRs response = new ResponseRs();
		try {
			Proveedor vProveedor = proveedorDao.findById(id.intValue());
			if(vProveedor != null) {
				
				if(proveedor.getDireccion()!=null)
					vProveedor.setDireccion(proveedor.getDireccion());
				
				if(proveedor.getNit()!=null)
					vProveedor.setNit(proveedor.getNit());
				
				if(proveedor.getNombre()!=null) {
					vProveedor.setNombre(proveedor.getNombre());
				}
				
				proveedorDao.update(vProveedor);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vProveedor);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Proveedor")
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
	public ResponseRs deleteProveedor(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Proveedor vProveedor = proveedorDao.findById(id.intValue());
			if(vProveedor != null) {
				vProveedor.setEstado(MessagesInv.INACTIVO);
				proveedorDao.update(vProveedor);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Proveedor")
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
		
		List<Proveedor> proveedores = proveedorDao.findAllProveedores();
		
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
            pdfTO.setSubtitle("PROVEEDORES REGISTRADOS");
            Map<String,String> fields = new HashMap<String,String>();
            
            Set<Integer> sets = new HashSet<>(Arrays.asList(1));
            pdfTO.setSections(sets);
            
            LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
            fields.put("date",localDate.format(formatter));
            fields.put("user",params.get("usuario").toString());
            
            pdfTO.setParams(fields);
            pdfTO.setRows(4);
            
            List<String> header = new ArrayList<String>();
            header.add("Nombre");
            header.add("NIT");
            header.add("Direccion");
            header.add("Estado");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(proveedores);
            
            PdfUtils.createDocument(document, pdfTO);
			
			document.close();
			file = os.toByteArray();
			
			return file;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/** getters and setters **/
	public ProveedorRepository getProveedorDao() {
		return proveedorDao;
	}

	public void setProveedorDao(ProveedorRepository proveedorDao) {
		this.proveedorDao = proveedorDao;
	}

}
