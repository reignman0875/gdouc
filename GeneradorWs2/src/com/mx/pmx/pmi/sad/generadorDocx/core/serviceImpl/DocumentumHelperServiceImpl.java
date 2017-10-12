package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.DocumentumHelperService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService.DocumentumMimeTypes;

public class DocumentumHelperServiceImpl implements DocumentumHelperService {

	private GestionarDocumentoService gestionarDocumentoService=null;
	
	public DocumentumHelperServiceImpl() {
		// IOMH constructor
		gestionarDocumentoService = new GestionarDocumentoServiceImpl(); 
	}

	public void setGestionarDocumentoService(
			GestionarDocumentoService gestionarDocumentoService) {
		this.gestionarDocumentoService = gestionarDocumentoService;
	}

	public DocumentoDto recuperarDocumentoAnexo(String idDocumentoVirtual,
			String formato) throws IOException {

		String formatoDocumentum = null;
		final DocumentumMimeTypes dmt = DocumentumMimeTypes.valueOf(formato);
		if (dmt != null) {
			formatoDocumentum = dmt.getDocumentumMimeType();
		}

		StringBuilder dql = new StringBuilder()
				.append("select A.").append(gestionarDocumentoService.getQualifierAttribute()).append(" from ").append(gestionarDocumentoService.getDocumentType()).append(" A, dmr_containment B where A.r_object_id = B.component_id and B.parent_id IN (select r_object_id from ")
				.append(gestionarDocumentoService.getDocumentType()).append(" D where D.").append("id_documento").append(" = '").append(idDocumentoVirtual).append("') and A.a_content_type ='").append(formatoDocumentum).append("'");

		List<String> idDocumento = gestionarDocumentoService
				.consultarIdDocumento(dql.toString());

		// TODO: Evaluar queno venga nulo ni vacio y controlarlo propiamente
		DocumentoDto documento = gestionarDocumentoService.recuperarDocumento(idDocumento.get(0),"");
		File contenido=obtenerContenido(documento);
		documento.setContenido(contenido);

		return  documento;

	}

	public DocumentoDto recuperarDocumento(String idDocumento)
			throws IOException {

		DocumentoDto documento = gestionarDocumentoService
				.recuperarDocumento(idDocumento,"");
		File contenido=obtenerContenido(documento);
		documento.setContenido(contenido);

		return documento;

	}

	private File obtenerContenido(DocumentoDto documento) throws IOException {
		File temp;
		FileOutputStream flujoEscritura = null;
		URL url = new URL(documento.getUrlDocumento());
		BufferedInputStream bufIn = null;
		BufferedOutputStream bufOut = null;
		InputStream flujoURL=null;

		try {
			temp = File.createTempFile(documento.getNombreDocumento(), "");
			temp.deleteOnExit();
			flujoEscritura = new FileOutputStream(temp);
			flujoURL=url.openStream();
			bufIn = new BufferedInputStream(flujoURL);
			bufOut = new BufferedOutputStream(flujoEscritura);

			byte[] buf = new byte[1024];
			int numRead = 0;
			while ((numRead = bufIn.read(buf)) >= 0) {
				bufOut.write(buf, 0, numRead);
			}
			
		   flujoEscritura.flush();
		   bufOut.flush();
	
		 
           
		} finally {
			if(bufIn!=null)
			{
				try{
				bufIn.close();
				}
				catch(IOException e){
				e.printStackTrace();
				}
			}
			if(bufOut!=null){
				bufOut.close();
			}
			
			
		}
		return temp;

	}
	
	public DocumentoDto generarPDF(final String idDocumentoXml, final String idDocx, DocumentoDto dtoRendicion) {
		
		
		dtoRendicion.addAtributo(DocumentumHelperService.PROPIEDAD_VISIBLE,DocumentumHelperService.VALOR_VISIBLE );
		
		dtoRendicion = gestionarDocumentoService.generarRendicion(idDocumentoXml, idDocx, dtoRendicion);
		DocumentoDto xml= new DocumentoDto();
		xml.addAtributo(DocumentumHelperService.PROPIEDAD_RENDICION, DocumentumHelperService.TIENE_RENDICION);
		xml.setIdDocumento(idDocumentoXml);
		gestionarDocumentoService.modificarDocumento(xml);
				
		return dtoRendicion;
	}

	public String generarRutaRfc(final String cabinet, final String rfc) {

		String rfcPathCompleto;

		String rfcNormalizado=rfc.toUpperCase(Locale.getDefault());
		rfcPathCompleto = "";
		StringBuffer rfcPathA = new StringBuffer(""); 
		String rfcPathB = "";

		int limite = rfcNormalizado.length();
		for (int i = 0; i < limite; i++) {
			char n = rfcNormalizado.charAt(i);
			if (n == ' ')
				n = '_';
			if (n == '?' || n == '¿')
				n = 'Ñ';
			if (n >= '0' && n <= '9') {
				rfcPathB = "/" + rfcNormalizado.substring(i, limite);
				rfcPathCompleto = rfcPathA + rfcPathB;
				rfcPathCompleto = "/" + cabinet + rfcPathCompleto;
				break;
			}
			rfcPathA.append( "/" + n);
		}

		return rfcPathCompleto;

	}

	public String deDecimalABase62(long decimalNumber) {
		String tempVal = decimalNumber == 0 ? "0" : "";
		int mod = 0;
		long base = 62;
		while (decimalNumber != 0) { 
			mod = (int) (decimalNumber % base);
			tempVal = BASE_CHARS.substring(mod, mod + 1) + tempVal;
			decimalNumber = decimalNumber / base;
		}

		return tempVal;
	}

	
	
	public void modificarDocumento(DocumentoDto documentoDto) {
		gestionarDocumentoService.modificarDocumento(documentoDto);
	}
//	public void guardarDocumento(DocumentoDto documentoDto,String userLT) {
//		gestionarDocumentoService.guardarDocumento(documentoDto, gestionarDocumentoService.getVirtualDocumentType(),userLT); 
//	}
	public void guardarDocumento(DocumentoDto documentoDto,String userLT, Map<String, String> attr) {
		gestionarDocumentoService.guardarDocumento(documentoDto, gestionarDocumentoService.getVirtualDocumentType(),userLT, attr); 
	}

	public GestionarDocumentoService getGestionarDocumentoService() {
		return gestionarDocumentoService;
	}

	public void guardarDocumentoVirtual(String idDocumentoXml,
			ArrayList<DocumentoDto> documentosVirtuales) {
		gestionarDocumentoService.guardarDocumentoVirtual(idDocumentoXml, documentosVirtuales);
		
	}
	
	public void borrarDocumento(String idDocumentoXml) {
		gestionarDocumentoService.borrarDocumento(idDocumentoXml);
		
	}

}
