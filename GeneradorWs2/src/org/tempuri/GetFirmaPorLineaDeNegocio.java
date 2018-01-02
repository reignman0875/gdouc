
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orden_comercial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linea_negocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ordenComercial",
    "lineaNegocio"
})
@XmlRootElement(name = "getFirmaPorLineaDeNegocio")
public class GetFirmaPorLineaDeNegocio {

    @XmlElement(name = "orden_comercial")
    protected String ordenComercial;
    @XmlElement(name = "linea_negocio")
    protected String lineaNegocio;

    /**
     * Obtiene el valor de la propiedad ordenComercial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdenComercial() {
        return ordenComercial;
    }

    /**
     * Define el valor de la propiedad ordenComercial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdenComercial(String value) {
        this.ordenComercial = value;
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

}
