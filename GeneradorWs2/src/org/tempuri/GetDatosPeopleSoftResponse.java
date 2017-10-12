
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
 *         &lt;element name="getDatosPeopleSoftResult" type="{http://tempuri.org/}ArrayOfDatosPeoplesoft" minOccurs="0"/>
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
    "getDatosPeopleSoftResult"
})
@XmlRootElement(name = "getDatosPeopleSoftResponse")
public class GetDatosPeopleSoftResponse {

    protected ArrayOfDatosPeoplesoft getDatosPeopleSoftResult;

    /**
     * Gets the value of the getDatosPeopleSoftResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDatosPeoplesoft }
     *     
     */
    public ArrayOfDatosPeoplesoft getGetDatosPeopleSoftResult() {
        return getDatosPeopleSoftResult;
    }

    /**
     * Sets the value of the getDatosPeopleSoftResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDatosPeoplesoft }
     *     
     */
    public void setGetDatosPeopleSoftResult(ArrayOfDatosPeoplesoft value) {
        this.getDatosPeopleSoftResult = value;
    }

}
