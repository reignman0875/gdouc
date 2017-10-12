package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Representa la definición de una variable de XPresso.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 *
 */
public class XPressoVarDef {
	
	/**
	 * Tipo Array.
	 */
	public static final String ARRAY_TYPE = "Array";
	
	/**
	 * Tipo Simple.
	 */
	public static final String SIMPLE_TYPE = "Simple";
	
	/**
	 * id de la variable.
	 */
	private String id;
	
	/**
	 * Nombre de la variable.
	 */
	private String name;
	
	/**
	 * Tipo de la variable ej. {@link #ARRAY_TYPE}, {@link #SIMPLE_TYPE}.
	 */
	private String type;
	
	/**
	 * El mapeo xpath de la variable.
	 */
	private String xmlMapping;
	
	/**
	 * Variables hijos de esta variable.
	 */
	private List<XPressoVarDef> elements = new LinkedList<XPressoVarDef>();

	/**
	 * Regresa el id de la variable.
	 * 
	 * @return el id de la variable
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id de la variable.
	 * 
	 * @param id el id de la variable
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Regresa el nombre de la variable.
	 * 
	 * @return el nombre de la variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre de la variable.
	 * 
	 * @param name el nombre de la variable
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Regresa el tipo de la variable.
	 * 
	 * @return el tipo de la variable
	 */
	public String getType() {
		return type;
	}

	/**
	 * Establece el tipo de la variable.
	 * 
	 * @param type el tipo de la variable
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Regresa el mapeo xpath de la variable.
	 * 
	 * @return el mapeo xpath de la variable
	 */
	public String getXmlMapping() {
		return xmlMapping;
	}

	/**
	 * Establece el mapeo xpath de la variable.
	 * 
	 * @param xmlMapping el mapeo xpath de la variable
	 */
	public void setXmlMapping(String xmlMapping) {
		this.xmlMapping = xmlMapping;
	}

	/**
	 * Regresa las variables hijos de esta variable.
	 * 
	 * @return las variables hijos de esta variable
	 */
	public List<XPressoVarDef> getElements() {
		return elements;
	}

	/**
	 * Establece las variables hijos de esta variable.
	 * 
	 * @param elements las variables hijos de esta variable
	 */
	public void setElements(List<XPressoVarDef> elements) {
		this.elements = elements;
	}

	/**
	 * Hash code generado a partir del id.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Determinado a partir del id.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XPressoVarDef other = (XPressoVarDef) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
