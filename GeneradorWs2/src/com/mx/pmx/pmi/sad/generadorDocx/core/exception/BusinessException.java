package com.mx.pmx.pmi.sad.generadorDocx.core.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -4561143699098077864L;

	private String idErrorPersonalizado;
	private String mensajeErrorPersonalizado;
	
	public BusinessException(String idErrorPersonalizado){
		super();
	    this.idErrorPersonalizado = idErrorPersonalizado;
	}

	public BusinessException(String idErrorPersonalizado, String mensajeErrorPersonalizado){
		super();
	    this.idErrorPersonalizado = idErrorPersonalizado;
	    this.mensajeErrorPersonalizado = mensajeErrorPersonalizado;
	}
	
	public String getIdErrorPersonalizado() {
		return idErrorPersonalizado;
	}

	public void setIdErrorPersonalizado(String idErrorPersonalizado) {
		this.idErrorPersonalizado = idErrorPersonalizado;
	}

	public String getMensajeErrorPersonalizado() {
		return mensajeErrorPersonalizado;
	}

	public void setMensajeErrorPersonalizado(String mensajeErrorPersonalizado) {
		this.mensajeErrorPersonalizado = mensajeErrorPersonalizado;
	}
}