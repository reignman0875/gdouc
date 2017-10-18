package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistOperativoEstado")
public class ChecklistOperativoEstadoBean extends PadreBean {

	public ChecklistOperativoEstadoBean() {
		 fecha=ordenPmi=ordenesRelacionadas=allocation=areaContractual=nominacionesMensuales=nominacionesMensualesC=
		 registrosEntregaDiarios=registrosEntregaDiariosC=certificadoCalidadCantidad=certificadoCalidadCantidadC=
		 actaEntregaRecepcionContratista=actaEntregaRecepcionContratistaC=actaEntregaRecepcionComprador=
		 actaEntregaRecepcionCompradorC=documentosTransporte=documentosTransporteC=documentosAduanales=documentosAduanalesC=
		 documentosSoporteReclamosDemora=documentosSoporteReclamosDemoraC=nominacionesServiciosLogisticos=
		 nominacionesServiciosLogisticosC=otros=otrosC=numeroFolios=integroNombre=operadorNombre= new String();
	}
	
	private String fecha;
	private String ordenPmi;
	private String ordenesRelacionadas;
	private String allocation;
	private String areaContractual;
	private String nominacionesMensuales;
	private String nominacionesMensualesC;
	private String registrosEntregaDiarios;
	private String registrosEntregaDiariosC;
	private String certificadoCalidadCantidad;
	private String certificadoCalidadCantidadC;
	private String actaEntregaRecepcionContratista;
	private String actaEntregaRecepcionContratistaC;
	private String actaEntregaRecepcionComprador;
	private String actaEntregaRecepcionCompradorC;
	private String documentosTransporte;
	private String documentosTransporteC;
	private String documentosAduanales;
	private String documentosAduanalesC;
	private String documentosSoporteReclamosDemora;
	private String documentosSoporteReclamosDemoraC;
	private String nominacionesServiciosLogisticos;
	private String nominacionesServiciosLogisticosC;
	private String otros;
	private String otrosC;
	private String numeroFolios;
	private String integroNombre;
	private String operadorNombre;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getOrdenPmi() {
		return ordenPmi;
	}
	public void setOrdenPmi(String ordenPmi) {
		this.ordenPmi = ordenPmi;
	}
	public String getOrdenesRelacionadas() {
		return ordenesRelacionadas;
	}
	public void setOrdenesRelacionadas(String ordenesRelacionadas) {
		this.ordenesRelacionadas = ordenesRelacionadas;
	}
	public String getAllocation() {
		return allocation;
	}
	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}
	public String getAreaContractual() {
		return areaContractual;
	}
	public void setAreaContractual(String areaContractual) {
		this.areaContractual = areaContractual;
	}
	public String getNominacionesMensuales() {
		return nominacionesMensuales;
	}
	public void setNominacionesMensuales(String nominacionesMensuales) {
		this.nominacionesMensuales = nominacionesMensuales;
	}
	public String getNominacionesMensualesC() {
		return nominacionesMensualesC;
	}
	public void setNominacionesMensualesC(String nominacionesMensualesC) {
		this.nominacionesMensualesC = nominacionesMensualesC;
	}
	public String getRegistrosEntregaDiarios() {
		return registrosEntregaDiarios;
	}
	public void setRegistrosEntregaDiarios(String registrosEntregaDiarios) {
		this.registrosEntregaDiarios = registrosEntregaDiarios;
	}
	public String getRegistrosEntregaDiariosC() {
		return registrosEntregaDiariosC;
	}
	public void setRegistrosEntregaDiariosC(String registrosEntregaDiariosC) {
		this.registrosEntregaDiariosC = registrosEntregaDiariosC;
	}
	public String getCertificadoCalidadCantidad() {
		return certificadoCalidadCantidad;
	}
	public void setCertificadoCalidadCantidad(String certificadoCalidadCantidad) {
		this.certificadoCalidadCantidad = certificadoCalidadCantidad;
	}
	public String getCertificadoCalidadCantidadC() {
		return certificadoCalidadCantidadC;
	}
	public void setCertificadoCalidadCantidadC(String certificadoCalidadCantidadC) {
		this.certificadoCalidadCantidadC = certificadoCalidadCantidadC;
	}
	public String getActaEntregaRecepcionContratista() {
		return actaEntregaRecepcionContratista;
	}
	public void setActaEntregaRecepcionContratista(String actaEntregaRecepcionContratista) {
		this.actaEntregaRecepcionContratista = actaEntregaRecepcionContratista;
	}
	public String getActaEntregaRecepcionContratistaC() {
		return actaEntregaRecepcionContratistaC;
	}
	public void setActaEntregaRecepcionContratistaC(String actaEntregaRecepcionContratistaC) {
		this.actaEntregaRecepcionContratistaC = actaEntregaRecepcionContratistaC;
	}
	public String getActaEntregaRecepcionComprador() {
		return actaEntregaRecepcionComprador;
	}
	public void setActaEntregaRecepcionComprador(String actaEntregaRecepcionComprador) {
		this.actaEntregaRecepcionComprador = actaEntregaRecepcionComprador;
	}
	public String getActaEntregaRecepcionCompradorC() {
		return actaEntregaRecepcionCompradorC;
	}
	public void setActaEntregaRecepcionCompradorC(String actaEntregaRecepcionCompradorC) {
		this.actaEntregaRecepcionCompradorC = actaEntregaRecepcionCompradorC;
	}
	public String getDocumentosTransporte() {
		return documentosTransporte;
	}
	public void setDocumentosTransporte(String documentosTransporte) {
		this.documentosTransporte = documentosTransporte;
	}
	public String getDocumentosTransporteC() {
		return documentosTransporteC;
	}
	public void setDocumentosTransporteC(String documentosTransporteC) {
		this.documentosTransporteC = documentosTransporteC;
	}
	public String getDocumentosAduanales() {
		return documentosAduanales;
	}
	public void setDocumentosAduanales(String documentosAduanales) {
		this.documentosAduanales = documentosAduanales;
	}
	public String getDocumentosAduanalesC() {
		return documentosAduanalesC;
	}
	public void setDocumentosAduanalesC(String documentosAduanalesC) {
		this.documentosAduanalesC = documentosAduanalesC;
	}
	public String getDocumentosSoporteReclamosDemora() {
		return documentosSoporteReclamosDemora;
	}
	public void setDocumentosSoporteReclamosDemora(String documentosSoporteReclamosDemora) {
		this.documentosSoporteReclamosDemora = documentosSoporteReclamosDemora;
	}
	public String getDocumentosSoporteReclamosDemoraC() {
		return documentosSoporteReclamosDemoraC;
	}
	public void setDocumentosSoporteReclamosDemoraC(String documentosSoporteReclamosDemoraC) {
		this.documentosSoporteReclamosDemoraC = documentosSoporteReclamosDemoraC;
	}
	public String getNominacionesServiciosLogisticos() {
		return nominacionesServiciosLogisticos;
	}
	public void setNominacionesServiciosLogisticos(String nominacionesServiciosLogisticos) {
		this.nominacionesServiciosLogisticos = nominacionesServiciosLogisticos;
	}
	public String getNominacionesServiciosLogisticosC() {
		return nominacionesServiciosLogisticosC;
	}
	public void setNominacionesServiciosLogisticosC(String nominacionesServiciosLogisticosC) {
		this.nominacionesServiciosLogisticosC = nominacionesServiciosLogisticosC;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getOtrosC() {
		return otrosC;
	}
	public void setOtrosC(String otrosC) {
		this.otrosC = otrosC;
	}
	public String getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getIntegroNombre() {
		return integroNombre;
	}
	public void setIntegroNombre(String integroNombre) {
		this.integroNombre = integroNombre;
	}
	public String getOperadorNombre() {
		return operadorNombre;
	}
	public void setOperadorNombre(String operadorNombre) {
		this.operadorNombre = operadorNombre;
	}
	
	
}
