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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="elimina-otorgantes" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="elimina-acreedor-adicional" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "eliminar-partes-transmision")
public class EliminarPartesTransmision {

    @XmlAttribute(name = "elimina-otorgantes")
    protected Boolean eliminaOtorgantes;
    @XmlAttribute(name = "elimina-acreedor-adicional")
    protected Boolean eliminaAcreedorAdicional;

    /**
     * Gets the value of the eliminaOtorgantes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEliminaOtorgantes() {
        return eliminaOtorgantes;
    }

    /**
     * Sets the value of the eliminaOtorgantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEliminaOtorgantes(Boolean value) {
        this.eliminaOtorgantes = value;
    }

    /**
     * Gets the value of the eliminaAcreedorAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEliminaAcreedorAdicional() {
        return eliminaAcreedorAdicional;
    }

    /**
     * Sets the value of the eliminaAcreedorAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEliminaAcreedorAdicional(Boolean value) {
        this.eliminaAcreedorAdicional = value;
    }

}
