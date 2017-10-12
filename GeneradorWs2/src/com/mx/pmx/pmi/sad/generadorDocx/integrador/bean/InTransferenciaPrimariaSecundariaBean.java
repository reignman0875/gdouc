package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="InTransferenciaPrimariaSecundaria")
public class InTransferenciaPrimariaSecundariaBean extends PadreBean {
	
	public InTransferenciaPrimariaSecundariaBean() {
		unidadAdministrativa=areaProcedenciaGeneradora=pagNo=de=fechaRecepcionDia=fechaRecepcionMes=fechaRecepcionAnio=tipoDocumentacion
				=tramiteResguardo=concentracion=baja=historico=transferencia=numero=anio=numeroTotalCajas=pesoTotalKilogramos=elaboro
				=cargoElaboro=responsableArchivoTramite=responsableArchivoConcentracion=new String();
	}

	private String unidadAdministrativa;
	private String areaProcedenciaGeneradora;
	private String pagNo;
	private String de;
	private String fechaRecepcionDia;
	private String fechaRecepcionMes;
	private String fechaRecepcionAnio;
	private String tipoDocumentacion;
	private String tramiteResguardo;
	private String concentracion;
	private String baja;
	private String historico;
	private String transferencia;
	private String numero;
	private String anio;
	private String numeroTotalCajas;
	private String pesoTotalKilogramos;
	private String elaboro;
	private String cargoElaboro;
	private String responsableArchivoTramite;
	private String responsableArchivoConcentracion;
//	private List <InTransferenciaPrimariaSecundariaTablaBean> tablaContenido;
//	
//	@XmlTransient
//	public List<InTransferenciaPrimariaSecundariaTablaBean> getTablaContenido() {
//		return tablaContenido;
//	}
//	public void setTablaContenido(List<InTransferenciaPrimariaSecundariaTablaBean> tablaContenido) {
//		this.tablaContenido = tablaContenido;
//	}

	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}
	public String getAreaProcedenciaGeneradora() {
		return areaProcedenciaGeneradora;
	}
	public void setAreaProcedenciaGeneradora(String areaProcedenciaGeneradora) {
		this.areaProcedenciaGeneradora = areaProcedenciaGeneradora;
	}
	public String getPagNo() {
		return pagNo;
	}
	public void setPagNo(String pagNo) {
		this.pagNo = pagNo;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getFechaRecepcionDia() {
		return fechaRecepcionDia;
	}
	public void setFechaRecepcionDia(String fechaRecepcionDia) {
		this.fechaRecepcionDia = fechaRecepcionDia;
	}
	public String getFechaRecepcionMes() {
		return fechaRecepcionMes;
	}
	public void setFechaRecepcionMes(String fechaRecepcionMes) {
		this.fechaRecepcionMes = fechaRecepcionMes;
	}
	public String getFechaRecepcionAnio() {
		return fechaRecepcionAnio;
	}
	public void setFechaRecepcionAnio(String fechaRecepcionAnio) {
		this.fechaRecepcionAnio = fechaRecepcionAnio;
	}
	public String getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	public void setTipoDocumentacion(String tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
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
	public String getBaja() {
		return baja;
	}
	public void setBaja(String baja) {
		this.baja = baja;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public String getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(String transferencia) {
		this.transferencia = transferencia;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getNumeroTotalCajas() {
		return numeroTotalCajas;
	}
	public void setNumeroTotalCajas(String numeroTotalCajas) {
		this.numeroTotalCajas = numeroTotalCajas;
	}
	public String getPesoTotalKilogramos() {
		return pesoTotalKilogramos;
	}
	public void setPesoTotalKilogramos(String pesoTotalKilogramos) {
		this.pesoTotalKilogramos = pesoTotalKilogramos;
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
	public String getResponsableArchivoTramite() {
		return responsableArchivoTramite;
	}
	public void setResponsableArchivoTramite(String responsableArchivoTramite) {
		this.responsableArchivoTramite = responsableArchivoTramite;
	}
	public String getResponsableArchivoConcentracion() {
		return responsableArchivoConcentracion;
	}
	public void setResponsableArchivoConcentracion(String responsableArchivoConcentracion) {
		this.responsableArchivoConcentracion = responsableArchivoConcentracion;
	}



}
