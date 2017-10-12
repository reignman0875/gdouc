package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="GuiaSimple")
public class GuiaSimpleBean extends PadreBean {
	public GuiaSimpleBean() {
		fechaDia=fechaMes=fechaAnio=unidadAdministrativa=areaProcedencia=nombreRat=cargoRat=direccion=telefono=correoElectronico=elaboro
				=cargoElaboro=reviso=cargoReviso=responsableArchivoTramite = new String();

	}

	private String fechaDia;
	private String fechaMes;
	private String fechaAnio;
	private String unidadAdministrativa;
	private String areaProcedencia;
	private String nombreRat;
	private String cargoRat;
	private String direccion;
	private String telefono;
	private String correoElectronico;

	private String elaboro;
	private String cargoElaboro;
	private String reviso;
	private String cargoReviso;
	private String responsableArchivoTramite;
//	private List <GuiaSimpleTablaBean> tablaContenido;
//	
//	@XmlTransient
//	public List<GuiaSimpleTablaBean> getTablaContenido() {
//		return tablaContenido;
//	}
//	public void setTablaContenido(List<GuiaSimpleTablaBean> tablaContenido) {
//		this.tablaContenido = tablaContenido;
//	}
	
	

	

	public String getFechaDia() {
		return fechaDia;
	}

	public void setFechaDia(String fechaDia) {
		this.fechaDia = fechaDia;
	}

	public String getFechaMes() {
		return fechaMes;
	}

	public void setFechaMes(String fechaMes) {
		this.fechaMes = fechaMes;
	}

	public String getFechaAnio() {
		return fechaAnio;
	}

	public void setFechaAnio(String fechaAnio) {
		this.fechaAnio = fechaAnio;
	}

	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	public String getAreaProcedencia() {
		return areaProcedencia;
	}

	public void setAreaProcedencia(String areaProcedencia) {
		this.areaProcedencia = areaProcedencia;
	}

	public String getNombreRat() {
		return nombreRat;
	}

	public void setNombreRat(String nombreRat) {
		this.nombreRat = nombreRat;
	}

	public String getCargoRat() {
		return cargoRat;
	}

	public void setCargoRat(String cargoRat) {
		this.cargoRat = cargoRat;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}



	public String getElaboro() {
		return elaboro;
	}

	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}

	public String getCargoElaboro() {
		return cargoElaboro;
	}

	public void setCargoElaboro(String cargoElaboro) {
		this.cargoElaboro = cargoElaboro;
	}

	
	public String getReviso() {
		return reviso;
	}

	public void setReviso(String reviso) {
		this.reviso = reviso;
	}

	public String getCargoReviso() {
		return cargoReviso;
	}

	public void setCargoReviso(String cargoReviso) {
		this.cargoReviso = cargoReviso;
	}

	
	public String getResponsableArchivoTramite() {
		return responsableArchivoTramite;
	}

	public void setResponsableArchivoTramite(String responsableArchivoTramite) {
		this.responsableArchivoTramite = responsableArchivoTramite;
	}


	
}
