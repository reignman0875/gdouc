package com.mx.pmx.pmi.sad.generadorDocx.core.bean;

import java.io.Serializable; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 * Representa un documento Docx. Un docx es un zip que contiene archivos,
 * llamados parts, con rutas definidas. El contenido del docx se mantiene
 * descomprimido en esta clase.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public class DocxDocumentDto implements Serializable, Iterable<String>, Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * El path del xml principal. Aqui es donde se encuentra el documento en sí.
	 */
	public static final String DOCUMENT_PATH = "word/document.xml";

	/**
	 * El path del directorio que contiene al xml principal.
	 */
	public static final String WORD_DIR_PATH = "word/";

	/**
	 * El path del directorio en donde se guardan imágenes.
	 */
	public static final String MEDIA_DIR_PATH = "word/media/";

	/**
	 * El path en donde se guardan las relaciones del xml principal.
	 */
	public static final String DOCUMENT_RELS_PATH = "word/_rels/document.xml.rels";

	/**
	 * El contenido del docx. Cada entrada del mapa representa un archivo. La
	 * llave representa su path y el valor el contenido.
	 */
	private HashMap<String, byte[]> contents;
	
	private byte[] docxContent;
	
	/**
	 * Es el nombre del documento
	 */
	private String nombreDocumento;

	/**
	 * Constructor default. Si se usa este constructor se espera que el
	 * contenido sea establecido llamando <code>setPart</code>.
	 */
	public DocxDocumentDto() {
		contents = new HashMap<String, byte[]>();
	}

	/**
	 * Constructor
	 * 
	 * @param contents
	 *            el contenido del docx. Cada
	 */
	public DocxDocumentDto(HashMap<String, byte[]> contents) {
		this.contents = contents;
	}

	/**
	 * Regresa el contenido en bytes del archivo indicado por su path.
	 * 
	 * @param partPath
	 *            el path del archivo
	 * @return el contenido del archivo
	 */
	public byte[] getPart(String partPath) {
		return contents.get(partPath);
	}

	/**
	 * Agrega un archivo (part) o modifica el contenido de uno ya existente.
	 * 
	 * @param partPath
	 *            el path del archivo
	 * @param partData
	 *            el contenido en bytes del archivo
	 */
	public void setPart(String partPath, byte[] partData) {
		contents.put(partPath, partData);
	}

	/**
	 * Regres el nombre del documento
	 * 
	 * @return el nombre del archivo
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Asigna el nombre del documento
	 * 
	 * @param nombreDocumento es el nombre que se desea asignar al documento
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * Elimina un archivo (part) indicado por su path.
	 * 
	 * @param partPath
	 *            el path del archivo
	 * @return el contenido del archivo eliminado o null si el archivo no existe
	 */
	public byte[] removePart(String partPath) {
		return contents.remove(partPath);
	}

	public Iterator<String> iterator() {
		return contents.keySet().iterator();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		DocxDocumentDto clone = (DocxDocumentDto) super.clone();
		clone.contents = new HashMap<String, byte[]>();
		
		for (Entry<String, byte[]> entry : this.contents.entrySet()) {
			byte[] srcValue = entry.getValue();
			byte[] dstValue=copy(srcValue);
			clone.contents.put(entry.getKey(), dstValue);
		}
		clone.setDocxContent(copy(this.docxContent));
		
		return clone;
	}
	
    private byte[] copy(byte[] srcValue){
    	byte[] dstValue=null;
    
    	if(srcValue!=null){
    	int length =srcValue.length;
		dstValue = new byte[length];
		for (int i = 0; i < srcValue.length; i++) {
			dstValue[i] = srcValue[i];
		}}
		return dstValue;
    	
    }
	public void setDocxContent(byte[] docxContent) {
		this.docxContent = docxContent;
	}

	public byte[] getDocxContent() {
		//System.out.println("contenido ="+docxContent);
		return docxContent;
	}
	
}
