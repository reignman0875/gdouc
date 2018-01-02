
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSDocumentumSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSDocumentumSoap {


    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getContraparte")
    @WebResult(name = "getContraparteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getContraparte", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetContraparte")
    @ResponseWrapper(localName = "getContraparteResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetContraparteResponse")
    public String getContraparte(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getEmpresaPropietaria")
    @WebResult(name = "getEmpresaPropietariaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getEmpresaPropietaria", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEmpresaPropietaria")
    @ResponseWrapper(localName = "getEmpresaPropietariaResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEmpresaPropietariaResponse")
    public ArrayOfString getEmpresaPropietaria(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getRfc")
    @WebResult(name = "getRfcResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getRfc", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetRfc")
    @ResponseWrapper(localName = "getRfcResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetRfcResponse")
    public String getRfc(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getTerceros")
    @WebResult(name = "getTercerosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getTerceros", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTerceros")
    @ResponseWrapper(localName = "getTercerosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTercerosResponse")
    public String getTerceros(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getCompraVenta")
    @WebResult(name = "getCompraVentaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getCompraVenta", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCompraVenta")
    @ResponseWrapper(localName = "getCompraVentaResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCompraVentaResponse")
    public String getCompraVenta(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getContrapartes")
    @WebResult(name = "getContrapartesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getContrapartes", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetContrapartes")
    @ResponseWrapper(localName = "getContrapartesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetContrapartesResponse")
    public ArrayOfString getContrapartes();

    /**
     * 
     * @param estrategia
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getProductos")
    @WebResult(name = "getProductosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getProductos", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProductos")
    @ResponseWrapper(localName = "getProductosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProductosResponse")
    public ArrayOfString getProductos(
        @WebParam(name = "estrategia", targetNamespace = "http://tempuri.org/")
        String estrategia);

    /**
     * 
     * @param estrategia
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getOrdenesReclamos")
    @WebResult(name = "getOrdenesReclamosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getOrdenesReclamos", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetOrdenesReclamos")
    @ResponseWrapper(localName = "getOrdenesReclamosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetOrdenesReclamosResponse")
    public ArrayOfString getOrdenesReclamos(
        @WebParam(name = "estrategia", targetNamespace = "http://tempuri.org/")
        String estrategia);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getPuertoCarga")
    @WebResult(name = "getPuertoCargaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPuertoCarga", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPuertoCarga")
    @ResponseWrapper(localName = "getPuertoCargaResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPuertoCargaResponse")
    public String getPuertoCarga(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getPuertoDescarga")
    @WebResult(name = "getPuertoDescargaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPuertoDescarga", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPuertoDescarga")
    @ResponseWrapper(localName = "getPuertoDescargaResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPuertoDescargaResponse")
    public String getPuertoDescarga(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getProducto")
    @WebResult(name = "getProductoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getProducto", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProducto")
    @ResponseWrapper(localName = "getProductoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProductoResponse")
    public String getProducto(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getAgente")
    @WebResult(name = "getAgenteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getAgente", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetAgente")
    @ResponseWrapper(localName = "getAgenteResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetAgenteResponse")
    public String getAgente(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getTrader")
    @WebResult(name = "getTraderResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getTrader", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTrader")
    @ResponseWrapper(localName = "getTraderResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTraderResponse")
    public String getTrader(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getBroker")
    @WebResult(name = "getBrokerResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getBroker", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetBroker")
    @ResponseWrapper(localName = "getBrokerResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetBrokerResponse")
    public String getBroker(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getInspector")
    @WebResult(name = "getInspectorResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getInspector", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInspector")
    @ResponseWrapper(localName = "getInspectorResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInspectorResponse")
    public String getInspector(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getMedioTransporte")
    @WebResult(name = "getMedioTransporteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getMedioTransporte", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetMedioTransporte")
    @ResponseWrapper(localName = "getMedioTransporteResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetMedioTransporteResponse")
    public String getMedioTransporte(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getBarco")
    @WebResult(name = "getBarcoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getBarco", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetBarco")
    @ResponseWrapper(localName = "getBarcoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetBarcoResponse")
    public String getBarco(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @param baseDatos
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getEstrategias")
    @WebResult(name = "getEstrategiasResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getEstrategias", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategias")
    @ResponseWrapper(localName = "getEstrategiasResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategiasResponse")
    public ArrayOfString getEstrategias(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos);

    /**
     * 
     * @param usuario
     * @param baseDatos
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getEstrategiasUsuario")
    @WebResult(name = "getEstrategiasUsuarioResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getEstrategiasUsuario", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategiasUsuario")
    @ResponseWrapper(localName = "getEstrategiasUsuarioResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategiasUsuarioResponse")
    public ArrayOfString getEstrategiasUsuario(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos,
        @WebParam(name = "usuario", targetNamespace = "http://tempuri.org/")
        String usuario);

    /**
     * 
     * @param baseDatos
     * @param portFullName
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getProfitCenter")
    @WebResult(name = "getProfitCenterResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getProfitCenter", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProfitCenter")
    @ResponseWrapper(localName = "getProfitCenterResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetProfitCenterResponse")
    public ArrayOfString getProfitCenter(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos,
        @WebParam(name = "port_full_name", targetNamespace = "http://tempuri.org/")
        String portFullName);

    /**
     * 
     * @param baseDatos
     * @return
     *     returns org.tempuri.ArrayOfReporteSeguimiento
     */
    @WebMethod(action = "http://tempuri.org/getOrdenesExistentes")
    @WebResult(name = "getOrdenesExistentesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getOrdenesExistentes", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetOrdenesExistentes")
    @ResponseWrapper(localName = "getOrdenesExistentesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetOrdenesExistentesResponse")
    public ArrayOfReporteSeguimiento getOrdenesExistentes(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos);

    /**
     * 
     * @param ordenComercial
     * @return
     *     returns org.tempuri.ArrayOfReporteSeguimiento
     */
    @WebMethod(action = "http://tempuri.org/getEstrategiaPorOrden")
    @WebResult(name = "getEstrategiaPorOrdenResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getEstrategiaPorOrden", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategiaPorOrden")
    @ResponseWrapper(localName = "getEstrategiaPorOrdenResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEstrategiaPorOrdenResponse")
    public ArrayOfReporteSeguimiento getEstrategiaPorOrden(
        @WebParam(name = "orden_comercial", targetNamespace = "http://tempuri.org/")
        String ordenComercial);

    /**
     * 
     * @param baseDatos
     * @param portFullName
     * @return
     *     returns org.tempuri.ArrayOfPortafolios
     */
    @WebMethod(action = "http://tempuri.org/getPortafolios")
    @WebResult(name = "getPortafoliosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPortafolios", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafolios")
    @ResponseWrapper(localName = "getPortafoliosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafoliosResponse")
    public ArrayOfPortafolios getPortafolios(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos,
        @WebParam(name = "port_full_name", targetNamespace = "http://tempuri.org/")
        String portFullName);

    /**
     * 
     * @param month
     * @param year
     * @param baseDatos
     * @param portFullName
     * @return
     *     returns org.tempuri.ArrayOfPortafolios
     */
    @WebMethod(action = "http://tempuri.org/getPortafoliosPorMes")
    @WebResult(name = "getPortafoliosPorMesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPortafoliosPorMes", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafoliosPorMes")
    @ResponseWrapper(localName = "getPortafoliosPorMesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafoliosPorMesResponse")
    public ArrayOfPortafolios getPortafoliosPorMes(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos,
        @WebParam(name = "port_full_name", targetNamespace = "http://tempuri.org/")
        String portFullName,
        @WebParam(name = "month", targetNamespace = "http://tempuri.org/")
        int month,
        @WebParam(name = "year", targetNamespace = "http://tempuri.org/")
        int year);

    /**
     * 
     * @param month
     * @param year
     * @param baseDatos
     * @param portFullName
     * @return
     *     returns org.tempuri.ArrayOfPortafolios
     */
    @WebMethod(action = "http://tempuri.org/getPortafoliosPorMesCimComEdo")
    @WebResult(name = "getPortafoliosPorMesCimComEdoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPortafoliosPorMesCimComEdo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafoliosPorMesCimComEdo")
    @ResponseWrapper(localName = "getPortafoliosPorMesCimComEdoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPortafoliosPorMesCimComEdoResponse")
    public ArrayOfPortafolios getPortafoliosPorMesCimComEdo(
        @WebParam(name = "base_datos", targetNamespace = "http://tempuri.org/")
        String baseDatos,
        @WebParam(name = "port_full_name", targetNamespace = "http://tempuri.org/")
        String portFullName,
        @WebParam(name = "month", targetNamespace = "http://tempuri.org/")
        int month,
        @WebParam(name = "year", targetNamespace = "http://tempuri.org/")
        int year);

    /**
     * 
     * @param year
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getAreasContractualesCimComEdo")
    @WebResult(name = "getAreasContractualesCimComEdoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getAreasContractualesCimComEdo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetAreasContractualesCimComEdo")
    @ResponseWrapper(localName = "getAreasContractualesCimComEdoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetAreasContractualesCimComEdoResponse")
    public ArrayOfString getAreasContractualesCimComEdo(
        @WebParam(name = "year", targetNamespace = "http://tempuri.org/")
        int year);

    /**
     * 
     * @param perfil
     * @return
     *     returns org.tempuri.ArrayOfDatosPeoplesoft
     */
    @WebMethod(action = "http://tempuri.org/getDatosPeopleSoft")
    @WebResult(name = "getDatosPeopleSoftResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getDatosPeopleSoft", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDatosPeopleSoft")
    @ResponseWrapper(localName = "getDatosPeopleSoftResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDatosPeopleSoftResponse")
    public ArrayOfDatosPeoplesoft getDatosPeopleSoft(
        @WebParam(name = "perfil", targetNamespace = "http://tempuri.org/")
        String perfil);

    /**
     * 
     * @param perfil
     * @return
     *     returns org.tempuri.ArrayOfDatosPeoplesoft
     */
    @WebMethod(action = "http://tempuri.org/getDatosPeopleSoftAdicional")
    @WebResult(name = "getDatosPeopleSoftAdicionalResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getDatosPeopleSoftAdicional", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDatosPeopleSoftAdicional")
    @ResponseWrapper(localName = "getDatosPeopleSoftAdicionalResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDatosPeopleSoftAdicionalResponse")
    public ArrayOfDatosPeoplesoft getDatosPeopleSoftAdicional(
        @WebParam(name = "perfil", targetNamespace = "http://tempuri.org/")
        String perfil);

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getCurrency")
    @WebResult(name = "getCurrencyResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getCurrency", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCurrency")
    @ResponseWrapper(localName = "getCurrencyResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCurrencyResponse")
    public ArrayOfString getCurrency();

    /**
     * 
     * @param calidadDemora
     * @param ordenComercial
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getIdReclamo")
    @WebResult(name = "getIdReclamoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getIdReclamo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetIdReclamo")
    @ResponseWrapper(localName = "getIdReclamoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetIdReclamoResponse")
    public ArrayOfString getIdReclamo(
        @WebParam(name = "ordenComercial", targetNamespace = "http://tempuri.org/")
        String ordenComercial,
        @WebParam(name = "calidad_demora", targetNamespace = "http://tempuri.org/")
        String calidadDemora);

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getInstitucionesBancarias")
    @WebResult(name = "getInstitucionesBancariasResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getInstitucionesBancarias", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInstitucionesBancarias")
    @ResponseWrapper(localName = "getInstitucionesBancariasResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInstitucionesBancariasResponse")
    public ArrayOfString getInstitucionesBancarias();

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getCatalogoBarcosArrendados")
    @WebResult(name = "getCatalogoBarcosArrendadosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getCatalogoBarcosArrendados", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCatalogoBarcosArrendados")
    @ResponseWrapper(localName = "getCatalogoBarcosArrendadosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCatalogoBarcosArrendadosResponse")
    public ArrayOfString getCatalogoBarcosArrendados();

    /**
     * 
     * @param lineaNegocio
     * @param ordenComercial
     * @return
     *     returns org.tempuri.Firmas
     */
    @WebMethod(action = "http://tempuri.org/getFirmaPorLineaDeNegocio")
    @WebResult(name = "getFirmaPorLineaDeNegocioResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getFirmaPorLineaDeNegocio", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetFirmaPorLineaDeNegocio")
    @ResponseWrapper(localName = "getFirmaPorLineaDeNegocioResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetFirmaPorLineaDeNegocioResponse")
    public Firmas getFirmaPorLineaDeNegocio(
        @WebParam(name = "orden_comercial", targetNamespace = "http://tempuri.org/")
        String ordenComercial,
        @WebParam(name = "linea_negocio", targetNamespace = "http://tempuri.org/")
        String lineaNegocio);

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getLineaNegocio")
    @WebResult(name = "getLineaNegocioResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getLineaNegocio", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetLineaNegocio")
    @ResponseWrapper(localName = "getLineaNegocioResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetLineaNegocioResponse")
    public ArrayOfString getLineaNegocio();

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfString
     */
    @WebMethod(action = "http://tempuri.org/getPeriodos")
    @WebResult(name = "getPeriodosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getPeriodos", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPeriodos")
    @ResponseWrapper(localName = "getPeriodosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetPeriodosResponse")
    public ArrayOfString getPeriodos();

    /**
     * 
     * @param orden
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getTipoDeContrato")
    @WebResult(name = "getTipoDeContratoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getTipoDeContrato", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTipoDeContrato")
    @ResponseWrapper(localName = "getTipoDeContratoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetTipoDeContratoResponse")
    public String getTipoDeContrato(
        @WebParam(name = "orden", targetNamespace = "http://tempuri.org/")
        String orden);

    /**
     * 
     * @return
     *     returns org.tempuri.ArrayOfTrade
     */
    @WebMethod(action = "http://tempuri.org/getCTTs")
    @WebResult(name = "getCTTsResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getCTTs", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCTTs")
    @ResponseWrapper(localName = "getCTTsResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCTTsResponse")
    public ArrayOfTrade getCTTs();

}
