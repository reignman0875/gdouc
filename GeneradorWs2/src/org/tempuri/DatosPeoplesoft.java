
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosPeoplesoft complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosPeoplesoft">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundo_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="curp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="antiguedad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area_seg_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dir_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dir_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dir_seg_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosPeoplesoft", propOrder = {
    "matricula",
    "nombre",
    "apellido",
    "segundoApellido",
    "curp",
    "antiguedad",
    "puesto",
    "area",
    "areaNombre",
    "areaApellido",
    "areaSegApellido",
    "direccion",
    "dirNombre",
    "dirApellido",
    "dirSegApellido"
})
public class DatosPeoplesoft {

    protected int matricula;
    protected String nombre;
    protected String apellido;
    @XmlElement(name = "segundo_apellido")
    protected String segundoApellido;
    protected String curp;
    protected String antiguedad;
    protected String puesto;
    protected String area;
    @XmlElement(name = "area_nombre")
    protected String areaNombre;
    @XmlElement(name = "area_apellido")
    protected String areaApellido;
    @XmlElement(name = "area_seg_apellido")
    protected String areaSegApellido;
    protected String direccion;
    @XmlElement(name = "dir_nombre")
    protected String dirNombre;
    @XmlElement(name = "dir_apellido")
    protected String dirApellido;
    @XmlElement(name = "dir_seg_apellido")
    protected String dirSegApellido;

    /**
     * Gets the value of the matricula property.
     * 
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Sets the value of the matricula property.
     * 
     */
    public void setMatricula(int value) {
        this.matricula = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the segundoApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Sets the value of the segundoApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoApellido(String value) {
        this.segundoApellido = value;
    }

    /**
     * Gets the value of the curp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurp() {
        return curp;
    }

    /**
     * Sets the value of the curp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurp(String value) {
        this.curp = value;
    }

    /**
     * Gets the value of the antiguedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAntiguedad() {
        return antiguedad;
    }

    /**
     * Sets the value of the antiguedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAntiguedad(String value) {
        this.antiguedad = value;
    }

    /**
     * Gets the value of the puesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Sets the value of the puesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuesto(String value) {
        this.puesto = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaNombre() {
        return areaNombre;
    }

    /**
     * Sets the value of the areaNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaNombre(String value) {
        this.areaNombre = value;
    }

    /**
     * Gets the value of the areaApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaApellido() {
        return areaApellido;
    }

    /**
     * Sets the value of the areaApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaApellido(String value) {
        this.areaApellido = value;
    }

    /**
     * Gets the value of the areaSegApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaSegApellido() {
        return areaSegApellido;
    }

    /**
     * Sets the value of the areaSegApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaSegApellido(String value) {
        this.areaSegApellido = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the dirNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirNombre() {
        return dirNombre;
    }

    /**
     * Sets the value of the dirNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirNombre(String value) {
        this.dirNombre = value;
    }

    /**
     * Gets the value of the dirApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirApellido() {
        return dirApellido;
    }

    /**
     * Sets the value of the dirApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirApellido(String value) {
        this.dirApellido = value;
    }

    /**
     * Gets the value of the dirSegApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirSegApellido() {
        return dirSegApellido;
    }

    /**
     * Sets the value of the dirSegApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirSegApellido(String value) {
        this.dirSegApellido = value;
    }

}
