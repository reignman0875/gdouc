package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Funciones de ayuda para accesar un DOM.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public final class DOMUtils {

	/**
	 * Realiza un recorrido DFS en el árbol de nodos partiendo del nodo <code>node</code>.
	 * Cuando se visita un nodo, todos sus nodos hijos ya han sido visitados.
	 * 
	 * @param node
	 *            el nodo a partir del cual se hace el recorrido
	 * @param v
	 *            instancia de <code>NodeVisitor</code> que recibe las "visitas"
	 *            a cada nodo.
	 */
	public static void traverse(Node node, NodeVisitor v) {
				
		if (node.hasChildNodes()) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node child = nodeList.item(i);
				traverse(child, v);
			}
		}
		
		v.visit(node);
	}
	
	/**
	 * Realiza un recorrido DFS en el árbol de nodos partiendo del nodo
	 * <code>node</code>. Unicamente se visitan elementos. Cuando se visita un
	 * elemento, todos sus elementos hijos ya han sido visitados.
	 * 
	 * @param node
	 *            el nodo a partir del cual se hace el recorrido
	 * @param vs
	 *            instancias de <code>NodeVisitor</code> que reciben las
	 *            "visitas" a cada nodo.
	 */
	public static void traverseElements(Node node, NodeVisitor ... vs) {
		
		if (!(node instanceof Document) && !(node instanceof Element)) {
			return;
		}
		
		List<Node> children = new LinkedList<Node>();
		
		if (node instanceof Document) {
			Document doc = (Document) node;
			children.add(doc.getDocumentElement());
		}
		else {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				children.add(nodeList.item(i));
			}
		}
		
		for (Node child : children) {
			traverseElements(child, vs);
		}
		
		for (NodeVisitor v : vs) {
			v.visit(node);
		}
	}
	
	/**
	 * Determina si un nodo de un DOM es un elemento.
	 * 
	 * @param node El nodo
	 * @param namespaceUri el uri de su namespace, no debe ser null
	 * @param localName el nombre del uri sin prefijo, no debe ser null
	 * @return true si es un elemento false en caso contrario
	 */
	public static boolean isElement(Node node, String namespaceUri, String localName) {
		
		if (!(node instanceof Element))
			return false;
		
		Element elem = (Element) node;
		if (localName.equals(elem.getLocalName()) && 
				namespaceUri.equals(elem.getNamespaceURI())) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Obtiene el primer elemento hijo del elemento dado.
	 * 
	 * @param node El elemento padre
	 * @param namespaceURI el uri del namespace del nodo, no debe ser null
	 * @param localName el nombre del nodo sin prefijo, no debe ser null
	 * @return el primer elemento hijo o null si no tiene hijos
	 */
	public static Element getChildElement(Element node, String namespaceURI, String localName) {
		if (node == null) {
			return null;
		}
		NodeList nodeList = node.getElementsByTagNameNS(namespaceURI, localName);
		return (Element) (nodeList.getLength() > 0 ? nodeList.item(0) : null);
	}
}
