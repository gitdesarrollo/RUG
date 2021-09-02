package gt.gob.rgm.inv.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.mail.MailService;
import gt.gob.rgm.inv.model.Salida;
import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.service.ParametroConfService;
import gt.gob.rgm.inv.service.SalidaService;
import gt.gob.rgm.inv.service.UsuarioService;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/salidas")
@RequestScoped
public class SalidaRs {

	@Inject
	private SalidaService salidaService;
	
	@Inject
	ParametroConfService parametroService;
	
	@Inject
	MailService mailService;
	
	@Inject
	UsuarioService usuarioService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getSalidas(@QueryParam(value="page") Integer page, 
				  @QueryParam(value="size") Integer size,
				  @QueryParam(value="estado") String estado,
				  @QueryParam(value="fechaInicio") String fechaInicio,
				  @QueryParam(value="fechaFin") String fechaFin
			  	  ){
		
		Map<String,Object> params = new HashMap<String,Object>();		
		if(estado!=null) params.put("estado", estado);
		if(fechaInicio!=null) params.put("fechaInicio", fechaInicio);
		if(fechaFin!=null) params.put("fechaFin", fechaFin);
		
		return salidaService.listSalidas(params, page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getSalida(@PathParam("id") Long id) {
		return salidaService.getSalida(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs addSalida(Salida salida) {
		ResponseRs result = salidaService.addSalida(salida);
		
		List<Usuario> usuarios = usuarioService.getUsuariosByRole("F");
		for(Usuario user : usuarios) {
			int idTipoMensaje = 1;
			int idAccountSmtp = Integer.valueOf(parametroService.getParam(MessagesInv.ID_SMTP_MAIL_REGISTRO_USUARIOS).getValorParametro());
			String to = user.getEmail();
			String cc = null;
			String cco = null;
			String subject = parametroService.getParam(MessagesInv.MAIL_SUBJECT_CUENTA_OPER).getValorParametro();
			String message = parametroService.getParam(MessagesInv.MAIL_THEME_CUENTA_OPER).getValorParametro();
			subject = subject.replace("@operacion", "Ingreso");
			message = message.replace("@nombreCompleto", user.getNombre());
			message = message.replace("@operacion", "Ingreso");
			message = message.replace("@numero", salida.getCorrelativo());
			
			try {
				mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			} catch(Exception e) {
				// si el envio del correo falla, se captura la excepcion pero continua el proceso
		    	e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs updateSalida(@PathParam("id") Long id, Salida salida) {
		return salidaService.updateSalida(id, salida);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs deleteSalida(@PathParam("id") Long id) {
		return salidaService.deleteSalida(id);
	}
}
