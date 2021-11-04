package gt.gob.rgm.inv.util;

import gt.gob.rgm.inv.domain.Inventario;
import gt.gob.rgm.inv.domain.PdfTO;
import gt.gob.rgm.inv.model.Articulo;
import gt.gob.rgm.inv.model.Despacho;
import gt.gob.rgm.inv.model.DetalleDespacho;
import gt.gob.rgm.inv.model.DetalleIngreso;
import gt.gob.rgm.inv.model.DetalleRequisicion;
import gt.gob.rgm.inv.model.DetalleSalida;
import gt.gob.rgm.inv.model.Ingreso;
import gt.gob.rgm.inv.model.Kardex;
import gt.gob.rgm.inv.model.Proveedor;
import gt.gob.rgm.inv.model.Requisicion;
import gt.gob.rgm.inv.model.Salida;
import gt.gob.rgm.inv.model.Usuario;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

public class PdfUtils {

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void createDocument(Document document, PdfTO pdfTO) throws IOException {
		
		//Create header
		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.setWidth(UnitValue.createPercentValue(60));
        table.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        	        
        table.addCell(new Cell().setBorder(Border.NO_BORDER));
        
        //Image img = new Image(ImageDataFactory.create(MessagesInv.BASEURI + "img/logo-mineco-pweb_0.jpg"));	                
        Image img = new Image(ImageDataFactory.create("/home/corellana/Projects/Java/RUG/inventario/rgm-inventario-frontend/src/assets/img/logo-mineco-pweb_0.jpg"));
        Cell cell = new Cell().add(img.setAutoScale(true));	     
        cell.setBorder(Border.NO_BORDER);        
        table.addCell(cell);
        
        document.add(table);
        
        // Titles
        addTitlePage(document, pdfTO);
        addFields(document,pdfTO);
        addContent(document, pdfTO);
        
	}
	
	private static void addTitlePage(Document document, PdfTO pdfTO)
            throws IOException {
        Paragraph preface = new Paragraph();
        PdfFont catfont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD); 
        
        addEmptyLine(preface, 1);
        
        Paragraph title = new Paragraph(pdfTO.getTitle());
        title.setFont(catfont);
        title.setFontSize(20f);
        preface.setTextAlignment(TextAlignment.CENTER);
        preface.add(title);
        addEmptyLine(preface, 1);
        
        Paragraph subtitle = new Paragraph(pdfTO.getSubtitle());
        subtitle.setFont(catfont);
        subtitle.setFontSize(14f);        
        preface.add(subtitle);       
        addEmptyLine(preface, 1);
                
        document.add(preface);
                      
    }
	
	private static void addFields(Document document, PdfTO pdfTO) throws IOException {
		
		PdfFont catfont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD); 
		
		Style style = new Style()
                .setFont(catfont)
                .setFontSize(12)
                .setFontColor(ColorConstants.LIGHT_GRAY);   
		
		if(pdfTO.getSections().contains(1)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Fecha: ").addStyle(style));
	        p.add(pdfTO.getParams().get("date"));
	        p.add(new Tab());
	        p.add(new Text("Usuario: ").addStyle(style));
	        p.add(pdfTO.getParams().get("user"));
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(2)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Fecha: ").addStyle(style));
	        p.add(pdfTO.getParams().get("date"));
	        p.add(new Tab());
	        p.add(new Text("Reporte No.: ").addStyle(style));
	        p.add(pdfTO.getParams().get("no"));
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(9)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Requisicion No.: ").addStyle(style));
	        p.add(pdfTO.getParams().get("requisition"));	
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
        }
				
		if(pdfTO.getSections().contains(4)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Fecha Inicio: ").addStyle(style));
	        p.add(pdfTO.getParams().get("start"));
	        p.add(new Tab());
	        p.add(new Text("Fecha Fin: ").addStyle(style));
	        p.add(pdfTO.getParams().get("end"));
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(3)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Código Artículo: ").addStyle(style));
	        p.add(pdfTO.getParams().get("code"));
	        p.add(new Tab());
	        p.add("");	        
	        p.add("\n");
	        document.add(p);
	        
	        p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Nombre: ").addStyle(style));
	        p.add(pdfTO.getParams().get("name"));
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(5)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Usuario: ").addStyle(style));
	        p.add(pdfTO.getParams().get("user"));	
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(7)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Tipo Ingreso: ").addStyle(style));
	        p.add(pdfTO.getParams().get("type"));	 
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
	        
	        p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Referencia: ").addStyle(style));
	        p.add(pdfTO.getParams().get("reference"));	 
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
		}
	        
	    if(pdfTO.getSections().contains(8)) {
	    	Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.CENTER);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Tipo Salida: ").addStyle(style));
	        p.add(pdfTO.getParams().get("type"));	 
	        p.add(new Tab());
	        p.add("");
	        p.add("\n");
	        document.add(p);
        }
		
		if(pdfTO.getSections().contains(6)) {
	        Paragraph p = new Paragraph();     
	        p.setTextAlignment(TextAlignment.LEFT);
	        p.addTabStops(new TabStop(400f, TabAlignment.RIGHT));
	        p.add(new Text("Observaciones: ").addStyle(style));
	        p.add(MessagesInv.stringNull(pdfTO.getParams().get("observations")));	        
	        p.add("\n");
	        document.add(p);
        }
		
		
	}
	
	private static void addContent(Document document, PdfTO pdfTO)
            throws IOException {
    	
    	PdfFont catfont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
    	PdfFont colfont = PdfFontFactory.createFont(StandardFonts.HELVETICA); 
    	
    	Cell cell;
    	Table table;
    	
    	if(pdfTO.getSections().contains(3)) {
    		table = new Table(new float[] { 10, 70, 70, 70 }).useAllAvailableWidth();
    	} else {
    		table = new Table(UnitValue.createPercentArray(pdfTO.getRows())).useAllAvailableWidth();
    	}
    	
        for (int i = 0; i < pdfTO.getRows(); i++) {        	
        	cell = new Cell().add(new Paragraph(pdfTO.getHeaders().get(i))
        			.setFont(catfont)
        			.setFontColor(ColorConstants.WHITE)
        			.setFontSize(10f));
            cell.setBackgroundColor(ColorConstants.BLACK);
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addHeaderCell(cell);
        }
        
        for(Object value: pdfTO.getValues()) {
        	if (value instanceof Inventario) {
        		cell = new Cell().add(new Paragraph(((Inventario) value).getCodigo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getDescripcion())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getTipo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getMarca())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getInicial().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getIngreso().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getSalida().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Inventario) value).getExistencia().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);                
        	} else if (value instanceof Usuario) {
        		cell = new Cell().add(new Paragraph(((Usuario) value).getEmail())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Usuario) value).getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Usuario) value).getRol())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Usuario) value).getEstado())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof Articulo) {
        		cell = new Cell().add(new Paragraph(((Articulo) value).getCodigo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getDescripcion())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getTipoArticulo().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getMarca().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getProveedor().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getStock().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Articulo) value).getMinimoExistencia().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                                         
        	} else if (value instanceof Requisicion) {
        		cell = new Cell().add(new Paragraph(((Requisicion) value).getCorrelativo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(dateFormat.format(((Requisicion) value).getFecha()))).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Requisicion) value).getSolicitante().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((Requisicion) value).getObservaciones()).toString())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof DetalleRequisicion) {
        		cell = new Cell().add(new Paragraph(((DetalleRequisicion) value).getCodigoArticulo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleRequisicion) value).getArticulo().getDescripcion())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleRequisicion) value).getCantidad().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((DetalleRequisicion) value).getCantidadAprobada()).toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof Kardex) {
        		cell = new Cell().add(new Paragraph(replace(((Kardex) value).getReferencia()))).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.LEFT);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((Kardex) value).getEntrada()).toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((Kardex) value).getSalida()).toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Kardex) value).getExistencia().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof Ingreso) {
        		cell = new Cell().add(new Paragraph(((Ingreso) value).getCorrelativo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(dateFormat.format(((Ingreso) value).getFecha()))).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Ingreso) value).getReferencia())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Ingreso) value).getTipoIngreso().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Ingreso) value).getSolicitante().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof DetalleIngreso) {
        		cell = new Cell().add(new Paragraph(((DetalleIngreso) value).getCodigoArticulo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((DetalleIngreso) value).getArticulo().getDescripcion()).toString())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.dateNull(((DetalleIngreso) value).getFechaVencimiento()))).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph()).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof Despacho) {
        		cell = new Cell().add(new Paragraph(((Despacho) value).getCorrelativo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(dateFormat.format(((Despacho) value).getFecha()))).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Despacho) value).getRequisicion().getCorrelativo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(MessagesInv.notNull(((Despacho) value).getObservaciones()).toString())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof DetalleDespacho) {
        		cell = new Cell().add(new Paragraph(((DetalleDespacho) value).getCodigoArticulo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleDespacho) value).getArticulo().getDescripcion())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleDespacho) value).getCantidad().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);                              
        	} else if (value instanceof Salida) {
        		cell = new Cell().add(new Paragraph(((Salida) value).getCorrelativo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(dateFormat.format(((Salida) value).getFecha()))).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Salida) value).getObservaciones())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Salida) value).getTipoSalida().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Salida) value).getSolicitante().getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	} else if (value instanceof DetalleSalida) {
        		cell = new Cell().add(new Paragraph(((DetalleSalida) value).getCodigoArticulo())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleSalida) value).getArticulo().getDescripcion())).setFont(colfont).setFontSize(6f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((DetalleSalida) value).getCantidad().toString())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);  
        	} else if (value instanceof Proveedor) {
        		cell = new Cell().add(new Paragraph(((Proveedor) value).getNombre())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Proveedor) value).getNit())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Proveedor) value).getDireccion())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                
                cell = new Cell().add(new Paragraph(((Proveedor) value).getEstado())).setFont(colfont).setFontSize(10f).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
        	}
        }
        document.add(table);
        
    }    
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
	
	public static String replace(String value) {		                  
		return value.replaceAll("\\[TIPO INGRESO\\]:", "INGRESO: ")
				.replaceAll("\\[DOC-REF\\]:", ".")
				.replaceAll("\\[NO_DOC\\]:", ".")
				.replaceAll("\\[TIPO SALIDA\\]:", "SALIDA: ")
				.replaceAll("\\[FECHA_DES\\]:", ".")
				.replaceAll("\\[NO_REQ\\]:", ".")
				.replaceAll("\\[SOLICITANTE\\]:", ".")
				.replaceAll("\\[MOTIVO\\]:", ".")
				.replaceAll("\\[DESPACHO\\]:", "DESPACHO: ")
				.replaceAll("null","");
	}
}
