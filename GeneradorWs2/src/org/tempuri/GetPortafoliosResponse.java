
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getPortafoliosResult" type="{http://tempuri.org/}ArrayOfPortafolios" minOccurs="0"/>
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
    "getPortafoliosResult"
})
@XmlRootElement(name = "getPortafoliosResponse")
public class GetPortafoliosResponse {

    protected ArrayOfPortafolios getPortafoliosResult;

    /**
     * Obtiene el valor de la propiedad getPortafoliosResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPortafolios }
     *     
     */
    public ArrayOfPortafolios getGetPortafoliosResult() {
        return getPortafoliosResult;
    }

    /**
     * Define el valor de la propiedad getPortafoliosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPortafolios }
     *     
     */
    public void setGetPortafoliosResult(ArrayOfPortafolios value) {
        this.getPortafoliosResult = value;
    }

}
