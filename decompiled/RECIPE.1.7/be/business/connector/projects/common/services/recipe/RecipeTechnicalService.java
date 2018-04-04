package org.taktik.connector.business.recipeprojects.common.services.recipe;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;

public interface RecipeTechnicalService {
   UploadFileResponseType uploadFilePatient(UploadFileRequestType var1) throws IntegrationModuleException;

   UploadFileResponseType uploadFilePrescriber(UploadFileRequestType var1) throws IntegrationModuleException;

   UploadFileResponseType uploadFileExecutor(UploadFileRequestType var1) throws IntegrationModuleException;
}
