package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.util.HashMap;
import java.util.Map;

public class DocumentoV3Dto extends DocumentoV2Dto {

	private static final long serialVersionUID = -4753865641317067762L;

	private Map<String, String> atributos = new HashMap<String, String>();
	
	public DocumentoV3Dto(String idDocumento, String nombreDocumento, boolean esFolder) {
		super(idDocumento, nombreDocumento, esFolder);
	}

	public void addAtributo(String nombre, String valor) {
		atributos.put(nombre, valor);
	}

	public Map<String, String> getAtributos() {
		return atributos;
	}
}
