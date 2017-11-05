package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.model.datastorage.XPathEnhancerParser.main_return;
import org.tempuri.DatosPeoplesoft;
import org.tempuri.Firmas;
import org.tempuri.WSDocumentum;
import org.tempuri.WSDocumentumSoap;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.impl.util.StringUtil;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.DocumentumService;
import com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl.DocumentumServiceImpl;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.CaratulaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistComCrudoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistComercialBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistComercialEstadoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistDemorasBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistFletamentosBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistMaritimoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistOperativoEstadoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistReclamosBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistTerrestreBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GeneradorBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GuiaSimpleBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GuiaSimpleTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.InTransferenciaPrimariaSecundariaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.InTransferenciaPrimariaSecundariaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceCajaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceCajaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceCarpetaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceCarpetaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceExpedientesReservaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceExpedientesReservaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.service.ChecklistID;
import com.rsa.cryptoj.o.in;
import com.rsa.cryptoj.o.pa;


/*Obtencion del proceso que origina [pmx_integraci_n_de_documentos]
SELECT distinct(pr.system_name) FROM dmi_workitem_s wi, dm_workflow_s wf, dm_process_s pr
where 
wi.r_workflow_id = wf.r_object_id
and wf.process_id = pr.r_object_id
and wi.r_workflow_id=(select workflow_id  from dmc_wfsd_element_string where string_value = 'PMI-02C-019-001-000001-2017')
 * */
/*Obtencion de los performers
SELECT wi.r_performer_name, pr.r_act_name FROM dmi_workitem_s wi, dm_workflow_s wf, dm_process_r pr
where 
wi.r_workflow_id = wf.r_object_id
and wf.process_id = pr.r_object_id
and wi.r_act_def_id = pr.r_act_def_id
and wi.r_workflow_id=(select workflow_id  from dmc_wfsd_element_string where string_value = 'PMI-02C-019-001-000001-2017')
and pr.r_act_name like 'Aprobar%'
 * */
public class IntegradorDocx {

	private static final Log log = LogFactory.getLog(IntegradorDocx.class);

	private String checked = "X";
	private DocumentumService documentumService;
	private int seleccionadoDoc = 1;
	DateFormat dateFormat;
	Calendar cal;
	
	public static enum TipoExpediente {
		COMPROD_COMERCIAL, COMPROD_OPERATIVO_MARITIMO, COMPROD_OPERATIVO_TERRESTRE, COMPROD_FLETAMENTOS, COMPROD_TESORERIA, COMCRUD_COMERCIAL, COMCRUD_OPERATIVO_MARITIMO, COMCRUD_TESORERIA, RECLAMOS_DEMORA, RECLAMOS_CAL_CANT, NO_DISPONIBLE;
	}
	
//	public static void main(String[] args) throws Exception {
//		log.info("###### CRUDO ######");
//		log.info(identificarNumeroExpediente("PMI-15E-056-000-000001-2017", "Operativo Marítimo"));
//		log.info(identificarNumeroExpediente("PMI-15E-056-000-000002-2017", "Tesorería"));
//		log.info(identificarNumeroExpediente("PMI-15E-056-000-000003-2017", "Comercial de Crudo"));
//		
//		log.info("\n###### PRODUCTO ######");
//		log.info(identificarNumeroExpediente("PMI-15E-071-000-000004-2017", "Comercial de Producto"));
//		log.info(identificarNumeroExpediente("PMI-15E-071-000-000005-2017", "Operativo Marítimo"));
//		log.info(identificarNumeroExpediente("PMI-15E-071-000-000006-2017", "Operativo Terrestre"));
//		log.info(identificarNumeroExpediente("PMI-15E-071-000-000007-2017", "Fletamentos"));
//		log.info(identificarNumeroExpediente("PMI-15E-071-000-000008-2017", "Tesorería"));
//		
//		log.info("\n###### RECLAMOS ######");
//		log.info(identificarNumeroExpediente("PMI-14E-058-001-000009-2017", null));
//		log.info(identificarNumeroExpediente("PMI-14E-058-002-000010-2017", ""));
//		
//		log.info("\n###### OTROS ######");
//		log.info(identificarNumeroExpediente("PMI-10C-003-002-000011-2017", null));
//	}

	private CaratulaBean identificarNumeroExpediente(IDfSession iDfSession, CaratulaBean paramBean, String expAsunto) throws DfException {
		log.info("Identificando el numero de expediente:"+paramBean.getPeriodoAdicionalNoExpediente()+"; asunto:"+expAsunto);
		String expNumero = paramBean.getPeriodoAdicionalNoExpediente();
		String queryTxt = "SELECT wi.r_performer_name, pr.r_act_name FROM dmi_workitem_s wi, dm_workflow_s wf, dm_process_r pr "
				+ "where "
				+ "wi.r_workflow_id = wf.r_object_id "
				+ "and wf.process_id = pr.r_object_id "
				+ "and wi.r_act_def_id = pr.r_act_def_id "
				+ "and wi.r_workflow_id=(select workflow_id  from dmc_wfsd_element_string where string_value = '"
				+ expNumero
				+ "') "
				+ "and pr.r_act_name like '";
		
		String queryTxt2 = "SELECT wi.r_performer_name, pr.r_act_name FROM dmi_workitem_s wi, dm_workflow_s wf, dm_process_r pr "
				+ "where "
				+ "wi.r_workflow_id = wf.r_object_id "
				+ "and wf.process_id = pr.r_object_id "
				+ "and wi.r_act_def_id = pr.r_act_def_id "
				+ "and wi.r_workflow_id=( "
				+ "SELECT wel.workflow_id  FROM dm_workflow_s wfl, dmc_wfsd_element_string wel  WHERE "
				+ "wfl.r_object_id = wel.workflow_id AND wel.object_name = 'id_infsub_bo' and wfl.object_name like '%"
				+ expAsunto
				+ "%' AND wel.string_value in (SELECT r_object_id FROM pmx_infosubexpediente where numero_expediente='"
				+ expNumero
				+ "') ) and pr.r_act_name like '";
		
		String queryTxt3 = "SELECT supervisor_name as r_performer_name from dmi_queue_item "
				+ "WHERE  task_state = 'finished' AND "
				+ "router_id IN ("
				+ "SELECT r_object_id FROM dm_workflow "
				+ "WHERE object_name LIKE '"
				+ expNumero
				+ "%') AND "
				+ "task_name like '";
		
				String integracion="Integración de Documentos%'",revision="Revisar documentos%'",aprobacion="Aprobar %'";
				
				
		if (StringUtil.isEmptyOrNull(expNumero)) {
//			throw new DfException("Se debe proporcionar un n\u00famero de expediente v\u00e1lido");
			return paramBean;
		}

		if ((expNumero.toUpperCase()).contains("PMI-15E-056") || (expNumero.toUpperCase()).contains("PMI-15E-071") || (expNumero.toUpperCase()).contains("PMI-15E-050")) {
			if (StringUtil.isEmptyOrNull(expAsunto)) {	
//				throw new DfException("Se debe proporcionar un asunto de subexpediente v\u00e1lido");
				return paramBean;
			}

			if (expNumero.contains("PMI-15E-071")) {
				if(expAsunto.equals("Comercial de Productos")) {
					integracion="Integración de Documentos%'";
					revision="Revisar documentos%'";
					aprobacion="Aprobar %'";
					queryTxt = queryTxt2;
				} else if (expAsunto.equals("Operativo Mar\u00edtimo")) {
					integracion="Integración de Documentos%'";
					revision="Revisar documentos%'";
					aprobacion="Aprobar %'";
					queryTxt = queryTxt2;
				} else if (expAsunto.equals("Operativo Terrestre")) {
					integracion="Integración de Documentos%'";
					revision="Revisar documentos%'";
					aprobacion="Aprobar %'";
					queryTxt = queryTxt2;
				} else if (expAsunto.equals("Fletamentos")) {
					integracion="Integración de Documentos%'";
					revision="Revisar documentos%'";
					aprobacion="Aprobar %'";
					queryTxt = queryTxt2;
				} else if (expAsunto.equals("Tesorer\u00eda")) {
					integracion="Integración de Documentos%'";
					revision="Revisar documentos%'";
					aprobacion="Aprobar %'";
					queryTxt = queryTxt2;
				}
				
			} else {
				if (expNumero.contains("PMI-15E-056")) {
					
					if(expAsunto.equals("Comercial de Crudo")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt2;
					} else if (expAsunto.equals("Operativo Mar\u00edtimo")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt2;
					} else if (expAsunto.equals("Tesorer\u00eda")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt2;
					}
				}
				else {
					if(expAsunto.equals("Comercial")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt3;
					} else if (expAsunto.equals("Operativo")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt3;
					} else if (expAsunto.equals("Tesorer\u00eda")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt3;
					} else if (expAsunto.equals("Finanzas")) {
						integracion="Integración de Documentos%'";
						revision="Revisar documentos%'";
						aprobacion="Aprobar %'";
						queryTxt = queryTxt3;
					}
				}
			}
		} else if (expNumero.contains("PMI-14E-058-001")) {
			integracion="Integración de Documentos%'";
			revision="Revisar documentos%'";
			aprobacion="Aprobar %'";
		} else if (expNumero.contains("PMI-14E-058-002")) {
			integracion="Integración de Documentos%'";
			revision="Revisar documentos%'";
			aprobacion="Aprobar %'";
		} else {
			integracion="Integración de Documentos%'";
			revision="Revisar documentos%'";
			aprobacion="Aprobar %'";
		}
		log.info("Query para obtener int:"+queryTxt+integracion);
		IDfQuery query = new DfQuery(queryTxt+integracion);
		IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
		while (resultSet.next()) {
			paramBean.setClasificacionArchivisticaNombreResponsableIntegracion(resultSet.getString("r_performer_name"));
			log.info("Integrador:"+resultSet.getString("r_performer_name"));
		}
		log.info("Query para obtener rev:"+queryTxt+revision);
		IDfQuery query2 = new DfQuery(queryTxt+revision);
		IDfCollection resultSet2 = query2.execute(iDfSession, IDfQuery.DF_READ_QUERY);
		while (resultSet2.next()) {
			paramBean.setClasificacionArchivisticaNombreResponsableRevision(resultSet2.getString("r_performer_name"));
			log.info("Revisor:"+resultSet2.getString("r_performer_name"));
		}
		log.info("Query para obtener apr:"+queryTxt+aprobacion);
		IDfQuery query3 = new DfQuery(queryTxt+aprobacion);
		IDfCollection resultSet3 = query3.execute(iDfSession, IDfQuery.DF_READ_QUERY);
		while (resultSet3.next()) {
			paramBean.setReservaNombreTitularUnidad(resultSet3.getString("r_performer_name"));
			log.info("Aprobador:"+resultSet3.getString("r_performer_name"));
		}

		return paramBean;
	}
	
	public IntegradorDocx() {
		documentumService = new DocumentumServiceImpl();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		cal = Calendar.getInstance();
	}

	private String obtenNumeroPaginasFolder(IDfSession iDfSession, String r_object_id) throws DfException{
		String numeroPaginas = new String();
		String getQuery = "SELECT SUM(n_numr_pagns) AS total_paginas FROM pmx_pce_documento WHERE FOLDER(ID('"
				+ r_object_id + "'), DESCEND) ";
//						+ "AND a_content_type like '%pdf%'";
		IDfQuery query = new DfQuery(getQuery);
		IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
		while (resultSet.next()) {
			numeroPaginas = resultSet.getString("total_paginas");
		}
		return numeroPaginas;
	}
	private String obtenNumeroPaginasDocumento(IDfSession iDfSession, String r_object_id) throws DfException{
		String numeroPaginas = new String();
		if(r_object_id!=null&&r_object_id.trim().length()>0&&!r_object_id.trim().equals("0000000000000000")) {
			String getQuery = "SELECT n_numr_pagns FROM pmx_pce_documento WHERE r_object_id = '"
					+ r_object_id + "'";
			IDfQuery query = new DfQuery(getQuery);
			IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet.next()) {
				numeroPaginas = resultSet.getString("n_numr_pagns");
			}
		}
		return numeroPaginas;
	}
	//ar_fech_cierr, ar_numr_expdnt, ar_serie_docmntl
	private ExpedienteBean obtenDatosExpediente(IDfSession iDfSession, String r_object_id) throws DfException{
		ExpedienteBean expedienteBean = new ExpedienteBean();
		expedienteBean.setrObjectId(r_object_id);
		if(r_object_id!=null&&r_object_id.trim().length()>0&&!r_object_id.trim().equals("0000000000000000")) {
			String getQuery = "select object_name, ar_numr_expdnt, ar_fech_cierr, ar_serie_docmntl from pmx_pmi_expediente where r_object_id = '"
					+ r_object_id + "'";
			IDfQuery query = new DfQuery(getQuery);
			IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet.next()) {
				expedienteBean.setObjectName(resultSet.getString("object_name"));
				expedienteBean.setArNumrExpdnt(resultSet.getString("ar_numr_expdnt"));
				expedienteBean.setArFechCierr(resultSet.getString("ar_fech_cierr"));
				expedienteBean.setArSerieDocmntl(resultSet.getString("ar_serie_docmntl"));
				
			}
		}
		return expedienteBean;		
	}
	private CadidoBean obtenSerieDocumentalExpediente(IDfSession iDfSession, CadidoBean cadidoBean) throws DfException{
		if(cadidoBean.getIdCadido()!=null&&cadidoBean.getIdCadido().trim().length()>0&&!cadidoBean.getIdCadido().trim().equals("0000000000000000")) {
			String getQuery = "select ci.codigo_cica as codigo_cica ,padre	,hospeda_doc	,profundidad	,descripcion	,area_responsable	"
					+ ",fundamento_legal	,articulo_fraccion	,	valor_documental_a	,valor_documental_l	,valor_documental_f	,tramite	"
					+ ",concentracion	,destino_final	,clasificacion	,tiempo_desclasificacion from dm_dbo.CICA ci, dm_dbo.CADIDO ca "
					+ "where ci.codigo_cica = ca.codigo_cica and ci.codigo_cica = (select ar_serie_docmntl from pmx_pmi_expediente where r_object_id='"
					+ cadidoBean.getIdCadido() + "')";
			IDfQuery query = new DfQuery(getQuery);
			IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet.next()) {
				cadidoBean.setCodigoCica(resultSet.getString("codigo_cica"));
				cadidoBean.setPadre(resultSet.getString("padre"));
				cadidoBean.setHospedaDoc(resultSet.getString("hospeda_doc"));
				cadidoBean.setProfundidad(resultSet.getString("profundidad"));
				cadidoBean.setDescripcion(resultSet.getString("descripcion"));
				cadidoBean.setAreaResponsable(resultSet.getString("area_responsable"));
				cadidoBean.setFundamento_legal(resultSet.getString("fundamento_legal"));
				cadidoBean.setArticuloFraccion(resultSet.getString("articulo_fraccion"));
				cadidoBean.setValorDocumentalA(resultSet.getString("valor_documental_a"));
				cadidoBean.setValorDocumentalL(resultSet.getString("valor_documental_l"));
				cadidoBean.setValorDocumentalF(resultSet.getString("valor_documental_f"));
				cadidoBean.setTramite(resultSet.getString("tramite"));
				cadidoBean.setConcentracion(resultSet.getString("concentracion"));
				cadidoBean.setDestinoFinal(resultSet.getString("destino_final"));
				cadidoBean.setClasificacion(resultSet.getString("clasificacion"));
				cadidoBean.setTiempoDesclasificacion(resultSet.getString("tiempo_desclasificacion"));
			}
		}
		return cadidoBean;		
	}
	private String obtenLoginName(IDfSession iDfSession, String userName) throws DfException{
		String loginName = new String();
		if(userName!=null&&userName.trim().length()>0) {
			String getQuery = "select user_login_name from dm_user where user_name like '%"
					+ userName.trim() + "%'";
			IDfQuery query = new DfQuery(getQuery);
			IDfCollection resultSet = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet.next()) {
				loginName = resultSet.getString("user_login_name");
				log.info("LoginName:"+loginName);
			}
		}
		return loginName.toUpperCase();		
	}
	//alter group pmx_pmi_t_juicios add (select user_name from dm_user where user_login_name = 'romero ortiz, francisco')
//	 select user_login_name from dm_user where user_name = 'Hernandez Guzman, Ariadna'
//	 select ar_serie_docmntl from pmx_pmi_expediente where r_object_id = ''
	 	
	 	

	public CaratulaBean integraDatosCaratulaDocumentum(CaratulaBean parambean, Map<String, String> parametros,
			String userLT, String asuntoSubexpediente) throws Exception {
		String loginName = null;
		CadidoBean cadidoBean = new CadidoBean();
		PeopleSoftBean peopleSoftBean = null;
		IntegraPeopleSoftDB integraPeopleSoftDB = new IntegraPeopleSoftDB();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		cadidoBean.setIdCadido(parametros.get("r_object_id"));
		ExpedienteBean expedienteBean= null;
		IDfSession iDfSession = documentumService.getSession(userLT);
		String numeroPaginas = new String();
		try {
			//Obten integrador, revisor y aprobador
			parambean = this.identificarNumeroExpediente(iDfSession, parambean, asuntoSubexpediente);
			log.info("Integrador:"+parambean.getClasificacionArchivisticaNombreResponsableIntegracion());
			log.info("Revisor:"+parambean.getClasificacionArchivisticaNombreResponsableRevision());
			//Obten integrador, revisor y aprobador
		   	numeroPaginas = this.obtenNumeroPaginasFolder(iDfSession, cadidoBean.getIdCadido());			
			parambean.setPeriodoAdicionalNoHojas(numeroPaginas);
			expedienteBean = this.obtenDatosExpediente(iDfSession, cadidoBean.getIdCadido());
			parambean.setPeriodoAdicionalNoExpediente(expedienteBean.getArNumrExpdnt());
			parambean.setClasificacionReservada(expedienteBean.getObjectName());
			
//			if(expedienteBean.getArFechCierr()!=null&&expedienteBean.getArFechCierr().trim().length()>0){
//				try {
//					parambean.setClasificacionFechaClasificacion(simpleDateFormat.format(new Date(expedienteBean.getArFechCierr())));
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//					parambean.setClasificacionFechaClasificacion("");
//				}
//			}
			cadidoBean = this.obtenSerieDocumentalExpediente(iDfSession, cadidoBean);
			parambean.setPeriodoAdicionalDesclasificacion(cadidoBean.getTiempoDesclasificacion());
			parambean.setReservaFundamentoLegal(cadidoBean.getFundamento_legal());
			loginName = this.obtenLoginName(iDfSession, parambean.getClasificacionArchivisticaNombreResponsableIntegracion());
			
			if(loginName!=null && loginName.trim().length()>0) {
				peopleSoftBean = integraPeopleSoftDB.ejecutaWsEmp(loginName);
//				parambean.setClasificacionArchivisticaNombreResponsableIntegracion(parambean.getClasificacionArchivisticaNombreResponsableIntegracion()+"; "+
//				peopleSoftBean.getNombrePosicion());
//				parambean.setReservaNombreTitularUnidad(peopleSoftBean.getDirApellido()+", "+peopleSoftBean.getDirNombre()+"; "+peopleSoftBean.getNombrePosicionDirector());
			}
			
			
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return parambean;
	}
	
//	public static void main(String[] args) throws Exception{
//		log.info("Holas");
//		IntegraPeopleSoftDB integraPeopleSoftDB = new IntegraPeopleSoftDB(); 
//		integraPeopleSoftDB.ejecutaQuery("FROMERO");
//		log.info("sali");
//	}
	public CaratulaBean integraDatosCaratula (CaratulaBean parambean, Map<String,String> parametros, String userLT, String asuntoSubexpediente) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
//		if(parambean.getClasificacionArchivisticaFechaAperturaExpediente()!=null&&!parambean.getClasificacionArchivisticaFechaAperturaExpediente().trim().equals("")){
//			try {
//				parambean.setClasificacionArchivisticaFechaAperturaExpediente(simpleDateFormat.format(new Date(new Long(parambean.getClasificacionArchivisticaFechaAperturaExpediente()).longValue())));
//			} catch (NumberFormatException e) {
//				parambean.setClasificacionArchivisticaFechaAperturaExpediente("");
//				e.printStackTrace();
//			}
//		}
//		
//		if(parambean.getClasificacionArchivisticaFechaCierreExpediente()!=null&&!parambean.getClasificacionArchivisticaFechaCierreExpediente().trim().equals("")){
//			try {
//				parambean.setClasificacionArchivisticaFechaCierreExpediente(simpleDateFormat.format(new Date(new Long(parambean.getClasificacionArchivisticaFechaCierreExpediente()).longValue())));
//			} catch (NumberFormatException e) {
//				parambean.setClasificacionArchivisticaFechaCierreExpediente("");
//				e.printStackTrace();
//			}
//		}
				
		if(parambean.getValorDocumentalAdministrativo()!=null&&!parambean.getValorDocumentalAdministrativo().trim().equals(""))
			parambean.setValorDocumentalAdministrativo(parambean.getValorDocumentalAdministrativo().trim().equals("true")?"X":"");
		else
			parambean.setValorDocumentalAdministrativo("");
		if(parambean.getValorDocumentalLegal()!=null&&!parambean.getValorDocumentalLegal().trim().equals(""))
			parambean.setValorDocumentalLegal(parambean.getValorDocumentalLegal().trim().equals("true")?"X":"");
		else
			parambean.setValorDocumentalLegal("");
		if(parambean.getValorDocumentalFinanciero()!=null&&!parambean.getValorDocumentalFinanciero().trim().equals(""))
			parambean.setValorDocumentalFinanciero(parambean.getValorDocumentalFinanciero().trim().equals("true")?"X":"");
		else
			parambean.setValorDocumentalFinanciero("");
		if(parametros!=null && parametros.get("r_object_id")!=null){
			this.integraDatosCaratulaDocumentum(parambean, parametros, userLT, asuntoSubexpediente);
		}
		return parambean;
	}
	
	public List<Map<String, String>> integraTablaExpedientesDesclasifica(
			List<ExpedientesDesclasificaTablaBean> paramBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
		if (paramBean == null || paramBean.size() == 0) {
			List<ExpedientesDesclasificaTablaBean> list = new ArrayList<ExpedientesDesclasificaTablaBean>();
			list.add(new ExpedientesDesclasificaTablaBean());
			paramBean = list;
		}
		Iterator<ExpedientesDesclasificaTablaBean> iterator = paramBean.iterator();
		while (iterator.hasNext()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			ExpedientesDesclasificaTablaBean bean = iterator.next();
			repl1.put("SJ_" + (i++), bean.getAsunto());
			repl1.put("SJ_" + (i++), bean.getFechaApertura());
			repl1.put("SJ_" + (i++), bean.getFechaCierre());
			repl1.put("SJ_" + (i++), bean.getFechaClasificacion());
			repl1.put("SJ_" + (i++), bean.getFechaDesclasificacion());
			repl1.put("SJ_" + (i++), bean.getFundamentoLegal());
			repl1.put("SJ_" + (i++), bean.getNoExpediente());
			repl1.put("SJ_" + (i++), bean.getPeriodoReserva());
			repl1.put("SJ_" + (i++), bean.getPlazoConservacion());
			repl1.put("SJ_" + (i++), bean.getSeccion());
			repl1.put("SJ_" + (i++), bean.getSerie());
			repl1.put("SJ_" + (i++), bean.getVigenciaDocumental());
			e.add(repl1);
		}
		return e;
	}

	public List<Map<String, String>> integraTablaGuiaSimple(List<GuiaSimpleTablaBean> paramBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
		if (paramBean == null || paramBean.size() == 0) {
			List<GuiaSimpleTablaBean> list = new ArrayList<GuiaSimpleTablaBean>();
			list.add(new GuiaSimpleTablaBean());
			paramBean = list;
		}
		Iterator<GuiaSimpleTablaBean> iterator = paramBean.iterator();
		while (iterator.hasNext()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			GuiaSimpleTablaBean bean = iterator.next();
			repl1.put("SJ_" + (i++), bean.getDescExpediente());
			repl1.put("SJ_" + (i++), bean.getNumExpediente());
			repl1.put("SJ_" + (i++), bean.getUbicacionTopografica());
			repl1.put("SJ_" + (i++), bean.getVolumenTotalArchTramite());
			repl1.put("SJ_" + (i++), bean.getVolumenTotalGenerados());
			repl1.put("SJ_" + (i++), bean.getVolumenTotalTransPrimaria());
			repl1.put("SJ_" + (i++), bean.getVolumenTotalTransSecundaria());
			e.add(repl1);
		}
		return e;
	}

	public List<Map<String, String>> integraTablaIndiceExpedientesReserva(
			List<IndiceExpedientesReservaTablaBean> paramBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
		if (paramBean == null || paramBean.size() == 0) {
			List<IndiceExpedientesReservaTablaBean> list = new ArrayList<IndiceExpedientesReservaTablaBean>();
			list.add(new IndiceExpedientesReservaTablaBean());
			paramBean = list;
		}
		Iterator<IndiceExpedientesReservaTablaBean> iterator = paramBean.iterator();
		while (iterator.hasNext()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			IndiceExpedientesReservaTablaBean bean = iterator.next();
			repl1.put("SJ_" + (i++), bean.getFechaClasificacion());
			repl1.put("SJ_" + (i++), bean.getNumExpediente());
			e.add(repl1);
		}
		return e;
	}

	public List<Map<String, String>> integraTablaInTransferenciaPrimariaSecundaria(
			List<InTransferenciaPrimariaSecundariaTablaBean> paramBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
		if (paramBean == null || paramBean.size() == 0) {
			List<InTransferenciaPrimariaSecundariaTablaBean> list = new ArrayList<InTransferenciaPrimariaSecundariaTablaBean>();
			list.add(new InTransferenciaPrimariaSecundariaTablaBean());
			paramBean = list;
		}
		Iterator<InTransferenciaPrimariaSecundariaTablaBean> iterator = paramBean.iterator();
		while (iterator.hasNext()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			InTransferenciaPrimariaSecundariaTablaBean bean = iterator.next();
			repl1.put("SJ_" + (i++), bean.getClasificacion());
			repl1.put("SJ_" + (i++), bean.getDescripcionExpediente());
			repl1.put("SJ_" + (i++), bean.getDisposiscion());
			repl1.put("SJ_" + (i++), bean.getFechaApertura());
			repl1.put("SJ_" + (i++), bean.getFechaCierre());
			repl1.put("SJ_" + (i++), bean.getNoCaja());
			repl1.put("SJ_" + (i++), bean.getNoTomo());
			repl1.put("SJ_" + (i++), bean.getNumExpediente());
			repl1.put("SJ_" + (i++), bean.getPeriodoConcentracion());
			repl1.put("SJ_" + (i++), bean.getPeriodoTramite());
			repl1.put("SJ_" + (i++), bean.getSerie());
			repl1.put("SJ_" + (i++), bean.getTotalExpedientes());
			repl1.put("SJ_" + (i++), bean.getTotalFojas());
			repl1.put("SJ_" + (i++), bean.getUbicacionTopografica());
			e.add(repl1);
		}
		return e;
	}

	public ValePrestamoBean integraValePrestamo(ValePrestamoBean paramBean, GeneradorBean generadorBean, Map<String,String> parametros) throws Exception{
		/***/
		String loginName = null;
		PeopleSoftBean peopleSoftBean = null;
		IntegraPeopleSoftDB integraPeopleSoftDB = new IntegraPeopleSoftDB();
		IDfSession iDfSession = documentumService.getSession(generadorBean.getUserName());
		try {
		   	loginName = this.obtenLoginName(iDfSession, paramBean.getNombreSolicitante());
			if(loginName!=null && loginName.trim().length()>0) {
				peopleSoftBean = integraPeopleSoftDB.ejecutaWsEmp(loginName);
				paramBean.setUnidadAdministrativa(peopleSoftBean.getArea());
				paramBean.setCargoSolicitante(peopleSoftBean.getPuesto());
				paramBean.setAreaProcedencia(peopleSoftBean.getDepartamento());
			}
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return paramBean;
	}
	
	public List<Map<String, String>> integraTablaValePrestamo(List<ValePrestamoTablaBean> paramBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
//		IDfSession iDfSession = documentumService.getSession("");
//		String numeroPaginas=null;
		if (paramBean == null || paramBean.size() == 0) {
			List<ValePrestamoTablaBean> list = new ArrayList<ValePrestamoTablaBean>();
			list.add(new ValePrestamoTablaBean());
			paramBean = list;
		}
		Iterator<ValePrestamoTablaBean> iterator = paramBean.iterator();
		while (iterator.hasNext()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			ValePrestamoTablaBean bean = iterator.next();
			repl1.put("SJ_" + (i++), bean.getNumExpediente());
			repl1.put("SJ_" + (i++), bean.getNumOrden());
			repl1.put("SJ_" + (i++), bean.getTotalExpedientes());
			repl1.put("SJ_" + (i++), bean.getTotalPagFisica());
			repl1.put("SJ_" + (i++), bean.getTotalPagDigital());
			repl1.put("SJ_" + (i++), bean.getFechaDevolucion());
			repl1.put("SJ_" + (i++), bean.getFechaProroga());
			repl1.put("SJ_" + (i++), bean.getUbicacionTopografica());
			e.add(repl1);
//		   	numeroPaginas = this.obtenNumeroPaginasFolder(iDfSession, cadidoBean.getIdCadido());
//			parambean.setPeriodoAdicionalNoHojas(numeroPaginas);
			
			
		}
		return e;
	}
	public IndiceCajaBean integraIndiceCajaC(GeneradorBean generadorBean, Map<String,String> parametros) throws Exception{
		/***/
		IndiceCajaBean paramBean = null;
//		PeopleSoftBean peopleSoftBean = null;
//		IntegraPeopleSoftDB integraPeopleSoftDB = new IntegraPeopleSoftDB();
//		IDfSession iDfSession = documentumService.getSession(generadorBean.getUserName());
//		try {
//		   	loginName = this.obtenLoginName(iDfSession, paramBean.getNombreSolicitante());
//			if(loginName!=null && loginName.trim().length()>0) {
//				peopleSoftBean = integraPeopleSoftDB.ejecutaWsEmp(loginName);
//				paramBean.setUnidadAdministrativa(peopleSoftBean.getArea());
//				paramBean.setCargoSolicitante(peopleSoftBean.getPuesto());
//				paramBean.setAreaProcedencia(peopleSoftBean.getDepartamento());
//			}
//		} catch (DfException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			documentumService.releaseSession(iDfSession);
//		}
		return paramBean;
	}
	public List<Map<String, String>> integraTablaIndiceCajaC(IndiceCajaTablaBean indiceCajaTablaBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
//		if (paramBean == null || paramBean.size() == 0) {
//			List<ValePrestamoTablaBean> list = new ArrayList<ValePrestamoTablaBean>();
//			list.add(new ValePrestamoTablaBean());
//			paramBean = list;
//		}
//		Iterator<ValePrestamoTablaBean> iterator = paramBean.iterator();
//		while (iterator.hasNext()) {
//			int i = 1;
//			repl1 = new HashMap<String, String>();
//			ValePrestamoTablaBean bean = iterator.next();
//			repl1.put("SJ_" + (i++), bean.getNumExpediente());
//			repl1.put("SJ_" + (i++), bean.getNumOrden());
//			repl1.put("SJ_" + (i++), bean.getTotalExpedientes());
//			repl1.put("SJ_" + (i++), bean.getTotalPagFisica());
//			repl1.put("SJ_" + (i++), bean.getTotalPagDigital());
//			repl1.put("SJ_" + (i++), bean.getFechaDevolucion());
//			repl1.put("SJ_" + (i++), bean.getFechaProroga());
//			repl1.put("SJ_" + (i++), bean.getUbicacionTopografica());
//			e.add(repl1);
//		}
		return e;
	}
	public IndiceCajaBean integraIndiceCajaP(GeneradorBean generadorBean, Map<String,String> parametros) throws Exception{
		
		IndiceCajaBean paramBean = new IndiceCajaBean();
		paramBean.setLineaNegocio(parametros.get("lineaNegocio"));
		paramBean.setMes(parametros.get("mes"));
		paramBean.setAnio(parametros.get("anio"));
		paramBean.setSubexpediente(parametros.get("subexpediente"));
		return paramBean;
	}
	public List<Map<String, String>> integraTablaIndiceCajaP(IndiceCajaTablaBean indiceCajaTablaBean) {
		Map<String, String> repl1;
		List<Map<String, String>> e = new ArrayList<Map<String, String>>();
//		if (paramBean == null || paramBean.size() == 0) {
//			List<ValePrestamoTablaBean> list = new ArrayList<ValePrestamoTablaBean>();
//			list.add(new ValePrestamoTablaBean());
//			paramBean = list;
//		}
//		Iterator<ValePrestamoTablaBean> iterator = paramBean.iterator();
//		while (iterator.hasNext()) {
//			int i = 1;
//			repl1 = new HashMap<String, String>();
//			ValePrestamoTablaBean bean = iterator.next();
//			repl1.put("SJ_" + (i++), bean.getNumExpediente());
//			repl1.put("SJ_" + (i++), bean.getNumOrden());
//			repl1.put("SJ_" + (i++), bean.getTotalExpedientes());
//			repl1.put("SJ_" + (i++), bean.getTotalPagFisica());
//			repl1.put("SJ_" + (i++), bean.getTotalPagDigital());
//			repl1.put("SJ_" + (i++), bean.getFechaDevolucion());
//			repl1.put("SJ_" + (i++), bean.getFechaProroga());
//			repl1.put("SJ_" + (i++), bean.getUbicacionTopografica());
//			e.add(repl1);
//		}
		return e;
	}
	public IndiceCarpetaBean integraIndiceCarpetaP(GeneradorBean generadorBean, Map<String,String> parametros) throws Exception{


		IndiceCarpetaBean paramBean = new IndiceCarpetaBean();
		paramBean.setLineaNegocio(parametros.get("lineaNegocio"));
		paramBean.setMes(parametros.get("mes"));
		paramBean.setAnio(parametros.get("anio"));
		paramBean.setSubexpediente(parametros.get("subexpediente"));
		paramBean.setContraparte(parametros.get("contraparte"));
				return paramBean;

	}
	public List<Map<String, String>> integraTablaIndiceCarpetaP(GeneradorBean generadorBean, IndiceCarpetaBean indiceCarpetaBean, IndiceCarpetaTablaBean indiceCarpetaTablaBean)  throws Exception {
		//SJ1:Estrategia; SJ2:Orden; SJ3:FechaGen; SJ4:CreacionTrade; SJ5:NoHojas; SJ6:Tomo;
		String pagTotales, numTomo, numPaginas;
		pagTotales=numTomo=numPaginas = new String();
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		Firmas wsFirm = new Firmas();
		int pTot=0;
		Map<String, String> repl1;
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		IDfSession iDfSession = documentumService.getSession(generadorBean.getUserName());
		String sQuery1 = "SELECT DISTINCT(child_id) as child_id FROM dm_relation "
				+ "WHERE relation_name = 'pmx_carpetas_integrac_1' AND parent_id IN "
				+ "(SELECT r_object_id FROM pmx_carpetas_integracio WHERE "
				+ "linea_de_negocio = '" + indiceCarpetaBean.getLineaNegocio() + "' "
				+ "AND a_os = " + indiceCarpetaBean.getAnio()
				+ " AND meses = " + indiceCarpetaBean.getMes()
				+ " AND subexpediente = '"	+ indiceCarpetaBean.getSubexpediente() + "')";
		try {
			log.info("Se ejecutara el query:"+sQuery1);
			IDfQuery dQuery1 = new DfQuery(sQuery1);
			IDfCollection resultSet = dQuery1.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			String sQuery2 = "SELECT r_folder_path, r_object_id, object_name, ar_numr_expdnt, n_estrtg_comrcl, r_creation_date "
					+ " FROM pmx_pmi_ecexpcomrop WHERE r_object_id IN "
					+ " (SELECT i_folder_id FROM dm_document WHERE r_object_id IN (";
			String sQuery2_7 = new String();
			int i=0, k;			
			while (resultSet.next()) {
				if(resultSet.getString("child_id")!=null && resultSet.getString("child_id").trim().length()>0) {
					if(i>0)
						sQuery2_7 = sQuery2_7 + ", "; 				
					sQuery2_7 = sQuery2_7 + "'" + resultSet.getString("child_id") + "' ";
					i++;
				}
			}
			if(i>0) {
			sQuery2 +=  sQuery2_7 + "))";
			log.info("Se ejecutara el query:"+sQuery2);
			IDfQuery dQuery2 = new DfQuery(sQuery2);
			IDfCollection resultSet2 = dQuery2.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet2.next()) {
				String sQuery5 = "select max (ar_numr_tomo) as num_tomo from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery6 = "select sum(n_numr_pagns) as total_pag from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery3 = "SELECT DISTINCT(n_numr_ordn_relcnd) as n_numr_ordn_relcnd "
						+ "FROM  pmx_pmi_ecexpcomrop WHERE r_object_id = '" + resultSet2.getString("r_object_id") + "'";
				log.info("Se ejecutara el query:"+sQuery5);
				IDfQuery dQuery5 = new DfQuery(sQuery5);
				IDfCollection resultSet5 = dQuery5.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet5.next()) {
					numTomo = resultSet5.getString("num_tomo");
				}
				log.info("Se ejecutara el query:"+sQuery6);
				IDfQuery dQuery6 = new DfQuery(sQuery6);
				IDfCollection resultSet6 = dQuery6.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet6.next()) {
					numPaginas=resultSet6.getString("total_pag");
					pTot += resultSet6.getInt("total_pag");
				}
				k = 1;
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_" + (k++), resultSet2.getString("n_estrtg_comrcl"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), resultSet2.getString("r_creation_date"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), numPaginas);
				repl1.put("SJ_" + (k++), numTomo);
				listMap.add(repl1);
				log.info("Se ejecutara el query:"+sQuery3);
				IDfQuery dQuery3 = new DfQuery(sQuery3);
				IDfCollection resultSet3 = dQuery3.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet3.next()) {
					if(resultSet3.getString("n_numr_ordn_relcnd")!=null && resultSet3.getString("n_numr_ordn_relcnd").trim().length()>0) {
					String sQuery4 = "select max (ar_numr_tomo) as num_tomo, sum(n_numr_pagns) as total_pag from pmx_pce_documento "
							+ " where any n_ordns_relcnds = '"+ resultSet3.getString("n_numr_ordn_relcnd") + "' "
							+ " and any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
					log.info("Se ejecutara el query:"+sQuery4);
					IDfQuery dQuery4 = new DfQuery(sQuery4);
					IDfCollection resultSet4 = dQuery4.execute(iDfSession, IDfQuery.DF_READ_QUERY);
					while (resultSet4.next()) {
						wsFirm = wSDocumentumSoap.getFirmaPorLineaDeNegocio(resultSet3.getString("n_numr_ordn_relcnd"), indiceCarpetaBean.getLineaNegocio());
						indiceCarpetaBean.setAprobadorExpedientes(wsFirm.getFirmaAprobacion()!=null?wsFirm.getFirmaAprobacion():" ");
						indiceCarpetaBean.setIntegradorExpedientes(wsFirm.getFirmaIntegrador()!=null?wsFirm.getFirmaIntegrador():" ");
						indiceCarpetaBean.setRecibioRevisoExpediente(wsFirm.getFirmaRevisionFisica()!=null?wsFirm.getFirmaRevisionFisica():" ");
						indiceCarpetaBean.setRevisorExpedientes(wsFirm.getFirmaRevisor()!=null?wsFirm.getFirmaRevisor():" ");
						indiceCarpetaBean.setRecepcionExpedientes(wsFirm.getFirmaVistoBueno()!=null?wsFirm.getFirmaVistoBueno():" ");
						k = 1;
						repl1 = new HashMap<String, String>();
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), resultSet3.getString("n_numr_ordn_relcnd"));
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), wsFirm.getFechaTrade().toString());
						repl1.put("SJ_" + (k++), resultSet4.getString("total_pag"));
						repl1.put("SJ_" + (k++), resultSet4.getString("num_tomo"));
						listMap.add(repl1);
					}
					}
				}
			}
			}
			else {
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_1", " ");
				repl1.put("SJ_2", " ");
				repl1.put("SJ_3", " ");
				repl1.put("SJ_4", " ");
				repl1.put("SJ_5", " ");
				repl1.put("SJ_6", " ");
				listMap.add(repl1);
			}
	} catch (DfException e) {
		e.printStackTrace();
		throw e;
	} finally {
		documentumService.releaseSession(iDfSession);
	}
		indiceCarpetaBean.setTotalHojas(new Integer(pTot).toString());
		return listMap;
	}
	public IndiceCarpetaBean integraIndiceCarpetaC(GeneradorBean generadorBean, Map<String,String> parametros) throws Exception{
		IndiceCarpetaBean paramBean = new IndiceCarpetaBean();
		paramBean.setLineaNegocio(parametros.get("lineaNegocio"));
		paramBean.setMes(parametros.get("mes"));
		paramBean.setAnio(parametros.get("anio"));
		paramBean.setSubexpediente(parametros.get("subexpediente"));
		paramBean.setContraparte(parametros.get("contraparte"));
		paramBean.setAreaContractual(parametros.get("areaContractual"));
				return paramBean;
	}
	public List<Map<String, String>> integraTablaIndiceCarpetaC(GeneradorBean generadorBean, IndiceCarpetaBean indiceCarpetaBean, IndiceCarpetaTablaBean indiceCarpetaTablaBean)  throws Exception {
		//SJ1:Estrategia; SJ2:Orden; SJ3:FechaGen; SJ4:CreacionTrade; SJ5:NoHojas; SJ6:Tomo;
		String pagTotales, numTomo, numPaginas;
		pagTotales=numTomo=numPaginas = new String();
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		Firmas wsFirm = new Firmas();
		int pTot=0;
		Map<String, String> repl1;
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		IDfSession iDfSession = documentumService.getSession(generadorBean.getUserName());
		String sQuery1 = "SELECT DISTINCT(child_id) as child_id FROM dm_relation "
				+ "WHERE relation_name = 'pmx_carpetas_integrac_1' AND parent_id IN "
				+ "(SELECT r_object_id FROM pmx_carpetas_integracio WHERE "
				+ "contraparte = '" + indiceCarpetaBean.getContraparte() + "' "
				+ "AND a_os = " + indiceCarpetaBean.getAnio()
				+ " AND meses = " + indiceCarpetaBean.getMes()
				+ " AND subexpediente = '"	+ indiceCarpetaBean.getSubexpediente() + "')";
		try {
			log.info("Se ejecutara el query:"+sQuery1);
			IDfQuery dQuery1 = new DfQuery(sQuery1);
			IDfCollection resultSet = dQuery1.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			String sQuery2 = "SELECT r_folder_path, r_object_id, object_name, ar_numr_expdnt, n_contrprt, r_creation_date "
					+ " FROM pmx_pmi_contrexpcrud WHERE r_object_id IN "
					+ " (SELECT i_folder_id FROM dm_document WHERE r_object_id IN (";
			String sQuery2_7 = new String();
			int i=0, k;			
			while (resultSet.next()) {
				if(resultSet.getString("child_id")!=null && resultSet.getString("child_id").trim().length()>0) {
					if(i>0)
						sQuery2_7 = sQuery2_7 + ", "; 				
					sQuery2_7 = sQuery2_7 + "'" + resultSet.getString("child_id") + "' ";
					i++;
				}
			}
			if(i>0) {
			sQuery2 +=  sQuery2_7 + "))";
			log.info("Se ejecutara el query:"+sQuery2);
			IDfQuery dQuery2 = new DfQuery(sQuery2);
			IDfCollection resultSet2 = dQuery2.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet2.next()) {
				String sQuery5 = "select max (ar_numr_tomo) as num_tomo from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery6 = "select sum(n_numr_pagns) as total_pag from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery3 = "SELECT DISTINCT(n_numr_ordn_relcnd) as n_numr_ordn_relcnd "
						+ "FROM  pmx_pmi_contrexpcrud WHERE r_object_id = '" + resultSet2.getString("r_object_id") + "'";
				log.info("Se ejecutara el query:"+sQuery5);
				IDfQuery dQuery5 = new DfQuery(sQuery5);
				IDfCollection resultSet5 = dQuery5.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet5.next()) {
					numTomo = resultSet5.getString("num_tomo");
				}
				log.info("Se ejecutara el query:"+sQuery6);
				IDfQuery dQuery6 = new DfQuery(sQuery6);
				IDfCollection resultSet6 = dQuery6.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet6.next()) {
					numPaginas=resultSet6.getString("total_pag");
					pTot += resultSet6.getInt("total_pag");
				}
				k = 1;
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_" + (k++), resultSet2.getString("n_contrprt"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), resultSet2.getString("r_creation_date"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), numPaginas);
				repl1.put("SJ_" + (k++), numTomo);
				listMap.add(repl1);
				log.info("Se ejecutara el query:"+sQuery3);
				IDfQuery dQuery3 = new DfQuery(sQuery3);
				IDfCollection resultSet3 = dQuery3.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet3.next()) {
					if(resultSet3.getString("n_numr_ordn_relcnd")!=null && resultSet3.getString("n_numr_ordn_relcnd").trim().length()>0) {
					String sQuery4 = "select max (ar_numr_tomo) as num_tomo, sum(n_numr_pagns) as total_pag from pmx_pce_documento "
							+ " where any n_ordns_relcnds = '"+ resultSet3.getString("n_numr_ordn_relcnd") + "' "
							+ " and any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
					log.info("Se ejecutara el query:"+sQuery4);
					IDfQuery dQuery4 = new DfQuery(sQuery4);
					IDfCollection resultSet4 = dQuery4.execute(iDfSession, IDfQuery.DF_READ_QUERY);
					while (resultSet4.next()) {
						wsFirm = wSDocumentumSoap.getFirmaPorLineaDeNegocio(resultSet3.getString("n_numr_ordn_relcnd"), indiceCarpetaBean.getLineaNegocio());
						indiceCarpetaBean.setAprobadorExpedientes(wsFirm.getFirmaAprobacion()!=null?wsFirm.getFirmaAprobacion():" ");
						indiceCarpetaBean.setIntegradorExpedientes(wsFirm.getFirmaIntegrador()!=null?wsFirm.getFirmaIntegrador():" ");
						indiceCarpetaBean.setRecibioRevisoExpediente(wsFirm.getFirmaRevisionFisica()!=null?wsFirm.getFirmaRevisionFisica():" ");
						indiceCarpetaBean.setRevisorExpedientes(wsFirm.getFirmaRevisor()!=null?wsFirm.getFirmaRevisor():" ");
						indiceCarpetaBean.setRecepcionExpedientes(wsFirm.getFirmaVistoBueno()!=null?wsFirm.getFirmaVistoBueno():" ");
						k = 1;
						repl1 = new HashMap<String, String>();
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), resultSet3.getString("n_numr_ordn_relcnd"));
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), wsFirm.getFechaTrade().toString());
						repl1.put("SJ_" + (k++), resultSet4.getString("total_pag"));
						repl1.put("SJ_" + (k++), resultSet4.getString("num_tomo"));
						listMap.add(repl1);
					}
					}
				}
			}
			}
			else {
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_1", " ");
				repl1.put("SJ_2", " ");
				repl1.put("SJ_3", " ");
				repl1.put("SJ_4", " ");
				repl1.put("SJ_5", " ");
				repl1.put("SJ_6", " ");
				listMap.add(repl1);
			}
	} catch (DfException e) {
		e.printStackTrace();
		throw e;
	} finally {
		documentumService.releaseSession(iDfSession);
	}
		indiceCarpetaBean.setTotalHojas(new Integer(pTot).toString());
		return listMap;

	}

	public List<Map<String, String>> integraTablaIndiceCarpetaE(GeneradorBean generadorBean, IndiceCarpetaBean indiceCarpetaBean, IndiceCarpetaTablaBean indiceCarpetaTablaBean)  throws Exception {
		//SJ1:Estrategia; SJ2:Orden; SJ3:FechaGen; SJ4:CreacionTrade; SJ5:NoHojas; SJ6:Tomo;
		String pagTotales, numTomo, numPaginas;
		pagTotales=numTomo=numPaginas = new String();
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		Firmas wsFirm = new Firmas();
		int pTot=0;
		Map<String, String> repl1;
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		IDfSession iDfSession = documentumService.getSession(generadorBean.getUserName());
		String sQuery1 = "SELECT DISTINCT(child_id) as child_id FROM dm_relation "
				+ "WHERE relation_name = 'pmx_carpetas_integrac_1' AND parent_id IN "
				+ "(SELECT r_object_id FROM pmx_carpetas_integracio WHERE "
				+ "area_contractual = '" + indiceCarpetaBean.getContraparte() + "' "
				+ "AND a_os = " + indiceCarpetaBean.getAnio()
				+ " AND meses = " + indiceCarpetaBean.getMes()
				+ " AND subexpediente = '"	+ indiceCarpetaBean.getSubexpediente() + "')";
		try {
			log.info("Se ejecutara el query:"+sQuery1);
			IDfQuery dQuery1 = new DfQuery(sQuery1);
			IDfCollection resultSet = dQuery1.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			String sQuery2 = "SELECT r_folder_path, r_object_id, object_name, ar_numr_expdnt, area_contractual, r_creation_date "
					+ " FROM pmx_pmi_comrcld_std WHERE r_object_id IN "
					+ " (SELECT i_folder_id FROM dm_document WHERE r_object_id IN (";
			String sQuery2_7 = new String();
			int i=0, k;			
			while (resultSet.next()) {
				if(resultSet.getString("child_id")!=null && resultSet.getString("child_id").trim().length()>0) {
					if(i>0)
						sQuery2_7 = sQuery2_7 + ", "; 				
					sQuery2_7 = sQuery2_7 + "'" + resultSet.getString("child_id") + "' ";
					i++;
				}
			}
			if(i>0) {
			sQuery2 +=  sQuery2_7 + "))";
			log.info("Se ejecutara el query:"+sQuery2);
			IDfQuery dQuery2 = new DfQuery(sQuery2);
			IDfCollection resultSet2 = dQuery2.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			while (resultSet2.next()) {
				String sQuery5 = "select max (ar_numr_tomo) as num_tomo from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery6 = "select sum(n_numr_pagns) as total_pag from pmx_pce_documento where any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
				String sQuery3 = "SELECT DISTINCT(n_mero_de_orden) as n_numr_ordn_relcnd "
						+ "FROM  pmx_pmi_comrcld_std WHERE r_object_id = '" + resultSet2.getString("r_object_id") + "'";
				
//				SELECT DISTINCT(n_mero_de_orden) as n_numr_ordn_relcnd FROM  pmx_pmi_comrcld_std WHERE r_object_id =
				
				log.info("Se ejecutara el query:"+sQuery5);
				IDfQuery dQuery5 = new DfQuery(sQuery5);
				IDfCollection resultSet5 = dQuery5.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet5.next()) {
					numTomo = resultSet5.getString("num_tomo");
				}
				log.info("Se ejecutara el query:"+sQuery6);
				IDfQuery dQuery6 = new DfQuery(sQuery6);
				IDfCollection resultSet6 = dQuery6.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet6.next()) {
					numPaginas=resultSet6.getString("total_pag");
					pTot += resultSet6.getInt("total_pag");
				}
				k = 1;
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_" + (k++), resultSet2.getString("area_contractual"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), resultSet2.getString("r_creation_date"));
				repl1.put("SJ_" + (k++), " ");
				repl1.put("SJ_" + (k++), numPaginas);
				repl1.put("SJ_" + (k++), numTomo);
				listMap.add(repl1);
				log.info("Se ejecutara el query:"+sQuery3);
				IDfQuery dQuery3 = new DfQuery(sQuery3);
				IDfCollection resultSet3 = dQuery3.execute(iDfSession, IDfQuery.DF_READ_QUERY);
				while (resultSet3.next()) {
					if(resultSet3.getString("n_numr_ordn_relcnd")!=null && resultSet3.getString("n_numr_ordn_relcnd").trim().length()>0) {
					String sQuery4 = "select max (ar_numr_tomo) as num_tomo, sum(n_numr_pagns) as total_pag from pmx_pce_documento "
							+ " where any n_ordns_relcnds = '"+ resultSet3.getString("n_numr_ordn_relcnd") + "' "
							+ " and any i_folder_id = '" + resultSet2.getString("r_object_id") + "'";
					log.info("Se ejecutara el query:"+sQuery4);
					IDfQuery dQuery4 = new DfQuery(sQuery4);
					IDfCollection resultSet4 = dQuery4.execute(iDfSession, IDfQuery.DF_READ_QUERY);
					while (resultSet4.next()) {
						wsFirm = wSDocumentumSoap.getFirmaPorLineaDeNegocio(resultSet3.getString("n_numr_ordn_relcnd"), indiceCarpetaBean.getLineaNegocio());
						indiceCarpetaBean.setAprobadorExpedientes(wsFirm.getFirmaAprobacion()!=null?wsFirm.getFirmaAprobacion():" ");
						indiceCarpetaBean.setIntegradorExpedientes(wsFirm.getFirmaIntegrador()!=null?wsFirm.getFirmaIntegrador():" ");
						indiceCarpetaBean.setRecibioRevisoExpediente(wsFirm.getFirmaRevisionFisica()!=null?wsFirm.getFirmaRevisionFisica():" ");
						indiceCarpetaBean.setRevisorExpedientes(wsFirm.getFirmaRevisor()!=null?wsFirm.getFirmaRevisor():" ");
						indiceCarpetaBean.setRecepcionExpedientes(wsFirm.getFirmaVistoBueno()!=null?wsFirm.getFirmaVistoBueno():" ");
						k = 1;
						repl1 = new HashMap<String, String>();
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), resultSet3.getString("n_numr_ordn_relcnd"));
						repl1.put("SJ_" + (k++), " ");
						repl1.put("SJ_" + (k++), wsFirm.getFechaTrade().toString());
						repl1.put("SJ_" + (k++), resultSet4.getString("total_pag"));
						repl1.put("SJ_" + (k++), resultSet4.getString("num_tomo"));
						listMap.add(repl1);
					}
					}
				}
			}
			}
			else {
				repl1 = new HashMap<String, String>();
				repl1.put("SJ_1", " ");
				repl1.put("SJ_2", " ");
				repl1.put("SJ_3", " ");
				repl1.put("SJ_4", " ");
				repl1.put("SJ_5", " ");
				repl1.put("SJ_6", " ");
				listMap.add(repl1);
			}
	} catch (DfException e) {
		e.printStackTrace();
		throw e;
	} finally {
		documentumService.releaseSession(iDfSession);
	}
		indiceCarpetaBean.setTotalHojas(new Integer(pTot).toString());
		return listMap;

	}

	
	public List<ChecklistReclamosBean> integraChecklistsReclamos(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistReclamosBean> listChecklistReclamosBean = new ArrayList<ChecklistReclamosBean>();
		ChecklistReclamosBean checklistReclamosBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String hojasDocumento = null;
		int conteoHojas=1;
		String object_id = null;
		int seleccionado;
		int digital;

		String getOrdenesQuery = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			
			   
			while (collOrdenesRelacionadas.next()) {
				conteoHojas=1;
				checklistReclamosBean = new ChecklistReclamosBean();
				checklistReclamosBean.setFecha(dateFormat.format(cal.getTime()));
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistReclamosBean.setOrdenPmi(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					checklistReclamosBean = registraChecklistReclamos(checklistReclamosBean, idDocumento,
							descripcion, seleccionado, digital);
				}
				checklistReclamosBean.setNumeroHojas(Integer.toString(conteoHojas));
				listChecklistReclamosBean.add(checklistReclamosBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistReclamosBean;
	}

	public List<ChecklistDemorasBean> integraChecklistsDemoras(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistDemorasBean> listChecklistDemorasBean = new ArrayList<ChecklistDemorasBean>();
		ChecklistDemorasBean checklistDemorasBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String hojasDocumento = null;
		int conteoHojas=1;
		String object_id = null;
		int seleccionado;
		int digital;

		String getOrdenesQuery = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			
			   
			while (collOrdenesRelacionadas.next()) {
				conteoHojas=1;
				checklistDemorasBean = new ChecklistDemorasBean();
				checklistDemorasBean.setFecha(dateFormat.format(cal.getTime()));
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistDemorasBean.setOrdenPmi(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					checklistDemorasBean = registraChecklistDemoras(checklistDemorasBean, idDocumento,
							descripcion, seleccionado, digital);
				}
				checklistDemorasBean.setNumeroFolios(Integer.toString(conteoHojas));
				listChecklistDemorasBean.add(checklistDemorasBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistDemorasBean;
	}
	
	public ChecklistComCrudoBean integraChecklistsComun(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		ChecklistComCrudoBean checklistDemorasBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		try{
		getDocumentosQuery = "select r_object_id from pmx_pmi_expediente where ar_numr_expdnt = '"
				+ expediente + "'";
		queryDoc = new DfQuery(getDocumentosQuery);

		collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);
		while(collDocumentos.next()){
		checklistDemorasBean = new ChecklistComCrudoBean();
				checklistDemorasBean.setComunicacionesAlCliente(dateFormat.format(cal.getTime()));
				checklistDemorasBean.setComunicacionesDelCliente(asuntoSubexpediente);
				checklistDemorasBean.setNumHojas(collDocumentos.getString("r_object_id"));
				checklistDemorasBean.setConfirmacionCompraVenta(this.obtenNumeroPaginasFolder(iDfSession, checklistDemorasBean.getNumHojas()));
		}
		
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return checklistDemorasBean;
	}

	public List<Map<String, String>> integraTablaExpedientesTesoreria(
			String numeroExpediente, String userLT, String rutaDoc) throws DfException {
		List<Map<String, String>> es = new ArrayList<Map<String, String>>();
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		IDfSession iDfSession = documentumService.getSession(userLT);
		int conteo =0;
		try{
			Map<String, String> repl1;
			getDocumentosQuery = "SELECT object_name,n_numr_pagns,ar_contnd_dgtl FROM pmx_pce_documento WHERE FOLDER('"+rutaDoc+"', DESCEND)";
					
			queryDoc = new DfQuery(getDocumentosQuery);
			collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

		while (collDocumentos.next()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			repl1.put("SJ_" + (i++), collDocumentos.getString("object_name"));
			repl1.put("SJ_" + (i++), collDocumentos.getString("n_numr_pagns"));
			repl1.put("SJ_" + (i++), collDocumentos.getBoolean("ar_contnd_dgtl")==true?"X":"");
			es.add(repl1);
			conteo++;
		}
		if (conteo == 0) {
			List<IndiceExpedientesReservaTablaBean> list = new ArrayList<IndiceExpedientesReservaTablaBean>();
			IndiceExpedientesReservaTablaBean indiceExpedientesReservaTablaBean = new IndiceExpedientesReservaTablaBean();
			list.add(indiceExpedientesReservaTablaBean);
			int i = 1;
			repl1 = new HashMap<String, String>();
			repl1.put("SJ_" + (i++), indiceExpedientesReservaTablaBean.getFechaClasificacion());
			repl1.put("SJ_" + (i++), indiceExpedientesReservaTablaBean.getNumExpediente());
			es.add(repl1);
		}
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return es;
	}

	public List<Map<String, String>> integraTablaExpedientesComun(
			String numeroExpediente, String userLT) throws DfException {
		List<Map<String, String>> es = new ArrayList<Map<String, String>>();
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		IDfSession iDfSession = documentumService.getSession(userLT);
		int conteo =0;
		try{
			Map<String, String> repl1;
			getDocumentosQuery = "SELECT object_name,n_numr_pagns,ar_contnd_dgtl FROM pmx_pce_documento WHERE FOLDER(ID('"+numeroExpediente+ "'), DESCEND)";
					
			queryDoc = new DfQuery(getDocumentosQuery);
			collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

		while (collDocumentos.next()) {
			int i = 1;
			repl1 = new HashMap<String, String>();
			repl1.put("SJ_" + (i++), collDocumentos.getString("object_name"));
			repl1.put("SJ_" + (i++), collDocumentos.getString("n_numr_pagns"));
			repl1.put("SJ_" + (i++), collDocumentos.getBoolean("ar_contnd_dgtl")==true?"X":"");
			es.add(repl1);
			conteo++;
		}
		if (conteo == 0) {
			List<IndiceExpedientesReservaTablaBean> list = new ArrayList<IndiceExpedientesReservaTablaBean>();
			IndiceExpedientesReservaTablaBean indiceExpedientesReservaTablaBean = new IndiceExpedientesReservaTablaBean();
			list.add(indiceExpedientesReservaTablaBean);
			int i = 1;
			repl1 = new HashMap<String, String>();
			repl1.put("SJ_" + (i++), indiceExpedientesReservaTablaBean.getFechaClasificacion());
			repl1.put("SJ_" + (i++), indiceExpedientesReservaTablaBean.getNumExpediente());
			es.add(repl1);
		}
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return es;
	}
	
	public List<ChecklistTerrestreBean> integraChecklistsTerrestre(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistTerrestreBean> listChecklistTerrestreBean = new ArrayList<ChecklistTerrestreBean>();
		ChecklistTerrestreBean checklistTerrestreBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String hojasDocumento = null;
		int conteoHojas=1;
		String object_id = null;
		int seleccionado;
		int digital;

		String getOrdenesQuery = "SELECT distinct orden_relacionada "
				+ "FROM dm_dbo.DOCUMENTO_SELECCIONADO "
				+ "WHERE numero_expediente = '"
				+ expediente
				+ "' AND descripcion_expediente = '"
				+ asuntoSubexpediente
				+ "' AND (orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd FROM pmx_pmi_ec_prodcts "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ecexpcomrop WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_fletmnts WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_tesrr "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "'))";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
//			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			
			   
//			while (collOrdenesRelacionadas.next()) {
				conteoHojas=1;
				checklistTerrestreBean = new ChecklistTerrestreBean();
				checklistTerrestreBean.setFecha(dateFormat.format(cal.getTime()));
//				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistTerrestreBean.setOrdenPmi(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' ";
//						+ "AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					checklistTerrestreBean = registraChecklistTerrestre(checklistTerrestreBean, idDocumento,
							descripcion, seleccionado, digital);
				}
				checklistTerrestreBean.setNumeroFolios(Integer.toString(conteoHojas));
				listChecklistTerrestreBean.add(checklistTerrestreBean);
//			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistTerrestreBean;
	}

	private ChecklistReclamosBean registraChecklistReclamos(ChecklistReclamosBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {
		if (idDocumento.trim().equals(ChecklistID.Acuerdos) && seleccionado == seleccionadoDoc) {
			bean.setAcuerdos(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nota_informativa) && seleccionado == seleccionadoDoc) {
			bean.setNotaInformativa(checked);
		}if (idDocumento.trim().equals(ChecklistID.Notificacion_del_reclamo) && seleccionado == seleccionadoDoc) {
			bean.setNotificacionReclamo(checked);
		}if (idDocumento.trim().equals(ChecklistID.Correo_de_ajuste_de_volumen) && seleccionado == seleccionadoDoc) {
			bean.setCorreoAjusteVolumen(checked);
		}if (idDocumento.trim().equals(ChecklistID.Hoja_de_analisis_de_temperatura_del_cargamento_frc_05) && seleccionado == seleccionadoDoc) {
			bean.setHojaAnalisisTemperaturaCargamento(checked);
		}if (idDocumento.trim().equals(ChecklistID.Hoja_de_analisis_de_viaje_carga_y_descarga_frc_02) && seleccionado == seleccionadoDoc) {
			bean.setHojaAnalisisViajeCargaDescarga(checked);
		}if (idDocumento.trim().equals(ChecklistID.Hoja_de_analisis_de_volumen_en_barco_frc_03) && seleccionado == seleccionadoDoc) {
			bean.setHojaAnalisisVolumenBarco(checked);
		}if (idDocumento.trim().equals(ChecklistID.Hoja_de_analisis_del_vef_frc_04) && seleccionado == seleccionadoDoc) {
			bean.setHojaAnalisisVef(checked);
		}if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_de_carga) && seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionCarga(checked);
		}if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_de_descarga) && seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionDescarga(checked);
		}if (idDocumento.trim().equals(ChecklistID.Reporte_de_reanalisis_de_laboratorio) && seleccionado == seleccionadoDoc) {
			bean.setReporteReanaisisLaboratorio(checked);
		}if (idDocumento.trim().equals(ChecklistID.Solicitud_de_cobro_a_tesoreria) && seleccionado == seleccionadoDoc) {
			bean.setSolicitudCobroTesoreria(checked);
		}if (idDocumento.trim().equals(ChecklistID.Solicitud_de_pago_a_tesoreria) && seleccionado == seleccionadoDoc) {
			bean.setSolicitudPagoTesoreria(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones) && seleccionado == seleccionadoDoc) {
			bean.setComunicaciones(checked);
		}
		return bean;
		}
	private ChecklistDemorasBean registraChecklistDemoras(ChecklistDemorasBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {
		if (idDocumento.trim().equals(ChecklistID.Nota_informativaDem) && seleccionado == seleccionadoDoc) {
			bean.setNotaInformativa(checked);
		}if (idDocumento.trim().equals(ChecklistID.Hoja_de_calculo_final) && seleccionado == seleccionadoDoc) {
			bean.setHojaCalculoFinal(checked);
		}if (idDocumento.trim().equals(ChecklistID.Reclamo_inicial_y_calculo_inicial_contraparte) && seleccionado == seleccionadoDoc) {
			bean.setReclamoIncialCalculoInicialContraparte(checked);
		}if (idDocumento.trim().equals(ChecklistID.Reclamo_inicial_y_calculo_inicial_pmi) && seleccionado == seleccionadoDoc) {
			bean.setReclamoInicialCalculoInicialPmi(checked);
		}if (idDocumento.trim().equals(ChecklistID.Respaldo_negociacion) && seleccionado == seleccionadoDoc) {
			bean.setRespaldoNegociacion(checked);
		}if (idDocumento.trim().equals(ChecklistID.Solicitud_de_cobro_a_tesoreriaDem) && seleccionado == seleccionadoDoc) {
			bean.setSolicitudCobroTesoreria(checked);
		}if (idDocumento.trim().equals(ChecklistID.Solicitud_de_pago_a_tesoreriaDem) && seleccionado == seleccionadoDoc) {
			bean.setSolicitudPagoTesoreria(checked);
		}if (idDocumento.trim().equals(ChecklistID.Otros_especificar) && seleccionado == seleccionadoDoc) {
			bean.setOtrosEspecificar(checked);
		}if (idDocumento.trim().equals(ChecklistID.Documentacion_soporte_cobro) && seleccionado == seleccionadoDoc) {
			bean.setDocumentacionSoporteCobro(checked);
		}if (idDocumento.trim().equals(ChecklistID.Documentacion_soporte_pago) && seleccionado == seleccionadoDoc) {
			bean.setDocumentacionSoportePago(checked);
		}
		return bean;
		}
	
	private ChecklistOperativoEstadoBean registraChecklistOperativoEstado(ChecklistOperativoEstadoBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {
		
		if (idDocumento.trim().equals(ChecklistID.Nominaciones_mensuales)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setNominacionesMensuales(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setNominacionesMensualesC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Registros_de_entrega_diarios)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setRegistrosEntregaDiarios(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setRegistrosEntregaDiariosC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Certificados_de_calidad_cantidad)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setCertificadoCalidadCantidad(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCertificadoCalidadCantidadC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comprobantes_actas_de_entrega_recepcion_contratista)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setActaEntregaRecepcionContratista(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setActaEntregaRecepcionContratistaC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comprobantes_actas_de_entrega_recepcion_comprador)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setActaEntregaRecepcionComprador(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setActaEntregaRecepcionCompradorC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Documentos_de_transporte)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setDocumentosTransporte(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDocumentosTransporteC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Documentos_aduanales)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setDocumentosAduanales(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDocumentosAduanalesC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Documentos_de_soporte_de_reclamos_demora)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setDocumentosSoporteReclamosDemora(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDocumentosSoporteReclamosDemoraC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Nominaciones_de_servicios_logisticos)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setNominacionesServiciosLogisticos(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setNominacionesServiciosLogisticosC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Otros_Operativo_Estado)) {
			if (seleccionado == seleccionadoDoc) {
				bean.setOtros(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setOtrosC(checked);
			}
		}
		
		return bean;
	}
	
	private ChecklistTerrestreBean registraChecklistTerrestre(ChecklistTerrestreBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {
		if (idDocumento.trim().equals(ChecklistID.Aviso_de_carga_entrega) && seleccionado == seleccionadoDoc) {
			bean.setAvisoCargaEntrega(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Confirmacion_de_booking_multimodal)
				&& seleccionado == seleccionadoDoc) {
			bean.setConfirmacionBookingMultimodal(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Factura_proforma) && seleccionado == seleccionadoDoc) {
			bean.setFacturaProforma(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instruccion_de_embarque_para_agente_aduanal)
				&& seleccionado == seleccionadoDoc) {
			bean.setInstruccionEmbarqueAgenteAduanal(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instruccion_para_aditivacion_de_producto)
				&& seleccionado == seleccionadoDoc) {
			bean.setInstruccionAditivacionProducto(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_de_servicio_de_inspeccion)
				&& seleccionado == seleccionadoDoc) {
			bean.setAceptacionServicioInspeccion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Ajuste_en_volumen_o_precio) && seleccionado == seleccionadoDoc) {
			bean.setAjusteVolumenPrecio(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_al_transportista) && seleccionado == seleccionadoDoc) {
			bean.setNominacionTransportista(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_carga) && seleccionado == seleccionadoDoc) {
			bean.setNominacionCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_descarga) && seleccionado == seleccionadoDoc) {
			bean.setNominacionDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_inspeccion) && seleccionado == seleccionadoDoc) {
			bean.setNominacionInspeccion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_servicios_de_terminal)
				&& seleccionado == seleccionadoDoc) {
			bean.setNominacionServiciosTerminal(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Carta_protesta) && seleccionado == seleccionadoDoc) {
			bean.setCartaProtesta(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Certificado_de_origen) && seleccionado == seleccionadoDoc) {
			bean.setCertificadoOrigen(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Certificado_de_origen_camara_de_comercio)
				&& seleccionado == seleccionadoDoc) {
			bean.setCertificadoOrigenCamaraComercio(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Correo_de_autorizacion_de_embarque_por_tipo_de_pago)
				&& seleccionado == seleccionadoDoc) {
			bean.setCorreoAutorizacionEmbarqueTipoPago(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Hojas_de_calculo_de_precio) && seleccionado == seleccionadoDoc) {
			bean.setHojaCalculoPrecio(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_calidad_y_cantidad_a_la_carga)
				&& seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionCalidadCantidadCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_calidad_y_cantidad_a_la_descarga)
				&& seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionCalidadCantidadDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Otros_especificarTer) && seleccionado == seleccionadoDoc) {
			bean.setOtrosEspecificar(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Talon_de_embarque_carta_porte) && seleccionado == seleccionadoDoc) {
			bean.setTalonEmbarqueCartaPorte(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Ticket_de_bombeo_ducto) && seleccionado == seleccionadoDoc) {
			bean.setTicketBombeoDucto(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Envio_de_documentos_al_cliente) && seleccionado == seleccionadoDoc) {
			bean.setEnvioDocumentosCliente(checked);
		}
		return bean;
	}

	public List<ChecklistMaritimoBean> integraChecklistsMaritimo(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistMaritimoBean> listChecklistMaritimoBean = new ArrayList<ChecklistMaritimoBean>();
		ChecklistMaritimoBean checklistMaritimoBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String object_id = null;
		String hojasDocumento = null;
		int seleccionado;
		int digital;
		int conteoHojas=1;

		String getOrdenesQuery = "SELECT distinct orden_relacionada "
				+ "FROM dm_dbo.DOCUMENTO_SELECCIONADO "
				+ "WHERE numero_expediente = '"
				+ expediente
				+ "' AND descripcion_expediente = '"
				+ asuntoSubexpediente
				+ "' AND (orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd FROM pmx_pmi_ec_prodcts "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ecexpcomrop WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_fletmnts WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_tesrr "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "'))";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
//			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
//
//			while (collOrdenesRelacionadas.next()) {
//				conteoHojas=1;
				checklistMaritimoBean = new ChecklistMaritimoBean();
				checklistMaritimoBean.setFecha(dateFormat.format(cal.getTime()));
//				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistMaritimoBean.setOrdenPmi(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id  FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' ";
//						+ "AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					checklistMaritimoBean = registraChecklistMaritimo(checklistMaritimoBean, idDocumento, descripcion,
							seleccionado, digital);
				}
				checklistMaritimoBean.setNumeroFolios(Integer.toString(conteoHojas));
				listChecklistMaritimoBean.add(checklistMaritimoBean);
//			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistMaritimoBean;
	}

	private ChecklistMaritimoBean registraChecklistMaritimo(ChecklistMaritimoBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {

		if (idDocumento.trim().equals(ChecklistID.Autorizacion_de_embarque_por_tipo_de_pago)
				&& seleccionado == seleccionadoDoc) {
			bean.setAutorizacionEmbarqueTipoPago(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aviso_de_importacion_PEMEX) && seleccionado == seleccionadoDoc) {
			bean.setAvisoImportacionPemex(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aviso_de_listo_puerto_de_carga) && seleccionado == seleccionadoDoc) {
			bean.setAvisoListoPuertoCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aviso_de_listo_puerto_descarga) && seleccionado == seleccionadoDoc) {
			bean.setAvisoListoPuertoDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Confirmacion_volumen_y_precio_de_compra_o_venta)
				&& seleccionado == seleccionadoDoc) {
			bean.setConfirmacionVolumenPrecioCompraVenta(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Conocimiento_de_embarque_bl) && seleccionado == seleccionadoDoc) {
			bean.setConocimientoEmbarque(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Estado_de_hechos_puerto_de_carga)
				&& seleccionado == seleccionadoDoc) {
			bean.setEstadoHechosPuertoCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Estado_de_hechos_puerto_de_descarga)
				&& seleccionado == seleccionadoDoc) {
			bean.setEstadoHechosPuertoDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Etas_de_la_embarcacion) && seleccionado == seleccionadoDoc) {
			bean.setEtasEmbarcacion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Factura_proformaMar) && seleccionado == seleccionadoDoc) {
			bean.setFacturaProforma(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instruccion_de_documentacion) && seleccionado == seleccionadoDoc) {
			bean.setInstruccionDocumentacion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instruccion_de_embarque_para_agente_aduanalMar)
				&& seleccionado == seleccionadoDoc) {
			bean.setInstruccionEmbarqueAgenteAduanal(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instruccion_para_aditivacion_de_productoMar)
				&& seleccionado == seleccionadoDoc) {
			bean.setInstruccionAditivacionProducto(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Instrucciones_de_viaje) && seleccionado == seleccionadoDoc) {
			bean.setInstruccionesViaje(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_de_buque_tanque) && seleccionado == seleccionadoDoc) {
			bean.setAceptacionBuqueTanque(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_de_servicio_de_inspeccionMar)
				&& seleccionado == seleccionadoDoc) {
			bean.setAceptacionServicioInspeccion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Acuse_de_recibo_del_capitan) && seleccionado == seleccionadoDoc) {
			bean.setAcuseReciboCapitan(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Manifiesto_de_carga) && seleccionado == seleccionadoDoc) {
			bean.setManifiestoCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Modificaciones_al_programa_cambio_de_volumen_yo_ventana)
				&& seleccionado == seleccionadoDoc) {
			bean.setModificacionesPrograma(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_cargaMar) && seleccionado == seleccionadoDoc) {
			bean.setNominacionInspeccionCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Nominacion_de_descargaMar) && seleccionado == seleccionadoDoc) {
			bean.setNominacionInspeccionDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Carta_de_indemnidad) && seleccionado == seleccionadoDoc) {
			bean.setCartaIndemnidad(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Carta_protestaMar) && seleccionado == seleccionadoDoc) {
			bean.setCartaProtesta(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Certificado_de_origenMar) && seleccionado == seleccionadoDoc) {
			bean.setCertificadoOrigen(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Certificado_de_origen_camara_de_comercioMar)
				&& seleccionado == seleccionadoDoc) {
			bean.setCertificadoOrigenCamaraComercio(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Hojas_de_calculo_de_precioMar) && seleccionado == seleccionadoDoc) {
			bean.setHojasCalculoPrecio(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_puerto_de_carga)
				&& seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionPuertoCarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Reporte_de_inspeccion_puerto_de_descarga)
				&& seleccionado == seleccionadoDoc) {
			bean.setReporteInspeccionPuertoDescarga(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Otros_especificarMar) && seleccionado == seleccionadoDoc) {
			bean.setOtrosEspecificar(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Envio_de_documentos_cliente) && seleccionado == seleccionadoDoc) {
			bean.setEnvioDocumentosCliente(checked);
		}
		return bean;
	}

	public List<ChecklistComercialBean> integraChecklistsComercial(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException, Exception {
		List<ChecklistComercialBean> listChecklistComercialBean = new ArrayList<ChecklistComercialBean>();
		ChecklistComercialBean checklistComercialBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);
		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		String getPaginasQuery = null;
		IDfQuery queryDoc = null;
		IDfQuery queryPag = null;
		IDfCollection collDocumentos = null;
		IDfCollection collPaginas = null;
		String idDocumento = null;
		String descripcion = null;
		int seleccionado;
		int digital;
		String object_id;
		String noPag="";
		String producto;
		IntegraPeopleSoftDB integraPeopleSoftDB = new IntegraPeopleSoftDB();
		String getOrdenesQuery2 = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente + "'";
		String getOrdenesQuery = "SELECT distinct orden_relacionada "
				+ "FROM dm_dbo.DOCUMENTO_SELECCIONADO "
				+ "WHERE numero_expediente = '"
				+ expediente
				+ "' AND descripcion_expediente = '"
				+ asuntoSubexpediente
				+ "' AND (orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd FROM pmx_pmi_ec_prodcts "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ecexpcomrop WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_fletmnts WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "') OR 	orden_relacionada IN ( "
				+ "SELECT n_numr_ordn_relcnd "
				+ "FROM pmx_pmi_ec_tesrr "
				+ "WHERE subject = '"
				+ asuntoSubexpediente
				+ "' AND ar_numr_expdnt = '"
				+ expediente
				+ "'))";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);

			while (collOrdenesRelacionadas.next()) {
				checklistComercialBean = new ChecklistComercialBean();
				checklistComercialBean.setFecha(dateFormat.format(cal.getTime()));
				
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistComercialBean.setOrdenExpediente(ordenRelacionada);
				producto = integraPeopleSoftDB.ejecutaWsProducto(ordenRelacionada);
				checklistComercialBean.setProducto(producto!=null?producto:"");
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					noPag=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					checklistComercialBean = registraChecklistComercial(checklistComercialBean, idDocumento,
							descripcion, seleccionado, digital,noPag);
				}
				listChecklistComercialBean.add(checklistComercialBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistComercialBean;
	}

	private ChecklistComercialBean registraChecklistComercial(ChecklistComercialBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital, String noPag) {

		if (idDocumento.trim().equals(ChecklistID.Contract)) {
			bean.setContractNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setContractF(checked);				
			}
			if (digital == seleccionadoDoc) {
				bean.setContractD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Contract_amendment)) {
			bean.setContractAmendmentNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setContractAmendmentF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setContractAmendmentD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Modificacion_a_confirmacion_comercial_a_PEMEX)) {
			bean.setModificacionConfirmacionComercialPemexNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setModificacionConfirmacionComercialPemexF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setModificacionConfirmacionComercialPemexD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Nota_de_mercado)) {
			bean.setNotaMercadoNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setNotaMercadoF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setNotaMercadoD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Oficios_requerimientos_o_disponibilidades_de_PEMEX)) {
			bean.setOficioRequerimientoDisponibilidadNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setOficioRequerimientoDisponibilidadF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setOficioRequerimientoDisponibilidadD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Counterparty_contract)) {
			bean.setCounterpartyContractNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCounterpartyContractF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCounterpartyContractD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Counterparty_contract_amendment)) {
			bean.setCounterpartyContractAmendmentNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCounterpartyContractAmendmentF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCounterpartyContractAmendmentD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Soporte_de_inteligencia_de_mercado)) {
			bean.setSoporteInteligenciaMercadoNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setSoporteInteligenciaMercadoF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setSoporteInteligenciaMercadoD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comprobante_de_envio_a_la_contraparte)) {
			bean.setComprobanteEnvioContraparteNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComprobanteEnvioContraparteF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComprobanteEnvioContraparteD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Confirmacion_comercial_a_PEMEX)) {
			bean.setConfirmacionComercialPemexNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setConfirmacionComercialPemexF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setConfirmacionComercialPemexD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Confirmacion_con_la_contraparte_recap)) {
			bean.setConfirmacionContraparteNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setConfirmacionContraparteF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setConfirmacionContraparteD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Deal_sheet)) {
			bean.setDealSheetNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setDealSheetF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDealSheetD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Documentos_varios)) {
			bean.setDocumentosVariosNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setDocumentosVariosF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDocumentosVariosD(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Trade_cancelado)) {
//			bean.setDocumentosVariosNP(noPag);
//			if (seleccionado == seleccionadoDoc) {
//				bean.setDocumentosVariosF(checked);
//			}
//			if (digital == seleccionadoDoc) {
//				bean.setDocumentosVariosD(checked);
//			}
			bean.setTradeCanceladoNP(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setTradeCanceladoF(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setTradeCanceladoD(checked);
			}
		}
		return bean;
	}

		
	public List<ChecklistOperativoEstadoBean> integraChecklistsOperativoEstado(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistOperativoEstadoBean> listChecklistOperativoEstadoBean = new ArrayList<ChecklistOperativoEstadoBean>();
		ChecklistOperativoEstadoBean checklistOperativoEstadoBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String hojasDocumento = null;
		int conteoHojas=1;
		String object_id = null;
		int seleccionado;
		int digital;

		String getOrdenesQuery = "select area_contractual from pmx_pmi_comrcld_std   where  ar_numr_expdnt= '"
				+ expediente
				+ "' AND subject = '"
				+ asuntoSubexpediente
				+ "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			collOrdenesRelacionadas.next();
			checklistOperativoEstadoBean = new ChecklistOperativoEstadoBean();
			checklistOperativoEstadoBean.setAreaContractual(collOrdenesRelacionadas.getString("area_contractual"));
			checklistOperativoEstadoBean.setOrdenPmi("");
			checklistOperativoEstadoBean.setOrdenesRelacionadas("");
			checklistOperativoEstadoBean.setAllocation("");
			   
//			while (collOrdenesRelacionadas.next()) {
				conteoHojas=1;
				checklistOperativoEstadoBean.setFecha(dateFormat.format(cal.getTime()));
//				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
//				checklistOperativoEstadoBean.setOrdenPmi(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' ";
//						+ "AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					checklistOperativoEstadoBean = registraChecklistOperativoEstado(checklistOperativoEstadoBean, idDocumento,
							descripcion, seleccionado, digital);
				}
				checklistOperativoEstadoBean.setNumeroFolios(Integer.toString(conteoHojas));
				listChecklistOperativoEstadoBean.add(checklistOperativoEstadoBean);
//			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistOperativoEstadoBean;
	}
	
	public List<ChecklistComCrudoBean> integraChecklistsComCrudo(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistComCrudoBean> listChecklistComCrudoBean = new ArrayList<ChecklistComCrudoBean>();
		ChecklistComCrudoBean checklistComCrudoBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		int seleccionado;
		int digital;

		String getOrdenesQuery = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente + "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);

			while (collOrdenesRelacionadas.next()) {
				checklistComCrudoBean = new ChecklistComCrudoBean();
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistComCrudoBean.setOrdenExpediente(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					checklistComCrudoBean = registraChecklistComCrudo(checklistComCrudoBean, idDocumento, descripcion,
							seleccionado, digital);
				}
				listChecklistComCrudoBean.add(checklistComCrudoBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistComCrudoBean;
	}

	
	public List<ChecklistComercialEstadoBean> integraChecklistsComercialEstado(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {
		List<ChecklistComercialEstadoBean> listChecklistComercialEstadoBean = new ArrayList<ChecklistComercialEstadoBean>();
		ChecklistComercialEstadoBean checklistComercialEstadoBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String object_id = null;
		String noPag = null;
		int seleccionado;
		int digital;

		String getAreaContrQuery = "select area_contractual from pmx_pmi_comrcld_std   where  ar_numr_expdnt= '"
				+ expediente
				+ "' AND subject = '"
				+ asuntoSubexpediente
				+ "'";


		
		String getOrdenesQuery = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente + "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		IDfQuery query0 = new DfQuery(getAreaContrQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			IDfCollection collArCnRelacionadas = query0.execute(iDfSession, IDfQuery.DF_READ_QUERY);
			collArCnRelacionadas.next();
			while (collOrdenesRelacionadas.next()) {
				checklistComercialEstadoBean = new ChecklistComercialEstadoBean();
				checklistComercialEstadoBean.setAreaContractual(collArCnRelacionadas.getString("area_contractual"));
				
				
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				log.info("Orden relacionada para generar checklist:"+ordenRelacionada);
				checklistComercialEstadoBean.setOrdenRelacionada(ordenRelacionada);
				checklistComercialEstadoBean.setOrdenExpediente(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id  FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					noPag=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					checklistComercialEstadoBean = registraChecklistComercialEstado(checklistComercialEstadoBean, idDocumento, descripcion,
							seleccionado, digital, noPag);
				}
				listChecklistComercialEstadoBean.add(checklistComercialEstadoBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistComercialEstadoBean;
	}

		
	private ChecklistComercialEstadoBean registraChecklistComercialEstado(ChecklistComercialEstadoBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital, String noPag) {

		if (idDocumento.trim().equals(ChecklistID.Distribucion_de_ingresos_comprador)) {
			bean.setDistribucionIngresosN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setDistribucionIngresos(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setDistribucionIngresosC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Calculo_de_precio_comercializador)) {
			bean.setCalculoPrecioComercializadorN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCalculoPrecioComercializador(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCalculoPrecioComercializadorC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Calculo_de_precio_comprador)) {
			bean.setCalculoPrecioCompradorN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCalculoPrecioComprador(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCalculoPrecioCompradorC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Calculo_de_precio_comprador)) {
			bean.setCalculoPrecioCompradorN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCalculoPrecioComprador(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCalculoPrecioCompradorC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Correo_de_CNH)) {
			bean.setCorreoCNHN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setCorreoCNH(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setCorreoCNHC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Memorandum_de_solicitud_a_tesoreria)) {
			bean.setMemorandumSolicitudN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setMemorandumSolicitud(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setMemorandumSolicitudC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_con_CNH)) {
			bean.setComunicacionesCNHN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComunicacionesCNH(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComunicacionesCNHC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_con_comprador)) {
			bean.setComunicacionesCompradorN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComunicacionesComprador(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComunicacionesCompradorC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_con_contratista)) {
			bean.setComunicacionesContratistaN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComunicacionesContratista(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComunicacionesContratistaC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_con_FMP)) {
			bean.setComunicacionesFMPN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComunicacionesFMP(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComunicacionesFMPC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_con_CRE)) {
			bean.setComunicacionesCREN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setComunicacionesCRE(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setComunicacionesCREC(checked);
			}
		}
		if (idDocumento.trim().equals(ChecklistID.Otros_Comercial_Estado)) {
			bean.setOtrosN(noPag);
			if (seleccionado == seleccionadoDoc) {
				bean.setOtros(checked);
			}
			if (digital == seleccionadoDoc) {
				bean.setOtrosC(checked);
			}
		}
		

		return bean;
	}
	
	private ChecklistComCrudoBean registraChecklistComCrudo(ChecklistComCrudoBean bean, String idDocumento,
			String descripcion, int seleccionado, int digital) {

		if (idDocumento.trim().equals(ChecklistID.Confirmacion_de_compra_venta) && seleccionado == seleccionadoDoc) {
			bean.setConfirmacionCompraVenta(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Modificaciones_al_programa_ordenes_nuevas_o_canceladas)
				&& seleccionado == seleccionadoDoc) {
			bean.setModificacionesPrograma(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Oficio_PEMEX_disponibilidad) && seleccionado == seleccionadoDoc) {
			bean.setOficioPemexDisponibilidad(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Oficios_a_pep) && seleccionado == seleccionadoDoc) {
			bean.setOficiosPep(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Contrato_simple) && seleccionado == seleccionadoDoc) {
			bean.setContratoSimple(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Recap) && seleccionado == seleccionadoDoc) {
			bean.setRecap(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Programa_contractual_previsto) && seleccionado == seleccionadoDoc) {
			bean.setProgramaContractualPrevisto(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_al_cliente) && seleccionado == seleccionadoDoc) {
			bean.setComunicacionesAlCliente(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Comunicaciones_del_cliente) && seleccionado == seleccionadoDoc) {
			bean.setComunicacionesDelCliente(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Confirmacion_carta_de_credito_garantia_de_pago)
				&& seleccionado == seleccionadoDoc) {
			bean.setConfirmacionCartaCredito(checked);
		}

		return bean;
	}

	public List<ChecklistFletamentosBean> integraChecklistsFletamentos(String asuntoSubexpediente, String expediente,
			String userLT) throws DfException {

		List<ChecklistFletamentosBean> listChecklistFletamentosBean = new ArrayList<ChecklistFletamentosBean>();
		ChecklistFletamentosBean checklistFletamentosBean = null;
		IDfSession iDfSession = documentumService.getSession(userLT);

		String ordenRelacionada = null;
		String getDocumentosQuery = null;
		IDfQuery queryDoc = null;
		IDfCollection collDocumentos = null;
		String idDocumento = null;
		String descripcion = null;
		String object_id = null;
		String hojasDocumento = null;
		int seleccionado;
		int digital;
		int conteoHojas=1;

		String getOrdenesQuery = "SELECT distinct orden_relacionada FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
				+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente + "'";

		IDfQuery query = new DfQuery(getOrdenesQuery);
		try {
			IDfCollection collOrdenesRelacionadas = query.execute(iDfSession, IDfQuery.DF_READ_QUERY);

			while (collOrdenesRelacionadas.next()) {
				conteoHojas=1;
				checklistFletamentosBean = new ChecklistFletamentosBean();
				checklistFletamentosBean.setFecha(dateFormat.format(cal.getTime()));
				ordenRelacionada = collOrdenesRelacionadas.getString("orden_relacionada");
				checklistFletamentosBean.setOrdenFletamento(ordenRelacionada);
				getDocumentosQuery = "SELECT id_documento, descripcion_documento, seleccionado, digital, object_id FROM dm_dbo.DOCUMENTO_SELECCIONADO WHERE numero_expediente = '"
						+ expediente + "' AND descripcion_expediente = '" + asuntoSubexpediente
						+ "' AND orden_relacionada = '" + ordenRelacionada + "'";
				queryDoc = new DfQuery(getDocumentosQuery);

				collDocumentos = queryDoc.execute(iDfSession, IDfQuery.DF_READ_QUERY);

				while (collDocumentos.next()) {
					idDocumento = collDocumentos.getString("id_documento");
					descripcion = collDocumentos.getString("descripcion_documento");
					seleccionado = collDocumentos.getInt("seleccionado");
					digital = collDocumentos.getInt("digital");
					object_id = collDocumentos.getString("object_id");
					hojasDocumento=this.obtenNumeroPaginasDocumento(iDfSession, object_id);
					conteoHojas+=Integer.parseInt(hojasDocumento.equals("")?"0":hojasDocumento);
					
					checklistFletamentosBean = registraChecklistFletamentos(checklistFletamentosBean, idDocumento, descripcion, seleccionado, digital);
				}
				checklistFletamentosBean.setNumeroFolios(Integer.toString(conteoHojas));
				listChecklistFletamentosBean.add(checklistFletamentosBean);
			}

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		} finally {
			documentumService.releaseSession(iDfSession);
		}
		return listChecklistFletamentosBean;
	}
	private ChecklistFletamentosBean registraChecklistFletamentos(ChecklistFletamentosBean bean, String idDocumento,String descripcion, int seleccionado, int digital) {

		if (idDocumento.trim().equals(ChecklistID.Contrato_de_fletamentos_preliminar) && seleccionado == seleccionadoDoc) {
			bean.setContratoFletamentos(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_area_comercial) && seleccionado == seleccionadoDoc) {
			bean.setAceptacionAreaComercial(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_area_de_vetting_de_embarcacion) && seleccionado == seleccionadoDoc) {
			bean.setAceptacionAreaVettingEmbarcacion(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Aceptacion_del_cliente_yo_proveedor_de_la_embarcacion_nominado_por_PMI) && seleccionado == seleccionadoDoc) {
			bean.setAceptacionClienteProveedorEmbarcacionNominadoPmi(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Levantamiento_de_sujetos) && seleccionado == seleccionadoDoc) {
			bean.setLevantamientoSujetos(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Ofertas_de_brokers_y_armadores) && seleccionado == seleccionadoDoc) {
			bean.setOfertasBrokersArmadores(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Contrato_de_fletamentos) && seleccionado == seleccionadoDoc) {
			bean.setContratoFletamentos(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Correo_comercial) && seleccionado == seleccionadoDoc) {
			bean.setCorreoComercial(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Formato_de_cuestionario_q88_cumplimentado) && seleccionado == seleccionadoDoc) {
			bean.setFormatoCuestionarioCumplimentado(checked);
		}
		if (idDocumento.trim().equals(ChecklistID.Envio_de_nominacion_de_bt_a_cliente_yo_proveedor) && seleccionado == seleccionadoDoc) {
			bean.setEnvioNominacionClienteProveedor(checked);
		}
			
			return bean;
	}
	public CaratulaBean integraCaratula(CaratulaBean bean) {
		bean.getClasificacionReservada();
		return bean;
	}
}
