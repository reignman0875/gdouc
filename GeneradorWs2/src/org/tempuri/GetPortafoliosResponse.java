
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
     * Gets the value of the getPortafoliosResult property.
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
     * Sets the value of the getPortafoliosResult property.
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
