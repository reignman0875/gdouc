package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ValePrestamo")
public class ValePrestamoBean extends PadreBean {

	public ValePrestamoBean() {
		folio=fechaPrestamoDia=fechaPrestamoMes=fechaPrestamoAnio=tramiteInterno=tramiteResguardo=concentracion=consulta=auditoria
				=solicitudInformacion=unidadAdministrativa=nombreSolicitante=cargoSolicitante=areaProcedencia=direccion=telefonoExtension
				=solicitante=nombreAutoriza=cargoAutoriza=nombreQuienEntrega=cargoQuienEntrega=new String();
	}
	private String folio;
	private String fechaPrestamoDia;
	private String fechaPrestamoMes;
	private String fechaPrestamoAnio;	
	private String tramiteInterno;
	private String tramiteResguardo;
	private String concentracion;
	private String consulta;
	private String auditoria;
	private String solicitudInformacion;
	private String unidadAdministrativa;
	private String nombreSolicitante;
	private String cargoSolicitante;
	private String areaProcedencia;
	private String direccion;
	private String telefonoExtension;
	private String solicitante;
	private String nombreAutoriza;
	private String cargoAutoriza;
	private String nombreQuienEntrega;
	private String cargoQuienEntrega;
//	private List <ValePrestamoTablaBean> tablaContenido;
//	
//	@XmlTransient
//	public List<ValePrestamoTablaBean> getTablaContenido() {
//		return tablaContenido;
//	}
//	public void setTablaContenido(List<ValePrestamoTablaBean> tablaContenido) {
//		this.tablaContenido = tablaContenido;
//	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}



	public String getFechaPrestamoDia() {
		return fechaPrestamoDia;
	}

	public void setFechaPrestamoDia(String fechaPrestamoDia) {
		this.fechaPrestamoDia = fechaPrestamoDia;
	}

	public String getFechaPrestamoMes() {
		return fechaPrestamoMes;
	}

	public void setFechaPrestamoMes(String fechaPrestamoMes) {
		this.fechaPrestamoMes = fechaPrestamoMes;
	}

	public String getFechaPrestamoAnio() {
		return fechaPrestamoAnio;
	}

	public void setFechaPrestamoAnio(String fechaPrestamoAnio) {
		this.fechaPrestamoAnio = fechaPrestamoAnio;
	}

	
	public String getTramiteInterno() {
		return tramiteInterno;
	}

	public void setTramiteInterno(String tramiteInterno) {
		this.tramiteInterno = tramiteInterno;
	}

	public String getTramiteResguardo() {
		return tramiteResguardo;
	}

	public void setTramiteResguardo(String tramiteResguardo) {
		this.tramiteResguardo = tramiteResguardo;
	}

	public String getConcentracion() {
		return concentracion;
	}

	public void setConcentracion(String concentracion) {
		this.concentracion = concentracion;
	}

	
	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
	}

	public String getSolicitudInformacion() {
		return solicitudInformacion;
	}

	public void setSolicitudInformacion(String solicitudInformacion) {
		this.solicitudInformacion = solicitudInformacion;
	}

	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getCargoSolicitante() {
		return cargoSolicitante;
	}

	public void setCargoSolicitante(String cargoSolicitante) {
		this.cargoSolicitante = cargoSolicitante;
	}

	public String getAreaProcedencia() {
		return areaProcedencia;
	}

	public void setAreaProcedencia(String areaProcedencia) {
		this.areaProcedencia = areaProcedencia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoExtension() {
		return telefonoExtension;
	}

	public void setTelefonoExtension(String telefonoExtension) {
		this.telefonoExtension = telefonoExtension;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
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

	public String getNombreQuienEntrega() {
		return nombreQuienEntrega;
	}

	public void setNombreQuienEntrega(String nombreQuienEntrega) {
		this.nombreQuienEntrega = nombreQuienEntrega;
	}

	public String getCargoQuienEntrega() {
		return cargoQuienEntrega;
	}

	public void setCargoQuienEntrega(String cargoQuienEntrega) {
		this.cargoQuienEntrega = cargoQuienEntrega;
	}

}
