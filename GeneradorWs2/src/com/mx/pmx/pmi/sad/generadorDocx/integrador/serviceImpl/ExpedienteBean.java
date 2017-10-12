package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

public class ExpedienteBean {

	private String rObjectId; 
	private String objectName;
	private String arFechCierr;
	private String arNumrExpdnt;
	private String arSerieDocmntl;
	
	public ExpedienteBean() {
		objectName=
		rObjectId= 
		arFechCierr=
		arNumrExpdnt=
		arSerieDocmntl=new String();
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getrObjectId() {
		return rObjectId;
	}
	public void setrObjectId(String rObjectId) {
		this.rObjectId = rObjectId;
	}
	public String getArFechCierr() {
		return arFechCierr;
	}
	public void setArFechCierr(String arFechCierr) {
		this.arFechCierr = arFechCierr;
	}
	public String getArNumrExpdnt() {
		return arNumrExpdnt;
	}
	public void setArNumrExpdnt(String arNumrExpdnt) {
		this.arNumrExpdnt = arNumrExpdnt;
	}
	public String getArSerieDocmntl() {
		return arSerieDocmntl;
	}
	public void setArSerieDocmntl(String arSerieDocmntl) {
		this.arSerieDocmntl = arSerieDocmntl;
	}
	
	
	
}
