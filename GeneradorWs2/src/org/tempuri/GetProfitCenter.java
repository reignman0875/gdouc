
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
 *         &lt;element name="base_datos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="port_full_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "baseDatos",
    "portFullName"
})
@XmlRootElement(name = "getProfitCenter")
public class GetProfitCenter {

    @XmlElement(name = "base_datos")
    protected String baseDatos;
    @XmlElement(name = "port_full_name")
    protected String portFullName;

    /**
     * Obtiene el valor de la propiedad baseDatos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseDatos() {
        return baseDatos;
    }

    /**
     * Define el valor de la propiedad baseDatos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseDatos(String value) {
        this.baseDatos = value;
    }

    /**
     * Obtiene el valor de la propiedad portFullName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortFullName() {
        return portFullName;
    }

    /**
     * Define el valor de la propiedad portFullName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortFullName(String value) {
        this.portFullName = value;
    }

}
