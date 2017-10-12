package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Representa a la "parte" de relaciones del xml principal en un docx.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public class RelationshipsPart {

	public static final String IMAGE_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image";

	public static final String CUSTOM_XML_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/customXml";

	public static final String RELATIONSHIP_NS_URI = "http://schemas.openxmlformats.org/package/2006/relationships";

	public static final String RELATIONSHIP_ELEMENT = "Relationship";

	public static final String ID_ATTRIBUTE = "Id";

	public static final String TYPE_ATTRIBUTE = "Type";

	public static final String TARGET_ATTRIBUTE = "Target";

	/**
	 * El DOM del xml de relaciones.
	 */
	private Document document;

	/**
	 * Mapa de ids a elementos de tipo {@link #RELATIONSHIP_ELEMENT}.
	 */
	private Map<String, Element> relationshipElements = new HashMap<String, Element>();

	/**
	 * Crea una instancia a partir del DOM del xml de relaciones.
	 * 
	 * @param document
	 *            el DOM del xml de relaciones
	 */
	public RelationshipsPart(Document document) {
		this.document = document;
		extractElements();
	}

	/**
	 * Uso interno. Navega el DOM buscando elementos de tipo
	 * {@link #RELATIONSHIP_ELEMENT} para almacenarlos en el mapa
	 * {@link #relationshipElements}.
	 */
	private void extractElements() {
		NodeList nodes = document.getDocumentElement().getElementsByTagNameNS(
				RELATIONSHIP_NS_URI, RELATIONSHIP_ELEMENT);

		for (int i = 0; i < nodes.getLength(); i++) {
			Element elem = (Element) nodes.item(i);
			String rId = elem.getAttribute(ID_ATTRIBUTE);
			relationshipElements.put(rId, elem);
		}
	}

	/**
	 * Regresa todos los ids de relaciones.
	 */
	public Set<String> getRelationshipIds() {
		return relationshipElements.keySet();
	}

	/**
	 * Crea una nueva relacion de tipo <code>type</code> con el target
	 * <code>target</code>.
	 * 
	 * @param type
	 *            el tipo de la relación ej. {@link #CUSTOM_XML_TYPE},
	 *            {@link #IMAGE_TYPE}
	 * @param target
	 *            el path del archivo destino de la relación
	 * @return el id autogenerado de la nueva relación
	 */
	public String addRelationship(String type, String target) {

		String rId = "rId1";
		for (int i = 2; relationshipElements.containsKey(rId); i++) {
			rId = "rId" + i;
		}

		Element relElement = document.createElementNS(RELATIONSHIP_NS_URI,
				RELATIONSHIP_ELEMENT);
		relElement.setAttribute(ID_ATTRIBUTE, rId);
		relElement.setAttribute(TYPE_ATTRIBUTE, type);
		relElement.setAttribute(TARGET_ATTRIBUTE, target);

		document.getDocumentElement().appendChild(relElement);

		relationshipElements.put(rId, relElement);

		return rId;
	}

	/**
	 * Modifica el tipo de la relación con id <code>rId</code>.
	 * 
	 * @param rId
	 *            el id de la relación
	 * @param type
	 *            el tipo de la relación ej. {@link #CUSTOM_XML_TYPE},
	 *            {@link #IMAGE_TYPE}
	 */
	public void setRelationshipType(String rId, String type) {
		relationshipElements.get(rId).setAttribute(TYPE_ATTRIBUTE, type);
	}

	/**
	 * Modifica el destino de la relación con id <code>rId</code>.
	 * 
	 * @param rId
	 *            el id de la relación
	 * @param target
	 *            el path del archivo destino de la relación
	 */
	public void setRelationshipTarget(String rId, String target) {
		relationshipElements.get(rId).setAttribute(TARGET_ATTRIBUTE, target);
	}

	/**
	 * Regresa el tipo de la relación con id <code>rId</code>.
	 * 
	 * @param rId
	 *            el id de la relación
	 * @return el tipo de la relación ej. {@link #CUSTOM_XML_TYPE},
	 *         {@link #IMAGE_TYPE}
	 */
	public String getRelationshipType(String rId) {
		return relationshipElements.get(rId).getAttribute(TYPE_ATTRIBUTE);
	}

	/**
	 * Regresa el destino de la relación con id <code>rId</code>.
	 * 
	 * @param rId
	 *            el id de la relación
	 * @return el path del archivo destino de la relación
	 */
	public String getRelationshipTarget(String rId) {
		return relationshipElements.get(rId).getAttribute(TARGET_ATTRIBUTE);
	}

	/**
	 * Regresa el DOM del xml de relaciones.
	 * 
	 * @return el DOM del xml de relacionesb
	 */
	public Document getDocument() {
		return document;
	}
}
