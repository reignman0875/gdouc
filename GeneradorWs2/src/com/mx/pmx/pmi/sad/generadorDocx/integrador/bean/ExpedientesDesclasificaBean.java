package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ExpedientesDesclasifica")
public class ExpedientesDesclasificaBean extends PadreBean {

	public ExpedientesDesclasificaBean() {
		unidadAdministrativa=areaProcedencia=hojaNo=de=fechaElaboracionDia=fechaElaboracionMes=fechaElaboracionAnio=nombreQuienElaboro
				=cargoElaboro=nombreAutoriza=cargoAutoriza=nombreResponsable=cargoResponsable = new String();
		
				
	}
	
	private String unidadAdministrativa;
	private String areaProcedencia;
	private String hojaNo;
	private String de;
	private String fechaElaboracionDia;
	private String fechaElaboracionMes;
	private String fechaElaboracionAnio;
	private String nombreQuienElaboro;
	private String cargoElaboro;
	private String nombreAutoriza;
	private String cargoAutoriza;
	private String nombreResponsable;
	private String cargoResponsable;
//	private List <ExpedientesDesclasificaTablaBean> tablaContenido;
//	
//	@XmlTransient
//	public List<ExpedientesDesclasificaTablaBean> getTablaContenido() {
//		return tablaContenido;
//	}
//	public void setTablaContenido(List<ExpedientesDesclasificaTablaBean> tablaContenido) {
//		this.tablaContenido = tablaContenido;
//	}
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
	public String getHojaNo() {
		return hojaNo;
	}
	public void setHojaNo(String hojaNo) {
		this.hojaNo = hojaNo;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getFechaElaboracionDia() {
		return fechaElaboracionDia;
	}
	public void setFechaElaboracionDia(String fechaElaboracionDia) {
		this.fechaElaboracionDia = fechaElaboracionDia;
	}
	public String getFechaElaboracionMes() {
		return fechaElaboracionMes;
	}
	public void setFechaElaboracionMes(String fechaElaboracionMes) {
		this.fechaElaboracionMes = fechaElaboracionMes;
	}
	public String getFechaElaboracionAnio() {
		return fechaElaboracionAnio;
	}
	public void setFechaElaboracionAnio(String fechaElaboracionAnio) {
		this.fechaElaboracionAnio = fechaElaboracionAnio;
	}
	public String getNombreQuienElaboro() {
		return nombreQuienElaboro;
	}
	public void setNombreQuienElaboro(String nombreQuienElaboro) {
		this.nombreQuienElaboro = nombreQuienElaboro;
	}
	public String getCargoElaboro() {
		return cargoElaboro;
	}
	public void setCargoElaboro(String cargoElaboro) {
		this.cargoElaboro = cargoElaboro;
	}
	public String getNombreAutoriza() {
		return nombreAutoriza;
	}
	public void setNombreAutoriza(String nombreAutoriza) {
		this.nombreAutoriza = nombreAutoriza;
	}
	public String getCargoAutoriza() {
		return cargoAutoriza;
	}
	public void setCargoAutoriza(String cargoAutoriza) {
		this.cargoAutoriza = cargoAutoriza;
	}
	public String getNombreResponsable() {
		return nombreResponsable;
	}
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
	public String getCargoResponsable() {
		return cargoResponsable;
	}
	public void setCargoResponsable(String cargoResponsable) {
		this.cargoResponsable = cargoResponsable;
	}

	
}
