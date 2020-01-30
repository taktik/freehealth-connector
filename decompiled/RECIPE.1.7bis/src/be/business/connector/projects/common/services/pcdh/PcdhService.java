package org.taktik.connector.business.recipeprojects.common.services.pcdh;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.ResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;

public interface PcdhService {
	SealedResponseType getData(SealedRequestType sealedRequestType) throws IntegrationModuleException;
	SealedResponseType getDataTypes(SealedRequestType sealedRequestType) throws IntegrationModuleException;
	SealedResponseType getPharmacyDetails(SealedRequestType paramSealedRequestType) throws IntegrationModuleException;
	ResponseType uploadPerformanceMetric(UploadPerformanceMetricRequestType paramUploadPerformanceMetricRequestType) throws IntegrationModuleException;
	CheckAliveResponseType checkAlivePCDH(CheckAliveRequestType paramCheckAliveRequestType) throws IntegrationModuleException;
}
