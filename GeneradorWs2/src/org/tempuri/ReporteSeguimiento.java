
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para ReporteSeguimiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReporteSeguimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaCreacion" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="StatusTrade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estrategia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Portafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Periodo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LineaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReporteSeguimiento", propOrder = {
    "orden",
    "fechaCreacion",
    "statusTrade",
    "estrategia",
    "portafolio",
    "periodo",
    "lineaNegocio",
    "tipoTransporte"
})
public class ReporteSeguimiento {

    @XmlElement(name = "Orden")
    protected String orden;
    @XmlElement(name = "FechaCreacion", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCreacion;
    @XmlElement(name = "StatusTrade")
    protected String statusTrade;
    @XmlElement(name = "Estrategia")
    protected String estrategia;
    @XmlElement(name = "Portafolio")
    protected String portafolio;
    @XmlElement(name = "Periodo")
    protected String periodo;
    @XmlElement(name = "LineaNegocio")
    protected String lineaNegocio;
    @XmlElement(name = "TipoTransporte")
    protected String tipoTransporte;

    /**
     * Obtiene el valor de la propiedad orden.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrden() {
        return orden;
    }

    /**
     * Define el valor de la propiedad orden.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrden(String value) {
        this.orden = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCreacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Define el valor de la propiedad fechaCreacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCreacion(XMLGregorianCalendar value) {
        this.fechaCreacion = value;
    }

    /**
     * Obtiene el valor de la propiedad statusTrade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusTrade() {
        return statusTrade;
    }

    /**
     * Define el valor de la propiedad statusTrade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusTrade(String value) {
        this.statusTrade = value;
    }

    /**
     * Obtiene el valor de la propiedad estrategia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstrategia() {
        return estrategia;
    }

    /**
     * Define el valor de la propiedad estrategia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstrategia(String value) {
        this.estrategia = value;
    }

    /**
     * Obtiene el valor de la propiedad portafolio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortafolio() {
        return portafolio;
    }

    /**
     * Define el valor de la propiedad portafolio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortafolio(String value) {
        this.portafolio = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodo(String value) {
        this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaNegocio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaNegocio() {
        return lineaNegocio;
    }

    /**
     * Define el valor de la propiedad lineaNegocio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaNegocio(String value) {
        this.lineaNegocio = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTransporte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTransporte() {
        return tipoTransporte;
    }

    /**
     * Define el valor de la propiedad tipoTransporte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTransporte(String value) {
        this.tipoTransporte = value;
    }

}
