
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WSDocumentum", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://172.19.120.44:8082/WSDocumentum.asmx?WSDL")
public class WSDocumentum
    extends Service
{

    private final static URL WSDOCUMENTUM_WSDL_LOCATION;
    private final static WebServiceException WSDOCUMENTUM_EXCEPTION;
    private final static QName WSDOCUMENTUM_QNAME = new QName("http://tempuri.org/", "WSDocumentum");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.19.120.44:8082/WSDocumentum.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSDOCUMENTUM_WSDL_LOCATION = url;
        WSDOCUMENTUM_EXCEPTION = e;
    }

    public WSDocumentum() {
        super(__getWsdlLocation(), WSDOCUMENTUM_QNAME);
    }

    public WSDocumentum(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSDOCUMENTUM_QNAME, features);
    }

    public WSDocumentum(URL wsdlLocation) {
        super(wsdlLocation, WSDOCUMENTUM_QNAME);
    }

    public WSDocumentum(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSDOCUMENTUM_QNAME, features);
    }

    public WSDocumentum(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSDocumentum(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSDocumentumSoap
     */
    @WebEndpoint(name = "WSDocumentumSoap")
    public WSDocumentumSoap getWSDocumentumSoap() {
        return super.getPort(new QName("http://tempuri.org/", "WSDocumentumSoap"), WSDocumentumSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSDocumentumSoap
     */
    @WebEndpoint(name = "WSDocumentumSoap")
    public WSDocumentumSoap getWSDocumentumSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "WSDocumentumSoap"), WSDocumentumSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSDOCUMENTUM_EXCEPTION!= null) {
            throw WSDOCUMENTUM_EXCEPTION;
        }
        return WSDOCUMENTUM_WSDL_LOCATION;
    }

}
