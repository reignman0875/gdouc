package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GuiaSimple")
public class GuiaSimpleTablaBean extends PadreBean {
	public GuiaSimpleTablaBean() {
		numExpediente=descExpediente=volumenTotalGenerados=volumenTotalTransPrimaria=volumenTotalTransSecundaria=volumenTotalArchTramite=ubicacionTopografica = new String();

	}

	private String numExpediente;
	private String descExpediente;
	private String volumenTotalGenerados;
	private String volumenTotalTransPrimaria;
	private String volumenTotalTransSecundaria;
	private String volumenTotalArchTramite;
	private String ubicacionTopografica;
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	public String getDescExpediente() {
		return descExpediente;
	}
	public void setDescExpediente(String descExpediente) {
		this.descExpediente = descExpediente;
	}
	public String getVolumenTotalGenerados() {
		return volumenTotalGenerados;
	}
	public void setVolumenTotalGenerados(String volumenTotalGenerados) {
		this.volumenTotalGenerados = volumenTotalGenerados;
	}
	public String getVolumenTotalTransPrimaria() {
		return volumenTotalTransPrimaria;
	}
	public void setVolumenTotalTransPrimaria(String volumenTotalTransPrimaria) {
		this.volumenTotalTransPrimaria = volumenTotalTransPrimaria;
	}
	public String getVolumenTotalTransSecundaria() {
		return volumenTotalTransSecundaria;
	}
	public void setVolumenTotalTransSecundaria(String volumenTotalTransSecundaria) {
		this.volumenTotalTransSecundaria = volumenTotalTransSecundaria;
	}
	public String getVolumenTotalArchTramite() {
		return volumenTotalArchTramite;
	}
	public void setVolumenTotalArchTramite(String volumenTotalArchTramite) {
		this.volumenTotalArchTramite = volumenTotalArchTramite;
	}
	public String getUbicacionTopografica() {
		return ubicacionTopografica;
	}
	public void setUbicacionTopografica(String ubicacionTopografica) {
		this.ubicacionTopografica = ubicacionTopografica;
	}
	
	
	
}
