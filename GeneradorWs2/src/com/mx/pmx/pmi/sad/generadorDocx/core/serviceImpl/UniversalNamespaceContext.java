package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.Document;

/**
 * Implementación de {@link javax.xml.namespace.NamespaceContext} usada para
 * resolver uris de namespaces a partir de sus prefijos y viceversa. Esta
 * implementación obtiene el mapeo entre prefijos/namespaces a partir de un
 * documento {@link org.w3c.dom.Document}.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public class UniversalNamespaceContext implements NamespaceContext {

	private Document document;

	/**
	 * Crea una instancia a partir de un documento {@link org.w3c.dom.Document}.
	 * 
	 * @param document
	 *            el documento
	 */
	public UniversalNamespaceContext(Document document) {
		this.document = document;
	}

	/**
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(String)
	 */
	public String getNamespaceURI(String prefix) {
		if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
			return document.lookupNamespaceURI(null);
		} else {
			return document.lookupNamespaceURI(prefix);
		}
	}

	/**
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(String)
	 */
	public String getPrefix(String namespaceURI) {
		return document.lookupPrefix(namespaceURI);
	}

	/**
	 * No implementado.
	 */
	@SuppressWarnings("rawtypes")
	public Iterator getPrefixes(String namespaceURI) {
		return null;
	}
}
