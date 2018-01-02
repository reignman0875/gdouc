
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Firmas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad fechaTrade.
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
     * Define el valor de la propiedad fechaTrade.
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
     * Obtiene el valor de la propiedad firmaIntegrador.
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
     * Define el valor de la propiedad firmaIntegrador.
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
     * Obtiene el valor de la propiedad firmaRevisor.
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
     * Define el valor de la propiedad firmaRevisor.
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
     * Obtiene el valor de la propiedad firmaAprobacion.
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
     * Define el valor de la propiedad firmaAprobacion.
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
     * Obtiene el valor de la propiedad firmaRevisionFisica.
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
     * Define el valor de la propiedad firmaRevisionFisica.
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
     * Obtiene el valor de la propiedad firmaVistoBueno.
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
     * Define el valor de la propiedad firmaVistoBueno.
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
