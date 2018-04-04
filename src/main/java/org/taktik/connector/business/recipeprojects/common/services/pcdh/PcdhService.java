package org.taktik.connector.business.recipeprojects.common.services.pcdh;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.ResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface PcdhService {
	SealedResponseType getData(SAMLToken samlToken, Credential credential, SealedRequestType sealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	SealedResponseType getDataTypes(SAMLToken samlToken, Credential credential, SealedRequestType sealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	SealedResponseType getPharmacyDetails(SAMLToken samlToken, Credential credential, SealedRequestType paramSealedRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	ResponseType uploadPerformanceMetric(SAMLToken samlToken, Credential credential, UploadPerformanceMetricRequestType paramUploadPerformanceMetricRequestType) throws IntegrationModuleException, TechnicalConnectorException;
	CheckAliveResponseType checkAlivePCDH(SAMLToken samlToken, Credential credential, CheckAliveRequestType paramCheckAliveRequestType) throws IntegrationModuleException, TechnicalConnectorException;
}
