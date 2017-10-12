package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.w3c.dom.Document;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoXMLDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocxDocumentDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.ParametrosDocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.ParametrosDocumentoXMLExistenteDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.UsuarioDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.DocumentumHelperService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarPlantilla;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.PoolPlantilla;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.TransformarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.XMLUtils;

public class TransformarDocumentoServiceImpl implements
		TransformarDocumentoService {
	private static final Log LOGGER = LogFactory
			.getLog(TransformarDocumentoServiceImpl.class);
	// servicio para guardar Documentos
	//private GestionarDocumentoService gestionarDocumentoService;
	private DocumentumHelperService documentumHelperService;
//	private PoolPlantilla poolPlantilla;
	private GestionarPlantilla gestionarPlantilla;

	private static final String CABINET_DEFAULT_FINALES = "DOCS_FINAL";
	private static final String CABINET_DEFAULT_DRAFT = "DOCS_DRAFT";
	private static final String NOMBRE_DEFAULT_XML = "documentoXML";
	private static final String SUFIJO_XML = ".xml";

	private static final String NOMBRE_DEFAULT_DOCX = "documentoDOCX";
	private static final String SUFIJO_DOCX = ".docx";
	
	private static final String NOMBRE_DEFAULT_PDF = "documentoPDF";
	private static final String SUFIJO_PDF = ".pdf";

	private static final String ERROR_XML_SIN_ID_PLANTILLA = "ERROR_XML_SIN_ID_PLANTILLA";
	private static final String ERROR_XML_SIN_CONTENIDO = "ERROR_XML_SIN_CONTENIDO";
	private static final String ERROR_EN_LECTURA_XML = "ERROR_EN_LECTURA_XML";
	private static final String ERROR_FALLO_AL_CLONAR_PLANTILLA = "ERROR_FALLO_AL_CLONAR_PLANTILLA";
	private static final String ERROR_EN_ESCRITURA_DOCX = "ERROR_EN_ESCRITURA_DOCX";
	private static final String ERROR_INESPERADO_CREACION_DOCX = "ERROR_INESPERADO_CREACION_DOCX";

	/*
	 * private static final SimpleDateFormat FORMATO_FECHA_HORA = new
	 * SimpleDateFormat( "ddMMyyyyhhmmss");
	 */
	private static final String ERROR_EN_ESCRITURA_XML = "ERROR_EN_ESCRITURA_XML";
	private static final String ERROR_EN_LECTURA_DOCX = "ERROR_EN_LECTURA_DOCX";

	public TransformarDocumentoServiceImpl() {
		// IOMH constructor
		documentumHelperService = new DocumentumHelperServiceImpl();
		gestionarPlantilla = new GestionarPlantillaImpl();
	
	}
//	public List<DocumentoXMLDto> crearDocumentoXML(UsuarioDto usuario,
//			List<ParametrosDocumentoDto> paramsDocumentoDto) {
//		LOGGER.info("entra a operacion");
//
//		List<DocumentoXMLDto> listaDocumentosXml = new ArrayList<DocumentoXMLDto>();
//		for (ParametrosDocumentoDto parametro : paramsDocumentoDto) {
//
//			DocumentoXMLDto documentoXML = crearDocumentoXML(usuario, parametro);
//			listaDocumentosXml.add(documentoXML);
//
//		}
//		return listaDocumentosXml;
//	}
//
//	private DocumentoXMLDto crearDocumentoXML(UsuarioDto usuario,
//			ParametrosDocumentoDto parametro,String userLT) {
//
//		DocumentoXMLDto documentoXML = new DocumentoXMLDto();
//		File temp = null;
//		try {
//			LOGGER.info(parametro.getIdDocumentoPlantilla());
//		
//
//			DocxDocumentDto plantilla=gestionarPlantilla.obtenerPlantilla(parametro.getIdDocumentoPlantilla());
//			Document documentoOriginal = XMLUtils.buildDOM(parametro
//					.getXmlDatos());
//			Document documentoNormalizado = XMLUtils
//					.createNormalizedCopy(documentoOriginal);
//
//			String xmlDatosNormalizado = XMLUtils
//					.serializeToString(documentoNormalizado);
//
//			String xmlConIdPlantilla = XMLUtils.insertarIdPlantilla(
//					xmlDatosNormalizado, parametro.getIdDocumentoPlantilla());
//
//			DocumentoDto documentoDto = new DocumentoDto();
//			String nombreDocumento = obtenerNombreDocumentoXML(parametro
//					.getNombreDocumento(), plantilla.getNombreDocumento());
//
//			String rutaDocumento = parametro.getRutaXml();
//
//
//			documentoDto.setRutaDocumento(rutaDocumento);
//
//			temp = File.createTempFile(nombreDocumento, SUFIJO_XML);
//			temp.deleteOnExit();
//			PrintWriter escritura = new PrintWriter(new FileWriter(temp));
//			escritura.print(xmlConIdPlantilla);
//			escritura.close();
//
//			documentoDto.setNombreDocumento(nombreDocumento);
//			documentoDto.setContenido(temp);
//
//
//
//			documentumHelperService.guardarDocumento(documentoDto, userLT);
//			documentoXML.setIdDocumento(documentoDto.getIdDocumento());
//			documentoXML.setNombreDocumento(documentoDto.getNombreDocumento());
//
//		} catch (BusinessException be) {
//
//			throw be;
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error(e);
//			throw new BusinessException(ERROR_INESPERADO_CREACION_DOCX, e
//					.getMessage());
//		}
//		return documentoXML;
//	}
	private DocumentoXMLDto crearDocumentoXML(UsuarioDto usuario,
			ParametrosDocumentoDto parametro,String userLT, Map<String, String> attr) {

		DocumentoXMLDto documentoXML = new DocumentoXMLDto();
		File temp = null;
		try {
			LOGGER.info(parametro.getIdDocumentoPlantilla());
		

			DocxDocumentDto plantilla=gestionarPlantilla.obtenerPlantilla(parametro.getIdDocumentoPlantilla());
			Document documentoOriginal = XMLUtils.buildDOM(parametro
					.getXmlDatos());
			Document documentoNormalizado = XMLUtils
					.createNormalizedCopy(documentoOriginal);

			String xmlDatosNormalizado = XMLUtils
					.serializeToString(documentoNormalizado);

			String xmlConIdPlantilla = XMLUtils.insertarIdPlantilla(
					xmlDatosNormalizado, parametro.getIdDocumentoPlantilla());

			DocumentoDto documentoDto = new DocumentoDto();
			String nombreDocumento = obtenerNombreDocumentoXML(parametro
					.getNombreDocumento(), plantilla.getNombreDocumento());

			String rutaDocumento = parametro.getRutaXml();


			documentoDto.setRutaDocumento(rutaDocumento);

			temp = File.createTempFile(nombreDocumento, SUFIJO_XML);
			temp.deleteOnExit();
			PrintWriter escritura = new PrintWriter(new FileWriter(temp));
			escritura.print(xmlConIdPlantilla);
			escritura.close();

			documentoDto.setNombreDocumento(nombreDocumento);
			documentoDto.setContenido(temp);



			documentumHelperService.guardarDocumento(documentoDto, userLT, attr);
			documentoXML.setIdDocumento(documentoDto.getIdDocumento());
			documentoXML.setNombreDocumento(documentoDto.getNombreDocumento());

		} catch (BusinessException be) {

			throw be;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			throw new BusinessException(ERROR_INESPERADO_CREACION_DOCX, e
					.getMessage());
		}
		return documentoXML;
	}
	
//
	private String obtenerNombreDocumentoXML(String nombreDocumentoParametro, String nombrePlantilla) {
		String nombreDocumento = null;
		LOGGER.info("nombreRecibido=" + nombreDocumentoParametro + ".");
		String idNegocio=nombreDocumentoParametro;
		nombrePlantilla=obtenerNombreArchivo(nombrePlantilla);
        //El nombre documento se forma nombrePlantilla+_+idNegocio+_+timestamp
		Date momentoActual = new Date();
		nombreDocumento = nombrePlantilla+"_"+idNegocio+"_"
		+ documentumHelperService.deDecimalABase62(momentoActual.getTime()) 
		+ SUFIJO_XML;
		LOGGER.info(nombreDocumento);
		return nombreDocumento;
	}
//
	private String obtenerNombreDocumentoDOCX(String nombreDocumentoParametro, String nombrePlantilla) {
		String nombreDocumento = null;
		String idNegocio=nombreDocumentoParametro;
		nombrePlantilla=obtenerNombreArchivo(nombrePlantilla);
        //El nombre documento se forma nombrePlantilla+_+idNegocio+_+timestamp
		Date momentoActual = new Date();
		nombreDocumento = nombrePlantilla+"_"+idNegocio+"_"
		+ documentumHelperService.deDecimalABase62(momentoActual.getTime()) 
		+ SUFIJO_DOCX;
		return nombreDocumento;
	}
//	
//	private String obtenerNombreDocumentoPDF(String nombreDocx) {
//		String nombreDocumento = null;
//		nombreDocx=obtenerNombreArchivo(nombreDocx);
//		
//		
//		// String fechaHora = FORMATO_FECHA_HORA.format(momentoActual);
//		
//			nombreDocumento = nombreDocx+SUFIJO_PDF;
//		
//		return nombreDocumento;
//	}
//   
//    
    private String obtenerNombreArchivo(String nombreArchivo) {
		StringTokenizer tokenizer=new StringTokenizer(nombreArchivo, ".");
		
		return tokenizer.nextElement().toString();
		
	}
//
	public List<DocumentoGeneradoDto> crearDocumentoDOCX(UsuarioDto usuario,
			List<ParametrosDocumentoDto> paramsDocumentoDto, List <Map<String, String>> contenidoTabla, String [] idTabla, boolean isTableOc,String userLT, Map<String, String> attr) {
//		LOGGER.info("entra a operacion");

		List<DocumentoGeneradoDto> listaDocumentosGenerados = new ArrayList<DocumentoGeneradoDto>();
		for (ParametrosDocumentoDto parametro : paramsDocumentoDto) {

			DocumentoGeneradoDto documentoGenerado = crearDocumentoDOCX(
					usuario, parametro, contenidoTabla, idTabla, isTableOc,userLT, attr );
			if(documentoGenerado.getDocumentoXML()==null){
				DocumentoXMLDto documentoXMLDto = new DocumentoXMLDto(); 
				documentoGenerado.setDocumentoXML(documentoXMLDto);
			}
			System.out.println("######DOCGENERADO:"+documentoGenerado.getIdDocumentoGenerado());
			listaDocumentosGenerados.add(documentoGenerado);
		}

		return listaDocumentosGenerados;
	}

//	private DocumentoGeneradoDto crearDocumentoDOCXDeDocumentoXML(
//			UsuarioDto usuario,
//			ParametrosDocumentoXMLExistenteDto parametroXMLExistente) {
//
//		DocumentoGeneradoDto documentoGenerado = null;
//		DocxDocumentDto plantilla = null;
//		String xmlCadena = null;
//		try {
//
//			String idDocumentoXml = parametroXMLExistente.getDocumentoXML()
//					.getIdDocumento();
//			xmlCadena = obtenerXmlCadena(idDocumentoXml);
//
//			String idDocumentoPlantilla = XMLUtils
//					.obtenerIdPlantilla(xmlCadena);
//			if (idDocumentoPlantilla != null) {
//				plantilla = poolPlantilla
//						.obtenerPlantilla(idDocumentoPlantilla);
//				DocxDocumentDto clonPlantilla = (DocxDocumentDto) plantilla
//						.clone();
//				Document dom = XMLUtils.buildDOM(xmlCadena);
//				DocxUtils.writeContentControls(clonPlantilla, dom,
//						documentumHelperService.getGestionarDocumentoService());
//				
//				DocumentoDto documentoDocx = crearDocxParaGuardarEnDocumentum(
//						usuario, parametroXMLExistente, clonPlantilla, plantilla.getNombreDocumento());
//				documentoDocx.addAtributo(
//						DocumentumHelperService.PROPIEDAD_VISIBLE,
//						DocumentumHelperService.VALOR_VISIBLE);
//				ArrayList<DocumentoDto> documentosVirtuales = new ArrayList<DocumentoDto>();
//				documentosVirtuales.add(documentoDocx);
//				documentumHelperService.guardarDocumentoVirtual(
//						idDocumentoXml, documentosVirtuales);
//				documentoGenerado = crearDocumentoGenerado(
//						parametroXMLExistente.getDocumentoXML(), documentoDocx);
//			} else
//
//			{
//				LOGGER.error(ERROR_XML_SIN_ID_PLANTILLA);
//				LOGGER.error("idDocumentoXml: " + idDocumentoXml);
//				throw new BusinessException(ERROR_XML_SIN_ID_PLANTILLA);
//			}
//
//		} catch (CloneNotSupportedException e) {
//			LOGGER.error(ERROR_FALLO_AL_CLONAR_PLANTILLA);
//			LOGGER.error("plantilla :" + plantilla);
//			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
//		}
//
//		return documentoGenerado;
//	}

	private DocumentoGeneradoDto crearDocumentoDOCXDeDocumentoXML(
			UsuarioDto usuario,
			ParametrosDocumentoXMLExistenteDto parametroXMLExistente,
			String idDocumentoPlantilla, String xmlCadena, List <Map<String, String>> contenidoTabla, String [] idTabla, boolean isTableOc,String userLT, Map<String, String> attr) {

		DocumentoGeneradoDto documentoGenerado = null;
		DocxDocumentDto plantilla = null;

		try {

			String idDocumentoXml = parametroXMLExistente.getDocumentoXML()
					.getIdDocumento();

			if (idDocumentoPlantilla != null) {
//				plantilla = poolPlantilla
//						.obtenerPlantilla(idDocumentoPlantilla);
				plantilla = gestionarPlantilla
						.obtenerPlantilla(idDocumentoPlantilla);
				DocxDocumentDto clonPlantilla = (DocxDocumentDto) plantilla
						.clone();
				Document dom = XMLUtils.buildDOM(xmlCadena);
				DocxUtils.writeContentControls(clonPlantilla, dom,
						documentumHelperService.getGestionarDocumentoService());
				DocumentoDto documentoDocx = crearDocxParaGuardarEnDocumentum(
						usuario, parametroXMLExistente, clonPlantilla,plantilla.getNombreDocumento());				
				//TODO: Funcionalidad Tabla Start
				if (isTableOc == true)
					documentoDocx.setContenido(actualizaTabla(documentoDocx.getContenido(),contenidoTabla,idTabla));
				//TODO: Funcionalidad Tabla END
				documentoDocx.addAtributo(
						DocumentumHelperService.PROPIEDAD_VISIBLE,
						DocumentumHelperService.VALOR_VISIBLE);
				ArrayList<DocumentoDto> documentosVirtuales = new ArrayList<DocumentoDto>();
				documentosVirtuales.add(documentoDocx);

				//TODO: IOMH Eliminando funcionalidad de guardarDocVirtual
//				documentumHelperService.guardarDocumentoVirtual(
//						idDocumentoXml, documentosVirtuales);
				documentumHelperService.guardarDocumento(
						documentoDocx,userLT, attr);
				documentumHelperService.borrarDocumento(idDocumentoXml);
				documentoGenerado = crearDocumentoGenerado(
						parametroXMLExistente.getDocumentoXML(), documentoDocx);
			} else

			{
				LOGGER.error(ERROR_XML_SIN_ID_PLANTILLA);
				LOGGER.error("idDocumentoXml: " + idDocumentoXml);
				throw new BusinessException(ERROR_XML_SIN_ID_PLANTILLA);
			}

		} catch (CloneNotSupportedException e) {
			LOGGER.error(ERROR_FALLO_AL_CLONAR_PLANTILLA);
			LOGGER.error("plantilla :" + plantilla);
			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
		}
		catch (Docx4JException e) {
			LOGGER.error("Error en Docx4J");
			LOGGER.error("plantilla :" + plantilla);
			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
		}
		catch (JAXBException e) {
			LOGGER.error("Excepcion JAXB");
			LOGGER.error("plantilla :" + plantilla);
			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
		}
		catch (FileNotFoundException e) {
			LOGGER.error("Archivo no encontrado");
			LOGGER.error("plantilla :" + plantilla);
			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
		}

		return documentoGenerado;
	}
	
	/**
	 * Gestion de tablas
	 * @param inputFile
	 * @return
	 */
	private File actualizaTabla(File inputFile, List <Map<String, String>> contenidoTabla, String [] idTabla) throws FileNotFoundException, Docx4JException, JAXBException {
		WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(new FileInputStream(inputFile));
		if (wordprocessingMLPackage!=null){
			System.out.println("Inicializado");
		}
		replaceTable(idTabla, contenidoTabla, wordprocessingMLPackage);
		wordprocessingMLPackage.save(inputFile);
		return inputFile;
	}

	/**
	 * Getsion de tablas
	 * @param obj
	 * @param toSearch
	 * @return
	 */
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();
 
		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}
 
		}
		return result;
	}
	/**
	 * Gestion de tablas
	 * @param placeholders
	 * @param textToAdd
	 * @param template
	 * @throws Docx4JException
	 * @throws JAXBException
	 */
	private void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
			WordprocessingMLPackage template) throws Docx4JException, JAXBException {
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
 
		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
 
		// first row is header, second row is content
		if (rows.size() == 2) {
			// this is our template row
			Tr templateRow = (Tr) rows.get(1);
 
			for (Map<String, String> replacements : textToAdd) {
				// 2 and 3 are done in this method
				addRowToTable(tempTable, templateRow, replacements);
			}
 
			// 4. remove the template row
			tempTable.getContent().remove(templateRow);
		}
	}
	/**
	 * Gestion de tablas
	 * @param tables
	 * @param templateKey
	 * @return
	 * @throws Docx4JException
	 * @throws JAXBException
	 */
	private Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}
	/**
	 * Gestion de tablas
	 * @param reviewtable
	 * @param templateRow
	 * @param replacements
	 */
	private static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {
			Text text = (Text) object;
			String replacementValue = (String) replacements.get(text.getValue());
			if (replacementValue != null)
				text.setValue(replacementValue);
		}
 
		reviewtable.getContent().add(workingRow);
	}
//	private DocumentoGeneradoDto modificarDocumentoDOCXDeDocumentoXML(
//			ParametrosDocumentoModificacionDto parametroModificacion) {
//
//		DocumentoGeneradoDto documentoGenerado = null;
//		DocxDocumentDto plantilla = null;
//		String xmlCadena = null;
//
//		try {
//
//			String idDocumentoXml = parametroModificacion.getDocumentoXML()
//					.getIdDocumento();
//
//			DocumentoDto documentoDocxOriginal = documentumHelperService
//					.recuperarDocumentoAnexo(parametroModificacion
//							.getDocumentoXML().getIdDocumento(),
//							DocumentumHelperService.FORMATO_DOCX);
//
//			xmlCadena = obtenerXmlCadena(idDocumentoXml);
//
//			String idDocumentoPlantilla = XMLUtils
//					.obtenerIdPlantilla(xmlCadena);
//			if (idDocumentoPlantilla != null) {
//				plantilla = poolPlantilla
//						.obtenerPlantilla(idDocumentoPlantilla);
//				DocxDocumentDto clonPlantilla = (DocxDocumentDto) plantilla
//						.clone();
//				Document dom = XMLUtils.buildDOM(xmlCadena);
//				// En clonPlantilla se insertan los valores de dom(xml)
//				DocxUtils.writeContentControls(clonPlantilla, dom,
//						documentumHelperService.getGestionarDocumentoService());
//
//				File contenido = obtenerArchivoTemporalDocx(
//						documentoDocxOriginal.getNombreDocumento(),
//						clonPlantilla);
//				documentoDocxOriginal.setContenido(contenido);
//
//				documentumHelperService
//						.modificarDocumento(documentoDocxOriginal);
//
//				documentoGenerado = crearDocumentoGenerado(
//						parametroModificacion.getDocumentoXML(),
//						documentoDocxOriginal);
//			} else
//
//			{
//				LOGGER.error(ERROR_XML_SIN_ID_PLANTILLA);
//				LOGGER.error("idDocumentoXml: " + idDocumentoXml);
//				throw new BusinessException(ERROR_XML_SIN_ID_PLANTILLA);
//			}
//
//		} catch (CloneNotSupportedException e) {
//			LOGGER.error(ERROR_FALLO_AL_CLONAR_PLANTILLA);
//			LOGGER.error("plantilla :" + plantilla);
//			throw new BusinessException(ERROR_FALLO_AL_CLONAR_PLANTILLA);
//		} catch (IOException ioe) {
//			LOGGER.error(ERROR_EN_LECTURA_DOCX);
//
//			throw new BusinessException(ERROR_EN_LECTURA_DOCX, ioe.getMessage());
//		}
//
//		return documentoGenerado;
//	}
//
	private String obtenerXmlCadena(String idDocumentoXml) {

		String xmlCadena = null;

		DocumentoDto documentoXml;
		try {
			documentoXml = documentumHelperService
					.recuperarDocumento(idDocumentoXml);
			xmlCadena = obtenerXmlCadena(documentoXml);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(ERROR_EN_LECTURA_XML);

			throw new BusinessException(ERROR_EN_LECTURA_XML, e.getMessage());
		}

		return xmlCadena;
	}
//
	private String obtenerXmlCadena(DocumentoDto documentoXml) {
		BufferedReader xmlLectura = null;
		StringBuffer xmlCadena = new StringBuffer();
		try {

			if (documentoXml.getContenido() != null) {
				FileReader contenido = new FileReader(documentoXml
						.getContenido());
				xmlLectura = new BufferedReader(contenido);
				String paso = xmlLectura.readLine();
				while (paso != null) {
					xmlCadena.append(paso);
					paso = xmlLectura.readLine();
				}

			} else {
				throw new BusinessException(ERROR_XML_SIN_CONTENIDO);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(ERROR_EN_LECTURA_XML);
			LOGGER.error("xmlLectura :" + xmlLectura);
			throw new BusinessException(ERROR_EN_LECTURA_XML);
		} finally {
			try {
				if (xmlLectura != null) {
					xmlLectura.close();
				}
			} catch (IOException e) {

				LOGGER.error(e);

			}

		}
		return xmlCadena.toString();
	}

	private DocumentoDto crearDocxParaGuardarEnDocumentum(UsuarioDto usuario,
			ParametrosDocumentoXMLExistenteDto parametroXMLExistente,
			DocxDocumentDto documentoDocx, String nombrePlantilla) {

		DocumentoDto documento = new DocumentoDto();
	
		String nombreDocumento = obtenerNombreDocumentoDOCX(parametroXMLExistente
				.getNombreDocumento(),nombrePlantilla);
		documento.setNombreDocumento(nombreDocumento);
//		String rutaDocumento = obtenerRutaDocumento(parametroXMLExistente
//				.getRutaDocumento(), documentumHelperService.generarRutaRfc(
//				CABINET_DEFAULT_FINALES, usuario.getRfcUsuario()));
		String rutaDocumento = parametroXMLExistente.getRutaDocumento();
		documento.setRutaDocumento(rutaDocumento);

		File contenido = obtenerArchivoTemporalDocx(nombreDocumento,
				documentoDocx);
		documento.setContenido(contenido);

		return documento;
	}
//
	private String obtenerRutaDocumento(String rutaDocumento, String rutaDefault) {
		if (rutaDocumento == null || "".equals(rutaDocumento.trim())) {

			return rutaDefault;
		} else {
			return rutaDocumento;
		}
	}
//
	private DocumentoGeneradoDto crearDocumentoGenerado(
			DocumentoXMLDto documentoXMLDto, DocumentoDto documentoDocx) {
		DocumentoGeneradoDto documentoGenerado = new DocumentoGeneradoDto();
		documentoGenerado.setDocumentoXML(documentoXMLDto);
		documentoGenerado
				.setIdDocumentoGenerado(documentoDocx.getIdDocumento());
		documentoGenerado.setNombreDocumentoGenerado(documentoDocx
				.getNombreDocumento());
		documentoGenerado.setRutaDocumentoGenerado(documentoDocx
				.getRutaDocumento());
		return documentoGenerado;
	}
//
//	public List<DocumentoGeneradoDto> crearDocumentoPDF(UsuarioDto usuario,
//			List<ParametrosDocumentoDto> paramsDocumentoDto) {
//		LOGGER.info("entra a operacion");
//		List<DocumentoGeneradoDto> listaDocumentosGenerados = new ArrayList<DocumentoGeneradoDto>();
//		for (ParametrosDocumentoDto parametro : paramsDocumentoDto) {
//			DocumentoGeneradoDto documentoGenerado = crearDocumentoPDF(usuario,
//					parametro);
//			listaDocumentosGenerados.add(documentoGenerado);
//		}
//
//		return listaDocumentosGenerados;
//	}
//
//	private DocumentoGeneradoDto crearDocumentoPDF(UsuarioDto usuario,
//			ParametrosDocumentoDto parametro) {
//
//		DocumentoGeneradoDto documentoGeneradoDOCX = crearDocumentoDOCX(
//				usuario, parametro);
//		DocumentoGeneradoDto documentoGenerado = new DocumentoGeneradoDto();
//		LOGGER.info("idDocumento ="
//				+ documentoGeneradoDOCX.getIdDocumentoGenerado());
//
//		DocumentoDto documentoPDF = crearPDFParaGuardarEnDocumentum(parametro.getRutaDocumento(), usuario);
// 
//		documentoPDF = documentumHelperService.generarPDF(documentoGeneradoDOCX
//				.getDocumentoXML().getIdDocumento(), documentoGeneradoDOCX
//				.getIdDocumentoGenerado(), documentoPDF);
//
//		documentoGenerado.setIdDocumentoGenerado(documentoPDF.getIdDocumento());
//		documentoGenerado.setNombreDocumentoGenerado(documentoPDF
//				.getNombreDocumento());
//        documentoGenerado.setDocumentoXML(documentoGeneradoDOCX.getDocumentoXML());
//		return documentoGenerado;
//	}
//
//	private DocumentoDto crearPDFParaGuardarEnDocumentum(
//			String parametroRuta, UsuarioDto usuario) {
//		DocumentoDto documentoPDF = new DocumentoDto();
//		documentoPDF.setNombreDocumento("nombretemporal.pdf");
//		documentoPDF.setRutaDocumento(obtenerRutaDocumento(parametroRuta,
//				documentumHelperService.generarRutaRfc(CABINET_DEFAULT_FINALES,
//						usuario.getRfcUsuario())));
//		return documentoPDF;
//	}
//
	private DocumentoGeneradoDto crearDocumentoDOCX(UsuarioDto usuario,
			ParametrosDocumentoDto parametro, List <Map<String, String>> contenidoTabla, String [] idTabla, boolean isTableOc,String userLT, Map<String, String> attr) {

		DocumentoXMLDto documentoXML = crearDocumentoXML(usuario, parametro,userLT, attr);
		ParametrosDocumentoXMLExistenteDto parametroXMLExistente = new ParametrosDocumentoXMLExistenteDto(
				parametro, documentoXML);
		DocumentoGeneradoDto documentoGenerado = crearDocumentoDOCXDeDocumentoXML(
				usuario, parametroXMLExistente, parametro
						.getIdDocumentoPlantilla(), parametro.getXmlDatos(), contenidoTabla, idTabla, isTableOc, userLT, attr);
		/*
		 * - Llamar crearDocumentoXML - Llamar crearDocumentoDocxDeDocumentoXML
		 */
		return documentoGenerado;
	}
//
//	public List<DocumentoGeneradoDto> crearDocumentoDOCXDeDocumentoXML(
//			UsuarioDto usuario, List<ParametrosDocumentoXMLExistenteDto> params) {
//
//		LOGGER.info("entra a operacion");
//		List<DocumentoGeneradoDto> listaDocumentosGenerados = new ArrayList<DocumentoGeneradoDto>();
//		for (ParametrosDocumentoXMLExistenteDto parametro : params) {
//			DocumentoGeneradoDto documentoGenerado = crearDocumentoDOCXDeDocumentoXML(
//					usuario, parametro);
//			listaDocumentosGenerados.add(documentoGenerado);
//		}
//
//		return listaDocumentosGenerados;
//	}
//
//	public List<DocumentoGeneradoDto> crearDocumentoPDFDeDocumentoXML(
//			UsuarioDto usuario, List<ParametrosDocumentoXMLExistenteDto> params) {
//		LOGGER.info("entra a operacion");
//
//		List<DocumentoGeneradoDto> listaDocumentosGenerados = new ArrayList<DocumentoGeneradoDto>();
//		for (ParametrosDocumentoXMLExistenteDto parametro : params) {
//			DocumentoGeneradoDto documentoGenerado = crearDocumentoPDFDeDocumentoXML(
//					usuario, parametro);
//			listaDocumentosGenerados.add(documentoGenerado);
//		}
//
//		return listaDocumentosGenerados;
//	}
//
//	private DocumentoGeneradoDto crearDocumentoPDFDeDocumentoXML(
//			UsuarioDto usuario, ParametrosDocumentoXMLExistenteDto parametro) {
//
//		DocumentoGeneradoDto documentoGenerado = crearDocumentoDOCXDeDocumentoXML(
//				usuario, parametro);
//		LOGGER.info("docx =" + documentoGenerado.getIdDocumentoGenerado());
//		// TODO ajustar metodo para virtuales
//		DocumentoDto documentoPDF = crearPDFParaGuardarEnDocumentum(parametro
//				.getRutaDocumento(), usuario);
//
//		documentoPDF = documentumHelperService.generarPDF(documentoGenerado
//				.getDocumentoXML().getIdDocumento(), documentoGenerado
//				.getIdDocumentoGenerado(), documentoPDF);
//
//		documentoGenerado.setIdDocumentoGenerado(documentoPDF.getIdDocumento());
//		documentoGenerado.setNombreDocumentoGenerado(documentoPDF
//				.getNombreDocumento());
//		/*
//		 * - llamar crearDocumentoDocxDeDocumentoXML - generar el documento pdf
//		 * con rendition.
//		 */
//		return documentoGenerado;
//	}
//
//	public List<DocumentoGeneradoDto> modificarDocumentoDOCX(
//			UsuarioDto usuario,
//			List<ParametrosDocumentoModificacionDto> documento) {
//		LOGGER.info("entra a operacion");
//
//		List<DocumentoGeneradoDto> listaDocumentosGenerados = new ArrayList<DocumentoGeneradoDto>();
//		for (ParametrosDocumentoModificacionDto parametro : documento) {
//			DocumentoGeneradoDto documentoGenerado = modificarDocumentoDOCX(parametro);
//			listaDocumentosGenerados.add(documentoGenerado);
//		}
//
//		return listaDocumentosGenerados;
//	}
//
//	private DocumentoGeneradoDto modificarDocumentoDOCX(
//			ParametrosDocumentoModificacionDto parametro) {
//		LOGGER.info("------------------------");
//		ByteArrayInputStream flujoDocumentoModificado = new ByteArrayInputStream(
//				parametro.getDocumentoModificado());
//		DocxDocumentDto documentoModificado = DocxUtils
//				.readDocument(flujoDocumentoModificado);
//		LOGGER.info("LEE DOCX BASE64");
//		DocumentoDto documentoXml;
//		try {
//			documentoXml = documentumHelperService.recuperarDocumento(parametro
//					.getDocumentoXML().getIdDocumento());
//		} catch (Exception e) {
//			LOGGER.error(ERROR_EN_LECTURA_XML);
//
//			throw new BusinessException(ERROR_EN_LECTURA_XML, e.getMessage());
//		}
//		LOGGER.info("XML DATOS CONSULTADO");
//		Document domXml = XMLUtils.buildDOM(documentoXml.getContenido(),
//				XMLUtils.UTF8_ENCODING);
//		LOGGER.info("XML SE CONSTRUYE DOM");
//		String idDocumentoPlantilla = XMLUtils
//				.obtenerIdPlantilla(obtenerXmlCadena(documentoXml));
//		LOGGER.info("SE CONSULTA ID PLANTILLA");
//		DocxDocumentDto plantilla = poolPlantilla
//				.obtenerPlantilla(idDocumentoPlantilla);
//		LOGGER.info("SE CONSULTA PLANTILLA");
//		// En domXml se actualizarán los datos tomados de documentoModificado
//		DocxUtils.readContentControls(documentoModificado, domXml, plantilla);
//		LOGGER.info("SE MEZCLAN LOS DATOS");
//		// Se actualiza el Xml
//		File contenido = obtenerArchivoTemporalXml(documentoXml
//				.getNombreDocumento(), domXml);
//
//		documentoXml.setContenido(contenido);
//
//		documentumHelperService.modificarDocumento(documentoXml);
//		LOGGER.info("SE ACTUALIZA EL ARCHIVO XML EN DOCUMENTUM");
//		// se genera un documento docx con los datos actualizados y se sustituye
//		// por el anterior.
//		DocumentoGeneradoDto documentoGenerado = modificarDocumentoDOCXDeDocumentoXML(parametro);
//		LOGGER.info("SE GENERA DOCX");
//		/*- Obtener el docx modificado.
//		 * Convertir a DocxDocumentoDto con DocxUtils#readDocument
//		- Obtener el documento xml de datos.
//		 * Construir el DOM del xml de datos con XMLUtils#buildDOM
//		- Obtener el documento plantilla del pool de plantillas
//		- extraer las modificaciones en el documento xml de datos.
//		 * Usar DocxUtils#readContentControls para actualizar el DOM del xml de datos
//		- guardar el documento xml de datos.
//		 * Usar alguno de los métodos XMLUtils#serialize*
//		- llamar crearDocumentoDocxDeDocumentoXML*/
//
//		/*
//		 * - Obtener el docx modificado. - Obtener el documento xml de datos. -
//		 * extraer las modificaciones en el documento xml de datos. - guardar el
//		 * documento xml de datos. - llamar crearDocumentoDocxDeDocumentoXML
//		 */
//		return documentoGenerado;
//	}
//
//	public void modificarDocumentoXML(UsuarioDto usuario,
//			List<ParametrosDocumentoModificacionXMLDto> params) {
//		LOGGER.info("entra a operacion");
//
//		for (ParametrosDocumentoModificacionXMLDto parametro : params) {
//			modificarDocumentoXML(parametro);
//		}
//
//	}
//
//	private void modificarDocumentoXML(
//			ParametrosDocumentoModificacionXMLDto parametro) {
//
//		DocumentoDto documentoXml;
//		try {
//			documentoXml = documentumHelperService.recuperarDocumento(parametro
//					.getDocumentoXML().getIdDocumento());
//
//			String xmlCadena = obtenerXmlCadena(documentoXml);
//			LOGGER.info(xmlCadena);
//
//			Document domXmlOriginal = XMLUtils.buildDOM(documentoXml
//					.getContenido(), XMLUtils.UTF8_ENCODING);
//			domXmlOriginal = XMLUtils.createNormalizedCopy(domXmlOriginal);
//
//			List<ModificacionDto> modificaciones = parametro
//					.getListaModificaciones();
//
//			for (ModificacionDto modificacion : modificaciones) {
//				LOGGER.info(modificacion);
//				XMLUtils.actualizarValor(domXmlOriginal,
//						modificacion.getPath(), modificacion.getValor());
//
//			}
//
//			File contenidoModificado = obtenerArchivoTemporalXml(documentoXml
//					.getNombreDocumento(), domXmlOriginal);
//
//			documentoXml.setContenido(contenidoModificado);
//
//			documentumHelperService.modificarDocumento(documentoXml);
//		} catch (IOException e) {
//			LOGGER.error(e);
//			throw new BusinessException(ERROR_EN_LECTURA_XML, e.getMessage());
//		}
//		/*
//		 * - obtener el xml original. Crear DOM con XMLUtils#buildDOM -
//		 * acutalizar el xml original con el xml nuevo Crear DOM del xml nuevo
//		 * con XMLUtils#buildDOM Usar XMLUtils#mergeDOM - guardar el xml
//		 * original actualizado en documentum Usar alguno de los métodos
//		 * XMLUtils#serialize*
//		 */
//
//	}
//
//	private File obtenerArchivoTemporalXml(String nombreDocumento,
//			Document domXml) {
//		File temp;
//		FileOutputStream flujoEscritura = null;
//		try {
//			temp = File.createTempFile(nombreDocumento, SUFIJO_XML);
//
//			temp.deleteOnExit();
//			flujoEscritura = new FileOutputStream(temp);
//			XMLUtils.serialize(domXml, flujoEscritura);
//
//		} catch (IOException e) {
//			LOGGER.error(e);
//			throw new BusinessException(ERROR_EN_ESCRITURA_XML);
//		} finally {
//			try {
//				if (flujoEscritura != null) {
//					flujoEscritura.close();
//				}
//			} catch (IOException e) {
//				LOGGER.error(e);
//			}
//		}
//		return temp;
//	}
//
	private File obtenerArchivoTemporalDocx(String nombreDocumento,
			DocxDocumentDto documentoDocx) {
		File temp;
		FileOutputStream flujoEscritura = null;
		try {
			temp = File.createTempFile(nombreDocumento, SUFIJO_DOCX);
			temp.deleteOnExit();

			flujoEscritura = new FileOutputStream(temp);
			DocxUtils.serializeDocument(documentoDocx, flujoEscritura);

		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_EN_ESCRITURA_DOCX);
		} finally {
			try {
				if (flujoEscritura != null) {
					flujoEscritura.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}

		// System.out.println("docxModificado: " + temp.getAbsolutePath());

		return temp;
	}
//
//	public void setPoolPlantilla(PoolPlantilla poolPlantilla) {
//		this.poolPlantilla = poolPlantilla;
//	}
//
//	public PoolPlantilla getPoolPlantilla() {
//		return poolPlantilla;
//	}
//
//	
//
//	public void setDocumentumHelperService(
//			DocumentumHelperService documentumHelperService) {
//		this.documentumHelperService = documentumHelperService;
//	}
//
//	public DocumentoDto obtenerRendicionPDF(DocumentoDto documento) {
//		String nombreDocumento=obtenerNombreDocumentoPDF(documento.getNombreDocumento());
//		DocumentoDto pdf = documentumHelperService.getGestionarDocumentoService().recuperarRendicion(documento.getIdDocumento(),GestionarDocumentoService.DocumentumMimeTypes.pdf.toString(),nombreDocumento);
//		pdf.setRutaDocumento(obtenerRutaDocumento(documento.getRutaDocumento(), CABINET_DEFAULT_FINALES));
//		pdf.setNombreDocumento(nombreDocumento);
//		pdf.addAtributo(DocumentumHelperService.PROPIEDAD_VISIBLE,DocumentumHelperService.VALOR_VISIBLE);
//		LOGGER.info("nombre archivo pdf="+pdf.getNombreDocumento());
//		return pdf;
//	}

}
