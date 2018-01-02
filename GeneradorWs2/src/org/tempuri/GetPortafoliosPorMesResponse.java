
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
 *         &lt;element name="getPortafoliosPorMesResult" type="{http://tempuri.org/}ArrayOfPortafolios" minOccurs="0"/>
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
    "getPortafoliosPorMesResult"
})
@XmlRootElement(name = "getPortafoliosPorMesResponse")
public class GetPortafoliosPorMesResponse {

    protected ArrayOfPortafolios getPortafoliosPorMesResult;

    /**
     * Obtiene el valor de la propiedad getPortafoliosPorMesResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPortafolios }
     *     
     */
    public ArrayOfPortafolios getGetPortafoliosPorMesResult() {
        return getPortafoliosPorMesResult;
    }

    /**
     * Define el valor de la propiedad getPortafoliosPorMesResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPortafolios }
     *     
     */
    public void setGetPortafoliosPorMesResult(ArrayOfPortafolios value) {
        this.getPortafoliosPorMesResult = value;
    }

}
