package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable;

public class DocumentoGeneradoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private DocumentoXMLDto documentoXML;
	
	private String idDocumentoGenerado;
	
	private String nombreDocumentoGenerado;
	
	private String rutaDocumentoGenerado;

	@Override
	public String toString() {
		return "idDocumentoGenerado: "+idDocumentoGenerado+"; nombreDocumentoGenerado:"+nombreDocumentoGenerado+"; rutaDocumentoGenerado"+rutaDocumentoGenerado+"; "+documentoXML.toString();
	}

	public String getIdDocumentoGenerado() {
		return idDocumentoGenerado;
	}

	public void setIdDocumentoGenerado(String idDocumentoGenerado) {
		this.idDocumentoGenerado = idDocumentoGenerado;
	}

	public String getNombreDocumentoGenerado() {
		return nombreDocumentoGenerado;
	}

	public void setNombreDocumentoGenerado(String nombreDocumentoGenerado) {
		this.nombreDocumentoGenerado = nombreDocumentoGenerado;
	}

	public void setRutaDocumentoGenerado(String rutaDocumentoGenerado) {
		this.rutaDocumentoGenerado = rutaDocumentoGenerado;
	}

	public String getRutaDocumentoGenerado() {
		return rutaDocumentoGenerado;
	}

	public void setDocumentoXML(DocumentoXMLDto documentoXML) {
		this.documentoXML = documentoXML;
	}

	public DocumentoXMLDto getDocumentoXML() {
		return documentoXML;
	}
}
