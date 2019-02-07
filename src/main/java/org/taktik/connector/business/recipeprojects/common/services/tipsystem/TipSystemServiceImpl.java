package org.taktik.connector.business.recipeprojects.common.services.tipsystem;

import javax.xml.bind.JAXBElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.ehealth.apb.gfddpp.services.tipsystem.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.ObjectFactory;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedCheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public class TipSystemServiceImpl implements TipSystemService {
	
	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(TipSystemServiceImpl.class);
	
	public static final String ENDPOINT_NAME = "endpoint.tipsystem";

	private static TipSystemService tipSystemService;

	private TipSystemServiceImpl() {
	}

	/**
	 * Gets the singleton instance of TipSystemServiceImpl.
	 * 
	 * @return singleton instance of TipSystemServiceImpl
	 */
	public static TipSystemService getInstance() {
		if (tipSystemService == null) {
			tipSystemService = new TipSystemServiceImpl();
		}
		return tipSystemService;
	}
	
	@Override
	public SimpleResponseType registerData(SAMLToken samlToken, Credential credential, SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<SealedMessageRequestType> jb = objectFactory.createRegisterDataRequest(sealedMessageRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public SimpleResponseType updateData(SAMLToken samlToken, Credential credential, SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<SealedMessageRequestType> jb = objectFactory.createUpdateDataRequest(sealedMessageRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public SimpleResponseType deleteData(SAMLToken samlToken, Credential credential, SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<SealedMessageRequestType> jb = objectFactory.createDeleteDataRequest(sealedMessageRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public RoutedSealedResponseType getProductFilter(SAMLToken samlToken, Credential credential, RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<RoutedSealedRequestType> jb = objectFactory.createGetProductFilterRequest(routedSealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public RoutedSealedResponseType getSystemServices(SAMLToken samlToken, Credential credential, RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<RoutedSealedRequestType> jb = objectFactory.createGetSystemServicesRequest(routedSealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public RoutedSealedResponseType retrieveStatusMessages(SAMLToken samlToken, Credential credential, RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<RoutedSealedRequestType> jb = objectFactory.createRetrieveStatusMessagesRequest(routedSealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public SimpleResponseType sendStatusMessages(SAMLToken samlToken, Credential credential, SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<SealedMessageRequestType> jb = objectFactory.createSendStatusMessagesRequest(sealedMessageRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

	@Override
	public RoutedCheckAliveResponseType checkAliveTIP(SAMLToken samlToken, Credential credential, CheckAliveRequestType checkAliveRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<CheckAliveRequestType> jb = objectFactory.createCheckAliveTIPRequest(checkAliveRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, CheckAliveRequestType.class, RoutedCheckAliveResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, false, false, false);
	}

}
