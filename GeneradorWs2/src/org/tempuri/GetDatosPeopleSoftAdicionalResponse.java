
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
 *         &lt;element name="getDatosPeopleSoftAdicionalResult" type="{http://tempuri.org/}ArrayOfDatosPeoplesoft" minOccurs="0"/>
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
    "getDatosPeopleSoftAdicionalResult"
})
@XmlRootElement(name = "getDatosPeopleSoftAdicionalResponse")
public class GetDatosPeopleSoftAdicionalResponse {

    protected ArrayOfDatosPeoplesoft getDatosPeopleSoftAdicionalResult;

    /**
     * Obtiene el valor de la propiedad getDatosPeopleSoftAdicionalResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDatosPeoplesoft }
     *     
     */
    public ArrayOfDatosPeoplesoft getGetDatosPeopleSoftAdicionalResult() {
        return getDatosPeopleSoftAdicionalResult;
    }

    /**
     * Define el valor de la propiedad getDatosPeopleSoftAdicionalResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDatosPeoplesoft }
     *     
     */
    public void setGetDatosPeopleSoftAdicionalResult(ArrayOfDatosPeoplesoft value) {
        this.getDatosPeopleSoftAdicionalResult = value;
    }

}
