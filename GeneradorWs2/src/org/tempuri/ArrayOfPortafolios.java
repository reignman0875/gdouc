
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPortafolios complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPortafolios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Portafolios" type="{http://tempuri.org/}Portafolios" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPortafolios", propOrder = {
    "portafolios"
})
public class ArrayOfPortafolios {

    @XmlElement(name = "Portafolios", nillable = true)
    protected List<Portafolios> portafolios;

    /**
     * Gets the value of the portafolios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portafolios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortafolios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Portafolios }
     * 
     * 
     */
    public List<Portafolios> getPortafolios() {
        if (portafolios == null) {
            portafolios = new ArrayList<Portafolios>();
        }
        return this.portafolios;
    }

}
