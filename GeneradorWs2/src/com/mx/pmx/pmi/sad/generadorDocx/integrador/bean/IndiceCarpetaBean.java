package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="IndiceExpediente")
public class IndiceCarpetaBean extends PadreBean {

	public IndiceCarpetaBean() {
		contraparte=subexpediente=lineaNegocio=mes=anio=totalHojas=integradorExpedientes=revisorExpedientes=aprobadorExpedientes=recibioRevisoExpediente=recepcionExpedientes=new String();
	}
	private String contraparte;
	private String lineaNegocio;
	private String mes;
	private String anio;
	private String totalHojas;	
	private String integradorExpedientes;
	private String revisorExpedientes;
	private String aprobadorExpedientes;
	private String recibioRevisoExpediente;
	private String recepcionExpedientes;
	private String subexpediente;
	
	public String getContraparte() {
		return contraparte;
	}
	public void setContraparte(String contraparte) {
		this.contraparte = contraparte;
	}
	public String getSubexpediente() {
		return subexpediente;
	}
	public void setSubexpediente(String subexpediente) {
		this.subexpediente = subexpediente;
	}
	public String getLineaNegocio() {
		return lineaNegocio;
	}
	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getTotalHojas() {
		return totalHojas;
	}
	public void setTotalHojas(String totalHojas) {
		this.totalHojas = totalHojas;
	}
	public String getIntegradorExpedientes() {
		return integradorExpedientes;
	}
	public void setIntegradorExpedientes(String integradorExpedientes) {
		this.integradorExpedientes = integradorExpedientes;
	}
	public String getRevisorExpedientes() {
		return revisorExpedientes;
	}
	public void setRevisorExpedientes(String revisorExpedientes) {
		this.revisorExpedientes = revisorExpedientes;
	}
	public String getAprobadorExpedientes() {
		return aprobadorExpedientes;
	}
	public void setAprobadorExpedientes(String aprobadorExpedientes) {
		this.aprobadorExpedientes = aprobadorExpedientes;
	}
	public String getRecibioRevisoExpediente() {
		return recibioRevisoExpediente;
	}
	public void setRecibioRevisoExpediente(String recibioRevisoExpediente) {
		this.recibioRevisoExpediente = recibioRevisoExpediente;
	}
	public String getRecepcionExpedientes() {
		return recepcionExpedientes;
	}
	public void setRecepcionExpedientes(String recepcionExpedientes) {
		this.recepcionExpedientes = recepcionExpedientes;
	}
	
	
}
