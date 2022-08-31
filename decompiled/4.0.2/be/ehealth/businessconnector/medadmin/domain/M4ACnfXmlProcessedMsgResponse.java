package be.ehealth.businessconnector.medadmin.domain;

import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SinglePalliativeCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SingleSpecificTechnicalCareRequest;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import java.util.ArrayList;
import java.util.List;

public class M4ACnfXmlProcessedMsgResponse extends ProcessedMsgResponse<Object> {
   private List<SingleNurseContractualCareRequest> singleNurseContractualCareRequests = new ArrayList();
   private List<SinglePalliativeCareRequest> singlePalliativeCareRequests = new ArrayList();
   private List<SingleSpecificTechnicalCareRequest> singleSpecificTechnicalCareRequests = new ArrayList();

   public M4ACnfXmlProcessedMsgResponse(ProcessedMsgResponse processedMsgResponse) {
      super(processedMsgResponse.getMsgResponse(), processedMsgResponse.getBusinessResponse(), processedMsgResponse.getRawDecryptedBlob(), processedMsgResponse.getSignatureVerificationResult(), processedMsgResponse.getSignedData());
   }

   public List<SingleNurseContractualCareRequest> getSingleNurseContractualCareRequests() {
      return this.singleNurseContractualCareRequests;
   }

   public List<SinglePalliativeCareRequest> getSinglePalliativeCareRequests() {
      return this.singlePalliativeCareRequests;
   }

   public List<SingleSpecificTechnicalCareRequest> getSingleSpecificTechnicalCareRequests() {
      return this.singleSpecificTechnicalCareRequests;
   }
}
