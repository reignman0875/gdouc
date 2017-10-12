package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="IndiceExpedientesReserva")
public class IndiceExpedientesReservaBean extends PadreBean {

	public IndiceExpedientesReservaBean() {
		unidadAdministrativa=titular=atribucion=rubroTematico=nombreLey=articuloFraccion=periodoReserva=new String();
	}
	
	private String unidadAdministrativa;
	private String titular;
	private String atribucion;
	private String rubroTematico;
	private String nombreLey;
	private String articuloFraccion;
	private String periodoReserva;
//	private List <IndiceExpedientesReservaTablaBean> tablaContenido;
//	
//	@XmlTransient
//	public List<IndiceExpedientesReservaTablaBean> getTablaContenido() {
//		return tablaContenido;
//	}
//	public void setTablaContenido(List<IndiceExpedientesReservaTablaBean> tablaContenido) {
//		this.tablaContenido = tablaContenido;
//	}

	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getAtribucion() {
		return atribucion;
	}

	public void setAtribucion(String atribucion) {
		this.atribucion = atribucion;
	}

	public String getRubroTematico() {
		return rubroTematico;
	}

	public void setRubroTematico(String rubroTematico) {
		this.rubroTematico = rubroTematico;
	}



	public String getNombreLey() {
		return nombreLey;
	}

	public void setNombreLey(String nombreLey) {
		this.nombreLey = nombreLey;
	}

	public String getArticuloFraccion() {
		return articuloFraccion;
	}

	public void setArticuloFraccion(String articuloFraccion) {
		this.articuloFraccion = articuloFraccion;
	}

	public String getPeriodoReserva() {
		return periodoReserva;
	}

	public void setPeriodoReserva(String periodoReserva) {
		this.periodoReserva = periodoReserva;
	}

}
