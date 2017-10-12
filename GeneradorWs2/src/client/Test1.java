package client;


import org.tempuri.ArrayOfDatosPeoplesoft;

import org.tempuri.DatosPeoplesoft;

import org.tempuri.WSDocumentum;
import org.tempuri.WSDocumentumSoap;



public class Test1 {
	
	public static void clientePeopleSoft(String args) throws Exception {
		
		//SOAP Header
		//TODO: IOMH Leer los bookmarks para integrar el header con el handler de SOAP
//		SOAPHeader header = 
		WSDocumentum wSDocumentum = new WSDocumentum();
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		
		ArrayOfDatosPeoplesoft arrayOfDatosPeoplesoft = new ArrayOfDatosPeoplesoft();
		arrayOfDatosPeoplesoft = wSDocumentumSoap.getDatosPeopleSoft(args);
		DatosPeoplesoft datosPeoplesoft = null;
		
		for (int i = 0 ; arrayOfDatosPeoplesoft.getDatosPeoplesoft().size()>i;i++){
			datosPeoplesoft = arrayOfDatosPeoplesoft.getDatosPeoplesoft().get(i);
			System.out.println(datosPeoplesoft.getNombre()+" "+datosPeoplesoft.getApellido()+" "+datosPeoplesoft.getCurp());
		}
		
//		WSTest1Service service = new WSTest1Service();
//		CxfTest1SEI cxfTest1SEI = service.getWSTest1Port();
//		System.out.println(cxfTest1SEI.whatIsTheAnswer("Yeah!"));
		
	
	}
	public static void main(String[] args) throws Exception {
		Test1.clientePeopleSoft("FROMERO");
	}
}
