package org.taktik.connector.business.recipeprojects.common.services.recipe;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface RecipeTechnicalService {
	  UploadFileResponseType uploadFilePatient(SAMLToken samlToken, Credential credential, UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  UploadFileResponseType uploadFilePrescriber(SAMLToken samlToken, Credential credential, UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	  UploadFileResponseType uploadFileExecutor(SAMLToken samlToken, Credential credential, UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException, TechnicalConnectorException;
}
