package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable;

public class ParametrosDocumentoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idDocumentoPlantilla;
	private String nombreDocumento;
	private String xmlDatos;
	
	private String rutaXml;
	
	/**
	 * TODO: indicar que no se usa en crearDocumentoXML
	 */
	private String rutaDocumento;
	
	public String getIdDocumentoPlantilla() {
		return idDocumentoPlantilla;
	}
	
	public void setIdDocumentoPlantilla(String idDocumentoPlantilla) {
		this.idDocumentoPlantilla = idDocumentoPlantilla;
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	
	public String getXmlDatos() {
		return xmlDatos;
	}
	
	public void setXmlDatos(String xmlDatos) {
		this.xmlDatos = xmlDatos;
	}

	public void setRutaXml(String rutaXml) {
		this.rutaXml = rutaXml;
	}

	public String getRutaXml() {
		return rutaXml;
	}

	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}

	public String getRutaDocumento() {
		return rutaDocumento;
	}
}
