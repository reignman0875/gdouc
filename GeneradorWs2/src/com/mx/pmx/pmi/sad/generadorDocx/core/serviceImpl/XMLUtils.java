package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;

public class XMLUtils {
	private static final String TAG_ID_DOCUMENTO_PLANTILLA = "idDocumentoPlantilla";

	private static final String ERROR_DOM_IMPL_REGISTRY_INSTANCE = "ERROR_DOM_IMPL_REGISTRY_INSTANCE";

	private static final String ERROR_DOCUMENT_BUILDER_INSTANCE = "ERROR_DOCUMENT_BUILDER_INSTANCE";

	private static final String ERROR_SERIALIZER_INSTANCE = "ERROR_SERIALIZER_INSTANCE";

	private static final String ERROR_OBTENER_ID_PLANTILLA = "ERROR_OBTENER_ID_PLANTILLA";

	private static final String ERROR_INSERTAR_ID_PLANTILLA = "ERROR_INSERTAR_ID_PLANTILLA";

	private static final String ERROR_XML_ARCHIVO_NO_EXISTE = "ERROR_XML_ARCHIVO_NO_EXISTE";

	private static final String ERROR_AL_ACTUALIZAR_XML = "ERROR_AL_ACTUALIZAR_XML";
	static {
		//System.setProperty(DOMImplementationRegistry.PROPERTY,"org.apache.xerces.dom.DOMImplementationSourceImpl");
		System.setProperty(DOMImplementationRegistry.PROPERTY,"org.apache.xerces.dom.DOMXSImplementationSourceImpl");
	}

	/***
	 * IOMH code
	 * @return
	 * @throws BusinessException
	 */
	public static DOMImplementationLS getDOMImplementation()
			throws Exception {

//		if (domImpl != null) {
//			return domImpl;
//		}
			    	    	    
		

		try {
			DOMImplementationRegistry registry=DOMImplementationRegistry.newInstance();
		    DOMImplementationLS impl=(DOMImplementationLS)registry.getDOMImplementation("LS");
		    return impl;
		} catch (ClassCastException e) {
			e.printStackTrace();
			throw e;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw e;
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw e;
		}

//		domImpl = (DOMImplementationLS) registry.getDOMImplementation("LS");
//		return impl;
	}
	public static Document buildDOM(String xml) throws Exception {
		LSParser builder = getDOMImplementation().createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput lsInput = getDOMImplementation().createLSInput();
		lsInput.setStringData(xml);
		Document document = builder.parse(lsInput);
		return document;
	}
}
