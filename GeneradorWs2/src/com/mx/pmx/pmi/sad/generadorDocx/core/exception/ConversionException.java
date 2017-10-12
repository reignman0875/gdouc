package com.mx.pmx.pmi.sad.generadorDocx.core.exception;

public class ConversionException extends RuntimeException{

	private static final long serialVersionUID = -1506652088802621745L;

	private Exception excepcion;
	
	public ConversionException(Exception e){
		super();
		setExcepcion(e);
	}

	public Exception getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}
}