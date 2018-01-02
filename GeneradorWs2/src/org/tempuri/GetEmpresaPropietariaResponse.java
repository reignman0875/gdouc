
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
 *         &lt;element name="getEmpresaPropietariaResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
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
    "getEmpresaPropietariaResult"
})
@XmlRootElement(name = "getEmpresaPropietariaResponse")
public class GetEmpresaPropietariaResponse {

    protected ArrayOfString getEmpresaPropietariaResult;

    /**
     * Obtiene el valor de la propiedad getEmpresaPropietariaResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetEmpresaPropietariaResult() {
        return getEmpresaPropietariaResult;
    }

    /**
     * Define el valor de la propiedad getEmpresaPropietariaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetEmpresaPropietariaResult(ArrayOfString value) {
        this.getEmpresaPropietariaResult = value;
    }

}
