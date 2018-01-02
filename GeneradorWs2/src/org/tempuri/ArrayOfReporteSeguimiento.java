
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfReporteSeguimiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfReporteSeguimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReporteSeguimiento" type="{http://tempuri.org/}ReporteSeguimiento" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfReporteSeguimiento", propOrder = {
    "reporteSeguimiento"
})
public class ArrayOfReporteSeguimiento {

    @XmlElement(name = "ReporteSeguimiento", nillable = true)
    protected List<ReporteSeguimiento> reporteSeguimiento;

    /**
     * Gets the value of the reporteSeguimiento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reporteSeguimiento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReporteSeguimiento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReporteSeguimiento }
     * 
     * 
     */
    public List<ReporteSeguimiento> getReporteSeguimiento() {
        if (reporteSeguimiento == null) {
            reporteSeguimiento = new ArrayList<ReporteSeguimiento>();
        }
        return this.reporteSeguimiento;
    }

}
