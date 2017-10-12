package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IndiceExpedientesReserva")
public class IndiceExpedientesReservaTablaBean extends PadreBean {

	public IndiceExpedientesReservaTablaBean() {
		numExpediente=fechaClasificacion=new String();
	}
	
	private String numExpediente;
	private String fechaClasificacion;
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	public String getFechaClasificacion() {
		return fechaClasificacion;
	}
	public void setFechaClasificacion(String fechaClasificacion) {
		this.fechaClasificacion = fechaClasificacion;
	}
	
	
	}
