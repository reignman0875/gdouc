
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Firmas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Firmas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha_trade" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="firma_integrador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firma_revisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firma_aprobacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firma_revision_fisica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firma_visto_bueno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Firmas", propOrder = {
    "fechaTrade",
    "firmaIntegrador",
    "firmaRevisor",
    "firmaAprobacion",
    "firmaRevisionFisica",
    "firmaVistoBueno"
})
public class Firmas {

    @XmlElement(name = "fecha_trade", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaTrade;
    @XmlElement(name = "firma_integrador")
    protected String firmaIntegrador;
    @XmlElement(name = "firma_revisor")
    protected String firmaRevisor;
    @XmlElement(name = "firma_aprobacion")
    protected String firmaAprobacion;
    @XmlElement(name = "firma_revision_fisica")
    protected String firmaRevisionFisica;
    @XmlElement(name = "firma_visto_bueno")
    protected String firmaVistoBueno;

    /**
     * Gets the value of the fechaTrade property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaTrade() {
        return fechaTrade;
    }

    /**
     * Sets the value of the fechaTrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaTrade(XMLGregorianCalendar value) {
        this.fechaTrade = value;
    }

    /**
     * Gets the value of the firmaIntegrador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaIntegrador() {
        return firmaIntegrador;
    }

    /**
     * Sets the value of the firmaIntegrador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaIntegrador(String value) {
        this.firmaIntegrador = value;
    }

    /**
     * Gets the value of the firmaRevisor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaRevisor() {
        return firmaRevisor;
    }

    /**
     * Sets the value of the firmaRevisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaRevisor(String value) {
        this.firmaRevisor = value;
    }

    /**
     * Gets the value of the firmaAprobacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaAprobacion() {
        return firmaAprobacion;
    }

    /**
     * Sets the value of the firmaAprobacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaAprobacion(String value) {
        this.firmaAprobacion = value;
    }

    /**
     * Gets the value of the firmaRevisionFisica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaRevisionFisica() {
        return firmaRevisionFisica;
    }

    /**
     * Sets the value of the firmaRevisionFisica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaRevisionFisica(String value) {
        this.firmaRevisionFisica = value;
    }

    /**
     * Gets the value of the firmaVistoBueno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaVistoBueno() {
        return firmaVistoBueno;
    }

    /**
     * Sets the value of the firmaVistoBueno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaVistoBueno(String value) {
        this.firmaVistoBueno = value;
    }

}
