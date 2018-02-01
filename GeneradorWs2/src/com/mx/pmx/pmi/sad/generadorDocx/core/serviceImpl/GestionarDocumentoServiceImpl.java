package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfEnumeration;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVirtualDocument;
import com.documentum.fc.client.acs.IDfAcsRequest;
import com.documentum.fc.client.acs.IDfAcsTransferPreferences;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfTime;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfList;
import com.documentum.fc.common.IDfValue;
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfDeleteNode;
import com.documentum.operations.IDfDeleteOperation;
import com.documentum.operations.IDfExportNode;
import com.documentum.operations.IDfExportOperation;
import com.documentum.operations.IDfMoveOperation;
import com.documentum.operations.IDfOperation;
import com.documentum.operations.IDfOperationError;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoV2Dto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoV3Dto;
import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.DocumentumService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService;

public class GestionarDocumentoServiceImpl implements GestionarDocumentoService {

	private static final String STR_CURRENT = "CURRENT";

	private static final Log log = LogFactory
			.getLog(GestionarDocumentoServiceImpl.class);

	private static final String ERROR_PROCESO_EXPORTACION_ACS = "Error en el proceso de exportación del documento a ACS";
	private static final String ERROR_AL_EXPORTAR_DOCUMENTO = "Error al exportar el(los) documento(s)";
	private static final String ERROR_PROCESO_COPIAR = "Error al copiar el(los) documento(s)";
	private static final String ERROR_RECUPERAR_DOCUMENTO_VIRTUAL = "Error al recuperar documento virtual";
	private static final String ERROR_RECUPERAR_PROPIEDAD = "Error al recuperar una propiedad de un documento";
	private static final String ERROR_PROCESO_ELIMINAR = "Error al eliminar el(los) documento(s)";
	private static final String ERROR_PROCESO_CONSULTAR = "Error al consultar los documentos";
	private static final String ERROR_ABORT_TRANS = "Error al deshacer la transacción";
	private static final String ERROR_PROCESO_GUARDAR = "Error al guardar el(los) documento(s)";
	private static final String ERROR_PROCESO_MOVER = "Error al mover el(los) documento(s)";
	private static final String ERROR_OBTENER_DOCUMENTO = "Error al obtener el documento";
	private static final String ERROR_RUTA_INVALIDA = "La ruta especificada no es válida";
	private static final String ERROR_CREAR_RUTA = "Error al crear la ruta";
	private static final String ERROR_ENVIAR_MAIL = "Error al enviar correo";
	private static final String ERROR_CHECKOUT = "Error al realizar checkout";
	private static final String ERROR_AL_MODIFICAR_DOCUMENTO = "Error al modificar el(los) documento(s)";
	private static final String ERROR_AL_CONSULTAR_RENDICION = "Error al consultar rendición";

	private static final String FROM = " FROM ";
	private static final String STR_COMA = ", ";
	private static final String WHERE_FOLDER = " WHERE FOLDER ('";
	private static final String DM_FOLDER = "dm_folder";
	private static final String R_OBJECT_TYPE = "r_object_type";
	private static final String OBJECT_NAME = "object_name";
	private static final String ID_DOCUMENTO = "iddocumento";

	private static final String ERROR_PROCESO_ANEXAR_DOC_A_VIRTUAL = "Error al anexar documento al documento virtual";

	private DocumentumService documentumService;
	private String documentType;
	private String virtualDocumentType;
	private String qualifierAttribute;
	private String qualifierAttribute2;
	
	public GestionarDocumentoServiceImpl() {
		// IOMH constructor
		documentumService = new DocumentumServiceImpl();
//		documentType = new String("dm_document");
		documentType = new String("pmx_pmi_formt");
//		virtualDocumentType = new String("dm_document");
		virtualDocumentType = new String("pmx_pmi_formt");
//		qualifierAttribute = new String("r_object_id");
//		qualifierAttribute2 = new String("r_object_id");
		qualifierAttribute = new String("id_pemex");
		
	}

	public List<DocumentoV2Dto> consultarDocumentosPorRuta(String ruta) {

		final List<DocumentoV2Dto> retVal = new ArrayList<DocumentoV2Dto>();
		IDfSession session = null;

		try {
			/**IOMH **/
			documentumService = new DocumentumServiceImpl();
			/**IOMH **/
			session = documentumService.getSession("");

			final StringBuffer dql = new StringBuffer(200).append(
					"SELECT r_object_id as ").append(ID_DOCUMENTO).append(
					STR_COMA).append(OBJECT_NAME).append(STR_COMA).append(
					R_OBJECT_TYPE).append(FROM).append(DM_FOLDER).append(
					WHERE_FOLDER).append(ruta).append("') UNION SELECT ")
					.append(qualifierAttribute).append(" as ").append(
							ID_DOCUMENTO).append(STR_COMA).append(OBJECT_NAME)
					.append(STR_COMA).append(R_OBJECT_TYPE).append(FROM)
					.append(documentType).append(WHERE_FOLDER).append(ruta)
					.append("')");

			log.debug(dql.toString());

			final IDfCollection col = new DfQuery(dql.toString()).execute(
					session, DfQuery.DF_READ_QUERY);

			while (col.next()) {
				retVal.add(new DocumentoV2Dto(col.getString(ID_DOCUMENTO), col
						.getString(OBJECT_NAME), DM_FOLDER.equals(col
						.getString(R_OBJECT_TYPE)) ? true : false));
			}

		} catch (Exception e) {
			log.error(ERROR_PROCESO_CONSULTAR, e);
			throw new BusinessException("ERROR_PROCESO_CONSULTAR",
					ERROR_PROCESO_COPIAR);
		} finally {
			documentumService.releaseSession(session);
		}

		return retVal;

	}

	public List<DocumentoV3Dto> consultarDocumentosPorRuta(String documentType,
			String ruta, String[] atributos) {

		final List<DocumentoV3Dto> retVal = new ArrayList<DocumentoV3Dto>();
		IDfSession session = null;

		try {

			session = documentumService.getSession("");

			final StringBuffer dql = new StringBuffer(200).append("SELECT * ")
					.append(FROM).append(documentType).append(WHERE_FOLDER)
					.append(ruta).append("')");

			log.debug(dql.toString());

			final IDfCollection col = new DfQuery(dql.toString()).execute(
					session, DfQuery.DF_READ_QUERY);

			while (col.next()) {
				final DocumentoV3Dto documentoV3 = new DocumentoV3Dto(col
						.getString("r_object_id"), col.getString(OBJECT_NAME),
						DM_FOLDER.equals(col.getString(R_OBJECT_TYPE)) ? true
								: false);

				if (!documentoV3.isEsFolder()) {
					for (int i = 0; i < atributos.length; i++) {
						final IDfValue atributo = col.getValue(atributos[i]);
						documentoV3
								.addAtributo(atributos[i],
										(atributo != null) ? atributo
												.asString() : null);
					}
				}

				retVal.add(documentoV3);

			}

		} catch (Exception e) {
			log.error(ERROR_PROCESO_CONSULTAR, e);
			throw new BusinessException("ERROR_PROCESO_CONSULTAR",
					ERROR_PROCESO_COPIAR);
		} finally {
			documentumService.releaseSession(session);
		}

		return retVal;

	}

	public void copiarDocumento(List<String> idDocumentoList,
			String directorioDestino) {

		IDfSession session = null;

		try {

			session = documentumService.getSession("");

			if (getIdFolder(session, directorioDestino).isNull()) {
				crearFolder(session, directorioDestino);
			}

			final IDfCopyOperation co = new DfClientX().getCopyOperation();
			co.setDestinationFolderId(session
					.getFolderByPath(directorioDestino).getObjectId());

			for (String idDocumento : idDocumentoList) {
				co.add(obtenerDocumento(session, idDocumento));
			}

			if (!co.execute()) {
				logOperationErrors(co);
				throw new BusinessException("ERROR_PROCESO_COPIAR");
			}

		} catch (DfException dfe) {
			log.error(ERROR_PROCESO_COPIAR);
			throw new BusinessException("ERROR_PROCESO_COPIAR",
					ERROR_PROCESO_COPIAR);
		} catch (RuntimeException e) {
			log.error(ERROR_PROCESO_COPIAR);
			throw new BusinessException("ERROR_PROCESO_COPIAR",
					ERROR_PROCESO_COPIAR);

		}

		finally {
			documentumService.releaseSession(session);
		}

	}

	
	public void guardarDocumento(List<DocumentoDto> documentoList, String userLT) {

		IDfSession session = null;

		try {

			session = documentumService.getSession(userLT);
			session.beginTrans();

			for (DocumentoDto documentoDto : documentoList) {

				final IDfSysObject sysObj = (IDfSysObject) session
						.newObject(documentType);
				System.out.println("ID: " + sysObj.getObjectId().getId());
				sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
						.getNombreDocumento()));
				sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
						.getNombreDocumento()));
				sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
				
				//TODO: Esto es especifíco de PMI
				sysObj.setInt("n_numr_pagns",1);
				// sysObj.getObjectId().getId());

				if (getIdFolder(session, documentoDto.getRutaDocumento())
						.isNull()) {
					crearFolder(session, documentoDto.getRutaDocumento());
				}

				sysObj.link(documentoDto.getRutaDocumento());
				documentoDto.addAtributo("id_documento", sysObj.getObjectId().getId());
				setAtributos(sysObj, documentoDto.getAtributos());
				//Linea movida, estaba debajo de sysObj.save()
				System.out.print("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
				documentoDto.setIdDocumento(sysObj.getObjectId().getId());
				sysObj.save();

			}

			session.commitTrans();

		} catch (Exception e) {
			try {
				session.abortTrans();
			} catch (DfException dfe) {
				log.error(ERROR_ABORT_TRANS);
			}
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		} finally {
			documentumService.releaseSession(session);
		}
	}

	private void setAtributos(IDfSysObject sysObj, Map<String, Object> atributos)
			throws DfException {
		if (atributos != null) {
			for (Iterator<Entry<String, Object>> iterator = atributos
					.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Object> atributo = iterator.next();
				String key = atributo.getKey();
				Object value = atributo.getValue();

				if (value instanceof java.lang.String) {
					sysObj.setString(key, (String) value);
				} else if (value instanceof java.lang.Integer) {
					sysObj.setInt(key, (Integer) value);
				} else if (value instanceof java.lang.Boolean) {
					sysObj.setBoolean(key, (Boolean) value);
				} else if (value instanceof java.lang.Double) {
					sysObj.setDouble(key, (Double) value);
				} else if (value instanceof java.util.Date) {
					sysObj.setTime(key, new DfTime((java.util.Date) value));
				}

			}
		}

	}

	public List<IDfSysObject> guardarDocumentoSysObject(
			final IDfSession session, final List<DocumentoDto> documentoList) {

		List<IDfSysObject> retVal = new ArrayList<IDfSysObject>();

		try {

			for (DocumentoDto documentoDto : documentoList) {

				final IDfSysObject sysObj = (IDfSysObject) session
						.newObject(documentType);
				log.debug("ID: " + sysObj.getObjectId().getId());
				sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
						.getNombreDocumento()));
				sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
						.getNombreDocumento()));
				sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
				 sysObj.setString(qualifierAttribute,
				 sysObj.getObjectId().getId());

				if (getIdFolder(session, documentoDto.getRutaDocumento())
						.isNull()) {
					crearFolder(session, documentoDto.getRutaDocumento());
				}

				sysObj.link(documentoDto.getRutaDocumento());
				documentoDto.addAtributo("id_documento", sysObj.getObjectId().getId());
				setAtributos(sysObj, documentoDto.getAtributos());
				
				//>Se movio la linea de sysObj.save() una linea mas abajo
				documentoDto.setIdDocumento(sysObj.getObjectId().getId());
				System.out.print("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
				sysObj.save();
				retVal.add(sysObj);

			}

		} catch (Exception e) {
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		}

		return retVal;
	}

	public IDfSysObject guardarDocumentoSysObject(final IDfSession session,
			final DocumentoDto documentoDto) {

		IDfSysObject sysObj = null;

		try {

			sysObj = (IDfSysObject) session.newObject(documentType);
			log.debug("ID: " + sysObj.getObjectId().getId());
			sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
					.getNombreDocumento()));
			sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
					.getNombreDocumento()));
			sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
			 sysObj.setString(qualifierAttribute,
			 sysObj.getObjectId().getId());

			if (getIdFolder(session, documentoDto.getRutaDocumento()).isNull()) {
				crearFolder(session, documentoDto.getRutaDocumento());
			}

			sysObj.link(documentoDto.getRutaDocumento());
			documentoDto.addAtributo("id_documento", sysObj.getObjectId().getId());

			setAtributos(sysObj, documentoDto.getAtributos());

			
			//Se movio el sysObj.save() una linea mas abajo
			documentoDto.setIdDocumento(sysObj.getObjectId().getId());
			System.out.print("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
			sysObj.save();

		} catch (Exception e) {
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		}

		return sysObj;
	}

	public IDfSysObject guardarDocumentoSysObject(final IDfSession session,
			final DocumentoDto documentoDto, String tipoDocumental) {

		IDfSysObject sysObj = null;

		try {

			sysObj = (IDfSysObject) session.newObject(tipoDocumental);
			log.debug("ID: " + sysObj.getObjectId().getId());
			sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
					.getNombreDocumento()));
			sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
					.getNombreDocumento()));
			sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
			 sysObj.setString(qualifierAttribute,
			 sysObj.getObjectId().getId());

			if (getIdFolder(session, documentoDto.getRutaDocumento()).isNull()) {
				crearFolder(session, documentoDto.getRutaDocumento());
			}

			sysObj.link(documentoDto.getRutaDocumento());
			//TODO IOMH Ajustar al atributo que corresponda, id unico del docuemnto
			documentoDto.addAtributo("id_pemex", sysObj.getObjectId().getId());
			setAtributos(sysObj, documentoDto.getAtributos());

			
			//Se movio una linea para abajo
			documentoDto.setIdDocumento(sysObj.getObjectId().getId());
			System.out.print("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
			sysObj.save();
		} catch (Exception e) {
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		}

		return sysObj;
	}
	
	public void guardarDocumento(DocumentoDto documentoDto, String userLT) {

		final List<DocumentoDto> documentoList = new ArrayList<DocumentoDto>();
		documentoList.add(documentoDto);
		guardarDocumento(documentoList,userLT);

	}

//	public void guardarDocumento(DocumentoDto documentoDto,
//			String tipoDocumental,String userLT) {
//
//		IDfSession session = null;
//
//		try {
//
//			session = documentumService.getSession(userLT);
//			session.beginTrans();
//
//			final IDfSysObject sysObj = (IDfSysObject) session
//					.newObject(tipoDocumental);
//
//			sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
//					.getNombreDocumento()));
//			sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
//					.getNombreDocumento()));
//			sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
//			 sysObj.setString(qualifierAttribute,
//			 sysObj.getObjectId().getId());
//
//			if (getIdFolder(session, documentoDto.getRutaDocumento()).isNull()) {
//				crearFolder(session, documentoDto.getRutaDocumento());
//			}
//
//			sysObj.link(documentoDto.getRutaDocumento());
//
//			
////		TODO IOMH: alinear al tipo documental documentoDto.addAtributo("id_documento", sysObj.getObjectId().getId());
//			documentoDto.addAtributo("id_pemex", sysObj.getObjectId().getId());
//			setAtributos(sysObj, documentoDto.getAtributos());
//			//Linea modificada de lugar, estaba debajo de la de sysObj.save()
//			log.info("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
//			
//			documentoDto.setIdDocumento(sysObj.getObjectId().getId());
//
//			sysObj.save();
//
//			
//			
//
//			session.commitTrans();
//
//		} catch (Exception e) {
//			try {
//				session.abortTrans();
//			} catch (DfException dfe) {
//				log.error(ERROR_ABORT_TRANS);
//			}
//			log.error(ERROR_PROCESO_GUARDAR, e);
//			throw new BusinessException("ERROR_PROCESO_GUARDAR",
//					ERROR_PROCESO_GUARDAR);
//		} finally {
//			documentumService.releaseSession(session);
//		}
//
//	}
	public void guardarDocumento(DocumentoDto documentoDto,
			String tipoDocumental,String userLT, Map<String, String> attr) {

		IDfSession session = null;

		try {

			session = documentumService.getSession(userLT);
			session.beginTrans();

			final IDfSysObject sysObj = (IDfSysObject) session
					.newObject(tipoDocumental);

			sysObj.setObjectName(obtenerNombreSinExtension(documentoDto
					.getNombreDocumento()));
			sysObj.setContentType(obtenerExtensionDocumentum(documentoDto
					.getNombreDocumento()));
			sysObj.setFile(documentoDto.getContenido().getAbsolutePath());
			sysObj.setString(qualifierAttribute,
					 sysObj.getObjectId().getId());
			
			/*Integración de ACL solo aplica para las caratulas*/
			if(attr!=null && attr.get("idACL")!=null&&attr.get("isCaratula")!=null&&attr.get("isCaratula").equals("true")) {
				IDfACL acl = (IDfACL) session.getObject(new DfId(attr.get("idACL")));
				sysObj.setACL(acl);
			}
			
			
			if(attr != null && attr.get("ordenrelacionada")!=null){
			System.out.println("SE guardara con attr "+attr.get("ordenrelacionada"));
			 
			 sysObj.setRepeatingString("n_ordns_relcnds",0,attr.get("ordenrelacionada"));
			}
			if (getIdFolder(session, documentoDto.getRutaDocumento()).isNull()) {
				System.out.println("Se guardara en la ruta:"+documentoDto.getRutaDocumento());
				crearFolder(session, documentoDto.getRutaDocumento());
			}

			sysObj.link(documentoDto.getRutaDocumento());

			
//		TODO IOMH: alinear al tipo documental documentoDto.addAtributo("id_documento", sysObj.getObjectId().getId());
			documentoDto.addAtributo("id_pemex", sysObj.getObjectId().getId());
			setAtributos(sysObj, documentoDto.getAtributos());
			//Linea modificada de lugar, estaba debajo de la de sysObj.save()
			log.info("DENTRO DE GESTIONAR DOCUMENTOSERVICE:"+sysObj.getObjectId().getId());
			
			documentoDto.setIdDocumento(sysObj.getObjectId().getId());
System.out.println("se guarda el nuevo documento");
			sysObj.save();

			
			

			session.commitTrans();

		} catch (Exception e) {
			try {
				session.abortTrans();
			} catch (DfException dfe) {
				log.error(ERROR_ABORT_TRANS);
			}
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		} finally {
			documentumService.releaseSession(session);
		}

	}
	

	public void moverDocumento(List<String> idDocumentoList,
			String directorioDestino) {

		IDfSession session = null;

		try {

			session = documentumService.getSession("");

			if (getIdFolder(session, directorioDestino).isNull()) {
				crearFolder(session, directorioDestino);
			}

			final IDfMoveOperation mo = new DfClientX().getMoveOperation();
			mo.setDestinationFolderId(session
					.getFolderByPath(directorioDestino).getObjectId());

			for (String idDocumento : idDocumentoList) {
				final IDfDocument doc = obtenerDocumento(session, idDocumento);
				mo.setSourceFolderId(doc.getFolderId(0));
				mo.add(doc);

				if (!mo.execute()) {
					logOperationErrors(mo);
					throw new BusinessException("ERROR_PROCESO_MOVER",
							ERROR_PROCESO_MOVER);
				}

			}

		} catch (DfException dfe) {
			log.error(ERROR_PROCESO_MOVER, dfe);
			throw new BusinessException("ERROR_PROCESO_MOVER",
					ERROR_PROCESO_MOVER);
		} catch (RuntimeException e) {
			log.error(ERROR_PROCESO_MOVER, e);
			throw new BusinessException("ERROR_PROCESO_MOVER",
					ERROR_PROCESO_MOVER);
		} finally {
			documentumService.releaseSession(session);
		}

	}

	public List<DocumentoDto> recuperarDocumento(List<String> idDocumentoList,String userLT) {

		Map<String, DocumentoDto> retVal = new HashMap<String, DocumentoDto>();
		Map<String, DocumentoDto> retVal2 = new HashMap<String, DocumentoDto>();
		IDfSession session = null;

		try {
			session = documentumService.getSession(userLT);

			final IDfClientX clientX = new DfClientX();
			final IDfAcsTransferPreferences prefs = clientX
					.getAcsTransferPreferences();
			System.out.println("ACS Activado: " + prefs.isAcsTransferPreferred());

			final IDfExportOperation exportOp = clientX.getExportOperation();
			exportOp.setAcsTransferPreferences(prefs);
			for (String idDocumento : idDocumentoList) {
				final IDfDocument docObj = obtenerDocumento(session,
						idDocumento);
				final DocumentoDto documentoDto = new DocumentoDto();
				documentoDto.setIdDocumento(idDocumento);
				documentoDto.setNombreDocumento(docObj.getObjectName());
                System.out.println("se creó el documento "+documentoDto.getIdDocumento());
				final IDfFolder folder = (IDfFolder) session.getObject(docObj
						.getFolderId(0));
				if (folder != null) {
					documentoDto.setRutaDocumento(folder.getFolderPath(0));
				}
				System.out.println("r_object_id documentoDto:" + idDocumento);
				System.out.println("documentoDto.getIdDocumento:" + idDocumento);
				System.out.println("r_object_id documentoDto:" + documentoDto.getIdDocumento());
				System.out.println("id_documento atributo:" + documentoDto.getAtributos().get("id_documento"));
				System.out.println("valor getDocbaseId:" + docObj.getObjectId().getId());
				retVal.put(idDocumento, documentoDto);
				retVal2.put(docObj.getObjectId().getId(), documentoDto);
				exportOp.add(docObj);
			}

			if (exportOp.execute()) {
				System.out.println("export");
				final IDfList nodes = exportOp.getNodes();
				System.out.println("Export exitoso: " + nodes.getCount());
				if (nodes.getCount() > 0) {
					final IDfExportNode node = (IDfExportNode) nodes.get(0);
					final IDfEnumeration acsRequests = node.getAcsRequests();
					while (acsRequests.hasMoreElements()) {
						final IDfAcsRequest acsRequest = (IDfAcsRequest) acsRequests
								.nextElement();
						final String docURL = acsRequest.makeURL();
						final String docId = acsRequest.getObjectId().getId();
						
						System.out.println("ACS URL para " + docId + ": " + docURL);
						System.out.println("El id que viene dentro del acsRequest: " + acsRequest
								.getObjectId().getId());
						System.out.println("retVal vacia?:"+retVal.isEmpty());
						System.out.println("retVal tamano?:"+retVal.size());
						
						
						final DocumentoDto documentoDto = retVal2.get(acsRequest
								.getObjectId().getId());
								
						System.out.println("se consultó el documento "+documentoDto);
						System.out.println("documento "+documentoDto.getIdDocumento());
						System.out.println("ACS "+acsRequest);
						documentoDto.setUrlDocumento(acsRequest.makeURL());
						documentoDto.setMimeType(acsRequest.getMimeType());
						documentoDto.setContentLength(acsRequest
								.getContentLength());
						retVal.put(acsRequest.getObjectId().getId(),
								documentoDto);
					}
				}
			} else {
				log.error(ERROR_PROCESO_EXPORTACION_ACS);
				logOperationErrors(exportOp);
				throw new BusinessException("ERROR_PROCESO_EXPORTACION_ACS",
						ERROR_PROCESO_EXPORTACION_ACS);
			}

		} catch (DfException dfe) {
			dfe.printStackTrace();
			log.error(ERROR_AL_EXPORTAR_DOCUMENTO, dfe);
			throw new BusinessException("ERROR_AL_EXPORTAR_DOCUMENTO",
					ERROR_AL_EXPORTAR_DOCUMENTO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(ERROR_AL_EXPORTAR_DOCUMENTO, e);
			throw new BusinessException("ERROR_AL_EXPORTAR_DOCUMENTO",
					ERROR_AL_EXPORTAR_DOCUMENTO);
		} finally {
			documentumService.releaseSession(session);
		}

		return new ArrayList<DocumentoDto>(retVal.values());

	}

	public DocumentoDto recuperarDocumento(String idDocumento, String userLT) {

		final List<String> idDocumentoList = new ArrayList<String>();
		idDocumentoList.add(idDocumento);
		List<DocumentoDto> recuperarDocumento = recuperarDocumento(idDocumentoList, userLT);
		return recuperarDocumento.get(0);

	}

	public DocumentoDto recuperarRendicion(String idDocumento, String formato,
			String nombreArchivo) {
		IDfSession session = null;
		FileOutputStream flujoEscritura = null;
		BufferedOutputStream bufOut = null;

		try {
			session = documentumService.getSession("");
			IDfSysObject document = (IDfSysObject) session.getObject(new DfId(
					idDocumento));

			ByteArrayInputStream contentStream = document.getContentEx(formato,
					0);
			byte[] content = null;
			content = new byte[(int) document.getContentSize()];
			contentStream.read(content, 0, content.length);

			File temp = File.createTempFile(nombreArchivo, "");
			temp.deleteOnExit();
			flujoEscritura = new FileOutputStream(temp);

			bufOut = new BufferedOutputStream(flujoEscritura);

			byte[] buf = new byte[1024];
			int numRead = 0;
			while ((numRead = contentStream.read(buf)) >= 0) {
				bufOut.write(buf, 0, numRead);
			}

			flujoEscritura.flush();
			bufOut.flush();

			final DocumentoDto documentoDto = new DocumentoDto();
			documentoDto.setIdDocumento(idDocumento);
			documentoDto.setNombreDocumento(nombreArchivo);
			documentoDto.setContenido(temp);

			return documentoDto;
		} catch (Exception dfe) {
			log.error(ERROR_AL_CONSULTAR_RENDICION, dfe);
			throw new BusinessException("ERROR_AL_CONSULTAR_RENDICION",
					ERROR_AL_CONSULTAR_RENDICION);
		} finally {

			if (bufOut != null) {

				try {
					bufOut.close();
				} catch (IOException e) {
					log.error(
							ERROR_AL_CONSULTAR_RENDICION + " (cerrar flujos)",
							e);
				}
			}

			documentumService.releaseSession(session);

		}

	}

	public void enviarMail(String idUsuario, String idDocumento,
			String mensaje, String titulo) {

		IDfSession session = null;

		try {

			session = documentumService.getSession("");
			obtenerDocumento(session, idDocumento).queue(idUsuario, titulo, 1,
					false, new DfTime("08/09/2000", "mm/dd/yyyy"), mensaje);

		} catch (DfException dfe) {
			log.error(ERROR_ENVIAR_MAIL, dfe);
			throw new BusinessException("ERROR_ENVIAR_MAIL", ERROR_ENVIAR_MAIL);
		} finally {
			documentumService.releaseSession(session);
		}

	}

	public void setDocumentumService(DocumentumService documentumService) {
		this.documentumService = documentumService;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public void setQualifierAttribute(String qualifierAttribute) {
		this.qualifierAttribute = qualifierAttribute;
	}

	public DocumentumService getDocumentumService() {
		return documentumService;
	}

	public String getDocumentType() {
		return documentType;
	}

	public String getQualifierAttribute() {
		return qualifierAttribute;
	}

	private IDfDocument obtenerDocumento(IDfSession session, String idDocumento) {

		try {
			
			String dql=documentType + " WHERE "
							+  qualifierAttribute  + " = '" + idDocumento + "'";
			System.out.println("dql="+dql);
			final IDfDocument docObj = (IDfDocument) session
					.getObjectByQualification(dql);
			System.out.println("Valor documentType:"+documentType);
			System.out.println("Valor qualifierAttribute:"+qualifierAttribute);
			System.out.println("Valor repository:"+documentumService.getRepository());
			System.out.println("Valor idDocumento:"+idDocumento);
			System.out.println();
			if (docObj == null) {
				throw new Exception();
			}
			return docObj;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(ERROR_OBTENER_DOCUMENTO);
			throw new BusinessException("ERROR_OBTENER_DOCUMENTO",
					e.toString());
		}

	}

	public void borrarDocumento(String idDocumento) {
		
		
		IDfSession session=null;
		try {
			documentumService = new DocumentumServiceImpl();
			session = documentumService.getSession("");
			/**IOMH **/
			
			
			String dql=documentType + " WHERE "
							+  qualifierAttribute  + " = '" + idDocumento + "'";
			System.out.println("dql="+dql);
			final IDfDocument docObj = (IDfDocument) session
					.getObjectByQualification(dql);
			System.out.println("Valor documentType:"+documentType);
			System.out.println("Valor qualifierAttribute:"+qualifierAttribute);
			System.out.println("Valor repository:"+documentumService.getRepository());
			System.out.println("Valor idDocumento:"+idDocumento);
			System.out.println();
			if (docObj == null) {
				throw new Exception();
			}
			else {
				IDfClientX clientx  = new DfClientX();
				IDfDeleteOperation delo = clientx.getDeleteOperation();
				delo.setVersionDeletionPolicy(IDfDeleteOperation.ALL_VERSIONS);
				delo.add(docObj);
				if(delo.execute()){
					//return true;
				}
				else
				{
					throw new Exception();
				}
			}
//			return docObj;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(ERROR_OBTENER_DOCUMENTO);
			throw new BusinessException("ERROR_OBTENER_DOCUMENTO",
					e.toString());
		}
		finally {
			documentumService.releaseSession(session);
		}

	}
	private IDfId crearFolder(IDfSession sess, String path) {

		IDfId currentId = null;
		final String pathSep = "/";
		final String type = DM_FOLDER;

		if (path == null || path.length() == 0
				|| path.lastIndexOf(pathSep) == -1) {
			log.error(ERROR_RUTA_INVALIDA);
			throw new BusinessException("ERROR_RUTA_INVALIDA",
					ERROR_RUTA_INVALIDA);
		}

		try {
			final StringBuffer bufFldrPath = new StringBuffer(48);
			final StringTokenizer tokFldrNames = new StringTokenizer(path,
					pathSep);
			final String cabinetName = tokFldrNames.nextToken();
			final StringBuffer cabQual = new StringBuffer(32);
			cabQual.append("dm_cabinet where object_name='")
					.append(cabinetName).append("'");
			currentId = sess.getIdByQualification(cabQual.toString());

			if (currentId.isNull()) {
				final IDfFolder cab = (IDfFolder) sess.newObject("dm_cabinet");
				cab.setObjectName(cabinetName);
				cab.save();
				currentId = cab.getObjectId();
			}
			bufFldrPath.append(pathSep).append(cabinetName);

			while (tokFldrNames.hasMoreTokens()) {
				final String parentPath = bufFldrPath.toString();
				final String fldrName = tokFldrNames.nextToken();
				bufFldrPath.append(pathSep).append(fldrName);
				currentId = getIdFolder(sess, bufFldrPath.toString());
				if (currentId.isNull()) {
					final IDfFolder newFldr = (IDfFolder) sess.newObject(type);
					newFldr.setObjectName(fldrName);
					newFldr.link(parentPath);
					newFldr.save();
					currentId = newFldr.getObjectId();
				}
			}

		} catch (DfException e) {
			log.error(ERROR_CREAR_RUTA);
			throw new BusinessException("ERROR_CREAR_RUTA", ERROR_CREAR_RUTA);
		}

		catch (RuntimeException e) {
			log.error(ERROR_CREAR_RUTA);
			throw new BusinessException("ERROR_CREAR_RUTA", ERROR_CREAR_RUTA);
		}

		return currentId;
	}

	private IDfId getIdFolder(IDfSession sess, String path) throws DfException {

		final String pathSep = "/";
		if (path == null || path.length() == 0) {
			log.error(ERROR_RUTA_INVALIDA);
			throw new IllegalArgumentException(ERROR_RUTA_INVALIDA);
		}
		final int pathSepIndex = path.lastIndexOf(pathSep);

		if (pathSepIndex == -1) {
			log.error(ERROR_RUTA_INVALIDA);
			throw new IllegalArgumentException(ERROR_RUTA_INVALIDA);
		}

		final StringBuffer bufQual = new StringBuffer(32);
		if (pathSepIndex == 0) {
			bufQual.append(" dm_cabinet where object_name='");
			bufQual.append(path.substring(1));
			bufQual.append("'");
		} else {
			bufQual.append(" dm_sysobject where FOLDER('");
			bufQual.append(path.substring(0, pathSepIndex));
			bufQual.append("') ");
			bufQual.append(" and object_name='");
			bufQual.append(path.substring(pathSepIndex + 1));
			bufQual.append("'");
		}

		return sess.getIdByQualification(bufQual.toString());

	}

	private String obtenerExtension(String nombreArchivo) {

		String retVal = null;

		int dot = nombreArchivo.lastIndexOf(".");
		if (dot > 0) {
			retVal = nombreArchivo.substring(dot + 1).toLowerCase(
					Locale.getDefault());
		}

		return retVal;

	}

	private String obtenerNombreSinExtension(String nombreArchivo) {

		String retVal = null;

		int dot = nombreArchivo.lastIndexOf(".");
		if (dot > 0) {
			retVal = nombreArchivo.substring(0, dot);
		} else {
			retVal = nombreArchivo;
		}

		return retVal;

	}

	private String obtenerExtensionDocumentum(String nombreArchivo) {

		String formato = null;

		final DocumentumMimeTypes dmt = DocumentumMimeTypes
				.valueOf(obtenerExtension(nombreArchivo));
		if (dmt != null) {
			formato = dmt.getDocumentumMimeType();
		}

		return formato;

	}

	public DocumentoDto generarRendicion(String idDocumentoOriginal) {

		DocumentoDto retVal = null;
		IDfSession session = null;

		try {
			session = documentumService.getSession("");

			final IDfDocument documento = obtenerDocumento(session,
					idDocumentoOriginal);

			if (documento != null) {
				// TODO: Revisar parametros cuando esté instalado el ATS
				IDfId queue = documento.queue("dm_autorender_win31",
						"rendition", 0, false, new DfTime(new Date()),
						"rendition_req_ps_pdf");
				retVal = recuperarDocumento(queue.getDocbaseId(),"");
			}

		} catch (Exception e) {
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		} finally {
			documentumService.releaseSession(session);
		}

		return retVal;

	}

	public DocumentoDto generarRendicion(String idDocumentoXml,
			String idDocumentoOriginal, DocumentoDto documentoDtoRendicion) {

		IDfSession session = null;

		try {
			session = documentumService.getSession("");

			final IDfDocument documento = obtenerDocumento(session,
					idDocumentoOriginal);

			if (documento != null) {

				IDfId queue = documento.queue("dm_autorender_win31",
						"rendition", 0, false, new DfTime(new Date()),
						"rendition_req_ps_pdf");
				documentoDtoRendicion.setIdDocumento(queue.getId());

				// DocumentoDto pdfConsultado =
				// recuperarRendicion("09c85e318003d2ff",DocumentumMimeTypes.pdf.toString(),documentoDtoRendicion.getNombreDocumento());
				// documentoDtoRendicion
				// .setContenido(pdfConsultado.getContenido());
				// guardarDocumentoVirtual(idDocumentoXml,
				// documentoDtoRendicion);

				// session.getObject(arg0)
				// log.info(""+session.getObject(documentoDtoRendicion.getIdDocumento()));

				// documentoDtoRendicion.setContenido(queue.get)

				// modificarDocumento(retVal);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		} finally {
			documentumService.releaseSession(session);
		}

		return documentoDtoRendicion;

	}

	//TODO: IOMH Cambiar rutina para guardar documento
	public void guardarDocumentoVirtual(String documentoVirtualId,
			List<DocumentoDto> docVirtualHijoIdList) {

		IDfSession session = null;

		try {
			session = documentumService.getSession("");
			session.beginTrans();

			final IDfDocument documento = obtenerDocumento(session,
					documentoVirtualId);

			if (documento != null) {
				documento.checkout();
				final IDfVirtualDocument vDoc = documento.asVirtualDocument(
						STR_CURRENT, false);

				for (DocumentoDto documentoDto : docVirtualHijoIdList) {

					final IDfSysObject sysObj = guardarDocumentoSysObject(
							session, documentoDto, getVirtualDocumentType());
					anexarADocumentoVirtual(session, vDoc, sysObj);

				}

				documento.save();
			}

			session.commitTrans();
		} catch (Exception e) {
			try {
				session.abortTrans();
			} catch (DfException dfe) {
				log.error(ERROR_ABORT_TRANS);
			}
			log.error(ERROR_PROCESO_GUARDAR, e);
			throw new BusinessException("ERROR_PROCESO_GUARDAR",
					ERROR_PROCESO_GUARDAR);
		} finally {
			documentumService.releaseSession(session);
		}
	}

	public void anexarADocumentoVirtual(final IDfSession session,
			final IDfVirtualDocument documentoVirtual,
			final IDfSysObject documentoAnexo) {

		try {

			documentoVirtual.addNode(documentoVirtual.getRootNode(), null,
					documentoAnexo.getChronicleId(), STR_CURRENT, false, true);
			;

		} catch (Exception e) {
			log.error(ERROR_PROCESO_ANEXAR_DOC_A_VIRTUAL, e);
			throw new BusinessException("ERROR_PROCESO_ANEXAR_DOC_A_VIRTUAL",
					ERROR_PROCESO_ANEXAR_DOC_A_VIRTUAL);
		}
	}

	public void guardarDocumentoVirtual(String documentoVirtualId,
			DocumentoDto documentoDto) {

		final List<DocumentoDto> documentoList = new ArrayList<DocumentoDto>();
		documentoList.add(documentoDto);
		guardarDocumentoVirtual(documentoVirtualId, documentoList);

	}

	public IDfCollection ejecutarConsulta(final String dqlQuery) {

		IDfCollection retVal = null;
		IDfSession session = null;

		try {

			session = documentumService.getSession("");

			retVal = new DfQuery(dqlQuery).execute(session,
					DfQuery.DF_READ_QUERY);

		} catch (Exception e) {
			log.error(ERROR_PROCESO_CONSULTAR, e);
			throw new BusinessException("ERROR_PROCESO_CONSULTAR",
					ERROR_PROCESO_COPIAR);
		} finally {
			documentumService.releaseSession(session);
		}

		return retVal;
	}

	public void modificarDocumento(List<DocumentoDto> documentoDtoList) {

		IDfSession session = null;

		try {
			session = documentumService.getSession("");
			session.beginTrans();

			for (DocumentoDto documentoDto : documentoDtoList) {
				final IDfDocument docObj = obtenerDocumento(session,
						documentoDto.getIdDocumento());
				docObj.checkout();
				if (null != documentoDto.getNombreDocumento()
						&& documentoDto.getNombreDocumento().length() > 0) {
					docObj.setObjectName(documentoDto.getNombreDocumento());
				}
				if (null != documentoDto.getContenido()) {
					docObj.setFile(documentoDto.getContenido()
							.getAbsolutePath());
				}
				setAtributos(docObj, documentoDto.getAtributos());
				docObj.save();

			}
			session.commitTrans();
		} catch (DfException ex) {
			log.error(ERROR_CHECKOUT, ex);
			throw new BusinessException("ERROR_CHECKOUT",
					ERROR_AL_MODIFICAR_DOCUMENTO);
		} catch (Exception e) {
			log.error(ERROR_AL_MODIFICAR_DOCUMENTO, e);
			try {
				session.abortTrans();
			} catch (DfException dfe) {
				log.error(ERROR_ABORT_TRANS, dfe);
				throw new BusinessException("ERROR_ABORT_TRANS",
						ERROR_ABORT_TRANS);
			}
		} finally {
			documentumService.releaseSession(session);
		}

	}

	public void modificarDocumento(final IDfSession session,
			List<DocumentoDto> documentoDtoList) {

		try {

			for (DocumentoDto documentoDto : documentoDtoList) {
				final IDfDocument docObj = obtenerDocumento(session,
						documentoDto.getIdDocumento());
				docObj.checkout();
				if (null != documentoDto.getNombreDocumento()
						&& documentoDto.getNombreDocumento().length() > 0) {
					docObj.setObjectName(documentoDto.getNombreDocumento());
				}
				if (null != documentoDto.getContenido()) {
					docObj.setFile(documentoDto.getContenido()
							.getAbsolutePath());
				}
				setAtributos(docObj, documentoDto.getAtributos());

				docObj.save();
				System.out
						.println("idDocumento" + docObj.getObjectId().getId());
			}
		} catch (DfException ex) {
			log.error(ERROR_CHECKOUT, ex);
			throw new BusinessException("ERROR_CHECKOUT",
					ERROR_AL_MODIFICAR_DOCUMENTO);
		}

	}

	public void modificarDocumento(final IDfSession session,
			DocumentoDto documentoDto) {

		try {
			final IDfDocument docObj = obtenerDocumento(session, documentoDto
					.getIdDocumento());
			docObj.checkout();
			if (null != documentoDto.getNombreDocumento()
					&& documentoDto.getNombreDocumento().length() > 0) {
				docObj.setObjectName(documentoDto.getNombreDocumento());
			}
			if (null != documentoDto.getContenido()) {
				docObj.setFile(documentoDto.getContenido().getAbsolutePath());
			}
			setAtributos(docObj, documentoDto.getAtributos());
			docObj.save();
			System.out.println("idDocumento" + docObj.getObjectId().getId());

		} catch (DfException ex) {
			log.error(ERROR_CHECKOUT, ex);
			throw new BusinessException("ERROR_CHECKOUT",
					ERROR_AL_MODIFICAR_DOCUMENTO);
		}

	}

	public void modificarDocumento(DocumentoDto documentoDto) {

		final List<DocumentoDto> documentoDtoList = new ArrayList<DocumentoDto>();
		documentoDtoList.add(documentoDto);
		modificarDocumento(documentoDtoList);

	}

	public List<String> consultarIdDocumento(final String dql,
			final String[] paramName, final Object[] paramValue) {

		List<String> retVal = new ArrayList<String>();

		try {
			final StringBuilder dqlBuilder = new StringBuilder(dql);

			if (paramName != null) {
				for (int i = 0; i < paramName.length; i++) {
					if (i != 0) {
						dqlBuilder.append(" AND ");
					}
					// TODO: Evaluar tipos de parametros
					dqlBuilder.append(paramName[i]).append(" = '").append(
							paramValue[i]).append("' ");
				}
			}

			System.out.println(dql);

			IDfCollection col = ejecutarConsulta(dql.toString());

			while (col.next()) {
				retVal.add(col.getString(getQualifierAttribute()));
			}
		} catch (DfException ex) {
			log.error(ERROR_PROCESO_CONSULTAR, ex);
			throw new BusinessException("ERROR_PROCESO_CONSULTAR",
					ERROR_PROCESO_CONSULTAR);
		}

		return retVal;

	}

	public List<String> consultarIdDocumento(final String dql) {

		return consultarIdDocumento(dql, null, null);

	}

	public List<String> consultarIdDocumento(final String[] paramName,
			final Object[] paramValue) {

		final StringBuilder dql = new StringBuilder("SELECT "
				+ qualifierAttribute + " FROM " + documentType + " WHERE ");
		return consultarIdDocumento(dql.toString(), paramName, paramValue);

	}

	private void logOperationErrors(IDfOperation exportOp) throws DfException {

		final IDfList errors = exportOp.getErrors();
		for (int i = 0; i < errors.getCount(); i++) {
			final IDfOperationError err = (IDfOperationError) errors.get(i);
			log.error(err.getMessage());
		}

	}

	public void eliminarDocumento(List<String> idDocumentoList) {

		IDfSession session = null;

		try {

			session = documentumService.getSession("");

			final IDfDeleteOperation co = new DfClientX().getDeleteOperation();

			for (String idDocumento : idDocumentoList) {
				final IDfDocument doc = obtenerDocumento(session, idDocumento);
				if (doc.isCheckedOut()) {
					throw new RuntimeException("El archivo con id "
							+ idDocumento
							+ " esta bloqueado, no se puede eliminar.");
				}
				if (doc.isVirtualDocument()) {
					IDfVirtualDocument vDoc = doc.asVirtualDocument("CURRENT",
							false);
					co.add(vDoc);
				} else {
					co.add(doc);
				}
			}

			if (!co.execute()) {
				logOperationErrors(co);
				throw new BusinessException("ERROR_PROCESO_COPIAR");
			}

		} catch (Exception dfe) {
			log.error(ERROR_PROCESO_COPIAR, dfe);
			throw new BusinessException("ERROR_PROCESO_ELIMINAR",
					ERROR_PROCESO_ELIMINAR);
		} finally {
			documentumService.releaseSession(session);
		}

	}

	public void eliminarDocumento(String idDocumento) {

		final List<String> documentoList = new ArrayList<String>();
		documentoList.add(idDocumento);
		eliminarDocumento(documentoList);

	}

	public DocumentoDto recuperarDocumentoVisible(String idDocumento) {
        log.info("Buscando documento visible ="+ idDocumento);
		DocumentoDto documento = null;
		StringBuilder dql = new StringBuilder()
				.append("select A.")
				.append(getQualifierAttribute())
				.append(", A.visible from ")
				.append(getVirtualDocumentType())
				.append(
						" A, dmr_containment B where A.r_object_id = B.component_id and B.parent_id IN (select r_object_id from ")
				.append(getVirtualDocumentType()).append(" where ").append(
						getQualifierAttribute()).append(" = '").append(
						idDocumento).append("') and A.visible = 1");
		log.info(dql.toString());
		IDfCollection col = ejecutarConsulta(dql.toString());

		try {
			if (col.next()) {

				if (col.getInt("visible") == 1) {
					 log.info("Documento encontrado ="+ col
								.getString(getQualifierAttribute()));
					documento = recuperarDocumento(col
							.getString(getQualifierAttribute()),"");

				} else {
					throw new RuntimeException("No se encontró el documento");
				}
			}
		} catch (DfException e) {
			log.error(ERROR_RECUPERAR_DOCUMENTO_VIRTUAL, e);
			throw new BusinessException("ERROR_RECUPERAR_DOCUMENTO_VIRTUAL",
					ERROR_RECUPERAR_DOCUMENTO_VIRTUAL);
		}
		return documento;
	}

	public void setVirtualDocumentType(String virtualDocumentType) {
		this.virtualDocumentType = virtualDocumentType;
	}

	public String getVirtualDocumentType() {
		return virtualDocumentType;
	}

	public String obtenerPropiedad(String idDocumento, String nombrePropiedad,
			String tipoDocumental) {

		StringBuilder dql = new StringBuilder().append("select A.").append(
				getQualifierAttribute()).append(
				", A." + nombrePropiedad + " from ").append(tipoDocumental)
				.append(" A where ").append(getQualifierAttribute()).append(
						" = '").append(idDocumento).append("'");
        log.info("dql de propiedad="+dql.toString());
		IDfCollection col = ejecutarConsulta(dql.toString());
		

		try {
			if (col.next()) {

				return col.getString(nombrePropiedad);
			}
		} catch (DfException e) {
			log.error(ERROR_RECUPERAR_PROPIEDAD, e);
			throw new BusinessException("ERROR_RECUPERAR_PROPIEDAD",
					ERROR_RECUPERAR_PROPIEDAD);
		}
		return null;
	}

}
