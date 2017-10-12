package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocxDocumentDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.XMLUtils;

/**
 * Provee funciones de leectura, escritura y extracción e inserción de datos
 * para el formato docx.
 * 
 */
public final class DocxUtils {

	private static Log LOGGER = LogFactory.getLog(DocxUtils.class);
	
	private static final String XPRESSO_MODEL_STRING = "xPressoModel";
	
	private static final String ERROR_READ_DOC_MALFORMED_URL = "ERROR_READ_DOC_MALFORMED_URL";
	
	private static final String ERROR_READ_DOC_IO = "ERROR_READ_DOC_IO";
	
	private static final String ERROR_READ_DOC_OPEN_STREAM = "ERROR_READ_DOC_OPEN_STREAM";
	
	private static final String ERROR_DOCX_MISSING_DOCUMENT_PART = "ERROR_DOCX_MISSING_DOCUMENT_PART";
	
	private static final String ERROR_DOCX_MISSING_RELS_PART = "ERROR_DOCX_MISSING_RELS_PART";
	
	private static final String ERROR_DOCX_MISSING_XPRESSO_PART = "ERROR_DOCX_MISSING_XPRESSO_PART";
	
	private static final String ERROR_DOCX_IMAGE_DOWNLOAD_IO = "ERROR_DOCX_IMAGE_DOWNLOAD_IO";
	
	private static final String ERROR_DOCX_IMAGE_DOCUMENTUM = "ERROR_DOCX_IMAGE_DOCUMENTUM";
	
	private static final String ERROR_UNSUPPORTED_ENCODING = "ERROR_UNSUPPORTED_ENCODING";
	
	private static final String ERROR_CONVERT_INPUTSTREAM = "ERROR_CONVERT_INPUTSTREAM";
	
	private static final String ERROR_STREAM_CLOSE = "ERROR_STREAM_CLOSE";
	
	private static final String ERROR_DOCX_ARCHIVO_NO_EXISTE = "ERROR_DOCX_ARCHIVO_NO_EXISTE";
	
	private static final String ERROR_COPY_DOCX = "ERROR_COPY_DOCX";
	
	private static final String BMP_EXT = "bmp";
	
	private static final String JPG_EXT = "jpg";
	
	private static final String JPEG_EXT = "jpeg";
	
	private static final String PNG_EXT = "png";
	
	private static final String GIF_EXT = "gif";
	
	private static final String TIF_EXT = "tif";

	/**
	 * Lee un documento docx de un archivo.
	 * 
	 * @param file
	 *            el archivo del docx
	 * @return El docx leido.
	 * 
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	public static DocxDocumentDto readDocument(File file) throws BusinessException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return readDocument(fis);
		} catch (FileNotFoundException e) {
			LOGGER.error("El archivo docx no existe", e);
			throw new BusinessException(ERROR_DOCX_ARCHIVO_NO_EXISTE, e.getMessage());
		}
		finally {
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
	 * @author Osvaldo Ortega Martínez <osvaldo.ortega.martinez@hp.com> Lee un
	 *         docx a partir de un <code>InputStream</code>.
	 * 
	 * @param is
	 *            un <code>InputStream</code> de donde se lee el docx.
	 * @return El docx leido.
	 */
	public static DocxDocumentDto readDocument(InputStream is) throws BusinessException {
		
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		ZipInputStream zipInputStream = null;
		
		try
		{
			baos = new ByteArrayOutputStream();
			int read;
			while ((read = is.read()) != -1)
			{
				baos.write(read);
			}
			baos.close();
			
			byte[] docxContent = baos.toByteArray();
			
			bais = new ByteArrayInputStream(docxContent);
			
			HashMap<String, byte[]> docEnMapa = new HashMap<String, byte[]>();
			zipInputStream = new ZipInputStream(bais);
			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null)
			{
				docEnMapa.put(zipEntry.getName(), inputStreamToByteArray(zipInputStream));
			}
			
			DocxDocumentDto dto =  new DocxDocumentDto(docEnMapa);
			dto.setDocxContent(docxContent);
			return dto;
			
		} catch (MalformedURLException e) {
			LOGGER.error(e); 
			throw new BusinessException(ERROR_READ_DOC_MALFORMED_URL, e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_READ_DOC_IO, e.getMessage());
		}
		finally
		{
			Utils.closeStream(baos);
			Utils.closeStream(bais);
			Utils.closeStream(zipInputStream);
		}
	}

	/**
	 * Serializa un docx en su representación standard (dentro de un zip) a un <code>OutputStream</code>.
	 * 
	 * @param docx el docx
	 * @param os un <code>OutputStream</code>
	 */
//	public static void serializeDocument(DocxDocumentDto docx, OutputStream os) throws IOException {
//		ZipOutputStream zipOutputStream = new ZipOutputStream(os);
//		Iterator<String> iterator = docx.iterator();
//		while(iterator.hasNext()){
//			String key = iterator.next();
//			ZipEntry zipEntry = new ZipEntry(key);
//			zipOutputStream.putNextEntry(zipEntry);
//			zipOutputStream.write(docx.getPart(key));
//			zipOutputStream.flush();
//		}
//		zipOutputStream.close();
//	}
	
	/**
	 * Serializa un docx en su representación standard (dentro de un zip) a un
	 * arreglo de bytes.
	 * 
	 * @param docx
	 *            el documento
	 * @return un arreglo de bytes con el docx serializado
	 */
	public static byte[] serializeDocument(DocxDocumentDto docx) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		serializeDocument(docx, baos);
		baos.close();
		return baos.toByteArray();
	}

	/**
	 * Lee un docx a partr de una url.
	 * 
	 * @param url
	 *            la url de donde se obtendrá el docx.
	 * @return el docx leido.
	 * @throws BusinessException
	 */
	public static DocxDocumentDto readDocument(URL url) throws BusinessException {
		try {
			return readDocument(url.openStream());
		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_READ_DOC_OPEN_STREAM, e.getMessage());
		}

	}

	/**
	 * Modifica el docx actualizando el contenido de los content controls de
	 * acuerdo a su mapeo y los datos dados en el xml.
	 * 
	 * @param docx
	 *            el docx que será modificado
	 * @param data
	 *            el DOM del xml con los datos
	 * @param gestionarDocumentoService
	 *            usado para descargar imágenes
	 * 
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	public static void writeContentControls(DocxDocumentDto docx, Document data,
			GestionarDocumentoService gestionarDocumentoService) throws BusinessException {

		if (docx.getPart(DocxDocumentDto.DOCUMENT_PATH) == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_DOCUMENT_PART, 
					"El docx no contiene el archivo del xml principal.");
		} else if (docx.getPart(DocxDocumentDto.DOCUMENT_RELS_PATH) == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_RELS_PART, 
					"El docx no contiene la parte de relaciones del xml principal.");
		}
		
		// Crea una copia del documento de datos normalizado (sin prefijos,
		// namespaces
		// y eliminando el sufijo que inicia con _ del elemento raíz si existe.
		Document normalizedDataDoc = XMLUtils.createNormalizedCopy(data);

		// Crea el DOM del xml de relaciones
		Document relationshipsDoc = XMLUtils.buildDOM(docx.getPart(DocxDocumentDto.DOCUMENT_RELS_PATH));

		RelationshipsPart relationshipsPart = new RelationshipsPart(relationshipsDoc);

		// Busca el documento de xpresso con las definiciones de las variables y
		// sus mapeos
		String xpressoModelPath = getXPressoModelPath(docx, relationshipsPart);

		if (xpressoModelPath == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_XPRESSO_PART, 
					"No se encontro el modelo de xpresso en el docx.");
		}

		Document xpressoModelDoc = XMLUtils.buildDOM(docx.getPart(xpressoModelPath));

		// Crea el DOM del xml principal
		Document wordProcessingDoc = XMLUtils.buildDOM(docx.getPart(DocxDocumentDto.DOCUMENT_PATH));

		WordprocessingMLPart wordProcessingMLPart = new WordprocessingMLPart(wordProcessingDoc);

		// Obtiene y actualiza las imágenes en el docx. Esto solo modifica los
		// archivos del docx y actualiza
		// relationshipsPart
		downloadAndUpdateImages(docx, wordProcessingMLPart, relationshipsPart, normalizedDataDoc,
				gestionarDocumentoService);

		// Transforma el docx applicando los mapeos
		wordProcessingMLPart.updateControls(normalizedDataDoc, xpressoModelDoc);

		// Finalmente se actualiza el docx
		docx.setPart(DocxDocumentDto.DOCUMENT_PATH, XMLUtils.serializeToByteArray(wordProcessingMLPart.getDocument()));
		docx.setPart(DocxDocumentDto.DOCUMENT_RELS_PATH, XMLUtils.serializeToByteArray(relationshipsPart.getDocument()));
	}

	/**
	 * Método de ayuda. Descarga y actualiza las imágenes en el docx y el
	 * archivo de relaciones. Las imágenes se obtienen de
	 * <code>wordProcessingMLPart</code>.
	 * 
	 * @param docx
	 *            El docx
	 * @param wordProcessingMLPart
	 *            la parte del xml principal
	 * @param relationshipsPart
	 *            la parte de relaciones del xml principal
	 * @param normalizedDataDoc
	 *            el documento de datos normalizado
	 * @param gestionarDocumentoService
	 *            usado para descargar imágenes de documentum
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	private static void downloadAndUpdateImages(DocxDocumentDto docx, WordprocessingMLPart wordProcessingMLPart,
			RelationshipsPart relationshipsPart, Document normalizedDataDoc,
			GestionarDocumentoService gestionarDocumentoService) throws BusinessException {

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath dataDocumentXpath = xpathFactory.newXPath();

		for (Entry<String, String> entry : wordProcessingMLPart.getMappingImagesRefIdsMap().entrySet()) {

			String path = entry.getKey();
			String rId = entry.getValue();

			DocumentoDto documentoDto = null;
			String imgId = null;

			try {
				
				imgId = (String) dataDocumentXpath.evaluate(path, normalizedDataDoc, XPathConstants.STRING);
				if(imgId!=null&&!"".equals(imgId)){//Solo incrusta imagen si regresa valor la expresión
					
				documentoDto = gestionarDocumentoService.recuperarDocumento(imgId,"");
				if((documentoDto == null) || (documentoDto.getUrlDocumento() == null)) {
					throw new BusinessException(ERROR_DOCX_IMAGE_DOCUMENTUM); 
				}
				
				String relativeImagePath = relationshipsPart.getRelationshipTarget(rId);
				String absoluteImagePath = PathUtils.getAbsoluteFilePath(DocxDocumentDto.WORD_DIR_PATH, relativeImagePath);
				
				downloadImage(documentoDto.getUrlDocumento(), docx, absoluteImagePath);

//				docx.removePart(absoluteOldImagePath);

//				relationshipsPart.setRelationshipTarget(rId,
//						PathUtils.getRelativeFilePath(DocxDocumentDto.WORD_DIR_PATH, imgPath));
				}
			} catch (XPathExpressionException e) {
				// Esto no es un error, el path de la imágen en el xml de datos no necesariamente debe existir.
				LOGGER.debug("No se pudo evaluar la expresion xpath " + path + " en el documento de datos.", e);
			} catch (IOException e) {
				LOGGER.error("No se pudo obtener la imágen " + documentoDto.getUrlDocumento(), e);
				throw new BusinessException(ERROR_DOCX_IMAGE_DOWNLOAD_IO, "No se pudo obtener la imágen " + 
						documentoDto.getUrlDocumento()
						+ ": " + e.getMessage());
			}
		}
	}

	/**
	 * Método de ayuda. Descarga la imágen dada y actualiza el docx.
	 * 
	 * @param imageUrl
	 *            el url de la imágen
	 * @param docx
	 *            el docx
	 * @param filePath
	 *            el path del archivo docx en donde se va a guardar la imágen
	 * @throws IOException
	 *             si ocurre un error de e/s
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	private static void downloadImage(String imageUrl, DocxDocumentDto docx, String filePath) throws IOException {

		URL url = new URL(imageUrl);

		byte[] buffer = new byte[1024];

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();

		try {
			int read;
			while ((read = is.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					LOGGER.error("Error cerrando input stream", e);
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (Exception e) {
					LOGGER.error("Error cerrando output stream", e);
				}
			}
		}

//		String path;
//		int i = 0;
//		do {
//			i++;
//			String fileName = url.getFile();
//			
//			String fileExt = PNG_EXT; 
//			
//			int dotIdx = fileName.indexOf(".");
//			if (dotIdx != -1)
//			{
//				fileExt = fileName.substring(dotIdx + 1);
//				fileExt = fileExt.toLowerCase(Locale.getDefault());
//				
//				if (fileExt.startsWith(BMP_EXT)) {
//					fileExt = BMP_EXT;
//				}
//				else if (fileExt.startsWith(JPEG_EXT) || fileExt.startsWith(JPG_EXT)) {
//					fileExt = JPG_EXT;
//				}
//				else if (fileExt.startsWith(GIF_EXT)) {
//					fileExt = GIF_EXT;
//				}
//				else if (fileExt.startsWith(TIF_EXT)) {
//					fileExt = TIF_EXT;
//				}
//				else {
//					fileExt = PNG_EXT;
//				}
//			}
//			
//			path = dirPath + "img" + i + "." + fileExt;
//		} while (docx.getPart(path) != null);

		docx.setPart(filePath, baos.toByteArray());
	}

	/**
	 * Busca el xml del modelo de xpresso el cuál contiene las definiciones de
	 * las variables y sus mapeos.
	 * 
	 * @param docx
	 *            el docx
	 * @param relationshipsPart
	 *            la parte de relaciones
	 * @return la ruta absoluta en el docx del modelo de xpresso
	 * @throws BusinessException
	 *             Si ocurre un error durante la búsqueda
	 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
	 */
	private static String getXPressoModelPath(DocxDocumentDto docx, RelationshipsPart relationshipsPart)
			throws BusinessException {

		for (String relId : relationshipsPart.getRelationshipIds()) {
			if (RelationshipsPart.CUSTOM_XML_TYPE.equals(relationshipsPart.getRelationshipType(relId))) {

				String relativeFilePath = relationshipsPart.getRelationshipTarget(relId);
				String absoluteFilePath = PathUtils
						.getAbsoluteFilePath(DocxDocumentDto.WORD_DIR_PATH, relativeFilePath);

				if (docx.getPart(absoluteFilePath) != null) {

					String xml;

					try {
						xml = new String(docx.getPart(absoluteFilePath), XMLUtils.UTF8_ENCODING);
					} catch (UnsupportedEncodingException e) {
						throw new BusinessException(ERROR_UNSUPPORTED_ENCODING, e.getMessage());
					}

					if (xml.contains(XPRESSO_MODEL_STRING)) {
						return absoluteFilePath;
					}

				} else {
					LOGGER.warn("No se encontró el path " + absoluteFilePath
							+ " extraido del xml de relaciones en el docx.");
				}
			}
		}

		return null;
	}

	/**
	 * Lee el contenido de los content controls del docx dado y lo escribe en el
	 * xml dado por su DOM.
	 * 
	 * @param docx
	 *            el docx de donde serán leido los content controls
	 * @param data
	 *            el DOM del xml en donde serán escritos los datos leidos
	 * @param template
	 *            la plantilla de donde se obtendrá cuales controles se deben leer (los que son editables)
	 */
	public static void readContentControls(DocxDocumentDto docx, Document data, DocxDocumentDto template)
			throws BusinessException {

		if (docx.getPart(DocxDocumentDto.DOCUMENT_PATH) == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_DOCUMENT_PART,
					"El docx no contiene el archivo del xml principal.");
		} else if (template.getPart(DocxDocumentDto.DOCUMENT_RELS_PATH) == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_RELS_PART,
					"El docx original no contiene el xml de relaciones.");
		}

		// Crea una copia del documento de datos normalizado (sin prefijos,
		// namespaces
		// y eliminando el sufijo que inicia con _ del elemento raíz si existe.
		Document normalizedDataDoc = XMLUtils.createNormalizedCopy(data);

		// Crea el DOM del xml de relaciones del documento original
		Document origRelationshipsDoc = XMLUtils.buildDOM(template.getPart(DocxDocumentDto.DOCUMENT_RELS_PATH));

		RelationshipsPart origRelationshipsPart = new RelationshipsPart(origRelationshipsDoc);

		// Busca el documento de xpresso original con las definiciones de las
		// variables y sus mapeos
		String origXpressoModelPath = getXPressoModelPath(template, origRelationshipsPart);

		if (origXpressoModelPath == null) {
			throw new BusinessException(ERROR_DOCX_MISSING_XPRESSO_PART,
					"No se encontro el modelo de xpresso en el docx original.");
		}

		// construye el DOM del xml de xpresso del docx original
		Document origXpressoModelDoc = XMLUtils.buildDOM(template.getPart(origXpressoModelPath));

		// construye el DOM del xml principal del docx original
		Document origWordProcessingDoc = XMLUtils.buildDOM(template.getPart(DocxDocumentDto.DOCUMENT_PATH));

		WordprocessingMLPart origWordProcessingMLPart = new WordprocessingMLPart(origWordProcessingDoc);

		// Crea el DOM del xml principal
		Document wordProcessingDoc = XMLUtils.buildDOM(docx.getPart(DocxDocumentDto.DOCUMENT_PATH));

		WordprocessingMLPart wordProcessingMLPart = new WordprocessingMLPart(wordProcessingDoc);

		// Los paths del doc de datos sobre los que se puede escribir se
		// obtienen del documentod de xpresso
		Set<String> writablePaths = origWordProcessingMLPart.getWritablePaths(origXpressoModelDoc);

		// Finalmente se leen los controles, notese que tambien se está usando
		// el modelo de xpresso del docx
		// original. Esto se hace así ya que el documento recibido no es seguro
		// y únicamente nos interesan de él
		// algunos datos que se deben extraer.
		wordProcessingMLPart.readControls(normalizedDataDoc, origXpressoModelDoc, writablePaths);
	}

	/**
	 * Convierte de un InputStram a un arreglo de byte con el fin de dejar en memoria
	 * el contenido de un archivo.
	 *
	 * @author ortegaos
	 * @param inputStream archivo a convertir
	 * @return el arreglo de byte listo para manejar en memoria
	 * @throws BusinessException
	 */
	private static byte[] inputStreamToByteArray(InputStream inputStream) throws BusinessException {
		BufferedInputStream bufferedInputStream = null;
		ByteArrayOutputStream arrayOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			if (inputStream != null) {
				bufferedInputStream = new BufferedInputStream(inputStream);
				arrayOutputStream = new ByteArrayOutputStream();
				bufferedOutputStream = new BufferedOutputStream(arrayOutputStream);
				int leo = bufferedInputStream.read();
				while (leo != -1) {
					bufferedOutputStream.write(leo);
					leo = bufferedInputStream.read();
				}
				arrayOutputStream.flush();
				bufferedOutputStream.flush();
			} else {
				// A sugerencia de findbugs regresamos un arreglo vacio en lugar de null
				return new byte[0];
			}
		} catch (IOException e) {
			LOGGER.error(e);
			throw new BusinessException(ERROR_CONVERT_INPUTSTREAM, e.getMessage());
		} finally {
			try {
				if (arrayOutputStream != null) {
					arrayOutputStream.close();
				}
			} catch (IOException e) {
				throw new BusinessException(ERROR_STREAM_CLOSE, e.getMessage());
			}
		}

		return arrayOutputStream.toByteArray();
	}
	
	/**
	 * Copia un docx que recibe como inputStram, en el File que se le indique
	 * Reutiliza metodos de la misma utileria
	 * 
	 * @author Osvaldo Ortega Martínez <osvaldo.ortega.martinez@hp.com>
	 * @param inputStream Es el docx que se desea copiar
	 * @param file Es la ruta donde se desea copiar el archivo
	 */
	public static void copiarDocx(InputStream inputStream, File file){
		
		try {
			OutputStream outputStream = new FileOutputStream(file);
			// DocxUtils.serializeDocument(DocxUtils.readDocument(inputStream),outputStream);
			
			int read;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}
			
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException fileNotFoundException) {
			LOGGER.error(fileNotFoundException);
			throw new BusinessException(ERROR_COPY_DOCX, fileNotFoundException.getMessage());
		} catch (IOException ioException){
			LOGGER.error(ioException);
			throw new BusinessException(ERROR_COPY_DOCX, ioException.getMessage());
		}
	}
	
	private static boolean hasImageExt(String file) {
		
		String lcFile = file.toLowerCase();
		
		return (lcFile.endsWith("."+BMP_EXT) ||
				lcFile.endsWith("."+JPG_EXT) ||
				lcFile.endsWith("."+JPEG_EXT) ||
				lcFile.endsWith("."+PNG_EXT) ||
				lcFile.endsWith("."+GIF_EXT) ||
				lcFile.endsWith("."+TIF_EXT));
	}
	
	public static void serializeDocument(DocxDocumentDto docx, OutputStream os) throws IOException{
		
    	LEDataOutputStream out = null;
		
		try
		{
			ZipFormat zipFormat = new ZipFormat();
			zipFormat.read(docx.getDocxContent());
			
			zipFormat.insertFile(DocxDocumentDto.DOCUMENT_RELS_PATH, docx.getPart(DocxDocumentDto.DOCUMENT_RELS_PATH));
			zipFormat.insertFile(DocxDocumentDto.DOCUMENT_PATH, docx.getPart(DocxDocumentDto.DOCUMENT_PATH));
			
			for (String path : docx) {
				if (hasImageExt(path)) {
					zipFormat.insertFile(path, docx.getPart(path));
				}
			}
			
			out = new LEDataOutputStream(os);
			zipFormat.write(out);
		}
		finally
		{
			Utils.closeStream(os);
			Utils.closeStream(out);
		}
		
	}
}
