package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentoDto implements Serializable {

	private static final long serialVersionUID = -6109939280079214100L;
	
	private String idDocumento;
	private String nombreDocumento;
	private String rutaDocumento;
	private File contenido;
	private String urlDocumento;
	private String mimeType;
	private long contentLength;
	private List<DocumentoDto> anexos;
	private Map<String, Object> atributos = new HashMap<String, Object>();

	public void addAtributo(String nombre, Object valor) {
		atributos.put(nombre, valor);
	}

	public Map<String, Object> getAtributos() {
		return atributos;
	}
	
	public void addAnexo(DocumentoDto anexo) {
		if (anexos == null) {
			anexos = new ArrayList<DocumentoDto>();
		}
		anexos.add(anexo);
	}

	public List<DocumentoDto> getAnexos() {
		return anexos;
	}

	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
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
	public String getRutaDocumento() {
		return rutaDocumento;
	}
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}
	public File getContenido() {
		return contenido;
	}
	public void setContenido(File contenido) {
		this.contenido = contenido;
	}
	public String getUrlDocumento() {
		return urlDocumento;
	}
	public void setUrlDocumento(String urlDocumento) {
		this.urlDocumento = urlDocumento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
