package com.mx.pmx.pmi.sad.generadorDocx.core.service;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocxDocumentDto;


public interface PoolPlantilla {
	
	public DocxDocumentDto obtenerPlantilla(String idPlantilla) ;
	
	public void clear();
	
	public void setCapacity(Integer capacity);

}
