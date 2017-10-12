package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.tools.RegistryPasswordUtils;
import com.documentum.web.formext.session.TrustedAuthenticatorTool;
import com.documentum.web.formext.session.TrustedAuthenticatorUtils;
import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.DocumentumService;

public class DocumentumServiceImpl implements DocumentumService {
	
	private IDfSessionManager sessionManager;
	private IDfSessionManager sessionManagerLT;
	private String username;
	private String password;
	private String repository;

	private static final Log log = LogFactory.getLog(DocumentumServiceImpl.class);
	
	public DocumentumServiceImpl() {
		//TODO: IOMH Cambiar login documentum
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "generador.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) 
				prop.load(inputStream);
			else 
				throw new Exception("No se puede logear");
			sessionManager = null; 
			username = prop.getProperty("user");
			repository = prop.getProperty("repo");
				password = new String(RegistryPasswordUtils.decrypt(prop.getProperty("pwd")).trim());
//				password = new String((prop.getProperty("pwd")).trim());
				System.out.println("PasswordQA:"+password);
				
			} catch (Exception e){
				System.out.println("Exception: " + e);
			} 
		finally {
			try{
				inputStream.close();
			} catch(Exception e){
				
			}
		}
		
		
//		username = new String("Administrator");
//		password = new String("admin");
//		repository = new String("repo1");
		
		
		
	}
//	public IDfSession getSession() {
//		try {
//			
//			final IDfLoginInfo loginInfo = new DfClientX().getLoginInfo();
//    		loginInfo.setUser(username);
//    		loginInfo.setPassword(password);
//    		sessionManager = new DfClientX().getLocalClient().newSessionManager();
//
//    		if (sessionManager.hasIdentity(repository)) {
//    			sessionManager.clearIdentity(repository);
//    		}
//
//    		sessionManager.setIdentity(repository, loginInfo);
//    	
//    		return sessionManager.getSession(repository);
//    		
//		} catch (Exception e) {
//			final String msg = "Error al obtener una sesión de documentum";
//			log.error(msg, e);
//			throw new BusinessException("ERROR_OBTENER_SESION_DOCUMENTUM", msg);
//		}
//	}
	
	public IDfSession getSession(String userLT) {
		try {
			
			final IDfLoginInfo loginInfo = new DfClientX().getLoginInfo();
    		loginInfo.setUser(username);
    		loginInfo.setPassword(password);
    		sessionManager = new DfClientX().getLocalClient().newSessionManager();

    		if (sessionManager.hasIdentity(repository)) {
    			sessionManager.clearIdentity(repository);
    		}

    		sessionManager.setIdentity(repository, loginInfo);
    		if(userLT!=null&&!userLT.trim().equals(""))
    			return getSessionLT(sessionManager.getSession(repository),userLT);
    		return sessionManager.getSession(repository);
    		
		} catch (Exception e) {
			final String msg = "Error al obtener una sesión de documentum";
			log.error(msg, e);
			throw new BusinessException("ERROR_OBTENER_SESION_DOCUMENTUM", msg);
		}
	}
	public IDfSession getSessionLT(IDfSession session, String username) {
		try {
			
			IDfClientX _cx = new DfClientX();
			IDfClient _client = _cx.getLocalClient();
			IDfLoginInfo li = new DfLoginInfo();
			li.setUser(username);
			System.out.println("Obteniendo sesion para: "+username+"\n");
			// assume session is an already established IDfSession
			String ticket = session.getLoginTicketForUser(username);
			li.setPassword(ticket);
			// get session mgr
			releaseSession(session);
			sessionManager = _client.newSessionManager();
			sessionManager.setIdentity(repository,li);
			// return new session for named user
			IDfSession newSession = sessionManager.getSession(repository);
			
			return newSession;
			
		} catch (Exception e) {
			final String msg = "Error al obtener una sesión de documentum";
			log.error(msg, e);
			throw new BusinessException("ERROR_OBTENER_SESION_DOCUMENTUM", msg);
		}
	}

	
	public void releaseSession(final IDfSession session) {
		try {
			
			if (session != null && session.isConnected()) {
				sessionManager.release(session);
			}
		} catch (Exception ex) {
			log.error("Error al liberar la sesion de documentum", ex);
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = TrustedAuthenticatorUtils.decrypt(password);
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getRepository() {
		return repository;
	}
	
}
