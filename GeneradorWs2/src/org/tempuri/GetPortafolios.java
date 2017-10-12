
package org.tempuri;

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
@XmlRootElement(name = "getPortafolios")
public class GetPortafolios {

    @XmlElement(name = "base_datos")
    protected String baseDatos;
    @XmlElement(name = "port_full_name")
    protected String portFullName;

    /**
     * Gets the value of the baseDatos property.
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
     * Sets the value of the baseDatos property.
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
     * Gets the value of the portFullName property.
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
     * Sets the value of the portFullName property.
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
