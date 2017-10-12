package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistComCrudo")
public class ChecklistComCrudoBean extends PadreBean {
	
	public ChecklistComCrudoBean() {
		comunicacionesAlCliente=comunicacionesAlClienteC=comunicacionesDelCliente=comunicacionesDelClienteC=confirmacionCompraVenta
				=confirmacionCompraVentaC=confirmacionCartaCredito=confirmacionCartaCreditoC=recap=recapC=contratoSimple
				=contratoSimpleC=programaContractualPrevisto=programaContractualPrevistoC=modificacionesPrograma=modificacionesProgramaC
				=oficiosPep=oficiosPepC=oficioPemexDisponibilidad=oficioPemexDisponibilidadC=numPaginas=numHojas= new String();
	}
	private String ordenExpediente;
	public String getOrdenExpediente() {
		return ordenExpediente;
	}
	public void setOrdenExpediente(String ordenExpediente) {
		this.ordenExpediente = ordenExpediente;
	}
	private String comunicacionesAlCliente;
	private String comunicacionesAlClienteC;
	private String comunicacionesDelCliente;
	private String comunicacionesDelClienteC;
	private String confirmacionCompraVenta;
	private String confirmacionCompraVentaC;
	private String confirmacionCartaCredito;
	private String confirmacionCartaCreditoC;
	private String recap;
	private String recapC;
	private String contratoSimple;
	private String contratoSimpleC;
	private String programaContractualPrevisto;
	private String programaContractualPrevistoC;
	private String modificacionesPrograma;
	private String modificacionesProgramaC;
	private String oficiosPep;
	private String oficiosPepC;
	private String oficioPemexDisponibilidad;
	private String oficioPemexDisponibilidadC;
	private String numPaginas;
	private String numHojas;
	public String getComunicacionesAlCliente() {
		return comunicacionesAlCliente;
	}
	public void setComunicacionesAlCliente(String comunicacionesAlCliente) {
		this.comunicacionesAlCliente = comunicacionesAlCliente;
	}
	public String getComunicacionesAlClienteC() {
		return comunicacionesAlClienteC;
	}
	public void setComunicacionesAlClienteC(String comunicacionesAlClienteC) {
		this.comunicacionesAlClienteC = comunicacionesAlClienteC;
	}
	public String getComunicacionesDelCliente() {
		return comunicacionesDelCliente;
	}
	public void setComunicacionesDelCliente(String comunicacionesDelCliente) {
		this.comunicacionesDelCliente = comunicacionesDelCliente;
	}
	public String getComunicacionesDelClienteC() {
		return comunicacionesDelClienteC;
	}
	public void setComunicacionesDelClienteC(String comunicacionesDelClienteC) {
		this.comunicacionesDelClienteC = comunicacionesDelClienteC;
	}
	public String getConfirmacionCompraVenta() {
		return confirmacionCompraVenta;
	}
	public void setConfirmacionCompraVenta(String confirmacionCompraVenta) {
		this.confirmacionCompraVenta = confirmacionCompraVenta;
	}
	public String getConfirmacionCompraVentaC() {
		return confirmacionCompraVentaC;
	}
	public void setConfirmacionCompraVentaC(String confirmacionCompraVentaC) {
		this.confirmacionCompraVentaC = confirmacionCompraVentaC;
	}
	public String getConfirmacionCartaCredito() {
		return confirmacionCartaCredito;
	}
	public void setConfirmacionCartaCredito(String confirmacionCartaCredito) {
		this.confirmacionCartaCredito = confirmacionCartaCredito;
	}
	public String getConfirmacionCartaCreditoC() {
		return confirmacionCartaCreditoC;
	}
	public void setConfirmacionCartaCreditoC(String confirmacionCartaCreditoC) {
		this.confirmacionCartaCreditoC = confirmacionCartaCreditoC;
	}
	public String getRecap() {
		return recap;
	}
	public void setRecap(String recap) {
		this.recap = recap;
	}
	public String getRecapC() {
		return recapC;
	}
	public void setRecapC(String recapC) {
		this.recapC = recapC;
	}
	public String getContratoSimple() {
		return contratoSimple;
	}
	public void setContratoSimple(String contratoSimple) {
		this.contratoSimple = contratoSimple;
	}
	public String getContratoSimpleC() {
		return contratoSimpleC;
	}
	public void setContratoSimpleC(String contratoSimpleC) {
		this.contratoSimpleC = contratoSimpleC;
	}
	public String getProgramaContractualPrevisto() {
		return programaContractualPrevisto;
	}
	public void setProgramaContractualPrevisto(String programaContractualPrevisto) {
		this.programaContractualPrevisto = programaContractualPrevisto;
	}
	public String getProgramaContractualPrevistoC() {
		return programaContractualPrevistoC;
	}
	public void setProgramaContractualPrevistoC(String programaContractualPrevistoC) {
		this.programaContractualPrevistoC = programaContractualPrevistoC;
	}
	public String getModificacionesPrograma() {
		return modificacionesPrograma;
	}
	public void setModificacionesPrograma(String modificacionesPrograma) {
		this.modificacionesPrograma = modificacionesPrograma;
	}
	public String getModificacionesProgramaC() {
		return modificacionesProgramaC;
	}
	public void setModificacionesProgramaC(String modificacionesProgramaC) {
		this.modificacionesProgramaC = modificacionesProgramaC;
	}
	public String getOficiosPep() {
		return oficiosPep;
	}
	public void setOficiosPep(String oficiosPep) {
		this.oficiosPep = oficiosPep;
	}
	public String getOficiosPepC() {
		return oficiosPepC;
	}
	public void setOficiosPepC(String oficiosPepC) {
		this.oficiosPepC = oficiosPepC;
	}
	public String getOficioPemexDisponibilidad() {
		return oficioPemexDisponibilidad;
	}
	public void setOficioPemexDisponibilidad(String oficioPemexDisponibilidad) {
		this.oficioPemexDisponibilidad = oficioPemexDisponibilidad;
	}
	public String getOficioPemexDisponibilidadC() {
		return oficioPemexDisponibilidadC;
	}
	public void setOficioPemexDisponibilidadC(String oficioPemexDisponibilidadC) {
		this.oficioPemexDisponibilidadC = oficioPemexDisponibilidadC;
	}
	public String getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(String numPaginas) {
		this.numPaginas = numPaginas;
	}
	public String getNumHojas() {
		return numHojas;
	}
	public void setNumHojas(String numHojas) {
		this.numHojas = numHojas;
	}

}
