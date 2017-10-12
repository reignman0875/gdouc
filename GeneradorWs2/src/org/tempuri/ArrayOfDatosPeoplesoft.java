
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDatosPeoplesoft complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDatosPeoplesoft">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosPeoplesoft" type="{http://tempuri.org/}DatosPeoplesoft" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDatosPeoplesoft", propOrder = {
    "datosPeoplesoft"
})
public class ArrayOfDatosPeoplesoft {

    @XmlElement(name = "DatosPeoplesoft", nillable = true)
    protected List<DatosPeoplesoft> datosPeoplesoft;

    /**
     * Gets the value of the datosPeoplesoft property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datosPeoplesoft property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatosPeoplesoft().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosPeoplesoft }
     * 
     * 
     */
    public List<DatosPeoplesoft> getDatosPeoplesoft() {
        if (datosPeoplesoft == null) {
            datosPeoplesoft = new ArrayList<DatosPeoplesoft>();
        }
        return this.datosPeoplesoft;
    }

}
