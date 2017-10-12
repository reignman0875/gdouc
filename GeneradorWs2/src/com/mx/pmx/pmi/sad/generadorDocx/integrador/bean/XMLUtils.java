package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;


/**
 * Funciones de ayuda para manipulación de XML.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 * 
 */
public final class XMLUtils {
	

	public static final String UTF8_ENCODING = "UTF-8";

	public static final String WINDOWS_ENCODING = "windows-1252";

	private static volatile DOMImplementationLS domImpl;

	private static final Log LOGGER = LogFactory.getLog(XMLUtils.class);

	private static final String TAG_ID_DOCUMENTO_PLANTILLA = "idDocumentoPlantilla";

	private static final String ERROR_DOM_IMPL_REGISTRY_INSTANCE = "ERROR_DOM_IMPL_REGISTRY_INSTANCE";

	private static final String ERROR_DOCUMENT_BUILDER_INSTANCE = "ERROR_DOCUMENT_BUILDER_INSTANCE";

	private static final String ERROR_SERIALIZER_INSTANCE = "ERROR_SERIALIZER_INSTANCE";

	private static final String ERROR_OBTENER_ID_PLANTILLA = "ERROR_OBTENER_ID_PLANTILLA";

	private static final String ERROR_INSERTAR_ID_PLANTILLA = "ERROR_INSERTAR_ID_PLANTILLA";

	private static final String ERROR_XML_ARCHIVO_NO_EXISTE = "ERROR_XML_ARCHIVO_NO_EXISTE";

	private static final String ERROR_AL_ACTUALIZAR_XML = "ERROR_AL_ACTUALIZAR_XML";

	static {
		System.setProperty(DOMImplementationRegistry.PROPERTY,
				"org.apache.xerces.dom.DOMXSImplementationSourceImpl");
				 
	}

	
	/**
	 * Regresa la implementación de DOM usada por los métodos de esta clase. La
	 * instancia de esta implementación es única y la misma es regresada en
	 * distintas llamadas.
	 * 
	 * @return la implementación de DOM usada por los métodos de esta clase
	 * @throws TransformationServicesException
	 *             si no se puede obtener la implementación de DOM
	 */
	private static DOMImplementationLS getDOMImplementation()
			throws BusinessException {

		if (domImpl != null) {
			return domImpl;
		}
			    	    	    
		DOMImplementationRegistry registry;

		try {
			registry = DOMImplementationRegistry.newInstance();
		} catch (ClassCastException e) {
			LOGGER.error("No se pudo instanciar un DOMImplementationRegistry",
					e);
			throw new BusinessException(ERROR_DOM_IMPL_REGISTRY_INSTANCE, e
					.getMessage());
		} catch (ClassNotFoundException e) {
			LOGGER.error("No se pudo instanciar un DOMImplementationRegistry",
					e);
			throw new BusinessException(ERROR_DOM_IMPL_REGISTRY_INSTANCE, e
					.getMessage());
		} catch (InstantiationException e) {
			LOGGER.error("No se pudo instanciar un DOMImplementationRegistry",
					e);
			throw new BusinessException(ERROR_DOM_IMPL_REGISTRY_INSTANCE, e
					.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.error("No se pudo instanciar un DOMImplementationRegistry",
					e);
			throw new BusinessException(ERROR_DOM_IMPL_REGISTRY_INSTANCE, e
					.getMessage());
		}

		domImpl = (DOMImplementationLS) registry.getDOMImplementation("LS");
		return domImpl;
	}

	/**
	 * Crea un DOM a partir de un xml dado en un archivo. Se asume que el
	 * encoding es UTF-8.
	 * 
	 * @param file
	 *            el archivo del xml
	 * @return el DOM
	 * 
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	public static Document buildDOM(File file) throws BusinessException {
		return buildDOM(file, UTF8_ENCODING);
	}

	/**
	 * Crea un DOM a partir de un xml dado en un archivo.
	 * 
	 * @param file
	 *            el archivo del xml
	 * @param encoding
	 *            el encoding del archivo, sobreescribe la declaración del xml
	 * @return el DOM
	 * 
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	public static Document buildDOM(File file, String encoding)
			throws BusinessException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return buildDOM(fis, encoding);
		} catch (FileNotFoundException e) {
			LOGGER.error("El archivo xml no existe", e);
			throw new BusinessException(ERROR_XML_ARCHIVO_NO_EXISTE, e
					.getMessage());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.error("Error cerrando stream", e);
				}
			}
		}
	}

	/**
	 * Crea un DOM a partir de un xml dado en un <code>InputStream</code>. Se
	 * asume que el encoding es UTF-8.
	 * 
	 * @param xml
	 *            el xml
	 * @return el DOM
	 */
	public static Document buildDOM(InputStream is) throws BusinessException {
		return buildDOM(is, UTF8_ENCODING);
	}

	/**
	 * Crea un DOM a partir de un xml dado en un <code>InputStream</code> con el
	 * encoding indicado.
	 * 
	 * @param xml
	 *            el xml
	 * @param encoding
	 *            el encoding del xml
	 * @return el DOM
	 */
	public static Document buildDOM(InputStream is, String encoding)
			throws BusinessException {
		LSParser builder = getDOMImplementation().createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput lsInput = getDOMImplementation().createLSInput();
		lsInput.setByteStream(is);
		lsInput.setEncoding(encoding);
		Document document = builder.parse(lsInput);
		return document;
	}

	/**
	 * Recrea los nodos de <code>sourceDocument</code> en el nodo de
	 * <code>targetDocument</code> indicado por la expresión de XPath. Para que
	 * esto se lleve acabo al menos los nombres de los elementos raíz deben
	 * coincidir.
	 * 
	 * @param targetDocument
	 *            el documento en donde se va a incluir el nodo
	 * @param sourceDocument
	 *            el documento de donde se va a obtener el nodo
	 * @return true si se realizó al menos un merge, false en caso contrario
	 */
	public static boolean mergeDOM(Document targetDocument,
			Document sourceDocument) throws BusinessException {
		return mergeDOM(targetDocument.getDocumentElement(), sourceDocument
				.getDocumentElement());
	}

	/**
	 * Método recursivo que recrea el DOM de <code>elem2</code> dentro de
	 * <code>elem1</code> si el nombre de estos coincide y sin sobreescribir
	 * valores.
	 * 
	 * @param elem1
	 *            el elemento donde se va a recrear el dom de <code>elem2</code>
	 * @param elem2
	 *            el elemento cuyo DOM se va a recrear en <code>elem1</code>
	 * @return true si se realizó al menos un merge, false en caso contrario
	 */
	private static boolean mergeDOM(org.w3c.dom.Element elem1,
			org.w3c.dom.Element elem2) {

		if (!elem1.getLocalName().equals(elem2.getLocalName()))
			return false;

		boolean merged = false;

		Map<String, org.w3c.dom.Element> elem1ChildrenMap = new HashMap<String, org.w3c.dom.Element>();
		NodeList elem1Children = elem1.getChildNodes();

		Map<String, Integer> elem1ChildrenCountMap = new HashMap<String, Integer>();

		for (int j = 0; j < elem1Children.getLength(); j++) {
			Node childNode1 = (Node) elem1Children.item(j);

			if (!(childNode1 instanceof org.w3c.dom.Element))
				continue;

			org.w3c.dom.Element childElem1 = (org.w3c.dom.Element) childNode1;
			String childElem1Name = childElem1.getLocalName();
			int childElem1NameCount = (!elem1ChildrenCountMap
					.containsKey(childElem1Name) ? 0 : elem1ChildrenCountMap
					.get(childElem1Name));
			childElem1NameCount++;
			String key = childElem1Name + "_" + childElem1NameCount;
			elem1ChildrenMap.put(key, childElem1);
			elem1ChildrenCountMap.put(childElem1Name, childElem1NameCount);
		}

		Map<String, Integer> elem2ChildrenCountMap = new HashMap<String, Integer>();

		NodeList elem2Children = elem2.getChildNodes();
		for (int i = 0; i < elem2Children.getLength(); i++) {
			Node childNode2 = elem2Children.item(i);

			if (!(childNode2 instanceof org.w3c.dom.Element))
				continue;

			org.w3c.dom.Element childElem2 = (org.w3c.dom.Element) childNode2;

			String childElem2Name = childElem2.getLocalName();
			int childElem2NameCount = (!elem2ChildrenCountMap
					.containsKey(childElem2Name) ? 0 : elem2ChildrenCountMap
					.get(childElem2Name));
			childElem2NameCount++;

			String key = childElem2Name + "_" + childElem2NameCount;

			if (elem1ChildrenMap.containsKey(key)) {
				org.w3c.dom.Element childElem1 = elem1ChildrenMap.get(key);
				merged = mergeDOM(childElem1, childElem2) || merged;
			} else {
				Node nodeClone = childNode2.cloneNode(true);
				elem1.getOwnerDocument().adoptNode(nodeClone);
				elem1.appendChild(nodeClone);
				merged = true;
			}

			elem2ChildrenCountMap.put(childElem2Name, childElem2NameCount);
		}

		return merged;
	}

	/**
	 * Clona un DOM eliminando namespaces y prefijos de los nodos, y quitando un
	 * sufijo que inicia con "_" del nombre del nodo raiz si existe
	 * (workaround).
	 * 
	 * @param document
	 *            el DOM a clonar
	 * @return el nuevo DOM
	 */
	public static Document createNormalizedCopy(Document document)
			throws BusinessException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader;

		try {
			loader = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_DOCUMENT_BUILDER_INSTANCE, e
					.getMessage());
		}

		Document newDocument = loader.newDocument();

		String rootNodeName = document.getDocumentElement().getLocalName();
		int uIdx = rootNodeName.indexOf('_');
		if (uIdx != -1) {
			rootNodeName = rootNodeName.substring(0, uIdx);
		}

		org.w3c.dom.Element root = newDocument.createElement(rootNodeName);

		newDocument.appendChild(root);

		cloneNode(document.getDocumentElement(), root, newDocument);

		/*
		 * NodeList nodeList = document.getDocumentElement().getChildNodes();
		 * for (int i = 0; i < nodeList.getLength(); i++) { Node node =
		 * nodeList.item(i); if (node instanceof org.w3c.dom.Element) { Node
		 * nodeClone = node.cloneNode(true);
		 * 
		 * newDocument.adoptNode(nodeClone); root.appendChild(nodeClone); } }
		 */

		return newDocument;
	}

	private static void cloneNode(org.w3c.dom.Element origen,
			org.w3c.dom.Element destino, Document documentoDestino) {

		NodeList nodeList = origen.getChildNodes();
		if (nodeList.getLength() > 0) {

			// Se obtiene el texto del primer hijo
			Node primerHijo = nodeList.item(0);
			int inicio = 0;
			if (primerHijo instanceof org.w3c.dom.Text) {
				destino.setTextContent(primerHijo.getTextContent());
				inicio = 1;

			}

			for (int i = inicio; i < nodeList.getLength(); i++) {
				Node nodeHijoOrigen = nodeList.item(i);
				if (nodeHijoOrigen instanceof org.w3c.dom.Element) {

					org.w3c.dom.Element nodoHijoDestino = documentoDestino
							.createElement(((org.w3c.dom.Element) nodeHijoOrigen)
									.getNodeName());

					cloneNode((org.w3c.dom.Element) nodeHijoOrigen,
							nodoHijoDestino, documentoDestino);

					destino.appendChild(nodoHijoDestino);

				} else {
					// solo se evaluan los nodos que pueden tener hijos
				}
			}
		}

		

	}

	/**
	 * Crea un DOM a partir de un xml dado en un string.
	 * 
	 * @param xml
	 *            el xml
	 * @return el DOM
	 */
	public static Document buildDOM(String xml) throws BusinessException {
		LSParser builder = getDOMImplementation().createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput lsInput = getDOMImplementation().createLSInput();
		lsInput.setStringData(xml);
		Document document = builder.parse(lsInput);
		return document;
	}

	/**
	 * Crea un DOM a partir de un xml dado en un arreglo de bytes. Se asume que
	 * el encoding es UTF-8.
	 * 
	 * @param xml
	 *            el xml
	 * @return el DOM
	 */
	public static Document buildDOM(byte[] xml) throws BusinessException {
		return buildDOM(xml, UTF8_ENCODING);
	}

	/**
	 * Crea un DOM a partir de un xml dado en un arreglo de bytes con el
	 * encoding indicado.
	 * 
	 * @param xml
	 *            el xml
	 * @param encoding
	 *            el encoding del xml
	 * @return el DOM
	 */
	public static Document buildDOM(byte[] xml, String encoding)
			throws BusinessException {
		ByteArrayInputStream bais = new ByteArrayInputStream(xml);

		try {
			Document doc = buildDOM(bais, encoding);
			return doc;
		} finally {
			try {
				bais.close();
			} catch (Exception e) {
				LOGGER.error("Error cerrando input stream", e);
			}
		}
	}

	/**
	 * Serializa el DOM dado a un arreglo de bytes con encoding UTF-8.
	 * 
	 * @param document
	 *            el DOM
	 * @return el xml con encoding UTF-8
	 */
	public static byte[] serializeToByteArray(Document document)
			throws BusinessException {
		return serializeToByteArray(document, UTF8_ENCODING);
	}

	/**
	 * Serializa el DOM dado a un arreglo de bytes con el encoding dado.
	 * 
	 * @param document
	 *            el DOM
	 * @param encoding
	 *            el encoding del xml resultante
	 * @return el xml
	 */
	public static byte[] serializeToByteArray(Document document, String encoding)
			throws BusinessException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			serialize(document, baos, encoding);
			return baos.toByteArray();
		} finally {
			try {
				baos.close();
			} catch (Exception e) {
				LOGGER.error("Error cerrando output stream.", e);
			}
		}
	}

	/**
	 * Serializa el DOM dado a un string.
	 * 
	 * @param document
	 *            el DOM
	 * @return el xml
	 */
	public static String serializeToString(Document document)
			throws BusinessException {
		LSSerializer lsSerializer = getDOMImplementation().createLSSerializer();
		return lsSerializer.writeToString(document);
	}

	/**
	 * Serializa el DOM dado a un <code>OutputStream</code> usando el encoding
	 * UTF-8.
	 * 
	 * @param document
	 *            el DOM
	 * @param os
	 *            el <code>OutputStream</code> en donde se escribirá el
	 *            documento
	 * @return el xml
	 */
	public static void serialize(Document document, OutputStream os)
			throws BusinessException {
		serialize(document, os, UTF8_ENCODING);
	}

	/**
	 * Serializa el DOM dado a un <code>OutputStream</code> usando el encoding
	 * indicado.
	 * 
	 * @param document
	 *            el DOM
	 * @param os
	 *            el <code>OutputStream</code> en donde se escribirá el
	 *            documento
	 * @param encoding
	 *            el encoding del xml resultante
	 * @return el xml
	 */
	public static void serialize(Document document, OutputStream os,
			String encoding) throws BusinessException {

		LSSerializer lsSerializer;

		try {
			// Esto es un workaround, El serializer default incluye una
			// declaración del namespace xml que es
			// innecesaria y a word no le gusta.
			lsSerializer = (LSSerializer) Class.forName(
					"org.apache.xml.serialize.DOMSerializerImpl").newInstance();
		} catch (InstantiationException e) {
			LOGGER.error("No se pudo instanciar el serializer", e);
			throw new BusinessException(ERROR_SERIALIZER_INSTANCE, e
					.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.error("No se pudo instanciar el serializer", e);
			throw new BusinessException(ERROR_SERIALIZER_INSTANCE, e
					.getMessage());
		} catch (ClassNotFoundException e) {
			LOGGER.error("No se pudo instanciar el serializer", e);
			throw new BusinessException(ERROR_SERIALIZER_INSTANCE, e
					.getMessage());
		}

		LSOutput lsOutput = getDOMImplementation().createLSOutput();
		lsOutput.setEncoding(encoding);
		lsOutput.setByteStream(os);
		lsSerializer.write(document, lsOutput);
	}

	/**
	 * Inserta el tag TAG_ID_DOCUMENTO_PLANTILLA en la raíz del xml y regresa el
	 * xml actualizado forma de cadena.
	 * 
	 * @param xml
	 * @param idDocumentoPlantilla
	 *            - valor del tag a insertar.
	 * @return xml con tag insertado
	 */
	public static String insertarIdPlantilla(String xml,
			String idDocumentoPlantilla) {

		InputSource source = new InputSource(new StringReader(xml));
		String xmlConIdDocumentoPlantilla = "";
		SAXBuilder builder = new SAXBuilder();

		try {
			org.jdom.Document doc = builder.build(source);
			org.jdom.Element raiz = doc.getRootElement();
			org.jdom.Element etiquetaNueva = new org.jdom.Element(
					TAG_ID_DOCUMENTO_PLANTILLA);
			etiquetaNueva.setText(idDocumentoPlantilla);
			raiz.addContent(etiquetaNueva);
			Format format = Format.getPrettyFormat();
			XMLOutputter xmloutputter = new XMLOutputter(format);
			xmlConIdDocumentoPlantilla = xmloutputter.outputString(doc);

		} catch (JDOMException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_INSERTAR_ID_PLANTILLA, e
					.getMessage());
		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_INSERTAR_ID_PLANTILLA, e
					.getMessage());
		}

		return xmlConIdDocumentoPlantilla;
	}

	/**
	 * Obtiene el contenido del tag TAG_ID_DOCUMENTO_PLANTILLA del xml y lo
	 * regresa en forma de String.
	 * 
	 * @param xml
	 * @return contenido del tag , regresa nulo en caso del que no se encuentre
	 *         el tag en el xml.
	 */
	public static String obtenerIdPlantilla(String xml) {
		InputSource source = new InputSource(new StringReader(xml));
		String idDocumentoPlantilla = null;
		SAXBuilder builder = new SAXBuilder();

		try {
			org.jdom.Document doc = builder.build(source);
			org.jdom.Element raiz = doc.getRootElement();
			org.jdom.Element tagIdDocumentoPlantilla = raiz
					.getChild(TAG_ID_DOCUMENTO_PLANTILLA);
			if (tagIdDocumentoPlantilla != null) {
				idDocumentoPlantilla = tagIdDocumentoPlantilla.getText();
			}
		} catch (JDOMException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_OBTENER_ID_PLANTILLA, e
					.getMessage());
		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_OBTENER_ID_PLANTILLA, e
					.getMessage());
		}
		return idDocumentoPlantilla;
	}

	public static void dumpToFile(String file, InputStream is) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int read;
			while ((read = is.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}

			dumpToFile(file, baos.toByteArray());

			is.reset();
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	public static void dumpToFile(String file, byte[] bytes) {

		FileOutputStream fos = null;
		try {
			File f = new File(file);
			if (f.exists()) {
				if (!f.delete()) {
					LOGGER.error("No se pudo borrar el archivo: "
							+ f.getAbsolutePath());
				}
			}
			fos = new FileOutputStream(f);
			fos.write(bytes);

			LOGGER.info("FILE DUMPED: " + f.getAbsolutePath());
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error("No se pudo cerrar el stream", e);
				}
			}
		}
	}

	public static void dumpToFile(String file, Document document) {

		dumpToFile(file, serializeToByteArray(document));
	}

	public static void actualizarValor(Document document, String path,
			String valor) {

		XPath xpath = XPathFactory.newInstance().newXPath();

		XPathExpression expr;
		try {
			expr = xpath.compile(path);

			Object result = expr.evaluate(document, XPathConstants.NODE);

			NodeList nodes = (NodeList) result;

			if (0 <= nodes.getLength()) {

				Node elemento = (Node) result;
				LOGGER.info("nodo " + elemento.getTextContent());
				elemento.setTextContent(valor);
				LOGGER.info("nodo " + elemento.getTextContent());

			} else {
				for (int i = 0; i < nodes.getLength(); i++) {
					LOGGER.info("item " + nodes.item(i).getTextContent());
					nodes.item(i).setTextContent(valor);
					LOGGER.info("item " + nodes.item(i).getTextContent());

				}
			}

		} catch (XPathExpressionException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_AL_ACTUALIZAR_XML, e.getMessage());
		}

	}
}
