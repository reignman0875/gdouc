package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.tempuri.ArrayOfDatosPeoplesoft;
import org.tempuri.DatosPeoplesoft;
import org.tempuri.WSDocumentum;
import org.tempuri.WSDocumentumSoap;

import com.documentum.xerces_2_8_0.xerces.util.URI.MalformedURIException;



public class IntegraPeopleSoftDB {
	public String ejecutaWsProducto(String orden) throws Exception{
		
		InputStream inputStream = null;
			Properties prop = new Properties();
			String propFileName = "generador.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) 
				prop.load(inputStream);
			else 
				throw new Exception("No se puede logear");
			String servicio = prop.getProperty("serviciops");

		WSDocumentum wSDocumentum = new WSDocumentum(new URL(servicio));
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		
		String producto = wSDocumentumSoap.getProducto(orden);
		
		return producto;
	}
	public PeopleSoftBean ejecutaWsEmp(String perfil) throws Exception{
		PeopleSoftBean peopleSoftBean = new PeopleSoftBean();
		
		InputStream inputStream = null;
			Properties prop = new Properties();
			String propFileName = "generador.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) 
				prop.load(inputStream);
			else 
				throw new Exception("No se puede logear");
			String servicio = prop.getProperty("serviciops");

		WSDocumentum wSDocumentum = new WSDocumentum(new URL(servicio));
		WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
		
		ArrayOfDatosPeoplesoft arrayOfDatosPeoplesoft = new ArrayOfDatosPeoplesoft();
		arrayOfDatosPeoplesoft = wSDocumentumSoap.getDatosPeopleSoft(perfil);
		DatosPeoplesoft datosPeoplesoft = null;
		
		for (int i = 0 ; arrayOfDatosPeoplesoft.getDatosPeoplesoft().size()>i;i++){
			datosPeoplesoft = arrayOfDatosPeoplesoft.getDatosPeoplesoft().get(i);
			peopleSoftBean.setNombrePosicion(datosPeoplesoft.getPuesto()!=null?datosPeoplesoft.getPuesto().trim():"");
			peopleSoftBean.setPerfil(perfil);
			peopleSoftBean.setNombre(datosPeoplesoft.getNombre()!=null?datosPeoplesoft.getNombre().trim():"");
			peopleSoftBean.setApellidos((datosPeoplesoft.getApellido()!=null?datosPeoplesoft.getApellido().trim():"")+
					" "+(datosPeoplesoft.getSegundoApellido()!=null?datosPeoplesoft.getSegundoApellido().trim():""));
			peopleSoftBean.setPuesto(datosPeoplesoft.getPuesto()!=null?datosPeoplesoft.getPuesto().trim():"");
			peopleSoftBean.setDepartamento(datosPeoplesoft.getArea()!=null?datosPeoplesoft.getArea().trim():"");
			peopleSoftBean.setArea(datosPeoplesoft.getDireccion()!=null?datosPeoplesoft.getDireccion().trim():"");
			peopleSoftBean.setDirNombre(datosPeoplesoft.getDirNombre()!=null?datosPeoplesoft.getDirNombre().trim():"");
			peopleSoftBean.setDirApellido(datosPeoplesoft.getDirApellido()+" "+datosPeoplesoft.getDirSegApellido());
			System.out.println(datosPeoplesoft.getNombre()+" "+datosPeoplesoft.getApellido()+" "+datosPeoplesoft.getCurp());
		}
		return peopleSoftBean;
	}
	public PeopleSoftBean ejecutaQueryEmp(String perfil) {
		String userName = "erojasm";
		String password = "erojasm";
		Connection conn = null;
		String url = "jdbc:sqlserver://MXDSQL03.pmi.pmicim.com;instanceName=DEV1_SQL;databaseName=HR91QA";
		PeopleSoftBean peopleSoftBean = new PeopleSoftBean();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, userName, password);

			ResultSet rs = conn.prepareStatement(PeopleSoftQuery.queryPeopleEmp+"'%"+perfil.trim().toUpperCase()+"%'").executeQuery();
			while (rs.next()) {								
//				peopleSoftBean.setPosicion(rs.getString(1));
				peopleSoftBean.setNombrePosicion(rs.getString(2));
//				peopleSoftBean.setEmpleado(rs.getString(3));
				peopleSoftBean.setPerfil(rs.getString(4));
				peopleSoftBean.setNombre(rs.getString(5));
				peopleSoftBean.setApellidos(rs.getString(6));
				peopleSoftBean.setPuesto(rs.getString(7));
//				peopleSoftBean.setCumpleanios(rs.getString(8));
//				peopleSoftBean.setCorreo(rs.getString(9));
//				peopleSoftBean.setUsuario(rs.getString(10));
				peopleSoftBean.setDepartamento(rs.getString(11));
				peopleSoftBean.setArea(rs.getString(12));
//				peopleSoftBean.setPosicionReporta(rs.getString(13));
//				peopleSoftBean.setAdministrador(rs.getString(14));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if(conn!=null&&!conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return peopleSoftBean;
	}
	public PeopleSoftBean ejecutaQueryDir(String area) {
		String userName = "erojasm";
		String password = "erojasm";
		Connection conn = null;
		String url = "jdbc:sqlserver://MXDSQL03.pmi.pmicim.com;instanceName=DEV1_SQL;databaseName=HR91QA";
		PeopleSoftBean peopleSoftBean = new PeopleSoftBean();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, userName, password);

			ResultSet rs = conn.prepareStatement(PeopleSoftQuery.queryPeopleDir+"'%"+area.trim()+"%'").executeQuery();
			while (rs.next()) {								
//				peopleSoftBean.setPosicion(rs.getString(1));
				peopleSoftBean.setNombrePosicion(rs.getString(2));
//				peopleSoftBean.setEmpleado(rs.getString(3));
				peopleSoftBean.setPerfil(rs.getString(4));
				peopleSoftBean.setNombre(rs.getString(5));
				peopleSoftBean.setApellidos(rs.getString(6));
				peopleSoftBean.setPuesto(rs.getString(7));
//				peopleSoftBean.setCumpleanios(rs.getString(8));
//				peopleSoftBean.setCorreo(rs.getString(9));
//				peopleSoftBean.setUsuario(rs.getString(10));
				peopleSoftBean.setDepartamento(rs.getString(11));
				peopleSoftBean.setArea(rs.getString(12));
//				peopleSoftBean.setPosicionReporta(rs.getString(13));
//				peopleSoftBean.setAdministrador(rs.getString(14));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if(conn!=null&&!conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return peopleSoftBean;
	}
	
		public static void main(String[] args) throws Exception {
			System.out.println("numero de solicitud");
			//SOAP Header
			//TODO: IOMH Leer los bookmarks para integrar el header con el handler de SOAP
//SOAPHeader header = 
			WSDocumentum wSDocumentum = new WSDocumentum();
			WSDocumentumSoap wSDocumentumSoap = wSDocumentum.getWSDocumentumSoap();
			
			ArrayOfDatosPeoplesoft arrayOfDatosPeoplesoft = new ArrayOfDatosPeoplesoft();
			arrayOfDatosPeoplesoft = wSDocumentumSoap.getDatosPeopleSoft("FROMERO");
			DatosPeoplesoft datosPeoplesoft = null;
			
			for (int i = 0 ; arrayOfDatosPeoplesoft.getDatosPeoplesoft().size()>i;i++){
				datosPeoplesoft = arrayOfDatosPeoplesoft.getDatosPeoplesoft().get(i);
				System.out.println(datosPeoplesoft.getNombre()+" "+datosPeoplesoft.getApellido()+" "+datosPeoplesoft.getCurp());
			}
			
//			WSTest1Service service = new WSTest1Service();
//			CxfTest1SEI cxfTest1SEI = service.getWSTest1Port();
//			System.out.println(cxfTest1SEI.whatIsTheAnswer("Yeah!"));
			
		
		}
}
