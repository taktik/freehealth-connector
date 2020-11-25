package org.taktik.connector.business.recipeprojects.common.services.recipe;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;

public interface RecipeTechnicalService {
	  UploadFileResponseType uploadFilePatient(UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException;
	  UploadFileResponseType uploadFilePrescriber(UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException;
	  UploadFileResponseType uploadFileExecutor(UploadFileRequestType paramUploadFileRequestType) throws IntegrationModuleException;
}
