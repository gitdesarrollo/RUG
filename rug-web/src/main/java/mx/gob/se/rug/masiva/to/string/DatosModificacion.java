//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-548 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.16 at 10:22:20 AM CDT 
//


package mx.gob.se.rug.masiva.to.string;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{carga-masiva}tipo-bien-mueble"/>
 *       &lt;/sequence>
 *       &lt;attribute name="monto-maximo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id-moneda" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="descripcion-bienes-muebles" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="terminos-condiciones" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="preve-cambios-bienes-muebles-monto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tipoBienMueble"
})
@XmlRootElement(name = "datos-modificacion")
public class DatosModificacion {

    @XmlElement(name = "tipo-bien-mueble")
    protected List<TipoBienMueble> tipoBienMueble;
    @XmlAttribute(name = "monto-maximo")
    protected String montoMaximo;
    @XmlAttribute(name = "id-moneda")
    protected String idMoneda;
    @XmlAttribute(name = "descripcion-bienes-muebles")
    protected String descripcionBienesMuebles;
    @XmlAttribute(name = "terminos-condiciones")
    protected String terminosCondiciones;
    @XmlAttribute(name = "preve-cambios-bienes-muebles-monto")
    protected String preveCambiosBienesMueblesMonto;

    /**
     * Gets the value of the tipoBienMueble property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipoBienMueble property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoBienMueble().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoBienMueble }
     * 
     * 
     */
    public List<TipoBienMueble> getTipoBienMueble() {
        if (tipoBienMueble == null) {
            tipoBienMueble = new ArrayList<TipoBienMueble>();
        }
        return this.tipoBienMueble;
    }

    /**
     * Gets the value of the montoMaximo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoMaximo() {
        return montoMaximo;
    }

    /**
     * Sets the value of the montoMaximo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoMaximo(String value) {
        this.montoMaximo = value;
    }

    /**
     * Gets the value of the idMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMoneda() {
        return idMoneda;
    }

    /**
     * Sets the value of the idMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMoneda(String value) {
        this.idMoneda = value;
    }

    /**
     * Gets the value of the descripcionBienesMuebles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionBienesMuebles() {
        return descripcionBienesMuebles;
    }

    /**
     * Sets the value of the descripcionBienesMuebles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionBienesMuebles(String value) {
        this.descripcionBienesMuebles = value;
    }

    /**
     * Gets the value of the terminosCondiciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminosCondiciones() {
        return terminosCondiciones;
    }

    /**
     * Sets the value of the terminosCondiciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminosCondiciones(String value) {
        this.terminosCondiciones = value;
    }

    /**
     * Gets the value of the preveCambiosBienesMueblesMonto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreveCambiosBienesMueblesMonto() {
        return preveCambiosBienesMueblesMonto;
    }

    /**
     * Sets the value of the preveCambiosBienesMueblesMonto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreveCambiosBienesMueblesMonto(String value) {
        this.preveCambiosBienesMueblesMonto = value;
    }

}
