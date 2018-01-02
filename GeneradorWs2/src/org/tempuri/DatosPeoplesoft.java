
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosPeoplesoft complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosPeoplesoft">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundo_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="full_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "usuario",
    "apellido",
    "segundoApellido",
    "fullName",
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
    protected String usuario;
    protected String apellido;
    @XmlElement(name = "segundo_apellido")
    protected String segundoApellido;
    @XmlElement(name = "full_name")
    protected String fullName;
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
     * Obtiene el valor de la propiedad matricula.
     * 
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Define el valor de la propiedad matricula.
     * 
     */
    public void setMatricula(int value) {
        this.matricula = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
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
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido.
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
     * Define el valor de la propiedad apellido.
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
     * Obtiene el valor de la propiedad segundoApellido.
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
     * Define el valor de la propiedad segundoApellido.
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
     * Obtiene el valor de la propiedad fullName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Define el valor de la propiedad fullName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Obtiene el valor de la propiedad curp.
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
     * Define el valor de la propiedad curp.
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
     * Obtiene el valor de la propiedad antiguedad.
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
     * Define el valor de la propiedad antiguedad.
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
     * Obtiene el valor de la propiedad puesto.
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
     * Define el valor de la propiedad puesto.
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
     * Obtiene el valor de la propiedad area.
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
     * Define el valor de la propiedad area.
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
     * Obtiene el valor de la propiedad areaNombre.
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
     * Define el valor de la propiedad areaNombre.
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
     * Obtiene el valor de la propiedad areaApellido.
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
     * Define el valor de la propiedad areaApellido.
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
     * Obtiene el valor de la propiedad areaSegApellido.
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
     * Define el valor de la propiedad areaSegApellido.
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
     * Obtiene el valor de la propiedad direccion.
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
     * Define el valor de la propiedad direccion.
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
     * Obtiene el valor de la propiedad dirNombre.
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
     * Define el valor de la propiedad dirNombre.
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
     * Obtiene el valor de la propiedad dirApellido.
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
     * Define el valor de la propiedad dirApellido.
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
     * Obtiene el valor de la propiedad dirSegApellido.
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
     * Define el valor de la propiedad dirSegApellido.
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
