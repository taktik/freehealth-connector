package be.ehealth.businessconnector.medadmin.session;

import be.cin.mycarenet._1_0.carenet.types.MedAdminRequestList;
import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SinglePalliativeCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SingleSpecificTechnicalCareRequest;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.businessconnector.genericasync.domain.GetRequest;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedPostResponse;
import be.ehealth.businessconnector.genericasync.helper.CommonAsyncService;
import be.ehealth.businessconnector.medadmin.domain.M4ACnfXmlProcessedGetResponse;
import be.ehealth.businessconnector.medadmin.domain.M4AXmlProcessedGetResponse;
import be.ehealth.businessconnector.medadmin.domain.RejectXmlProcessedGetResponse;
import be.ehealth.technicalconnector.exception.ConnectorException;

public interface MedAdminService extends CommonAsyncService {
   ProcessedPostResponse postM4AFlat(byte[] var1, String var2, InputReference var3) throws ConnectorException;

   ProcessedPostResponse postMedAdminRequestList(MedAdminRequestList var1, String var2, InputReference var3) throws ConnectorException;

   ProcessedPostResponse postSingleNurseContractualCareRequest(SingleNurseContractualCareRequest var1, String var2, InputReference var3) throws ConnectorException;

   ProcessedPostResponse postSinglePalliativeCareRequest(SinglePalliativeCareRequest var1, String var2, InputReference var3) throws ConnectorException;

   ProcessedPostResponse postSingleSpecificTechnicalCareRequest(SingleSpecificTechnicalCareRequest var1, String var2, InputReference var3) throws ConnectorException;

   ProcessedGetResponse<byte[]> getM4AFlat(GetRequest var1) throws ConnectorException;

   M4AXmlProcessedGetResponse getM4AXml(GetRequest var1) throws ConnectorException;

   M4ACnfXmlProcessedGetResponse getM4ACnfXml(GetRequest var1) throws ConnectorException;

   ProcessedGetResponse<byte[]> getM4ACnfFlat(GetRequest var1) throws ConnectorException;

   RejectXmlProcessedGetResponse getRejected(GetRequest var1) throws ConnectorException;
}
