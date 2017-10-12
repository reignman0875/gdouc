
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getFirmaPorLineaDeNegocioResult" type="{http://tempuri.org/}Firmas" minOccurs="0"/>
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
    "getFirmaPorLineaDeNegocioResult"
})
@XmlRootElement(name = "getFirmaPorLineaDeNegocioResponse")
public class GetFirmaPorLineaDeNegocioResponse {

    protected Firmas getFirmaPorLineaDeNegocioResult;

    /**
     * Gets the value of the getFirmaPorLineaDeNegocioResult property.
     * 
     * @return
     *     possible object is
     *     {@link Firmas }
     *     
     */
    public Firmas getGetFirmaPorLineaDeNegocioResult() {
        return getFirmaPorLineaDeNegocioResult;
    }

    /**
     * Sets the value of the getFirmaPorLineaDeNegocioResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Firmas }
     *     
     */
    public void setGetFirmaPorLineaDeNegocioResult(Firmas value) {
        this.getFirmaPorLineaDeNegocioResult = value;
    }

}
