
package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.4
 * Sun Nov 22 00:31:15 CST 2015
 * Generated source version: 3.1.4
 */

@XmlRootElement(name = "generaExpedientesDesclasifica", namespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generaExpedientesDesclasifica", namespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/", propOrder = {"arg0", "arg1", "arg2", "arg3"})

public class GeneraExpedientesDesclasifica {

    @XmlElement(name = "arg0")
    private com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaBean arg0;
    @XmlElement(name = "arg1")
    private java.util.List<com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaTablaBean> arg1;
    @XmlElement(name = "arg2")
    private com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GeneradorBean arg2;
    @XmlElement(name = "arg3")
    private java.util.Map<java.lang.String, java.lang.String> arg3;

    public com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaBean getArg0() {
        return this.arg0;
    }

    public void setArg0(com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaBean newArg0)  {
        this.arg0 = newArg0;
    }

    public java.util.List<com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaTablaBean> getArg1() {
        return this.arg1;
    }

    public void setArg1(java.util.List<com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.ExpedientesDesclasificaTablaBean> newArg1)  {
        this.arg1 = newArg1;
    }

    public com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GeneradorBean getArg2() {
        return this.arg2;
    }

    public void setArg2(com.mx.pmx.pmi.sad.generadorDocx.integrador.bean.GeneradorBean newArg2)  {
        this.arg2 = newArg2;
    }

    public java.util.Map<java.lang.String, java.lang.String> getArg3() {
        return this.arg3;
    }

    public void setArg3(java.util.Map<java.lang.String, java.lang.String> newArg3)  {
        this.arg3 = newArg3;
    }

}

