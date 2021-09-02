package gt.gob.rgm.inv.util;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public interface MessagesInv {

	public static final String NOT_FOUND = "El objeto %object% con id %id% no se encuentra.";
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";
	public static final String SECRET = "ABCDEF123456#ZYXWVU987654";
	public static final Long SESSION_DURATION = 4 * 60 * 60 * 1000L;
	public static final String INACTIVO = "I";
	public static final String ACTIVO = "A";
	public static final String SOLICITUD = "S";
	public static final String DESPACHO = "D";
	public static final String ANULADA = "C";
	public static final String APROBADA = "A";
	public static final String BASEURI = "../applications/inventarios-web/WEB-INF/classes/html/";
	//public static final String BASEURI = "src/main/resources/html/";
	public static final String ID_SMTP_MAIL_REGISTRO_USUARIOS = "idSmtp-MailRegistroUsuarios";
	public static final String MAIL_SUBJECT_CUENTA_ADM = "mailSubjectCuentaAdm";
	public static final String MAIL_THEME_CUENTA_ADM = "mailThemeCuentaAdm";
	public static final String MAIL_SUBJECT_CUENTA_OPER = "mailSubjectOper";
	public static final String MAIL_THEME_CUENTA_OPER = "mailThemeOper";
	public static final String TEMPLATE_HTML_UNO = "HTMLTemplate1";
	public static final String TEMPLATE_HTML_DOS = "HTMLTemplate2";
	public static final String TEMPLATE_HTML_TRES = "HTMLTemplate3";
	public static final String TEMPLATE_HTML_INGRESO = "HTMLIngreso";
	public static final String TEMPLATE_HTML_SALIDA = "HTMLSalida";
	public static final String TEMPLATE_HTML_DESPACHO = "HTMLDespacho";
	
	public static final String smtp_host = "smtp_host";
	public static final String smtp_port = "smtp_port";
	public static final String smtp_user_mail = "smtp_user_mail";
	public static final String smtp_password = "smtp_password";
	public static final String smtp_auth = "smtp_auth";
	public static final String smtp_ssl_enable = "smtp_ssl_enable";
	public static final String mail_content_type = "mail_content_type";
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
	
	public static Cell createImageCell(String path) throws MalformedURLException {
        Image img = new Image(ImageDataFactory.create(path));
        Cell cell = new Cell().add(img.setAutoScale(false))
        		.setPadding(10f)
        		.setSpacingRatio(10f);
        cell.setBorder(null);
        return cell;
    }
 
    public static Cell createTextCell(String text) {
    	
    	Style code = new Style();       
        code.setFontColor(ColorConstants.BLACK);
    	
        Cell cell = new Cell();
        Paragraph p = new Paragraph(text);
        p.addStyle(code);
        p.setTextAlignment(TextAlignment.CENTER);
        cell.add(p).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }
    
    public static Object notNull(Object value) {
    	if(value == null) 
    		return "0";
    	
    	return value;
    }
    
    public static String stringNull(String value) {
    	if(value == null) 
    		return "";
    	
    	return value;
    }
    
    public static String dateNull(Date value) {
    	if(value == null) 
    		return "";
    	
    	return value.toInstant().atOffset(ZoneOffset.UTC)
				.toLocalDateTime().format(formatter);
    }
    
    public static Long numberNotNull(BigDecimal value) {
    	if(value == null) 
    		return 0L;
    	
    	return value.longValue();
    }
}
