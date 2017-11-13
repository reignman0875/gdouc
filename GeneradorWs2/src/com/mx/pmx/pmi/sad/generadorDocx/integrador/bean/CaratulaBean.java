package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Caratula")
public class CaratulaBean extends PadreBean {
	public CaratulaBean() {
		destinoFinalHistorico = destinoFinalBaja =
		unidadAdminsitrativa = clasificacionReservada = clasificacionConfidencial = clasificacionFechaClasificacion = reservaDel = reservaAl
				= reservaFundamentoLegal = reservaOtros = reservaAmplicacionDel = reservaAmplicacionAl = reservaNombreTitularUnidad 
				= desclasificacionFecha = desclasificacionNombreTitularUnidad = clasificacionArchivisticaNombreResponsableIntegracion
				= clasificacionArchivisticaNombreResponsableRevision
				= clasificacionArchivisticaSeccion = clasificacionArchivisticaSerie = clasificacionArchivisticaSubserie 
				= clasificacionArchivisticaAsunto = clasificacionArchivisticaFechaAperturaExpediente 
				= clasificacionArchivisticaFechaCierreExpediente = valorDocumentalAdministrativo = valorDocumentalLegal 
				= valorDocumentalFinanciero = plazoConservacionArchivoTramite = plazoConservacionArchivoConcentracion
				= periodoAdicionalSolicitudAcceso = periodoAdicionalDesclasificacion = periodoAdicionalNoExpediente 
				= periodoAdicionalNoHojas = periodoAdicionalNoPaginas = new String();
	}

	private String destinoFinalHistorico;
	private String destinoFinalBaja;
	private String unidadAdminsitrativa;
	private String clasificacionReservada;
	private String clasificacionConfidencial;
	private String clasificacionFechaClasificacion;
	private String reservaDel;
	private String reservaAl;
	private String reservaFundamentoLegal;
	private String reservaOtros;
	private String reservaAmplicacionDel;
	private String reservaAmplicacionAl;
	private String reservaNombreTitularUnidad;
	private String desclasificacionFecha;
	private String desclasificacionNombreTitularUnidad;
	private String clasificacionArchivisticaNombreResponsableIntegracion;
	private String clasificacionArchivisticaNombreResponsableRevision;
	private String clasificacionArchivisticaSeccion;
	private String clasificacionArchivisticaSerie;
	private String clasificacionArchivisticaSubserie;
	private String clasificacionArchivisticaAsunto;
	private String clasificacionArchivisticaFechaAperturaExpediente;
	private String clasificacionArchivisticaFechaCierreExpediente;
	private String valorDocumentalAdministrativo;
	private String valorDocumentalLegal;
	private String valorDocumentalFinanciero;
	private String plazoConservacionArchivoTramite;
	private String plazoConservacionArchivoConcentracion;
	private String periodoAdicionalSolicitudAcceso;
	private String periodoAdicionalDesclasificacion;
	private String periodoAdicionalNoExpediente;
	private String periodoAdicionalNoHojas;
	private String periodoAdicionalNoPaginas;
	
	
	
	public String getDestinoFinalHistorico() {
		return destinoFinalHistorico;
	}
	public void setDestinoFinalHistorico(String destinoFinalHistorico) {
		this.destinoFinalHistorico = destinoFinalHistorico;
	}
	public String getDestinoFinalBaja() {
		return destinoFinalBaja;
	}
	public void setDestinoFinalBaja(String destinoFinalBaja) {
		this.destinoFinalBaja = destinoFinalBaja;
	}
	public String getDesclasificacionNombreTitularUnidad() {
		return desclasificacionNombreTitularUnidad;
	}
	public void setDesclasificacionNombreTitularUnidad(String desclasificacionNombreTitularUnidad) {
		this.desclasificacionNombreTitularUnidad = desclasificacionNombreTitularUnidad;
	}
	public String getUnidadAdminsitrativa() {
		return unidadAdminsitrativa;
	}
	public void setUnidadAdminsitrativa(String unidadAdminsitrativa) {
		this.unidadAdminsitrativa = unidadAdminsitrativa;
	}
	public String getClasificacionReservada() {
		return clasificacionReservada;
	}
	public void setClasificacionReservada(String clasificacionReservada) {
		this.clasificacionReservada = clasificacionReservada;
	}
	public String getClasificacionArchivisticaNombreResponsableRevision() {
		return clasificacionArchivisticaNombreResponsableRevision;
	}
	public void setClasificacionArchivisticaNombreResponsableRevision(
			String clasificacionArchivisticaNombreResponsableRevision) {
		this.clasificacionArchivisticaNombreResponsableRevision = clasificacionArchivisticaNombreResponsableRevision;
	}
	public String getClasificacionConfidencial() {
		return clasificacionConfidencial;
	}
	public void setClasificacionConfidencial(String clasificacionConfidencial) {
		this.clasificacionConfidencial = clasificacionConfidencial;
	}
	public String getClasificacionFechaClasificacion() {
		return clasificacionFechaClasificacion;
	}
	public void setClasificacionFechaClasificacion(String clasificacionFechaClasificacion) {
		this.clasificacionFechaClasificacion = clasificacionFechaClasificacion;
	}
	public String getReservaDel() {
		return reservaDel;
	}
	public void setReservaDel(String reservaDel) {
		this.reservaDel = reservaDel;
	}
	public String getReservaAl() {
		return reservaAl;
	}
	public void setReservaAl(String reservaAl) {
		this.reservaAl = reservaAl;
	}
	public String getReservaFundamentoLegal() {
		return reservaFundamentoLegal;
	}
	public void setReservaFundamentoLegal(String reservaFundamentoLegal) {
		this.reservaFundamentoLegal = reservaFundamentoLegal;
	}
	public String getReservaOtros() {
		return reservaOtros;
	}
	public void setReservaOtros(String reservaOtros) {
		this.reservaOtros = reservaOtros;
	}
	
	public String getReservaAmplicacionDel() {
		return reservaAmplicacionDel;
	}
	public void setReservaAmplicacionDel(String reservaAmplicacionDel) {
		this.reservaAmplicacionDel = reservaAmplicacionDel;
	}
	public String getReservaAmplicacionAl() {
		return reservaAmplicacionAl;
	}
	public void setReservaAmplicacionAl(String reservaAmplicacionAl) {
		this.reservaAmplicacionAl = reservaAmplicacionAl;
	}
	public String getReservaNombreTitularUnidad() {
		return reservaNombreTitularUnidad;
	}
	public void setReservaNombreTitularUnidad(String reservaNombreTitularUnidad) {
		this.reservaNombreTitularUnidad = reservaNombreTitularUnidad;
	}
	public String getDesclasificacionFecha() {
		return desclasificacionFecha;
	}
	public void setDesclasificacionFecha(String desclasificacionFecha) {
		this.desclasificacionFecha = desclasificacionFecha;
	}
	public String getClasificacionArchivisticaNombreResponsableIntegracion() {
		return clasificacionArchivisticaNombreResponsableIntegracion;
	}
	public void setClasificacionArchivisticaNombreResponsableIntegracion(
			String clasificacionArchivisticaNombreResponsableIntegracion) {
		this.clasificacionArchivisticaNombreResponsableIntegracion = clasificacionArchivisticaNombreResponsableIntegracion;
	}
	public String getClasificacionArchivisticaSeccion() {
		return clasificacionArchivisticaSeccion;
	}
	public void setClasificacionArchivisticaSeccion(String clasificacionArchivisticaSeccion) {
		this.clasificacionArchivisticaSeccion = clasificacionArchivisticaSeccion;
	}
	public String getClasificacionArchivisticaSerie() {
		return clasificacionArchivisticaSerie;
	}
	public void setClasificacionArchivisticaSerie(String clasificacionArchivisticaSerie) {
		this.clasificacionArchivisticaSerie = clasificacionArchivisticaSerie;
	}
	public String getClasificacionArchivisticaSubserie() {
		return clasificacionArchivisticaSubserie;
	}
	public void setClasificacionArchivisticaSubserie(String clasificacionArchivisticaSubserie) {
		this.clasificacionArchivisticaSubserie = clasificacionArchivisticaSubserie;
	}
	public String getClasificacionArchivisticaAsunto() {
		return clasificacionArchivisticaAsunto;
	}
	public void setClasificacionArchivisticaAsunto(String clasificacionArchivisticaAsunto) {
		this.clasificacionArchivisticaAsunto = clasificacionArchivisticaAsunto;
	}
	public String getClasificacionArchivisticaFechaAperturaExpediente() {
		return clasificacionArchivisticaFechaAperturaExpediente;
	}
	public void setClasificacionArchivisticaFechaAperturaExpediente(
			String clasificacionArchivisticaFechaAperturaExpediente) {
		this.clasificacionArchivisticaFechaAperturaExpediente = clasificacionArchivisticaFechaAperturaExpediente;
	}
	public String getClasificacionArchivisticaFechaCierreExpediente() {
		return clasificacionArchivisticaFechaCierreExpediente;
	}
	public void setClasificacionArchivisticaFechaCierreExpediente(String clasificacionArchivisticaFechaCierreExpediente) {
		this.clasificacionArchivisticaFechaCierreExpediente = clasificacionArchivisticaFechaCierreExpediente;
	}
	public String getValorDocumentalAdministrativo() {
		return valorDocumentalAdministrativo;
	}
	public void setValorDocumentalAdministrativo(String valorDocumentalAdministrativo) {
		this.valorDocumentalAdministrativo = valorDocumentalAdministrativo;
	}
	public String getValorDocumentalLegal() {
		return valorDocumentalLegal;
	}
	public void setValorDocumentalLegal(String valorDocumentalLegal) {
		this.valorDocumentalLegal = valorDocumentalLegal;
	}
	public String getValorDocumentalFinanciero() {
		return valorDocumentalFinanciero;
	}
	public void setValorDocumentalFinanciero(String valorDocumentalFinanciero) {
		this.valorDocumentalFinanciero = valorDocumentalFinanciero;
	}
	public String getPlazoConservacionArchivoTramite() {
		return plazoConservacionArchivoTramite;
	}
	public void setPlazoConservacionArchivoTramite(String plazoConservacionArchivoTramite) {
		this.plazoConservacionArchivoTramite = plazoConservacionArchivoTramite;
	}
	public String getPlazoConservacionArchivoConcentracion() {
		return plazoConservacionArchivoConcentracion;
	}
	public void setPlazoConservacionArchivoConcentracion(String plazoConservacionArchivoConcentracion) {
		this.plazoConservacionArchivoConcentracion = plazoConservacionArchivoConcentracion;
	}
	public String getPeriodoAdicionalSolicitudAcceso() {
		return periodoAdicionalSolicitudAcceso;
	}
	public void setPeriodoAdicionalSolicitudAcceso(String periodoAdicionalSolicitudAcceso) {
		this.periodoAdicionalSolicitudAcceso = periodoAdicionalSolicitudAcceso;
	}
	public String getPeriodoAdicionalDesclasificacion() {
		return periodoAdicionalDesclasificacion;
	}
	public void setPeriodoAdicionalDesclasificacion(String periodoAdicionalDesclasificacion) {
		this.periodoAdicionalDesclasificacion = periodoAdicionalDesclasificacion;
	}
	public String getPeriodoAdicionalNoExpediente() {
		return periodoAdicionalNoExpediente;
	}
	public void setPeriodoAdicionalNoExpediente(String periodoAdicionalNoExpediente) {
		this.periodoAdicionalNoExpediente = periodoAdicionalNoExpediente;
	}
	public String getPeriodoAdicionalNoHojas() {
		return periodoAdicionalNoHojas;
	}
	public void setPeriodoAdicionalNoHojas(String periodoAdicionalNoHojas) {
		this.periodoAdicionalNoHojas = periodoAdicionalNoHojas;
	}
	public String getPeriodoAdicionalNoPaginas() {
		return periodoAdicionalNoPaginas;
	}
	public void setPeriodoAdicionalNoPaginas(String periodoAdicionalNoPaginas) {
		this.periodoAdicionalNoPaginas = periodoAdicionalNoPaginas;
	}

}
