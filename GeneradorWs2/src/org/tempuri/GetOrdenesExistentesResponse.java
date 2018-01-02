
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
 *         &lt;element name="getOrdenesExistentesResult" type="{http://tempuri.org/}ArrayOfReporteSeguimiento" minOccurs="0"/>
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
    "getOrdenesExistentesResult"
})
@XmlRootElement(name = "getOrdenesExistentesResponse")
public class GetOrdenesExistentesResponse {

    protected ArrayOfReporteSeguimiento getOrdenesExistentesResult;

    /**
     * Obtiene el valor de la propiedad getOrdenesExistentesResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReporteSeguimiento }
     *     
     */
    public ArrayOfReporteSeguimiento getGetOrdenesExistentesResult() {
        return getOrdenesExistentesResult;
    }

    /**
     * Define el valor de la propiedad getOrdenesExistentesResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReporteSeguimiento }
     *     
     */
    public void setGetOrdenesExistentesResult(ArrayOfReporteSeguimiento value) {
        this.getOrdenesExistentesResult = value;
    }

}
