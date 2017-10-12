package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable;

public class DocumentoXMLDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "idDocumento: "+idDocumento+"; nombreDocumento:"+nombreDocumento;
	}
	
	private String idDocumento;
	
	private String nombreDocumento;
	
	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
}
