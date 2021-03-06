//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-548 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.16 at 01:24:04 PM CDT 
//


package mx.gob.se.rug.masiva.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="acto-contrato" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fecha-celebracion" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="fecha-terminacion" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="terminos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "obligacion")
public class Obligacion {

    @XmlAttribute(name = "acto-contrato")
    protected String actoContrato;
    @XmlAttribute(name = "fecha-celebracion")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCelebracion;
    @XmlAttribute(name = "fecha-terminacion")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaTerminacion;
    @XmlAttribute
    protected String terminos;

    /**
     * Gets the value of the actoContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActoContrato() {
        return actoContrato;
    }

    /**
     * Sets the value of the actoContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActoContrato(String value) {
        this.actoContrato = value;
    }

    /**
     * Gets the value of the fechaCelebracion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCelebracion() {
        return fechaCelebracion;
    }

    /**
     * Sets the value of the fechaCelebracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCelebracion(XMLGregorianCalendar value) {
        this.fechaCelebracion = value;
    }

    /**
     * Gets the value of the fechaTerminacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaTerminacion() {
        return fechaTerminacion;
    }

    /**
     * Sets the value of the fechaTerminacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaTerminacion(XMLGregorianCalendar value) {
        this.fechaTerminacion = value;
    }

    /**
     * Gets the value of the terminos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminos() {
        return terminos;
    }

    /**
     * Sets the value of the terminos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminos(String value) {
        this.terminos = value;
    }

}
