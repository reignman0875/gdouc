package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable;

public class DocumentoV2Dto implements Serializable {

	private static final long serialVersionUID = -7105422978017354148L;
	
	private String idDocumento;
	private String nombreDocumento;
	private boolean esFolder;
	
	public DocumentoV2Dto(String idDocumento, String nombreDocumento, boolean esFolder) {
		this.idDocumento = idDocumento;
		this.nombreDocumento = nombreDocumento;
		this.esFolder = esFolder;
	}
	
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
	public boolean isEsFolder() {
		return esFolder;
	}
	public void setEsFolder(boolean esFolder) {
		this.esFolder = esFolder;
	}
	
}
