package gt.gob.rgm.inv.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.rgm.inv.annotation.SecuredResource;
import gt.gob.rgm.inv.domain.Principal;
import gt.gob.rgm.inv.mail.MailService;
import gt.gob.rgm.inv.model.Usuario;
import gt.gob.rgm.inv.service.ParametroConfService;
import gt.gob.rgm.inv.service.SecurityService;
import gt.gob.rgm.inv.service.UsuarioService;
import gt.gob.rgm.inv.util.MessagesInv;
import gt.gob.rgm.inv.util.ResponseRs;

@Path("/usuarios")
@RequestScoped
public class UsuariosRs {

	@Inject
	SecurityService securityService;
	
	@Inject
	UsuarioService usuarioService;
	
	/*@Inject
	BitacoraOperacionesService bitacoraService;*/
	
	@Inject
	ParametroConfService parametroService;
	
	@Inject
	MailService mailService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getAll() {
		return usuarioService.listUsuarios();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs create(@HeaderParam("Authorization") String authorization, Usuario usuario) {
		ResponseRs result = usuarioService.addUsuario(usuario);	
		
		Usuario vUsuario = (Usuario) result.getValue();
		
		int idTipoMensaje = 1;
		int idAccountSmtp = Integer.valueOf(parametroService.getParam(MessagesInv.ID_SMTP_MAIL_REGISTRO_USUARIOS).getValorParametro());
		String to = vUsuario.getEmail();
		String cc = null;
		String cco = null;
		String subject = parametroService.getParam(MessagesInv.MAIL_SUBJECT_CUENTA_ADM).getValorParametro();
		String message = parametroService.getParam(MessagesInv.MAIL_THEME_CUENTA_ADM).getValorParametro();
		subject = subject.replace("@nombreCompleto", vUsuario.getNombre());
		subject = subject.replace("@accion", "creada");
		message = message.replace("@nombreCompleto", vUsuario.getNombre());
		message = message.replace("@email", vUsuario.getEmail());
		message = message.replace("@accion", "creado");
		message = message.replace("@rol", vUsuario.getRol());
		message = message.replace("@password", vUsuario.getPassword());
		try {
			mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
		} catch(Exception e) {
			// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
		}
		
		return result;
	}
	
	@PUT
	@Path("/{id : \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs update(@HeaderParam("Authorization") String authorization, @PathParam(value="id") Long id, Usuario usuario) {
		ResponseRs result = usuarioService.updateUsuario(usuario);
		
		Usuario vUsuario = (Usuario) result.getValue();
		
		int idTipoMensaje = 1;
		int idAccountSmtp = Integer.valueOf(parametroService.getParam(MessagesInv.ID_SMTP_MAIL_REGISTRO_USUARIOS).getValorParametro());
		String to = vUsuario.getEmail();
		String cc = null;
		String cco = null;
		String subject = parametroService.getParam(MessagesInv.MAIL_SUBJECT_CUENTA_ADM).getValorParametro();
		String message = parametroService.getParam(MessagesInv.MAIL_THEME_CUENTA_ADM).getValorParametro();
		subject = subject.replace("@nombreCompleto", vUsuario.getNombre());
		subject = subject.replace("@accion", "actualizada");
		message = message.replace("@nombreCompleto", vUsuario.getNombre());
		message = message.replace("@email", vUsuario.getEmail());
		message = message.replace("@accion", "actualizado");
		message = message.replace("@rol", vUsuario.getRol());
		message = message.replace("@password", vUsuario.getPassword());
		try {
			mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
		} catch(Exception e) {
			// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
		}
		
		return result;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SecuredResource
	public ResponseRs getUsuario(@PathParam(value="id") Long id) {
		return usuarioService.getUsuario(id);
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Principal login(Principal principal) {
		return securityService.login(principal);
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Principal logout(Principal principal) {
		// TODO: implementar cierre de sesion
		return null;
	}	
	
	@PUT
	@Path("/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs recover(@PathParam(value="email") String email) {		
		ResponseRs result = usuarioService.recoverUsuario(email);
		Usuario usuario = (Usuario) result.getValue();
		
		int idTipoMensaje = 1;
		int idAccountSmtp = Integer.valueOf(parametroService.getParam(MessagesInv.ID_SMTP_MAIL_REGISTRO_USUARIOS).getValorParametro());
		String to = email;
		String cc = null;
		String cco = null;
		String subject = parametroService.getParam(MessagesInv.MAIL_SUBJECT_CUENTA_ADM).getValorParametro();
		String message = parametroService.getParam(MessagesInv.MAIL_THEME_CUENTA_ADM).getValorParametro();
		subject = subject.replace("@nombreCompleto", usuario.getNombre());
		subject = subject.replace("@accion", "actualizada");
		message = message.replace("@nombreCompleto", usuario.getNombre());
		message = message.replace("@email", email);
		message = message.replace("@accion", "actualizado");
		message = message.replace("@rol", usuario.getRol());
		message = message.replace("@password", usuario.getPassword());
		try {
			mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
		} catch(Exception e) {
			// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
		}
		
		return result;
	}
	
}
