package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistReclamos")
public class ChecklistReclamosBean extends PadreBean {

	public ChecklistReclamosBean() {
		fecha=ordenPmi=ordenPemex=ordenFletamento=notificacionReclamo=notificacionReclamoC=comunicaciones=comunicacionesC=acuerdos=acuerdosC
				=correoAjusteVolumen=correoAjusteVolumenC=solicitudCobroTesoreria=solicitudCobroTesoreriaC=solicitudPagoTesoreria
				=solicitudPagoTesoreriaC=reporteInspeccionCarga=reporteInspeccionCargaC=reporteInspeccionDescarga=reporteInspeccionDescargaC
				=hojaAnalisisViajeCargaDescarga=hojaAnalisisViajeCargaDescargaC=hojaAnalisisVolumenBarco=hojaAnalisisVolumenBarcoC
				=hojaAnalisisVef=hojaAnalisisVefC=hojaAnalisisTemperaturaCargamento=hojaAnalisisTemperaturaCargamentoC
				=reporteReanaisisLaboratorio=reporteReanaisisLaboratorioC=notaInformativa=notaInformativaC=integroNombre=numeroHojas
				=numeroPaginas= new String();
	}
	private String fecha;
	private String ordenPmi;
	private String ordenPemex;
	private String ordenFletamento;
	private String notificacionReclamo;
	private String notificacionReclamoC;
	private String comunicaciones;
	private String comunicacionesC;
	private String acuerdos;
	private String acuerdosC;
	private String correoAjusteVolumen;
	private String correoAjusteVolumenC;
	private String solicitudCobroTesoreria;
	private String solicitudCobroTesoreriaC;
	private String solicitudPagoTesoreria;
	private String solicitudPagoTesoreriaC;
	private String reporteInspeccionCarga;
	private String reporteInspeccionCargaC;
	private String reporteInspeccionDescarga;
	private String reporteInspeccionDescargaC;
	private String hojaAnalisisViajeCargaDescarga;
	private String hojaAnalisisViajeCargaDescargaC;
	private String hojaAnalisisVolumenBarco;
	private String hojaAnalisisVolumenBarcoC;
	private String hojaAnalisisVef;
	private String hojaAnalisisVefC;
	private String hojaAnalisisTemperaturaCargamento;
	private String hojaAnalisisTemperaturaCargamentoC;
	private String reporteReanaisisLaboratorio;
	private String reporteReanaisisLaboratorioC;
	private String notaInformativa;
	private String notaInformativaC;
	private String integroNombre;
	private String numeroHojas;
	private String numeroPaginas;
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
	public String getOrdenPemex() {
		return ordenPemex;
	}
	public void setOrdenPemex(String ordenPemex) {
		this.ordenPemex = ordenPemex;
	}
	public String getOrdenFletamento() {
		return ordenFletamento;
	}
	public void setOrdenFletamento(String ordenFletamento) {
		this.ordenFletamento = ordenFletamento;
	}
	public String getNotificacionReclamo() {
		return notificacionReclamo;
	}
	public void setNotificacionReclamo(String notificacionReclamo) {
		this.notificacionReclamo = notificacionReclamo;
	}
	public String getNotificacionReclamoC() {
		return notificacionReclamoC;
	}
	public void setNotificacionReclamoC(String notificacionReclamoC) {
		this.notificacionReclamoC = notificacionReclamoC;
	}
	public String getComunicaciones() {
		return comunicaciones;
	}
	public void setComunicaciones(String comunicaciones) {
		this.comunicaciones = comunicaciones;
	}
	public String getComunicacionesC() {
		return comunicacionesC;
	}
	public void setComunicacionesC(String comunicacionesC) {
		this.comunicacionesC = comunicacionesC;
	}
	public String getAcuerdos() {
		return acuerdos;
	}
	public void setAcuerdos(String acuerdos) {
		this.acuerdos = acuerdos;
	}
	public String getAcuerdosC() {
		return acuerdosC;
	}
	public void setAcuerdosC(String acuerdosC) {
		this.acuerdosC = acuerdosC;
	}
	public String getCorreoAjusteVolumen() {
		return correoAjusteVolumen;
	}
	public void setCorreoAjusteVolumen(String correoAjusteVolumen) {
		this.correoAjusteVolumen = correoAjusteVolumen;
	}
	public String getCorreoAjusteVolumenC() {
		return correoAjusteVolumenC;
	}
	public void setCorreoAjusteVolumenC(String correoAjusteVolumenC) {
		this.correoAjusteVolumenC = correoAjusteVolumenC;
	}
	public String getSolicitudCobroTesoreria() {
		return solicitudCobroTesoreria;
	}
	public void setSolicitudCobroTesoreria(String solicitudCobroTesoreria) {
		this.solicitudCobroTesoreria = solicitudCobroTesoreria;
	}
	public String getSolicitudCobroTesoreriaC() {
		return solicitudCobroTesoreriaC;
	}
	public void setSolicitudCobroTesoreriaC(String solicitudCobroTesoreriaC) {
		this.solicitudCobroTesoreriaC = solicitudCobroTesoreriaC;
	}
	public String getSolicitudPagoTesoreria() {
		return solicitudPagoTesoreria;
	}
	public void setSolicitudPagoTesoreria(String solicitudPagoTesoreria) {
		this.solicitudPagoTesoreria = solicitudPagoTesoreria;
	}
	public String getSolicitudPagoTesoreriaC() {
		return solicitudPagoTesoreriaC;
	}
	public void setSolicitudPagoTesoreriaC(String solicitudPagoTesoreriaC) {
		this.solicitudPagoTesoreriaC = solicitudPagoTesoreriaC;
	}
	public String getReporteInspeccionCarga() {
		return reporteInspeccionCarga;
	}
	public void setReporteInspeccionCarga(String reporteInspeccionCarga) {
		this.reporteInspeccionCarga = reporteInspeccionCarga;
	}
	public String getReporteInspeccionCargaC() {
		return reporteInspeccionCargaC;
	}
	public void setReporteInspeccionCargaC(String reporteInspeccionCargaC) {
		this.reporteInspeccionCargaC = reporteInspeccionCargaC;
	}
	public String getReporteInspeccionDescarga() {
		return reporteInspeccionDescarga;
	}
	public void setReporteInspeccionDescarga(String reporteInspeccionDescarga) {
		this.reporteInspeccionDescarga = reporteInspeccionDescarga;
	}
	public String getReporteInspeccionDescargaC() {
		return reporteInspeccionDescargaC;
	}
	public void setReporteInspeccionDescargaC(String reporteInspeccionDescargaC) {
		this.reporteInspeccionDescargaC = reporteInspeccionDescargaC;
	}
	public String getHojaAnalisisViajeCargaDescarga() {
		return hojaAnalisisViajeCargaDescarga;
	}
	public void setHojaAnalisisViajeCargaDescarga(String hojaAnalisisViajeCargaDescarga) {
		this.hojaAnalisisViajeCargaDescarga = hojaAnalisisViajeCargaDescarga;
	}
	public String getHojaAnalisisViajeCargaDescargaC() {
		return hojaAnalisisViajeCargaDescargaC;
	}
	public void setHojaAnalisisViajeCargaDescargaC(String hojaAnalisisViajeCargaDescargaC) {
		this.hojaAnalisisViajeCargaDescargaC = hojaAnalisisViajeCargaDescargaC;
	}
	public String getHojaAnalisisVolumenBarco() {
		return hojaAnalisisVolumenBarco;
	}
	public void setHojaAnalisisVolumenBarco(String hojaAnalisisVolumenBarco) {
		this.hojaAnalisisVolumenBarco = hojaAnalisisVolumenBarco;
	}
	public String getHojaAnalisisVolumenBarcoC() {
		return hojaAnalisisVolumenBarcoC;
	}
	public void setHojaAnalisisVolumenBarcoC(String hojaAnalisisVolumenBarcoC) {
		this.hojaAnalisisVolumenBarcoC = hojaAnalisisVolumenBarcoC;
	}
	public String getHojaAnalisisVef() {
		return hojaAnalisisVef;
	}
	public void setHojaAnalisisVef(String hojaAnalisisVef) {
		this.hojaAnalisisVef = hojaAnalisisVef;
	}
	public String getHojaAnalisisVefC() {
		return hojaAnalisisVefC;
	}
	public void setHojaAnalisisVefC(String hojaAnalisisVefC) {
		this.hojaAnalisisVefC = hojaAnalisisVefC;
	}
	public String getHojaAnalisisTemperaturaCargamento() {
		return hojaAnalisisTemperaturaCargamento;
	}
	public void setHojaAnalisisTemperaturaCargamento(String hojaAnalisisTemperaturaCargamento) {
		this.hojaAnalisisTemperaturaCargamento = hojaAnalisisTemperaturaCargamento;
	}
	public String getHojaAnalisisTemperaturaCargamentoC() {
		return hojaAnalisisTemperaturaCargamentoC;
	}
	public void setHojaAnalisisTemperaturaCargamentoC(String hojaAnalisisTemperaturaCargamentoC) {
		this.hojaAnalisisTemperaturaCargamentoC = hojaAnalisisTemperaturaCargamentoC;
	}
	public String getReporteReanaisisLaboratorio() {
		return reporteReanaisisLaboratorio;
	}
	public void setReporteReanaisisLaboratorio(String reporteReanaisisLaboratorio) {
		this.reporteReanaisisLaboratorio = reporteReanaisisLaboratorio;
	}
	public String getReporteReanaisisLaboratorioC() {
		return reporteReanaisisLaboratorioC;
	}
	public void setReporteReanaisisLaboratorioC(String reporteReanaisisLaboratorioC) {
		this.reporteReanaisisLaboratorioC = reporteReanaisisLaboratorioC;
	}
	public String getNotaInformativa() {
		return notaInformativa;
	}
	public void setNotaInformativa(String notaInformativa) {
		this.notaInformativa = notaInformativa;
	}
	public String getNotaInformativaC() {
		return notaInformativaC;
	}
	public void setNotaInformativaC(String notaInformativaC) {
		this.notaInformativaC = notaInformativaC;
	}
	public String getIntegroNombre() {
		return integroNombre;
	}
	public void setIntegroNombre(String integroNombre) {
		this.integroNombre = integroNombre;
	}
	public String getNumeroHojas() {
		return numeroHojas;
	}
	public void setNumeroHojas(String numeroHojas) {
		this.numeroHojas = numeroHojas;
	}
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	
}
