package org.taktik.connector.business.recipeprojects.common.services.pcdh;

import javax.xml.bind.JAXBElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.ObjectFactory;
import be.ehealth.apb.gfddpp.services.pcdh.ResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public class PcdhServiceImpl implements PcdhService {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(PcdhServiceImpl.class);

	public static final String ENDPOINT_NAME = "endpoint.pcdh";

	private static PcdhService pcdhService;

	private PcdhServiceImpl() {
	}

	/**
	 * Gets the singleton instance of PcdhServiceImpl.
	 * 
	 * @return singleton instance of PcdhServiceImpl
	 */
	public static PcdhService getInstance() {
		if (pcdhService == null) {
			pcdhService = new PcdhServiceImpl();
		}
		return pcdhService;
	}

	public SealedResponseType getDataTypes(SAMLToken samlToken, Credential credential, SealedRequestType sealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactoryPcdh = new ObjectFactory();
		JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetDataTypesRequest(sealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, false, false);
	}

	@Override
	public SealedResponseType getData(SAMLToken samlToken, Credential credential, SealedRequestType sealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactoryPcdh = new ObjectFactory();
		JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetDataRequest(sealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, false, false);
	}

	@Override
	public SealedResponseType getPharmacyDetails(SAMLToken samlToken, Credential credential, SealedRequestType sealedRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactoryPcdh = new ObjectFactory();
		JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetPharmacyDetailsRequest(sealedRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, false, false);
	}

	@Override
	public ResponseType uploadPerformanceMetric(SAMLToken samlToken, Credential credential, UploadPerformanceMetricRequestType uploadPerformanceMetricRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactoryPcdh = new ObjectFactory();
		JAXBElement<UploadPerformanceMetricRequestType> jb = objectFactoryPcdh.createUploadPerformanceMetricRequest(uploadPerformanceMetricRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, UploadPerformanceMetricRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, false, false);
	}

	@Override
	public CheckAliveResponseType checkAlivePCDH(SAMLToken samlToken, Credential credential, CheckAliveRequestType checkAliveRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		ObjectFactory objectFactoryPcdh = new ObjectFactory();
		JAXBElement<CheckAliveRequestType> jb = objectFactoryPcdh.createCheckAlivePCDHRequest(checkAliveRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, CheckAliveRequestType.class, CheckAliveResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, false, false);
	}

}
