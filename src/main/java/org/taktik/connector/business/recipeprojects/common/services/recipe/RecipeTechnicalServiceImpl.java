package org.taktik.connector.business.recipeprojects.common.services.recipe;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import be.fgov.ehealth.recipe.protocol.v1.ObjectFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;


public class RecipeTechnicalServiceImpl implements RecipeTechnicalService {

	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(RecipeTechnicalServiceImpl.class);

	private static final String ENDPOINT_NAME = "endpoint.technical";

	private static RecipeTechnicalService recipeTechnicalService;

	private RecipeTechnicalServiceImpl() {
	}

	/**
	 * Gets the singleton instance of RecipeTechnicalServiceImpl.
	 * 
	 * @return singleton instance of RecipeTechnicalServiceImpl
	 */
	public static RecipeTechnicalService getInstance() {
		if (recipeTechnicalService == null) {
			recipeTechnicalService = new RecipeTechnicalServiceImpl();
		}
		return recipeTechnicalService;
	}

	@Override
	public UploadFileResponseType uploadFilePatient(SAMLToken samlToken, Credential credential, UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		LOG.info("Entered uploadFilePatient");

		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFilePatientRequest(uploadFileRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, false);
	}

	@Override
	public UploadFileResponseType uploadFilePrescriber(SAMLToken samlToken, Credential credential, UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		LOG.info("Entered uploadFilePrescriber");

		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFilePrescriberRequest(uploadFileRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, false);
	}

	@Override
	public UploadFileResponseType uploadFileExecutor(SAMLToken samlToken, Credential credential, UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException {
		LOG.info("Entered uploadFileExecutor");

		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFileExecutorRequest(uploadFileRequestType);

		return GenericWebserviceCaller.callGenericWebservice(samlToken, credential, jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, false);
	}
}
