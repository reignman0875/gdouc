
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
 *         &lt;element name="getCTTsResult" type="{http://tempuri.org/}ArrayOfTrade" minOccurs="0"/>
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
    "getCTTsResult"
})
@XmlRootElement(name = "getCTTsResponse")
public class GetCTTsResponse {

    protected ArrayOfTrade getCTTsResult;

    /**
     * Obtiene el valor de la propiedad getCTTsResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTrade }
     *     
     */
    public ArrayOfTrade getGetCTTsResult() {
        return getCTTsResult;
    }

    /**
     * Define el valor de la propiedad getCTTsResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTrade }
     *     
     */
    public void setGetCTTsResult(ArrayOfTrade value) {
        this.getCTTsResult = value;
    }

}
