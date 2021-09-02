package gt.gob.rgm.inv.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import gt.gob.rgm.inv.dao.UsuarioRepository;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.util.CryptoUtils;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.PdfUtils;
import gt.gob.rgm.inv.util.ResponseRs;

@Stateless
public class UsuarioServiceImp implements UsuarioService {

	@Inject
	private UsuarioRepository usuarioDao;
	
	@Override
	public ResponseRs listUsuarios() {
		ResponseRs response = new ResponseRs();
		
		try { 
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(usuarioDao.findAllUsuarios());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}

	@Override
	public ResponseRs getUsuario(Long id) {
		ResponseRs response = new ResponseRs();
		
		try {
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(usuarioDao.findById(id.intValue()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs addUsuario(Usuario usuario) {
		ResponseRs response = new ResponseRs();
		
		try {			
			// verificar si el correo no existe
			Usuario existente = usuarioDao.findByEmail(usuario.getEmail());
			if(existente != null) {
				throw new Exception("El correo ya está siendo utilizado por otro usuario.");
			}
			// generar password aleatorio
			String password = RandomStringUtils.randomAlphanumeric(8);
			usuario.setPassword(CryptoUtils.hash(password, new StringBuffer(usuario.getEmail()).reverse().toString()));
			usuario.setCreado(Timestamp.valueOf(LocalDateTime.now()));
			usuario.setEstado("A");
			usuarioDao.save(usuario);
			
			// clonar usuario y asignarle el password en texto plano
			Usuario clonado = Usuario.clonar(usuario);
			clonado.setPassword(password);			
									
			response.setCode(0L);
			response.setReason(MessagesInv.OK);
			response.setValue(clonado);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}		
		return response;
	}
	
	@Override
	public ResponseRs updateUsuario(Usuario usuario) {
		ResponseRs response = new ResponseRs();
		try {
			Usuario usuarioEncontrado = usuarioDao.findById(usuario.getUsuarioId());
			if(usuarioEncontrado != null) {
				// actualizar campos
				if(usuario.getEstado() != null) {
					usuarioEncontrado.setEstado(usuario.getEstado());
				}
				if(usuario.getPassword() != null) {
					usuarioEncontrado.setPassword(CryptoUtils.hash(usuario.getPassword(), new StringBuffer(usuario.getEmail()).reverse().toString()));					
				}
				if(usuario.getRol() != null) {
					usuarioEncontrado.setRol(usuario.getRol());
				}
				usuarioDao.update(usuarioEncontrado);				
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(usuario);
			} else {
				// usuario no encontrado
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Usuario")
						.replace("%id%", usuario.getUsuarioId().toString()));
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs recoverUsuario(String email) {
		ResponseRs response = new ResponseRs();
		try {
			Usuario vUsuario = usuarioDao.findByEmail(email);
			String password = generatePassword();
			if(vUsuario != null) {				
				vUsuario.setPassword(password);
				usuarioDao.save(vUsuario);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
				response.setValue(vUsuario);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Usuario")
						.replace("%id%", email));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseRs deleteUsuario(Long id) {
		ResponseRs response = new ResponseRs();
		try {
			Usuario vUsuario = usuarioDao.findById(id.intValue());
			if(vUsuario != null) {
				usuarioDao.delete(vUsuario);
				response.setCode(0L);
				response.setReason(MessagesInv.OK);
			} else {
				response.setCode(1L);
				response.setReason(MessagesInv.NOT_FOUND
						.replace("%object%", "Usuario")
						.replace("%id%", id.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason(MessagesInv.ERROR);
		}
		return response;
	}
	
	public List<Usuario> getUsuariosByRole(String role){
		 return usuarioDao.findByRole(role);
	}
	
	public byte[] getReporteGeneralPdf(Map<String,Object> params) {
		
		
		List<Usuario> usuarios = usuarioDao.findAllUsuarios();
		
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
            pdfTO.setSubtitle("USUARIOS REGISTRADOS");
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
            header.add("Login");
            header.add("Nombre");
            header.add("Rol");
            header.add("Estado");            
            
            pdfTO.setHeaders(header);
			
            pdfTO.setValues(usuarios);
            
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
	
	public static String getEstado(String value) {
    	if(value == null) { 
    		return " ";
    	} else if(value.equalsIgnoreCase("A")) {
    		return "Activo";
    	} else {
    		return "Inactivo";
    	}
    	
    }
	
	public static String getRol(String value) {
    	if(value == null) { 
    		return " ";
    	} 
    	
    	switch(value) {
    		case "A": return "Administrador"; 
    		case "F": return "Financiero";
    		case "I": return "Inventario";    		
    		default: return "Usuario";
    	}
    	
    }
	
	private String generatePassword() {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
		        .build();
		return pwdGenerator.generate(16);
	}

	/** getters and setters **/
	public UsuarioRepository getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioRepository UsuarioDao) {
		this.usuarioDao = UsuarioDao;
	}

}
