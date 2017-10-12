package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="InTransferenciaPrimariaSecundaria")
public class InTransferenciaPrimariaSecundariaTablaBean extends PadreBean {
	
	public InTransferenciaPrimariaSecundariaTablaBean() {
		noCaja=fechaApertura=fechaCierre=descripcionExpediente=noTomo=serie=numExpediente=clasificacion=periodoTramite=periodoConcentracion=disposiscion=totalFojas
				=totalExpedientes=ubicacionTopografica=new String();
	}

	private String noCaja;
	private String fechaApertura;
	private String fechaCierre;
	private String descripcionExpediente;
	private String noTomo;
	private String serie;
	private String numExpediente;
	private String clasificacion;
	private String periodoTramite;
	private String periodoConcentracion;
	private String disposiscion;
	private String totalFojas;
	private String totalExpedientes;
	private String ubicacionTopografica;
	public String getNoCaja() {
		return noCaja;
	}
	public void setNoCaja(String noCaja) {
		this.noCaja = noCaja;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getDescripcionExpediente() {
		return descripcionExpediente;
	}
	public void setDescripcionExpediente(String descripcionExpediente) {
		this.descripcionExpediente = descripcionExpediente;
	}
	public String getNoTomo() {
		return noTomo;
	}
	public void setNoTomo(String noTomo) {
		this.noTomo = noTomo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getPeriodoTramite() {
		return periodoTramite;
	}
	public void setPeriodoTramite(String periodoTramite) {
		this.periodoTramite = periodoTramite;
	}
	public String getPeriodoConcentracion() {
		return periodoConcentracion;
	}
	public void setPeriodoConcentracion(String periodoConcentracion) {
		this.periodoConcentracion = periodoConcentracion;
	}
	public String getDisposiscion() {
		return disposiscion;
	}
	public void setDisposiscion(String disposiscion) {
		this.disposiscion = disposiscion;
	}
	public String getTotalFojas() {
		return totalFojas;
	}
	public void setTotalFojas(String totalFojas) {
		this.totalFojas = totalFojas;
	}
	public String getTotalExpedientes() {
		return totalExpedientes;
	}
	public void setTotalExpedientes(String totalExpedientes) {
		this.totalExpedientes = totalExpedientes;
	}
	public String getUbicacionTopografica() {
		return ubicacionTopografica;
	}
	public void setUbicacionTopografica(String ubicacionTopografica) {
		this.ubicacionTopografica = ubicacionTopografica;
	}
	
	
	
}
