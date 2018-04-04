package org.taktik.freehealth.middleware.drugs.logic.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.taktik.freehealth.middleware.drugs.dao.DrugsDAO;


public class DatasourceInterceptor implements MethodInterceptor {

	protected DrugsDAO drugsDAO;
	
	public Object invoke(MethodInvocation inv) throws Throwable {
		drugsDAO.openDataStoreSession();
		Object result = inv.proceed();
		drugsDAO.closeDataStoreSession();		
		return result;
	}

	public DrugsDAO getDrugsDAO() {
		return drugsDAO;
	}

	public void setDrugsDAO(DrugsDAO drugsDAO) {
		this.drugsDAO = drugsDAO;
	}

}
