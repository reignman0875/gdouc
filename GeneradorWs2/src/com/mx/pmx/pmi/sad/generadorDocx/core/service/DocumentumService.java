package com.mx.pmx.pmi.sad.generadorDocx.core.service;

import com.documentum.fc.client.IDfSession;

public interface DocumentumService {

	IDfSession getSession(String userLT);
//	IDfSession getSession();
	void releaseSession(final IDfSession session);
	String getRepository();
	
}
