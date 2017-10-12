package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import org.w3c.dom.Node;

/**
 * Interfaz para el patrón de diseño visitor aplicado para recorrer DOMs. 
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 *
 */
public interface NodeVisitor {
	
	/**
	 * Llamado cada que el recorrido visita un nodo.
	 * 
	 * @param node el nodo visitado.
	 */
	public void visit(Node node);
}
