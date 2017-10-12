package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.documentum.fc.common.DfException;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.CaratulaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistComCrudoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistComercialBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistDemorasBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistFletamentosBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistMaritimoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistReclamosBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ChecklistTerrestreBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GeneradorBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GuiaSimpleBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GuiaSimpleTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.InTransferenciaPrimariaSecundariaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.InTransferenciaPrimariaSecundariaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceExpedientesReservaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.IndiceExpedientesReservaTablaBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoTablaBean;

@WebService(name = "GeneradorWs", targetNamespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/")
public interface GeneradorWs {

	List<DocumentoGeneradoDto> generaCaratula(CaratulaBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistComCrudo(ChecklistComCrudoBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistComercial(ChecklistComercialBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistDemoras(ChecklistDemorasBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistFletamentos(ChecklistFletamentosBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistMaritimo(ChecklistMaritimoBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistReclamos(ChecklistReclamosBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaChecklistTerrestre(ChecklistTerrestreBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaExpedientesDesclasifica(ExpedientesDesclasificaBean paramBean,
			List<ExpedientesDesclasificaTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaGuiaSimple(GuiaSimpleBean paramBean, List<GuiaSimpleTablaBean> tablaBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaIndiceExpedientesReserva(IndiceExpedientesReservaBean paramBean,
			List<IndiceExpedientesReservaTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaInTransferenciaPrimariaSecundaria(InTransferenciaPrimariaSecundariaBean paramBean,
			List<InTransferenciaPrimariaSecundariaTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException;

	List<DocumentoGeneradoDto> generaValePrestamo(ValePrestamoBean paramBean, List<ValePrestamoTablaBean> tablaBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException;

	String prueba(ValePrestamoBean paramBean, List<ValePrestamoTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros, String p) throws DfException;

}