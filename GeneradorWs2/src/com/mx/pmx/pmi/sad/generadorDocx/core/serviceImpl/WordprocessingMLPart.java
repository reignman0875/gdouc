package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;

/**
 * Representa la "parte" de del xml principal en un docx. Este es el xml que
 * tiene el contenido del docx.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public class WordprocessingMLPart {

	public static final String WORDPROCESSINGML_NS_URI = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";

	public static final String WORDPROCESSINGML_NS_PREFIX = "w";

	public static final String RELATIONSHIPS_NS_URI = "http://schemas.openxmlformats.org/officeDocument/2006/relationships";

	public static final String RELATIONSHIPS_NS_PREFIX = "r";

	public static final String DRAWINGML_NS_URI = "http://schemas.openxmlformats.org/drawingml/2006/main";

	public static final String DRAWINGML_NS_PREFIX = "a";

	public static final String WPDRAWING_NS_URI = "http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing";

	public static final String WPDRAWING_NS_PREFIX = "wp";

	public static final String PICTURE_NS_URI = "http://schemas.openxmlformats.org/drawingml/2006/picture";

	public static final String PICTURE_NS_PREFIX = "pic";

	public static final String TAG_ELEMENT = "tag";

	public static final String BLIP_ELEMENT = "blip";

	public static final String TBL_ELEMENT = "tbl";

	public static final String TR_ELEMENT = "tr";

	public static final String TC_ELEMENT = "tc";

	public static final String P_ELEMENT = "p";

	public static final String SDT_ELEMENT = "sdt";

	public static final String SHOWING_PLCHDR_ELEMENT = "showingPlcHdr";

	public static final String SDTPR_ELEMENT = "sdtPr";

	public static final String PICTURE_ELEMENT = "picture";

	public static final String ALIAS_ELEMENT = "alias";

	public static final String SDT_CONTENT_ELEMENT = "sdtContent";

	public static final String R_ELEMENT = "r";

	public static final String T_ELEMENT = "t";

	public static final String VAL_ATTRIBUTE = "val";

	public static final String CONTAINER_KEY = "containter";

	public static final String CONTAINEE_KEY = "containee";

	public static final String PICTURE_KEY = "picture";

	public static final String XPRESSO_VARDEFTREE_TAG = "VarDefTree";
	
	public static final String XPRESSO_DATAELEMENT_TAG = "DataElement";

	public static final String XPRESSO_ID_TAG = "Id";

	public static final String XPRESSO_VARDEFTYPE_TAG = "VarDefType";

	public static final String XPRESSO_PARENT_ID_TAG = "ParentId";

	public static final String XPRESSO_NAME_TAG = "Name";
	
	public static final String XPRESSO_VARDEFID_TAG = "VarDefID";

	public static final String XPRESSO_XMLMAPPING_TAG = "XMLMapping";

	public static final String CONTENT_LOCKED_VALUE1 = "sdtContentLocked";

	public static final String CONTENT_LOCKED_VALUE2 = "contentLocked";

	public final XPathExpression contentCtrlAliasXpath;

	private final XPathExpression contentCtrlTextXpath;

	private final XPathExpression contentCtrlLockXpath;

	private final XPathExpression templateRowXpath;

	private final XPathExpression contentCtrlTagXpath;
	private final XPathExpression contentCtrlTitleXpath;

	private final XPathExpression contentCtrlBlipXpath;

	private Document document;

	private XPath wpmDocumentXpath;

	private XPathFactory xpathFactory;

	private static Log LOGGER = LogFactory.getLog(WordprocessingMLPart.class);

	private List<Element> contentControls = new LinkedList<Element>();

	private Map<String, String> mappingImagesRefIdsMap = new HashMap<String, String>();
	
	private static final String ERROR_XPATH_COMPILATION = "ERROR_XPATH_COMPILATION";

	private enum AccessMode {
		UPDATE_CONTROLS, READ_CONTROLS
	};

	/**
	 * Crea una instancia de esta clase con el DOM del xml principal de un docx.
	 * 
	 * @param document
	 *            el DOM del xml principal
	 */
	public WordprocessingMLPart(Document document) throws BusinessException {

		this.document = document;

		xpathFactory = XPathFactory.newInstance();

		Map<String, String> prefixNamespaceMap = new HashMap<String, String>();
		prefixNamespaceMap.put(WORDPROCESSINGML_NS_PREFIX, WORDPROCESSINGML_NS_URI);
		prefixNamespaceMap.put(DRAWINGML_NS_PREFIX, DRAWINGML_NS_URI);
		prefixNamespaceMap.put(RELATIONSHIPS_NS_PREFIX, RELATIONSHIPS_NS_URI);
		prefixNamespaceMap.put(WPDRAWING_NS_PREFIX, WPDRAWING_NS_URI);
		prefixNamespaceMap.put(PICTURE_NS_PREFIX, PICTURE_NS_URI);

		wpmDocumentXpath = xpathFactory.newXPath();
		wpmDocumentXpath.setNamespaceContext(new FixedNamespaceContext(prefixNamespaceMap));

		try {
			contentCtrlAliasXpath = wpmDocumentXpath.compile("w:sdtPr/w:alias/@w:val");
			contentCtrlLockXpath = wpmDocumentXpath.compile("w:sdtPr/w:lock/@w:val");
			contentCtrlTextXpath = wpmDocumentXpath.compile("w:sdtContent//w:r/w:t");
			// contentCtrlNodeXpath = wpmDocumentXpath.compile("w:sdt");
			templateRowXpath = wpmDocumentXpath.compile("w:sdtContent/w:tbl/w:tr");
			// headerRowXpath =
			// wpmDocumentXpath.compile("w:sdtContent/w:tbl/w:sdt/w:sdtContent/w:tr");
			contentCtrlTagXpath = wpmDocumentXpath.compile("w:sdtPr/w:tag/@w:val");
			contentCtrlTitleXpath = wpmDocumentXpath.compile("w:sdtPr/w:alias/@w:val");
			contentCtrlBlipXpath = wpmDocumentXpath
					.compile("w:sdtContent//w:drawing//a:graphic/a:graphicData/pic:pic/pic:blipFill/a:blip/@r:embed");

		} catch (XPathExpressionException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_XPATH_COMPILATION, e.getMessage());
		}

		extractContentControls();
	}

	/**
	 * Regresa el DOM del xml principal.
	 * 
	 * @return el DOM del xml principal
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Navega el DOM buscando los controles de contenido.
	 */
	private void extractContentControls() throws BusinessException {

		NodeVisitor contentControlsVisitor = new NodeVisitor() {

			public void visit(Node node) {

				if (!isContentControl(node))
					return;

				Node parent = node;
				while ((parent = parent.getParentNode()) != null) {
					if (isContentControl(parent)) {
						parent.setUserData(CONTAINER_KEY, Boolean.TRUE, null);
						node.setUserData(CONTAINEE_KEY, Boolean.TRUE, null);
						break;
					}
				}

				contentControls.add((Element) node);
			}
		};

		NodeVisitor pictureVisitor = new NodeVisitor() {

			public void visit(Node node) {

				if (!isContentControl(node))
					return;

				Element elem = (Element) node;
				NodeList nodeList;

				nodeList = elem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, SDTPR_ELEMENT);
				if (nodeList.getLength() > 0) {
					Element sdtPrElem = (Element) nodeList.item(0);
					nodeList = sdtPrElem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, PICTURE_ELEMENT);
					if (nodeList.getLength() > 0) {
						elem.setUserData(PICTURE_KEY, Boolean.TRUE, null);

						try {
							String title = xpathSafeStringEval(contentCtrlTitleXpath, elem);
							String tag = xpathSafeStringEval(contentCtrlTagXpath, elem);
							
							String mapping = title.trim() + tag.trim();
							String rId = (String) contentCtrlBlipXpath.evaluate(elem, XPathConstants.STRING);

							mappingImagesRefIdsMap.put(mapping, rId);
						} catch (XPathExpressionException e) {
							LOGGER.error("Error al intentar extraer las imágenes controles de contenido.", e);
						}
					}
				}
			}

		};

		DOMUtils.traverseElements(document, contentControlsVisitor, pictureVisitor);
	}
	
	private static String xpathSafeStringEval(XPathExpression xpath, Element elem)
	{
		try {
			String str = (String) xpath.evaluate(elem, XPathConstants.STRING);
			return (str == null ? "" : str);
		} catch (XPathExpressionException e) {
			return "";
		}
	}

	public Set<String> getWritablePaths(Document xpressoModel) {

		Set<String> writablePaths = new HashSet<String>();

		Map<String, XPressoVarDef> mappings = new HashMap<String, XPressoVarDef>();
		extractMappings(xpressoModel, mappings);

		for (Element contentControl : contentControls) {
			try {
				String alias = (String) contentCtrlAliasXpath.evaluate(contentControl, XPathConstants.STRING);
				alias = alias.trim();

				if (mappings.containsKey(alias)) {
					boolean contentLocked = isContentControlLocked(contentControl);

					XPressoVarDef varDef = mappings.get(alias);

					if (!contentLocked) {
						if (XPressoVarDef.SIMPLE_TYPE.equals(varDef.getType())) {
							writablePaths.add(varDef.getXmlMapping());
						} else {
							for (XPressoVarDef childVarDef : varDef.getElements()) {
								writablePaths.add(childVarDef.getXmlMapping());
							}
						}
					}
				}

			} catch (XPathExpressionException e) {
				LOGGER.error("Error obtieniendo el alias de content control en getWritablePaths", e);
			}
		}

		return writablePaths;
	}

	/**
	 * Regresa un mapa conteniendo como llaves los mapeos xpath y como valores
	 * los ids de relación de las imágenes controles de contenido.
	 * 
	 * @return un mapa conteniendo como llaves los mapeos xpath y como valores
	 *         los ids de relación de las imágenes controles de contenido.
	 */
	public Map<String, String> getMappingImagesRefIdsMap() {
		return mappingImagesRefIdsMap;
	}

	/**
	 * Actualiza el documento aplicando los mapeos de XPresso contenidos en
	 * <code>xpressoModelDocument</code> a los datos dados en el DOM
	 * <code>dataDocument</code>.
	 * 
	 * @param dataDocument
	 *            el documento de datos.
	 * @param xpressoModelDocument
	 *            el documento con los mapeos de XPresso
	 */
	public void updateControls(Document dataDocument, Document xpressoModelDocument) {
		doAccess(dataDocument, xpressoModelDocument, null, AccessMode.UPDATE_CONTROLS);
	}

	/**
	 * Lee los controles de contenido y actualiza el documento de datos en los
	 * paths indicados por <code>writablePaths</code>.
	 * 
	 * @param dataDocument
	 *            DOM del xml de datos. Será actualizado con el contenido de los
	 *            content controls
	 * @param xpressoModelDocument
	 *            DOM del modelo de xpresso el cual contiene las definiciones de
	 *            las variables y sus mapeos
	 * @param writablePaths
	 *            paths del xml de datos que se pueden modificar. Si es null
	 *            cualquier path se puede modificar
	 */
	public void readControls(Document dataDocument, Document xpressoModelDocument, Set<String> writablePaths) {
		doAccess(dataDocument, xpressoModelDocument, writablePaths, AccessMode.READ_CONTROLS);
	}

	/**
	 * Realiza el acceso a los controles de contenido de acuerdo a
	 * <code>accessMode</code>.
	 * 
	 * @param dataDocument
	 *            DOM del xml de datos. Será actualizado con el contenido de los
	 *            content controls
	 * @param xpressoModelDocument
	 *            DOM del modelo de xpresso el cual contiene las definiciones de
	 *            las variables y sus mapeos
	 * @param writablePaths
	 *            paths del xml de datos que se pueden modificar. Si es null
	 *            cualquier path se puede modificar
	 * @param accessMode
	 *            indica el tipo de acceso que se hace sobre los content
	 *            controls: {@link AccessMode#READ_CONTROLS} o
	 *            {@link AccessMode#UPDATE_CONTROLS}
	 */
	public void doAccess(Document dataDocument, Document xpressoModelDocument, Set<String> writablePaths,
			AccessMode accessMode) {

		Map<String, XPressoVarDef> xpressoMappings = new HashMap<String, XPressoVarDef>();

		extractMappings(xpressoModelDocument, xpressoMappings);

		XPath dataDocumentXpath = xpathFactory.newXPath();
		dataDocumentXpath.setNamespaceContext(new UniversalNamespaceContext(dataDocument));

		for (Element contentControl : contentControls) {

			boolean isContainer = Boolean.TRUE.equals(contentControl.getUserData(CONTAINER_KEY));
			boolean isContainee = Boolean.TRUE.equals(contentControl.getUserData(CONTAINEE_KEY));
			boolean isPicture = Boolean.TRUE.equals(contentControl.getUserData(PICTURE_KEY));

			boolean success = true;

			if (isPicture) {
				if (accessMode == AccessMode.UPDATE_CONTROLS) {
					success = updatePictureContentControl(contentControl, dataDocument, dataDocumentXpath,
							xpressoMappings);
				}
			} else if (!isContainer && !isContainee) {
				success = accessSimpleContentControl(contentControl, dataDocument, dataDocumentXpath, xpressoMappings,
						writablePaths, accessMode);
			} else if (isContainer) {
				success = accessCompoundContentControl(contentControl, dataDocument, dataDocumentXpath,
						xpressoMappings, writablePaths, accessMode);
			}

			if (!success) {
				LOGGER.warn("No se pudo accesar el " + "content control con  el texto '"
						+ contentControl.getTextContent().trim() + "'");
			}
		}
	}

	/**
	 * Método de ayuda. Determina si el nodo del DOM dado es un control de
	 * contenido.
	 * 
	 * @param node
	 *            el nodo del DOM
	 * @return true si el nodo es un control de contenido, false en caso
	 *         contrario
	 */
	private boolean isContentControl(Node node) {
		return DOMUtils.isElement(node, WORDPROCESSINGML_NS_URI, SDT_ELEMENT);
	}

	/**
	 * Transforma una imágen control de contenido eliminando la imágen
	 * "placeholder" que Word coloca sobre la imágen.
	 * 
	 * @param contentControl
	 *            El control de contenido de la imágen
	 * @param dataDocument
	 *            El DOM del documento de datos
	 * @param dataDocumentXpath
	 *            El xpath del documento de datos
	 * @param mappings
	 *            Los mapeos xpath y sus variables de xpresso
	 * @return true si transformó el control de contenido false en caso
	 *         contrario
	 */
	private boolean updatePictureContentControl(Element contentControl, Document dataDocument, XPath dataDocumentXpath,
			Map<String, XPressoVarDef> mappings) {

		NodeList nodeList;
		nodeList = contentControl.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, SDTPR_ELEMENT);
		if (nodeList.getLength() > 0) {
			Element sdtPrElem = (Element) nodeList.item(0);
			nodeList = sdtPrElem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, SHOWING_PLCHDR_ELEMENT);
			if (nodeList.getLength() > 0) {
				Element showingPlcHdrElem = (Element) nodeList.item(0);
				sdtPrElem.removeChild(showingPlcHdrElem);
			}
		}

		return true;
	}

	/**
	 * Accesa un control de contenido simple. Esto es un control de contenido
	 * que no contiene una tabla y que no es una imágen. En estos controles de
	 * contenido solo es necesario insertar el texto obtenido del mapeo.
	 * 
	 * Si <code>accessMode</code> es {@link AccessMode#READ_CONTROLS}
	 * <code>dataDocument</code> es actualizado con contenido del control. Si
	 * <code>accessMode</code> es {@link AccessMode#UPDATE_CONTROLS} el
	 * contenido del control es actualizado con los datos en
	 * <code>dataDocument</code>.
	 * 
	 * @param contentControl
	 *            el control de contenido.
	 * @param dataDocument
	 *            DOM del documento de datos
	 * @param dataDocumentXpath
	 *            xpath del documento de datos
	 * @param mappings
	 *            Los mapeos xpath y sus variables de xpresso
	 * @param accessMode
	 *            indica si el contenido del control debe ser leido o
	 *            actualizado
	 * @param writablePaths
	 *            contiene los paths del documento de datos que pueden ser
	 *            actualizados si <code>accessMode</code> es
	 *            {@link AccessMode#READ_CONTROLS}, si es null se permiten todos
	 * @return true si el acceso tuvo éxito, false en caso contrario
	 */
	private boolean accessSimpleContentControl(Element contentControl, Document dataDocument, XPath dataDocumentXpath,
			Map<String, XPressoVarDef> mappings, Set<String> writablePaths, AccessMode accessMode) {

		try {
			String alias = (String) contentCtrlAliasXpath.evaluate(contentControl, XPathConstants.STRING);
			Node textNode = (Node) contentCtrlTextXpath.evaluate(contentControl, XPathConstants.NODE);

			alias = alias.trim();
			LOGGER.debug("alias: " + alias );
			LOGGER.debug("textNode: " + textNode );

			if (mappings.containsKey(alias)) {
				String xpathMapping = mappings.get(alias).getXmlMapping();

				Node valueNode = (Node) dataDocumentXpath.evaluate(xpathMapping, dataDocument, XPathConstants.NODE);

				LOGGER.debug("Xpath: " + xpathMapping );

				if (accessMode == AccessMode.UPDATE_CONTROLS) {
					if(textNode!=null&&valueNode!=null){
					String text = valueNode.getTextContent();
					if (text == null) {
						text = "";
					}
					textNode.setTextContent(text);
					}
					else
					{
						//El content control no tiene nodo para asignar texto.
						LOGGER.error("Xpath: " + xpathMapping + " del doc de datos no es escribible");
						return false;
					}
				} else if (accessMode == AccessMode.READ_CONTROLS) {
					if ((writablePaths == null) || writablePaths.contains(xpathMapping)) {
						String text = textNode.getTextContent();
						if (text == null) {
							text = "";
						}
						valueNode.setTextContent(text);
						LOGGER.debug("Xpath: " + xpathMapping + " del doc de datos fue actualizado");
					} else {
						LOGGER.debug("Xpath: " + xpathMapping + " del doc de datos no es escribible");
					}
				}

				return true;
			}
		} catch (XPathExpressionException e) {
			LOGGER.error("Error de xpath mientras se transformaba un control de contenido simple.", e);
		}

		return false;
	}

	/**
	 * Determina si el control de contenido es de solo lectura.
	 * 
	 * @param contentControl
	 *            el elemento control de contenido
	 * @return true si es de solo lectura, false en caso contrario
	 */
	private boolean isContentControlLocked(Element contentControl) {
		try {
			String lock = (String) contentCtrlLockXpath.evaluate(contentControl, XPathConstants.STRING);
			return (CONTENT_LOCKED_VALUE1.equals(lock) || CONTENT_LOCKED_VALUE2.equals(lock));
		} catch (XPathExpressionException e) {
			return false;
		}
	}

	/**
	 * Método de ayuda. Crea una representación en string del
	 * <code>NodeList</code> dado.
	 * 
	 * @param nodes
	 *            el <code>NodeList</code> que se va a convertir a string
	 * @return una cadena con una representación del <code>NodeList</code> dado
	 */
	private String convertToString(NodeList nodes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(n.getTextContent());
		}
		return sb.toString();
	}

	/**
	 * Transforma un control de contenido compuesto. Eso es un control de
	 * contenido que contiene una tabla.
	 * 
	 * Si <code>accessMode</code> es {@link AccessMode#READ_CONTROLS}
	 * <code>dataDocument</code> es actualizado con contenido del control. Si
	 * <code>accessMode</code> es {@link AccessMode#UPDATE_CONTROLS} el
	 * contenido del control es actualizado con los datos en
	 * <code>dataDocument</code>.
	 * 
	 * @param contentControl
	 *            El control de contenido
	 * @param dataDocument
	 *            el DOM del xml de datos
	 * @param dataDocumentXpath
	 *            el xpath del xml de datos
	 * @param mappings
	 *            Los mapeos xpath y sus variables de xpresso
	 * @param accessMode
	 *            indica si el contenido del control debe ser leido o
	 *            actualizado
	 * @param writablePaths
	 *            contiene los paths del documento de datos que pueden ser
	 *            actualizados si <code>accessMode</code> es
	 *            {@link AccessMode#READ_CONTROLS}, si es null se permiten todos
	 * @return true si el acceso tuvo exito, false en caso contrario
	 */
	private boolean accessCompoundContentControl(Element contentControl, Document dataDocument,
			XPath dataDocumentXpath, Map<String, XPressoVarDef> mappings, Set<String> writablePaths,
			AccessMode accessMode) {

		if (accessMode != AccessMode.UPDATE_CONTROLS) {
			// La lectura no está implementada para controles compuestos
			return true;
		}

		try {
			String alias = (String) contentCtrlAliasXpath.evaluate(contentControl, XPathConstants.STRING);

			if (mappings.containsKey(alias)) {
				XPressoVarDef varDef = mappings.get(alias);

				// Por cada variable que representa una culumna completa, al
				// evaluar
				// la expresión xpath se obtiene un NodeList con todos los
				// valores de las
				// celdas de esa columna.
				List<NodeList> dataCols = new LinkedList<NodeList>();

				int totalRows = 0;

				for (XPressoVarDef elemVarDef : varDef.getElements()) {
					NodeList dataColumn = (NodeList) dataDocumentXpath.evaluate(elemVarDef.getXmlMapping(),
							dataDocument, XPathConstants.NODESET);

					LOGGER.debug("Xpath: " + elemVarDef.getXmlMapping() + " Resultado: " + convertToString(dataColumn));

					dataCols.add(dataColumn);

					if (totalRows < dataColumn.getLength()) {
						totalRows = dataColumn.getLength();
					}
				}

				Element templateRowElem = (Element) templateRowXpath.evaluate(contentControl, XPathConstants.NODE);
				Element tableElem = (Element) templateRowElem.getParentNode();

				for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
					if (rowIndex == 0) {
						setTextsInRow(templateRowElem, dataCols, rowIndex);
					} else {
						Element newRowElem = (Element) templateRowElem.cloneNode(true);
						setTextsInRow(newRowElem, dataCols, rowIndex);
						tableElem.appendChild(newRowElem);
					}
				}

				return true;
			}
		} catch (XPathExpressionException e) {
			LOGGER.error("Error mientras se transformaba un control de contenido compuesto", e);
		}

		return false;
	}

	/**
	 * Coloca los valores de la fila de <code>dataColumns</code> con índice
	 * <code>rowIndex</code> en el elemento fila <code>rowElement</code>
	 * 
	 * @param rowElement
	 *            el elemento fila al que se le van a insertar el contenido de
	 *            sus celdas
	 * @param dataColumns
	 *            contiene todos los valores de todas las celdas de la tabla.
	 *            Cada NodeList contiene los nodos con los valores de todas las
	 *            celdas de una columna
	 * @param rowIndex
	 *            el indice de la fila
	 */
	private void setTextsInRow(Element rowElement, List<NodeList> dataColumns, int rowIndex) {

		NodeList tcNodes = rowElement.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, TC_ELEMENT);

		for (int c = 0; c < dataColumns.size(); c++) {

			NodeList dataColumn = dataColumns.get(c);

			if (rowIndex >= dataColumn.getLength()) {
				continue;
			}

			String textContent = dataColumn.item(rowIndex).getTextContent();
			if (textContent == null) {
				textContent = "";
			}

			if (c < tcNodes.getLength()) {
				Element tcElem = (Element) tcNodes.item(c);

				NodeList pNodes = tcElem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, P_ELEMENT);
				Element pElem;
				if (pNodes.getLength() > 0) {
					pElem = (Element) pNodes.item(0);
				} else {
					pElem = document.createElementNS(WORDPROCESSINGML_NS_URI, P_ELEMENT);
					pElem.setPrefix(WORDPROCESSINGML_NS_PREFIX);
					tcElem.appendChild(pElem);
				}

				NodeList rNodes = pElem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, R_ELEMENT);
				Element rElem;
				if (rNodes.getLength() > 0) {
					rElem = (Element) rNodes.item(0);
				} else {
					rElem = document.createElementNS(WORDPROCESSINGML_NS_URI, R_ELEMENT);
					rElem.setPrefix(WORDPROCESSINGML_NS_PREFIX);
					pElem.appendChild(rElem);
				}

				NodeList tNodes = rElem.getElementsByTagNameNS(WORDPROCESSINGML_NS_URI, T_ELEMENT);
				Element tElem;
				if (tNodes.getLength() > 0) {
					tElem = (Element) tNodes.item(0);
				} else {
					tElem = document.createElementNS(WORDPROCESSINGML_NS_URI, T_ELEMENT);
					tElem.setPrefix(WORDPROCESSINGML_NS_PREFIX);
					rElem.appendChild(tElem);
				}

				tElem.setTextContent(textContent);
			}
		}
	}
	
	static class ExtractMappingsVisitor implements NodeVisitor {
		
		private Map<String, XPressoVarDef> mappings;
		
		private Map<String, XPressoVarDef> idMappings = new HashMap<String, XPressoVarDef>();
		
		public ExtractMappingsVisitor(final Map<String, XPressoVarDef> mappings) {
			this.mappings = mappings;
		}

		public void visit(Node node) {
			
			if ((node.getNodeType() == Node.ELEMENT_NODE) && XPRESSO_DATAELEMENT_TAG.equals(node.getNodeName()))
			{
				Element elem = (Element) node;
				NodeList nameList = elem.getElementsByTagName(XPRESSO_NAME_TAG);
				NodeList idList = elem.getElementsByTagName(XPRESSO_VARDEFID_TAG);
				
				if ((nameList.getLength() == 1) && (idList.getLength() == 1))
				{
					String id = idList.item(0).getTextContent().trim();
					String name = nameList.item(0).getTextContent().trim();
					
					if (idMappings.containsKey(id))
					{
						mappings.put(name, idMappings.get(id));
					}
					else
					{
						XPressoVarDef varDef = new XPressoVarDef();
						varDef.setId(id);
						idMappings.put(id, varDef);
					}
				}
			}
			else if ((node.getNodeType() == Node.ELEMENT_NODE) && XPRESSO_VARDEFTREE_TAG.equals(node.getNodeName()))
			{
				Element elem = (Element) node;

				NodeList idList = elem.getElementsByTagName(XPRESSO_ID_TAG);
				NodeList nameList = elem.getElementsByTagName(XPRESSO_NAME_TAG);
				NodeList mappingList = elem.getElementsByTagName(XPRESSO_XMLMAPPING_TAG);
				NodeList typeList = elem.getElementsByTagName(XPRESSO_VARDEFTYPE_TAG);
				NodeList parentIdList = elem.getElementsByTagName(XPRESSO_PARENT_ID_TAG);

				if ((idList.getLength() == 1) && (nameList.getLength() == 1)) {

					String id = idList.item(0).getTextContent().trim();
					String name = nameList.item(0).getTextContent().trim();
					String mapping = (mappingList.getLength() > 0 ? mappingList.item(0).getTextContent().trim()
							: null);
					String type = (typeList.getLength() > 0 ? typeList.item(0).getTextContent().trim() : null);
					String parentId = (parentIdList.getLength() > 0 ? parentIdList.item(0).getTextContent().trim()
							: null);
					
					XPressoVarDef varDef;
					if (idMappings.containsKey(id))
					{
						varDef = idMappings.get(id);
					}
					else
					{
						varDef = new XPressoVarDef();
						idMappings.put(id, varDef);
					}

					varDef.setId(id);
					varDef.setName(name);
					varDef.setXmlMapping(mapping);
					varDef.setType(type);

					if ((parentId != null) && idMappings.containsKey(parentId)) {
						idMappings.get(parentId).getElements().add(varDef);
					}

					mappings.put(name, varDef);
				}
			}
		}
		
	}

	/**
	 * Extrae las variables de xpresso con sus respectivos mapeos.
	 * 
	 * @param xpressoModel
	 *            el xml de XPresso que contiene las definiciones de las
	 *            variables y sus mapeos
	 * @param mappings
	 *            un mapa en el que se van a insertar los mapeos (llaves) y las
	 *            definiciones de variables (valores) de xpresso encontrados
	 */
	private void extractMappings(Document xpressoModel, Map<String, XPressoVarDef> mappings) {
		NodeVisitor nodeVisitor = new ExtractMappingsVisitor(mappings);
		DOMUtils.traverseElements(xpressoModel, nodeVisitor);
	}
}
