package com.mx.pmx.pmi.sad.generadorDocx.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;

public interface DocumentumHelperService {

	String BASE_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	String FORMATO_XML = "xml";
	String FORMATO_DOCX = "docx";
	String FORMATO_PDF = "pdf";
	//TODO IOMH Propiedad visible del documento virtual
	String PROPIEDAD_VISIBLE="id_pemex";
//	TODO IOMH Propiedad rendicion del documento virtual
	String PROPIEDAD_RENDICION="id_pemex";
	int VALOR_VISIBLE=1;
	int VALOR_NO_VISIBLE=0;
	int TIENE_RENDICION=1;
	
	DocumentoDto recuperarDocumentoAnexo(String idDocumentoVirtual, String formato) throws IOException;
	DocumentoDto recuperarDocumento(String idDocumento) throws IOException; 
	DocumentoDto generarPDF(final String idDocumentoXml, final String idDocx,DocumentoDto documentoRendicion);
	String generarRutaRfc(final String cabinet, final String rfc);
	String deDecimalABase62(long decimalNumber);
	public void modificarDocumento(DocumentoDto documentoDto);
//	public void guardarDocumento(DocumentoDto documentoDto, String userLT);
	public void guardarDocumento(DocumentoDto documentoDto, String userLT, Map<String, String> attr);
	public GestionarDocumentoService getGestionarDocumentoService();
	void guardarDocumentoVirtual(String idDocumentoXml,
			ArrayList<DocumentoDto> documentosVirtuales);
	public void borrarDocumento(String idDocumentoXml); 
	
	
}