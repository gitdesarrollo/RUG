package gt.gob.rgm.inv.mail;

public interface MailService {

	public void sendMail(int idTipoMensaje, int idAccountSmtp, String to, String cc, String cco, String subject, String message);
	
}
