//cambio insignificante 2
package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.tempuri.ArrayOfDatosPeoplesoft;
import org.tempuri.DatosPeoplesoft;
import org.tempuri.Firmas;
import org.tempuri.WSDocumentum;
import org.tempuri.WSDocumentumSoap;

import com.documentum.fc.common.DfException;
import com.documentum.fc.impl.util.RegistryPasswordUtils;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.ParametrosDocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.UsuarioDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.TransformarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl.TransformarDocumentoServiceImpl;
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
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.PadreBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoBean;
import com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ValePrestamoTablaBean;

import client.Test1;

@WebService(targetNamespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/", endpointInterface = "com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl.GeneradorWs", portName = "GeneradorDocxImplPort", serviceName = "GeneradorDocxImplService")
public class GeneradorDocxImpl implements GeneradorWs {
	public List<DocumentoGeneradoDto> generaCaratula(CaratulaBean paramBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			IntegradorDocx integradorDocx = new IntegradorDocx();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			String asuntoSubexpediente = parametros.get("asuntoSubexpediente");
//			if(asuntoSubexpediente==null||asuntoSubexpediente.equals(""))
//				paramBean.setPeriodoAdicionalNoExpediente(generadorBean.getNombreDoc());
			paramBean = integradorDocx.integraDatosCaratula(paramBean, parametros, generadorBean.getUserName(), asuntoSubexpediente);
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ed3e");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			parametros.put("isCaratula", "true");
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistComCrudo(ChecklistComCrudoBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()>0){
			// generaChecklistsComCrudo(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e601");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistComercial(ChecklistComercialBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		
		try {
			if (parametros != null && parametros.size() == 2) {
				System.out.println("case 1");
				List<DocumentoGeneradoDto> listaDocumentosGenerados = null; 
				String asuntoSubexpediente = parametros.get("asuntoSubexpediente").trim();
				String numeroExpediente = parametros.get("numeroExpediente").trim();
				if (asuntoSubexpediente.equals("Comercial de Productos")) {
					generaChecklistsComercial(asuntoSubexpediente, numeroExpediente, generadorBean);
				} else if (asuntoSubexpediente.equals("Comercial de Crudo")) {
					generaChecklistsComCrudo(asuntoSubexpediente, numeroExpediente, generadorBean);
				} else if (asuntoSubexpediente.contains("Operativo Mar")) {
					generaChecklistsMaritimo(asuntoSubexpediente, numeroExpediente, generadorBean);
				} else if (asuntoSubexpediente.equals("Operativo Terrestre")) {
					generaChecklistsTerrestre(asuntoSubexpediente, numeroExpediente, generadorBean);
				} else if (asuntoSubexpediente.equals("Fletamentos")) {
					generaChecklistsFletamentos(asuntoSubexpediente, numeroExpediente, generadorBean);
				}
				else if (asuntoSubexpediente.equals("Operativo")) {
					generaChecklistsOperativoEstado(asuntoSubexpediente, numeroExpediente, generadorBean);
				} 

				else if (asuntoSubexpediente.equals("Comercial")) {
					generaChecklistsComercialEstado(asuntoSubexpediente, numeroExpediente, generadorBean);
				}
				// Reclamos por demora
				else if (asuntoSubexpediente.equals("PMI-14E.58.1")) {
					generaChecklistsDemoras(asuntoSubexpediente, numeroExpediente, generadorBean);
				}
				// Reclamos por calidad y cantidad
				else if (asuntoSubexpediente.equals("PMI-14E.58.2")) {
					generaChecklistsReclamos(asuntoSubexpediente, numeroExpediente, generadorBean);
				}
				//Indice carpeta producto
				else if (asuntoSubexpediente.equals("IndiceCarpetaProducto")) {
					System.out.println("aqui");
					listaDocumentosGenerados = generaIndiceCarpetaProducto(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					System.out.println("&&&&&&&&&&&&&&&&ya casi al final:"+listaDocumentosGenerados.toString());
				}
				//Indice caja producto
				else if (asuntoSubexpediente.equals("IndiceCajaProducto")) {
					listaDocumentosGenerados = generaIndiceCajaProducto(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					System.out.println("&&&&&&&&&&&&&&&&ya casi al final:"+listaDocumentosGenerados.toString());
				}
				//Indice carpeta crudo
				else if (asuntoSubexpediente.equals("IndiceCarpetaCrudo")) {
					listaDocumentosGenerados = generaIndiceCarpetaCrudo(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
				}
				//Indice carpeta comercializador
				else if (asuntoSubexpediente.equals("IndiceCarpetaComercializador")) {
					listaDocumentosGenerados = generaIndiceCarpetaComercializador(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
				}
				//Indice caja crudo
				else if (asuntoSubexpediente.equals("IndiceCajaCrudo")) {
					listaDocumentosGenerados = generaIndiceCajaCrudo(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
				}
				else if (asuntoSubexpediente.equals("Tesorería")) {
//					listaDocumentosGenerados = generaChecklistsComun(asuntoSubexpediente, numeroExpediente, generadorBean);
					listaDocumentosGenerados = generaChecklistsTesoreria(asuntoSubexpediente, numeroExpediente, generadorBean, generadorBean.getRutaDocumento());
				}
				else {
					listaDocumentosGenerados = generaChecklistsComun(asuntoSubexpediente, numeroExpediente, generadorBean);
				}
				return listaDocumentosGenerados;
			} else {
				if (parametros != null && parametros.size() == 7) {
					System.out.println("case 2");
					List<DocumentoGeneradoDto> listaDocumentosGenerados = null;
					String asuntoSubexpediente = parametros.get("asuntoSubexpediente").trim();
					String numeroExpediente = parametros.get("numeroExpediente").trim();
					//Indice carpeta producto
					if (asuntoSubexpediente.equals("IndiceCarpetaProducto")) {
						listaDocumentosGenerados = generaIndiceCarpetaProducto(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					}
					//Indice caja producto
					else if (asuntoSubexpediente.equals("IndiceCajaProducto")) {
						listaDocumentosGenerados = generaIndiceCajaProducto(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					}
					//Indice carpeta crudo
					else if (asuntoSubexpediente.equals("IndiceCarpetaCrudo")) {
						listaDocumentosGenerados = generaIndiceCarpetaCrudo(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					}
					//Indice caja crudo
					else if (asuntoSubexpediente.equals("IndiceCajaCrudo")) {
						listaDocumentosGenerados = generaIndiceCajaCrudo(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					}
					//Indice carpeta comercializador
					else if (asuntoSubexpediente.equals("IndiceCarpetaComercializador")) {
						listaDocumentosGenerados = generaIndiceCarpetaComercializador(asuntoSubexpediente, numeroExpediente, generadorBean, parametros);
					}
					else {
						listaDocumentosGenerados = generaChecklistsComun(asuntoSubexpediente, numeroExpediente, generadorBean);
					}
					return listaDocumentosGenerados;
				}
				else {
					System.out.println("case 3");
				List<Map<String, String>> contenidoTabla;
				String[] idTabla;
				contenidoTabla = null;
				idTabla = null;
				UsuarioDto usuarioDto = new UsuarioDto();
				TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
				List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
				ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
				parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e602");
				parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
				parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
				parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
				parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
				listaParametros.add(parametrosDocumentoDto);
				List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
						usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
						generadorBean.getUserName(), parametros);
				return listaDocumentosGenerados;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistFletamentos(ChecklistFletamentosBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()==2){
			// generaChecklistsFletamentos(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e605");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistMaritimo(ChecklistMaritimoBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()==2){
			// generaChecklistsMaritimo(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e604");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistTerrestre(ChecklistTerrestreBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()==0){
			// generaChecklistsTerrestre(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();

			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e606");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaChecklistDemoras(ChecklistDemorasBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();

			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e9a5");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	public List<DocumentoGeneradoDto> generaChecklistReclamos(ChecklistReclamosBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();

			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000e608");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	public List<DocumentoGeneradoDto> generaChecklistComun(ChecklistComCrudoBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros, List<Map<String, String>> contenidoTabla) throws DfException {
		try {
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a980209c3a"); //TODO:ACTUALIZAR
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	private List<DocumentoGeneradoDto> generaChecklistsTesoreria(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean, String rutaDoc) throws DfException {
		try {
			List<DocumentoGeneradoDto> result = null;;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			ChecklistComCrudoBean checklistComunBean = integradorDocx
					.integraChecklistsComun(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
//			List<Map<String, String>> contenidoTabla = integradorDocx.integraTablaExpedientesComun(checklistComunBean.getNumHojas(), generadorBean.getUserName());
			List<Map<String, String>> contenidoTabla = integradorDocx.integraTablaExpedientesTesoreria(checklistComunBean.getNumHojas(), generadorBean.getUserName(), rutaDoc);
//			Iterator<ChecklistComunBean> iteratorChecklistComunBean = listChecklistComunBean.iterator();
//			ChecklistComunsBean checklistComunBean = null;
//			Map<String, String> orden = new HashMap<String, String>();
//			checklistComunBean = iteratorChecklistComunBean.next();
//			orden.put("ordenrelacionada", checklistComunBean.getOrdenPmi());
			generadorBean.setNombreDoc(numeroExpediente);
			result = generadorDocxImpl.generaChecklistComun(checklistComunBean, generadorBean,null, contenidoTabla);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	private List<DocumentoGeneradoDto> generaChecklistsComun(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			List<DocumentoGeneradoDto> result = null;;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			ChecklistComCrudoBean checklistComunBean = integradorDocx
					.integraChecklistsComun(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			List<Map<String, String>> contenidoTabla = integradorDocx.integraTablaExpedientesComun(checklistComunBean.getNumHojas(), generadorBean.getUserName());
//			Iterator<ChecklistComunBean> iteratorChecklistComunBean = listChecklistComunBean.iterator();
//			ChecklistComunsBean checklistComunBean = null;
//			Map<String, String> orden = new HashMap<String, String>();
//			checklistComunBean = iteratorChecklistComunBean.next();
//			orden.put("ordenrelacionada", checklistComunBean.getOrdenPmi());
			generadorBean.setNombreDoc(numeroExpediente);
			result = generadorDocxImpl.generaChecklistComun(checklistComunBean, generadorBean,null, contenidoTabla);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	
	private void generaChecklistsDemoras(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistDemorasBean> listChecklistDemorasBean = integradorDocx
					.integraChecklistsDemoras(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistDemorasBean> iteratorChecklistDemorasBean = listChecklistDemorasBean.iterator();
			ChecklistDemorasBean checklistDemorasBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistDemorasBean.hasNext()) {
				checklistDemorasBean = iteratorChecklistDemorasBean.next();
				orden.put("ordenrelacionada", checklistDemorasBean.getOrdenPmi());
				generadorBean.setNombreDoc(checklistDemorasBean.getOrdenPmi());
				generadorDocxImpl.generaChecklistDemoras(checklistDemorasBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	private void generaChecklistsReclamos(String asuntoSubexpediente, String numeroExpediente,
				GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistReclamosBean> listChecklistReclamosBean = integradorDocx
					.integraChecklistsReclamos(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistReclamosBean> iteratorChecklistReclamosBean = listChecklistReclamosBean.iterator();
			ChecklistReclamosBean checklistReclamosBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistReclamosBean.hasNext()) {
				checklistReclamosBean = iteratorChecklistReclamosBean.next();
				orden.put("ordenrelacionada", checklistReclamosBean.getOrdenPmi());
				generadorBean.setNombreDoc(checklistReclamosBean.getOrdenPmi());
				generadorDocxImpl.generaChecklistReclamos(checklistReclamosBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	private void generaChecklistsTerrestre(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistTerrestreBean> listChecklistTerrestreBean = integradorDocx
					.integraChecklistsTerrestre(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistTerrestreBean> iteratorChecklistTerrestreBean = listChecklistTerrestreBean.iterator();
			ChecklistTerrestreBean checklistTerrestreBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistTerrestreBean.hasNext()) {
				checklistTerrestreBean = iteratorChecklistTerrestreBean.next();
				orden.put("ordenrelacionada", checklistTerrestreBean.getOrdenPmi());
				generadorBean.setNombreDoc(checklistTerrestreBean.getOrdenPmi());
				generadorDocxImpl.generaChecklistTerrestre(checklistTerrestreBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	private void generaChecklistsMaritimo(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistMaritimoBean> listChecklistMaritimoBean = integradorDocx
					.integraChecklistsMaritimo(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistMaritimoBean> iteratorChecklistMaritimoBean = listChecklistMaritimoBean.iterator();
			ChecklistMaritimoBean checklistMaritimoBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistMaritimoBean.hasNext()) {
				checklistMaritimoBean = iteratorChecklistMaritimoBean.next();
				orden.put("ordenrelacionada", checklistMaritimoBean.getOrdenPmi());
				generadorBean.setNombreDoc(checklistMaritimoBean.getOrdenPmi());
				generadorDocxImpl.generaChecklistMaritimo(checklistMaritimoBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	private void generaChecklistsComercial(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			Map<String, String> orden = new HashMap<String, String>();
			List<ChecklistComercialBean> listChecklistComercialBean = integradorDocx
					.integraChecklistsComercial(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistComercialBean> iteratorChecklistComercialBean = listChecklistComercialBean.iterator();
			ChecklistComercialBean checklistComercialBean = null;
			while (iteratorChecklistComercialBean.hasNext()) {
				checklistComercialBean = iteratorChecklistComercialBean.next();
				orden.put("ordenrelacionada", checklistComercialBean.getOrdenExpediente());
				generadorBean.setNombreDoc(checklistComercialBean.getOrdenExpediente());
				generadorDocxImpl.generaChecklistComercial(checklistComercialBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	private void generaChecklistsComCrudo(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistComCrudoBean> listChecklistComCrudoBean = integradorDocx
					.integraChecklistsComCrudo(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistComCrudoBean> iteratorChecklistComCrudoBean = listChecklistComCrudoBean.iterator();
			ChecklistComCrudoBean checklistComCrudoBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistComCrudoBean.hasNext()) {
				checklistComCrudoBean = iteratorChecklistComCrudoBean.next();
				orden.put("ordenrelacionada", checklistComCrudoBean.getOrdenExpediente());
				generadorBean.setNombreDoc(checklistComCrudoBean.getOrdenExpediente());
				generadorDocxImpl.generaChecklistComCrudo(checklistComCrudoBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	private void generaChecklistsFletamentos(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistFletamentosBean> listChecklistFletamentosBean = integradorDocx
					.integraChecklistsFletamentos(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistFletamentosBean> iteratorChecklistFletamentosBean = listChecklistFletamentosBean
					.iterator();
			ChecklistFletamentosBean checklistFletamentosBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistFletamentosBean.hasNext()) {
				checklistFletamentosBean = iteratorChecklistFletamentosBean.next();
				orden.put("ordenrelacionada", checklistFletamentosBean.getOrdenFletamento());
				generadorBean.setNombreDoc(checklistFletamentosBean.getOrdenFletamento());
				generadorDocxImpl.generaChecklistFletamentos(checklistFletamentosBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	private void generaChecklistsOperativoEstado(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistOperativoEstadoBean> listChecklistOperativoEstadoBean = integradorDocx
					.integraChecklistsOperativoEstado(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistOperativoEstadoBean> iteratorChecklistOperativoEstadoBean = listChecklistOperativoEstadoBean
					.iterator();
			ChecklistOperativoEstadoBean checklistOperativoEstadoBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistOperativoEstadoBean.hasNext()) {
				checklistOperativoEstadoBean = iteratorChecklistOperativoEstadoBean.next();
				orden.put("ordenrelacionada", checklistOperativoEstadoBean.getOrdenPmi());
				generadorBean.setNombreDoc(checklistOperativoEstadoBean.getOrdenPmi());
				generadorDocxImpl.generaChecklistOperativoEstado(checklistOperativoEstadoBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	public List<DocumentoGeneradoDto> generaChecklistOperativoEstado(ChecklistOperativoEstadoBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()==2){
			// generaChecklistsMaritimo(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("0900d4318007d658");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	private void generaChecklistsComercialEstado(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean) throws DfException {
		try {
			IntegradorDocx integradorDocx = new IntegradorDocx();
			GeneradorDocxImpl generadorDocxImpl = new GeneradorDocxImpl();
			List<ChecklistComercialEstadoBean> listChecklistComercialEstadoBean = integradorDocx
					.integraChecklistsComercialEstado(asuntoSubexpediente, numeroExpediente, generadorBean.getUserName());
			Iterator<ChecklistComercialEstadoBean> iteratorChecklistComercialEstadoBean = listChecklistComercialEstadoBean
					.iterator();
			ChecklistComercialEstadoBean checklistComercialEstadoBean = null;
			Map<String, String> orden = new HashMap<String, String>();
			while (iteratorChecklistComercialEstadoBean.hasNext()) {
				checklistComercialEstadoBean = iteratorChecklistComercialEstadoBean.next();
				orden.put("ordenrelacionada", checklistComercialEstadoBean.getOrdenExpediente());
				generadorBean.setNombreDoc(checklistComercialEstadoBean.getOrdenExpediente());
				generadorDocxImpl.generaChecklistComercialEstado(checklistComercialEstadoBean, generadorBean, orden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	public List<DocumentoGeneradoDto> generaChecklistComercialEstado(ChecklistComercialEstadoBean paramBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			// if(parametros!=null && parametros.size()>0){
			// generaChecklistsComCrudo(parametros.get("asuntoSubexpediente"),
			// parametros.get("numeroExpediente"), generadorBean);
			// return null;
			// }
			// else {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			contenidoTabla = null;
			idTabla = null;
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("0900d4318007d657");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, false,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	
	public List<DocumentoGeneradoDto> generaExpedientesDesclasifica(ExpedientesDesclasificaBean paramBean,
			List<ExpedientesDesclasificaTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			String[] idTabla;
			contenidoTabla = integradorDocx.integraTablaExpedientesDesclasifica(tablaBean);
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6", "SJ_7", "SJ_8", "SJ_9", "SJ_10",
					"SJ_11", "SJ_12" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ea0e");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaGuiaSimple(GuiaSimpleBean paramBean, List<GuiaSimpleTablaBean> tablaBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			contenidoTabla = integradorDocx.integraTablaGuiaSimple(tablaBean);
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ed3d");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaIndiceExpedientesReserva(IndiceExpedientesReservaBean paramBean,
			List<IndiceExpedientesReservaTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			contenidoTabla = integradorDocx.integraTablaIndiceExpedientesReserva(tablaBean);
			idTabla = new String[] { "SJ_1" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ed40");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaInTransferenciaPrimariaSecundaria(
			InTransferenciaPrimariaSecundariaBean paramBean, List<InTransferenciaPrimariaSecundariaTablaBean> tablaBean,
			GeneradorBean generadorBean, Map<String, String> parametros) throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			contenidoTabla = integradorDocx.integraTablaInTransferenciaPrimariaSecundaria(tablaBean);
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6", "SJ_7", "SJ_8", "SJ_9", "SJ_10",
					"SJ_11", "SJ_12", "SJ_13", "SJ_14" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ed41");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaValePrestamo(ValePrestamoBean paramBean,
			List<ValePrestamoTablaBean> tablaBean, GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			contenidoTabla = integradorDocx.integraTablaValePrestamo(tablaBean);
			paramBean = integradorDocx.integraValePrestamo(paramBean, generadorBean, parametros);
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6", "SJ_7", "SJ_8" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090000018000ed3f");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	public List<DocumentoGeneradoDto> generaIndiceCajaCrudo(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			contenidoTabla = integradorDocx.integraTablaIndiceCajaC(new IndiceCajaTablaBean());
			IndiceCajaBean paramBean = integradorDocx.integraIndiceCajaC(generadorBean, parametros);
paramBean = new IndiceCajaBean();
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6"};
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a98025e880");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	public List<DocumentoGeneradoDto> generaIndiceCarpetaCrudo(String asuntoSubexpediente, String numeroExpediente,
			 GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			
			IndiceCarpetaBean paramBean = integradorDocx.integraIndiceCarpetaC(generadorBean, parametros);
			contenidoTabla = integradorDocx.integraTablaIndiceCarpetaC(generadorBean, paramBean, new IndiceCarpetaTablaBean());
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a98025e882");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	public List<DocumentoGeneradoDto> generaIndiceCarpetaComercializador(String asuntoSubexpediente, String numeroExpediente,
			 GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			
			IndiceCarpetaBean paramBean = integradorDocx.integraIndiceCarpetaC(generadorBean, parametros);
			contenidoTabla = integradorDocx.integraTablaIndiceCarpetaE(generadorBean, paramBean, new IndiceCarpetaTablaBean());
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a98025e882");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	public List<DocumentoGeneradoDto> generaIndiceCajaProducto(String asuntoSubexpediente, String numeroExpediente,
			GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			IndiceCajaBean paramBean = integradorDocx.integraIndiceCajaP(generadorBean, parametros);
			contenidoTabla = integradorDocx.integraTablaIndiceCajaP(new IndiceCajaTablaBean());
			
//paramBean = new IndiceCajaBean();
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a98025e881");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}
	public List<DocumentoGeneradoDto> generaIndiceCarpetaProducto(String asuntoSubexpediente, String numeroExpediente,
			 GeneradorBean generadorBean, Map<String, String> parametros)
					throws DfException {
		try {
			List<Map<String, String>> contenidoTabla;
			String[] idTabla;
			IntegradorDocx integradorDocx = new IntegradorDocx();
			IndiceCarpetaBean paramBean = integradorDocx.integraIndiceCarpetaP(generadorBean, parametros);
			contenidoTabla = integradorDocx.integraTablaIndiceCarpetaP(generadorBean, paramBean, new IndiceCarpetaTablaBean());
			idTabla = new String[] { "SJ_1", "SJ_2", "SJ_3", "SJ_4", "SJ_5", "SJ_6" };
			UsuarioDto usuarioDto = new UsuarioDto();
			TransformarDocumentoService transformarDocumentoService = new TransformarDocumentoServiceImpl();
			List<ParametrosDocumentoDto> listaParametros = new ArrayList<ParametrosDocumentoDto>();
			ParametrosDocumentoDto parametrosDocumentoDto = new ParametrosDocumentoDto();
			parametrosDocumentoDto.setIdDocumentoPlantilla("090420a98025e883");
			parametrosDocumentoDto.setNombreDocumento(generadorBean.getNombreDoc());
			parametrosDocumentoDto.setRutaDocumento(generadorBean.getRutaDocumento());
			parametrosDocumentoDto.setRutaXml(generadorBean.getRutaXML());
			parametrosDocumentoDto.setXmlDatos(jaxbObjectToXML(paramBean));
			listaParametros.add(parametrosDocumentoDto);
			List<DocumentoGeneradoDto> listaDocumentosGenerados = transformarDocumentoService.crearDocumentoDOCX(
					usuarioDto, (List<ParametrosDocumentoDto>) listaParametros, contenidoTabla, idTabla, true,
					generadorBean.getUserName(), parametros);
			System.out.println("Lista indice carpeta:"+listaDocumentosGenerados.toString());
			return listaDocumentosGenerados;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DfException(e.getMessage());
		}
	}

	// public List<DocumentoGeneradoDto> generaCheckListComun(){
	//
	// }
	public String prueba(ValePrestamoBean paramBean, List<ValePrestamoTablaBean> tablaBean, GeneradorBean generadorBean,
			Map<String, String> parametros, String p) throws DfException {
		System.out.println("Metodo de prueba");
		// String l=null;
		try {
//			this.clientePeopleSoft("FROMERO");
			this.clienteFirmas("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "val:" + p;
	}
	public void clienteFirmas(String args) throws Exception {

		// SOAP Header
		// TODO: IOMH Leer los bookmarks para integrar el header con el handler
		// de SOAP
		// SOAPHeader header =
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();

		Firmas arrayOfDatosPeoplesoft = new Firmas();
		arrayOfDatosPeoplesoft = wSDocumentumSoap.getFirmaPorLineaDeNegocio("TPA-0202", "(GAS) GASOLINAS Y COMPONENTES");
		DatosPeoplesoft datosPeoplesoft = null;

		
			System.out.println(arrayOfDatosPeoplesoft.getFirmaAprobacion() + " " + arrayOfDatosPeoplesoft.getFirmaIntegrador() + " " + 
					arrayOfDatosPeoplesoft.getFirmaRevisionFisica() + " " + arrayOfDatosPeoplesoft.getFirmaRevisor() + " " + 
					arrayOfDatosPeoplesoft.getFirmaVistoBueno() + " " + arrayOfDatosPeoplesoft.getFechaTrade() );
		
	}

	public void clientePeopleSoft(String args) throws Exception {

		// SOAP Header
		// TODO: IOMH Leer los bookmarks para integrar el header con el handler
		// de SOAP
		// SOAPHeader header =
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();

		ArrayOfDatosPeoplesoft arrayOfDatosPeoplesoft = new ArrayOfDatosPeoplesoft();
		arrayOfDatosPeoplesoft = wSDocumentumSoap.getDatosPeopleSoft(args);
		DatosPeoplesoft datosPeoplesoft = null;

		for (int i = 0; arrayOfDatosPeoplesoft.getDatosPeoplesoft().size() > i; i++) {
			datosPeoplesoft = arrayOfDatosPeoplesoft.getDatosPeoplesoft().get(i);
			System.out.println(datosPeoplesoft.getNombre() + " " + datosPeoplesoft.getApellido() + " "
					+ datosPeoplesoft.getCurp());
		}

		// WSTest1Service service = new WSTest1Service();
		// CxfTest1SEI cxfTest1SEI = service.getWSTest1Port();
		// System.out.println(cxfTest1SEI.whatIsTheAnswer("Yeah!"));

	}

	public static void main2(String[] args) throws Exception {

		try {
			GeneradorDocxImpl generadorDocx = new GeneradorDocxImpl();
			GeneradorBean generadorBean = new GeneradorBean();
			generadorBean.setRutaXML("/OTAC/GeneradosPMI");
			generadorBean.setRutaDocumento("/OTAC/GeneradosPMI");
			generadorBean.setNombreDoc("TPP..");
			generadorBean.setUserName("CEPMI001");

			Map<String, String> mp = new HashMap<String, String>();
			mp.put("numeroExpediente", "PMI-15E-71-000618-2015");
			mp.put("asuntoSubexpediente", "Operativo Terrestre");

			// generadorDocx.generaChecklistComercial(new
			// ChecklistComercialBean(), generadorBean,mp);
			// generadorDocx.generaChecklistComercial(new
			// ChecklistComercialBean(), generadorBean,mp);
			// for(int i=0;i<20;i++){

			CaratulaBean b = new CaratulaBean();
			b.setClasificacionArchivisticaFechaAperturaExpediente("1452492000000");
			b.setValorDocumentalAdministrativo("true");
			b.setValorDocumentalFinanciero("true");
			b.setValorDocumentalLegal("false");
			generadorDocx.generaCaratula(b, generadorBean, null);
			// generadorDocx.generaChecklistComCrudo(new
			// ChecklistComCrudoBean(), generadorBean,null);
			// generadorDocx.generaChecklistComercial(new
			// ChecklistComercialBean(), generadorBean,null);
			// generadorDocx.generaChecklistDemoras(new ChecklistDemorasBean(),
			// generadorBean,null);
			// generadorDocx.generaChecklistFletamentos(new
			// ChecklistFletamentosBean(), generadorBean,null);
			// generadorDocx.generaChecklistMaritimo(new
			// ChecklistMaritimoBean(), generadorBean,null);
			// generadorDocx.generaChecklistReclamos(new
			// ChecklistReclamosBean(), generadorBean,null);
			// generadorDocx.generaChecklistTerrestre(new
			// ChecklistTerrestreBean(), generadorBean,null);
			// generadorDocx.generaExpedientesDesclasifica(new
			// ExpedientesDesclasificaBean(),null, generadorBean,null);
			// generadorDocx.generaGuiaSimple(new GuiaSimpleBean(),
			// null,generadorBean,null);
			// generadorDocx.generaIndiceExpedientesReserva(new
			// IndiceExpedientesReservaBean(), null,generadorBean,null);
			// generadorDocx.generaInTransferenciaPrimariaSecundaria(new
			// InTransferenciaPrimariaSecundariaBean(),
			// null,generadorBean,null);
			// generadorDocx.generaValePrestamo(new ValePrestamoBean(),
			// null,generadorBean,null);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String jaxbObjectToXML(PadreBean emp) throws Exception {
		String xml = null;
		try {

			JAXBContext context = JAXBContext.newInstance(emp.getClass());
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			// Result uno;
			// Node node = new ;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			File writer = new File("temp.xml");
			// m.marshal(emp, System.out);
			m.marshal(emp, baos);
			xml = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			System.out.println("¿?:" + xml);

			// Write to File
			// m.marshal(emp, new File("prueba.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static void main1(String[] args) throws Exception {
		String n = "sgap.2015";
		System.out.println(n);
		n = RegistryPasswordUtils.encrypt(n);
		System.out.println(n);
		n = RegistryPasswordUtils.decrypt(n);
		System.out.println(n);

	}
	
}
