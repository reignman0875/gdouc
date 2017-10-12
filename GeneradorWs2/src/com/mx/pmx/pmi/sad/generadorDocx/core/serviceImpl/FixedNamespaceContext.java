package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.NamespaceContext;

/**
 * Implementación de {@link javax.xml.namespace.NamespaceContext} usada para
 * resolver uris de namespaces a partir de sus prefijos y viceversa. Esta
 * implementación recibe un mapeo fijo de sufijos a namespaces.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 * 
 */
public class FixedNamespaceContext implements NamespaceContext {
	
	/**
	 * Mapa de prefijos a namespaces.
	 */
	private Map<String, String> prefixNamespaceMap;
	
	/**
	 * Crea una instancia de esta clase a partir de un mapa de prefijos a namespaces.
	 * 
	 * @param prefixNamespaceMap mapa de prefijos a namespaces.
	 */
	public FixedNamespaceContext(Map<String, String> prefixNamespaceMap) {
		this.prefixNamespaceMap = prefixNamespaceMap;
	}

	/**
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(String)
	 */
	public String getNamespaceURI(String prefix) {
		return prefixNamespaceMap.get(prefix);
	}

	/**
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(String)
	 */
	public String getPrefix(String namespaceURI) {
		for (Entry<String, String> entry : prefixNamespaceMap.entrySet()) {
			if (namespaceURI.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(String)
	 */
	@SuppressWarnings("rawtypes")
	public Iterator getPrefixes(String namespaceURI) {
		List<String> prefixes = new LinkedList<String>();
		for (Entry<String, String> entry : prefixNamespaceMap.entrySet()) {
			if (namespaceURI.equals(entry.getValue())) {
				prefixes.add(entry.getKey());
			}
		}
		return prefixes.iterator();
	}

}
