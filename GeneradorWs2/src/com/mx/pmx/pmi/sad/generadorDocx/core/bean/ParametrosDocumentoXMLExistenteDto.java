package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable;

public class ParametrosDocumentoXMLExistenteDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private DocumentoXMLDto documentoXML;
	
	private String rutaDocumento;
	
	private String nombreDocumento;
	
	public ParametrosDocumentoXMLExistenteDto(){
		super();
	}

	/**
	 * Construye el objeto asumiendo que ParametrosDocumentoDto.rutaDocumento y ParametrosDocumentoDto.nombreDocumento
	 * serán los valores para las propiedades del objeto construido.
	 * @param parametro 
	 * @param documentoXML
	 */
	
	
	public ParametrosDocumentoXMLExistenteDto(ParametrosDocumentoDto parametro,
			DocumentoXMLDto documentoXML) {
		setDocumentoXML(documentoXML);
		setNombreDocumento(parametro.getNombreDocumento());
		setRutaDocumento(parametro.getRutaDocumento());
	}


	public String getRutaDocumento() {
		return rutaDocumento;
	}

	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public void setDocumentoXML(DocumentoXMLDto documentoXML) {
		this.documentoXML = documentoXML;
	}

	public DocumentoXMLDto getDocumentoXML() {
		return documentoXML;
	}
}
