//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-548 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.16 at 01:24:04 PM CDT 
//


package mx.gob.se.rug.masiva.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{carga-masiva}persona-solicita-autoridad-instruye-asiento" minOccurs="0"/>
 *         &lt;element ref="{carga-masiva}datos-renovacion-reduccion"/>
 *         &lt;element ref="{carga-masiva}identificador-garantia"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personaSolicitaAutoridadInstruyeAsiento",
    "datosRenovacionReduccion",
    "identificadorGarantia"
})
@XmlRootElement(name = "renovacion-reduccion")
public class RenovacionReduccion {

    @XmlElement(name = "persona-solicita-autoridad-instruye-asiento")
    protected PersonaSolicitaAutoridadInstruyeAsiento personaSolicitaAutoridadInstruyeAsiento;
    @XmlElement(name = "datos-renovacion-reduccion", required = true)
    protected DatosRenovacionReduccion datosRenovacionReduccion;
    @XmlElement(name = "identificador-garantia", required = true)
    protected IdentificadorGarantia identificadorGarantia;

    /**
     * Gets the value of the personaSolicitaAutoridadInstruyeAsiento property.
     * 
     * @return
     *     possible object is
     *     {@link PersonaSolicitaAutoridadInstruyeAsiento }
     *     
     */
    public PersonaSolicitaAutoridadInstruyeAsiento getPersonaSolicitaAutoridadInstruyeAsiento() {
        return personaSolicitaAutoridadInstruyeAsiento;
    }

    /**
     * Sets the value of the personaSolicitaAutoridadInstruyeAsiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaSolicitaAutoridadInstruyeAsiento }
     *     
     */
    public void setPersonaSolicitaAutoridadInstruyeAsiento(PersonaSolicitaAutoridadInstruyeAsiento value) {
        this.personaSolicitaAutoridadInstruyeAsiento = value;
    }

    /**
     * Gets the value of the datosRenovacionReduccion property.
     * 
     * @return
     *     possible object is
     *     {@link DatosRenovacionReduccion }
     *     
     */
    public DatosRenovacionReduccion getDatosRenovacionReduccion() {
        return datosRenovacionReduccion;
    }

    /**
     * Sets the value of the datosRenovacionReduccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosRenovacionReduccion }
     *     
     */
    public void setDatosRenovacionReduccion(DatosRenovacionReduccion value) {
        this.datosRenovacionReduccion = value;
    }

    /**
     * Gets the value of the identificadorGarantia property.
     * 
     * @return
     *     possible object is
     *     {@link IdentificadorGarantia }
     *     
     */
    public IdentificadorGarantia getIdentificadorGarantia() {
        return identificadorGarantia;
    }

    /**
     * Sets the value of the identificadorGarantia property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificadorGarantia }
     *     
     */
    public void setIdentificadorGarantia(IdentificadorGarantia value) {
        this.identificadorGarantia = value;
    }

}
