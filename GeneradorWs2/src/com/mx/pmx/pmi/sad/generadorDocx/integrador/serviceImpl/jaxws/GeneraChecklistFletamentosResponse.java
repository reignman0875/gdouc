
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

@XmlRootElement(name = "generaChecklistFletamentosResponse", namespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generaChecklistFletamentosResponse", namespace = "http://serviceImpl.integrador.generadorDocx.sad.pmi.pmx.mx.com/")

public class GeneraChecklistFletamentosResponse {

    @XmlElement(name = "return")
    private java.util.List<com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto> _return;

    public java.util.List<com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoGeneradoDto> new_return)  {
        this._return = new_return;
    }

}

