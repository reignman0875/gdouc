package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ValePrestamo")
public class IndiceCarpetaTablaBean extends PadreBean {

	public IndiceCarpetaTablaBean() {
		numExpediente=numOrden=totalExpedientes=totalPagFisica=totalPagDigital=fechaDevolucion=fechaProroga=ubicacionTopografica=new String();
	}
	private String numExpediente;
	private String numOrden;
	private String totalExpedientes;
	private String totalPagFisica;	
	private String totalPagDigital;
	private String fechaDevolucion;
	private String fechaProroga;
	private String ubicacionTopografica;
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}
	public String getTotalExpedientes() {
		return totalExpedientes;
	}
	public void setTotalExpedientes(String totalExpedientes) {
		this.totalExpedientes = totalExpedientes;
	}
	public String getTotalPagFisica() {
		return totalPagFisica;
	}
	public void setTotalPagFisica(String totalPagFisica) {
		this.totalPagFisica = totalPagFisica;
	}
	public String getTotalPagDigital() {
		return totalPagDigital;
	}
	public void setTotalPagDigital(String totalPagDigital) {
		this.totalPagDigital = totalPagDigital;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getFechaProroga() {
		return fechaProroga;
	}
	public void setFechaProroga(String fechaProroga) {
		this.fechaProroga = fechaProroga;
	}
	public String getUbicacionTopografica() {
		return ubicacionTopografica;
	}
	public void setUbicacionTopografica(String ubicacionTopografica) {
		this.ubicacionTopografica = ubicacionTopografica;
	}

	
	
}
