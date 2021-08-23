package mx.gob.se.rug.mailservice;

import java.util.logging.Level;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import gt.gob.rgm.mail.service.MailService;
import gt.gob.rgm.rs.SpringApplicationContext;
import gt.gob.rgm.service.JmsMessageSenderService;
//import mx.gob.se.mail.MailBeanRemote;
import mx.gob.se.rug.administracion.dto.RegistroUsuario;
import mx.gob.se.rug.constants.Constants;
import mx.gob.se.rug.dto.PersonaFisica;
import mx.gob.se.rug.garantia.to.BoletaPagoTO;
import mx.gob.se.rug.to.UsuarioTO;
import mx.gob.se.rug.util.MyLogger;

public class MailRegistroService /*extends Thread*/ {
	
	private MailService mailService;
	
	private JmsMessageSenderService messageSender;

	/*private MailBeanRemote getBean() {
		MailBeanRemote mailBean = null;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			MyLogger.Logger.log(Level.INFO, "--lookup--");
			mailBean = (MailBeanRemote) ctx.lookup("ejb/MailBeanJNDI");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailBean;
	}*/

	/*public void run() {
		while (true) {
			try {
				MyLogger.Logger.log(Level.INFO, "Init sleep---");
				sleep((int) ( 1000*60));
				MyLogger.Logger.log(Level.INFO, " sleep---");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MyLogger.Logger.log(Level.INFO, "Mandando Correo");
			sendMailPool();
		}
	}

	private void sendMailPool() {
		MailBeanRemote beanRemote = getBean();
		beanRemote.sendMail();
	}*/	

	public void sendMailRegistro(PersonaFisica personaFisica, RegistroUsuario registroUsuario) {
		MyLogger.Logger.log(Level.INFO, "PERSONA_FISICA: " + personaFisica);
		MyLogger.Logger.log(Level.INFO, "REGISTRO_USUARIO: " + registroUsuario);
		String activarUrl = registroUsuario.getUri() + "rs/usuarios/activar?token=" + personaFisica.getToken();
		MyLogger.Logger.log(Level.INFO, "ACTIVAR_TOKEN: " + activarUrl);
		Constants c = new Constants();
		int idTipoMensaje = 1;
		int idAccountSmtp = Integer.valueOf(c.getParamValue(Constants.IDSMPT_REGISTRO_USUARIO));
		String to = personaFisica.getDatosContacto().getEmailPersonal();
		String cc = null;
		String cco = null;
		String subject = c.getParamValue(Constants.MAIL_SUBJECT_REGISTRO);
		String message = c.getParamValue(Constants.MAIL_THEME_REGISTRO);
		subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto());
		message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto());
		message = message.replace("@cve_usuario", personaFisica.getDatosContacto().getEmailPersonal());
		message = message.replace("@uri", registroUsuario.getUri());
		message = message.replace("@activarUrl", activarUrl);
		try {
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
	    } catch(Exception e) {
	    	// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
	    }
	}

	public void sendMailRecupera(PersonaFisica personaFisica, RegistroUsuario registroUsuario) {
		Constants c = new Constants();

		int idTipoMensaje = 5;
		int idAccountSmtp = 1;
		String to = personaFisica.getDatosContacto().getEmailPersonal();
		String cc = null;
		String cco = null;
		String subject = c.getParamValue(Constants.MAIL_SUBJECT_RECUPERA);
		String message = c.getParamValue(Constants.MAIL_THEME_RECUPERA);

		subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto());
		message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto());
		message = message.replace("@cve_usuario", personaFisica.getDatosContacto().getEmailPersonal());
		message = message.replace("@password", registroUsuario.getPassword());

		try {
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
	    } catch(Exception e) {
	    	// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
	    }
	}

	public void sendMailRegUsuarioAcreedor(PersonaFisica personaFisica, RegistroUsuario registroUsuario) {
		MyLogger.Logger.log(Level.INFO, "PERSONA_FISICA: " + personaFisica);
		MyLogger.Logger.log(Level.INFO, "REGISTRO_USUARIO: " + registroUsuario);
		//MailBeanRemote mailBean = getBean();
		//if (mailBean != null) {
			Constants c = new Constants();

			int idTipoMensaje = 7;
			int idAccountSmtp = Integer.valueOf(c.getParamValue(Constants.IDSMPT_ALTA_USU_ACREEDOR));
			String to = personaFisica.getDatosContacto().getEmailPersonal();
			String cc = null;
			String cco = null;
			String subject = c.getParamValue(Constants.MAIL_SUBJECT_REGISTRO);
			String message = c.getParamValue(Constants.MAIL_THEME_REGISTRO_USU_ACREEDOR);

			subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto());

			message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto());
			message = message.replace("@cve_usuario", personaFisica.getDatosContacto().getEmailPersonal());
			message = message.replace("@password", registroUsuario.getPassword());
			message = message.replace("@uri", registroUsuario.getUri());
			message = message.replace("@nombreAcreedor", registroUsuario.getNombreAcreedor());

			//mailBean.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
		/*} else {
			MyLogger.Logger.log(Level.INFO, "No inicialzo el EJB");
		}*/
	}

	public void sendMailRegUsuarioAcreedorExiste(PersonaFisica personaFisica, RegistroUsuario registroUsuario) {
		MyLogger.Logger.log(Level.INFO, "PERSONA_FISICA: " + personaFisica);
		MyLogger.Logger.log(Level.INFO, "REGISTRO_USUARIO: " + registroUsuario);
		//MailBeanRemote mailBean = getBean();
		//if (mailBean != null) {
			Constants c = new Constants();

			int idTipoMensaje = 8;
			int idAccountSmtp = Integer.valueOf(Constants.getParamValue(Constants.IDSMPT_ALTA_USU_NUEVO_ACREEDOR));
			String to = personaFisica.getDatosContacto().getEmailPersonal();
			String cc = null;
			String cco = null;
			String subject = Constants.getParamValue(Constants.MAIL_SUBJECT_REGISTRO);
			String message = Constants.getParamValue(Constants.MAIL_THEME_REGISTRO_USU_ACREEDOR_NO_EXISTE);

			subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto());

			message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto());
			message = message.replace("@cve_usuario", personaFisica.getDatosContacto().getEmailPersonal());
			message = message.replace("@nombreAcreedor", registroUsuario.getNombreAcreedor());

			//mailBean.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
		/*} else {
			MyLogger.Logger.log(Level.WARNING, "No inicialzo el EJB");
		}*/
	}
	public void sendMailDesatendidoAviso(UsuarioTO usuarioTO) {
		MyLogger.Logger.log(Level.INFO, "Mail_REGISTRO_DESATENDIDO: " + usuarioTO.getNombre());
		//MailBeanRemote mailBean = getBean();
		
		//if (mailBean != null) {
			
			int idTipoMensaje = 2;
			int idAccountSmtp = Integer.valueOf(Constants.getParamValue(Constants.IDSMPT_ALTA_USU_NUEVO_ACREEDOR));
			String to = usuarioTO.getPersona().getCorreoElectronico();
			String cc = null;
			String cco = null;
			String subject = Constants.getParamValue(Constants.MAIL_SUBJECT_DESATENDIDO);
			String message = Constants.getParamValue(Constants.MAIL_THEME_DESATENDIDO);
			
			//mailBean.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco,subject, message);
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco,subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
		/*} else {
			MyLogger.Logger.log(Level.WARNING, "No inicialzo el EJB");
		}*/
		
	}
	public void sendMailDesatendidoAvisoFin(UsuarioTO usuarioTO,String idArchivoResultado) {
		MyLogger.Logger.log(Level.INFO, "Mail_REGISTRO_DESATENDIDO: " + usuarioTO.getNombre());
		//MailBeanRemote mailBean = getBean();
		
		//if (mailBean != null) {
			
			int idTipoMensaje = 3;
			int idAccountSmtp = Integer.valueOf(Constants.getParamValue(Constants.IDSMPT_ALTA_USU_NUEVO_ACREEDOR));
			String to = usuarioTO.getPersona().getCorreoElectronico();
			String cc = null;
			String cco = null;
			String subject = Constants.getParamValue(Constants.MAIL_SUBJECT_DESATENDIDO_FIN);
			String message = Constants.getParamValue(Constants.MAIL_THEME_DESATENDIDO_FIN);
			
			message= message.replace("@idArchivoResultado", idArchivoResultado);
			
			//mailBean.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco,subject, message);
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco,subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
		/*} else {
			MyLogger.Logger.log(Level.WARNING, "No inicialzo el EJB");
		}*/
		
	}

	public void sendMailRegistroRepAcreedores(PersonaFisica personaFisica, RegistroUsuario registroUsuario) {
		MyLogger.Logger.log(Level.INFO, "PERSONA_FISICA: " + personaFisica);
		MyLogger.Logger.log(Level.INFO, "REGISTRO_USUARIO: " + registroUsuario);
		//MailBeanRemote mailBean = getBean();
		//if (mailBean != null) {
			Constants c = new Constants();
			// smtpUserMail=c.getParamValue(c.SMTP_MAIL_SENDER_REG);
			int idTipoMensaje = 1;
			int idAccountSmtp = Integer.valueOf(c.getParamValue(Constants.IDSMPT_REGISTRO_USUARIO));
			String to = personaFisica.getDatosContacto().getEmailPersonal();
			String cc = null;
			String cco = null;
			String subject = c.getParamValue(Constants.MAIL_SUBJECT_REGISTRO);
			String message = c.getParamValue(Constants.MAIL_THEME_REGISTRO_REP_ACREEDOR);

			subject = subject.replace("@nombreCompleto",
					personaFisica.getNombreCompleto());

			message = message.replace("@nombreCompleto",
					personaFisica.getNombreCompleto());
			message = message.replace("@cve_usuario", personaFisica
					.getDatosContacto().getEmailPersonal());
			message = message.replace("@password", registroUsuario.getPassword());
			message = message.replace("@uri", registroUsuario.getUri());

			//mailBean.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
			messageSender.sendMessage("ENVIAR:" + idMail);
		/*} else {
			MyLogger.Logger.log(Level.WARNING, "No inicialzo el EJB");
		}*/
		
	}

	public void sendMailRegistroAprobar(PersonaFisica personaFisica, RegistroUsuario registroUsuario) { 
	    MyLogger.Logger.log(Level.INFO, "PERSONA_FISICA: " + personaFisica); 
	    MyLogger.Logger.log(Level.INFO, "REGISTRO_USUARIO: " + registroUsuario); 
	 
	    Constants c = new Constants(); 
	    int idTipoMensaje = 1; 
	    int idAccountSmtp = Integer.valueOf(c.getParamValue(Constants.IDSMPT_REGISTRO_USUARIO)); 
	    String to = personaFisica.getDatosContacto().getEmailPersonal(); 
	    String cc = null; 
	    String cco = null; 
	    String subject = c.getParamValue(Constants.MAIL_SUBJECT_APROBACION); 
	    String message = c.getParamValue(Constants.MAIL_THEME_APROBACION); 
	    subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto()); 
	    message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto()); 
	    message = message.replace("@cve_usuario", personaFisica.getDatosContacto().getEmailPersonal()); 
	    message = message.replace("@uri", registroUsuario.getUri()); 
	    try {
	    	int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
	    	messageSender.sendMessage("ENVIAR:" + idMail);
	    } catch(Exception e) {
	    	// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
	    }
	}
	
	public void sendMailBoletaAprobar(PersonaFisica personaFisica, BoletaPagoTO boleta) { 
	    Constants c = new Constants(); 
	    int idTipoMensaje = 1; 
	    int idAccountSmtp = Integer.valueOf(c.getParamValue(Constants.IDSMPT_REGISTRO_USUARIO)); 
	    String to = personaFisica.getDatosContacto().getEmailPersonal();
	    String cc = null; 
	    String cco = null; 
	    String subject = c.getParamValue(Constants.MAIL_SUBJECT_BOLETA_APROBACION); 
	    String message = c.getParamValue(Constants.MAIL_THEME_BOLETA_APROBACION); 
	    subject = subject.replace("@nombreCompleto", personaFisica.getNombreCompleto()); 
	    message = message.replace("@nombreCompleto", personaFisica.getNombreCompleto()); 
	    message = message.replace("@boleta", boleta.getSerie() + " " + boleta.getNumero()); 
	    message = message.replace("@monto", boleta.getMonto().toString());
	    try {
		    int idMail = mailService.sendMail(idTipoMensaje, idAccountSmtp, to, cc, cco, subject, message);
		    messageSender.sendMessage("ENVIAR:" + idMail);
	    } catch(Exception e) {
	    	// si el envio del correo falla, se captura la excepcion pero continua el proceso
	    	e.printStackTrace();
	    }
	}
	
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public JmsMessageSenderService getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(JmsMessageSenderService messageSender) {
		this.messageSender = messageSender;
	}
}
