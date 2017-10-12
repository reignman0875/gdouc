
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
 * JAX-WS RI 2.2.10
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
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/getEmpresaPropietaria")
    @WebResult(name = "getEmpresaPropietariaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getEmpresaPropietaria", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEmpresaPropietaria")
    @ResponseWrapper(localName = "getEmpresaPropietariaResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetEmpresaPropietariaResponse")
    public String getEmpresaPropietaria(
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

}
