package be.business.connector.projects.common.services.pcdh;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.ResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;

public interface PcdhService {
   SealedResponseType getData(SealedRequestType var1) throws IntegrationModuleException;

   SealedResponseType getDataTypes(SealedRequestType var1) throws IntegrationModuleException;

   SealedResponseType getPharmacyDetails(SealedRequestType var1) throws IntegrationModuleException;

   ResponseType uploadPerformanceMetric(UploadPerformanceMetricRequestType var1) throws IntegrationModuleException;

   CheckAliveResponseType checkAlivePCDH(CheckAliveRequestType var1) throws IntegrationModuleException;
}
