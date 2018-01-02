
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
 *         &lt;element name="getAreasContractualesCimComEdoResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
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
    "getAreasContractualesCimComEdoResult"
})
@XmlRootElement(name = "getAreasContractualesCimComEdoResponse")
public class GetAreasContractualesCimComEdoResponse {

    protected ArrayOfString getAreasContractualesCimComEdoResult;

    /**
     * Obtiene el valor de la propiedad getAreasContractualesCimComEdoResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetAreasContractualesCimComEdoResult() {
        return getAreasContractualesCimComEdoResult;
    }

    /**
     * Define el valor de la propiedad getAreasContractualesCimComEdoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetAreasContractualesCimComEdoResult(ArrayOfString value) {
        this.getAreasContractualesCimComEdoResult = value;
    }

}
