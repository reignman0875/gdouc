package com.mx.pmx.pmi.sad.generadorDocx.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoXMLDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.ParametrosDocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.UsuarioDto;

public interface TransformarDocumentoService {

//	List<DocumentoXMLDto> crearDocumentoXML(UsuarioDto usuario,List<ParametrosDocumentoDto> paramsDocumentoDto);
	
//	List<DocumentoGeneradoDto> crearDocumentoDOCX(UsuarioDto usuario,List<ParametrosDocumentoDto> paramsDocumentoDto, List <Map<String, String>> contenidoTabla, String [] idTabla, boolean isTableOc, String userLT);
	
	List<DocumentoGeneradoDto> crearDocumentoDOCX(UsuarioDto usuario,List<ParametrosDocumentoDto> paramsDocumentoDto, List <Map<String, String>> contenidoTabla, String [] idTabla, boolean isTableOc, String userLT, Map<String, String> attr);
	
//	List<DocumentoGeneradoDto> crearDocumentoPDF(UsuarioDto usuario,List<ParametrosDocumentoDto> paramsDocumentoDto);
//	
//	List<DocumentoGeneradoDto> crearDocumentoDOCXDeDocumentoXML(UsuarioDto usuario,List<ParametrosDocumentoXMLExistenteDto> params);
//	
//	List<DocumentoGeneradoDto> crearDocumentoPDFDeDocumentoXML(UsuarioDto usuario,List<ParametrosDocumentoXMLExistenteDto> params);
//	// upload
//	List<DocumentoGeneradoDto> modificarDocumentoDOCX(UsuarioDto usuario,List<ParametrosDocumentoModificacionDto> documento);
//	// para firma
//	void modificarDocumentoXML(UsuarioDto usuario,List<ParametrosDocumentoModificacionXMLDto> params);
//	
//	DocumentoDto obtenerRendicionPDF(DocumentoDto documentoOriginal);
	
}
