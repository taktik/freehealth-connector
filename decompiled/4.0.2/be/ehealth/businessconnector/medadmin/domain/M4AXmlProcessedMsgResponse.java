package be.ehealth.businessconnector.medadmin.domain;

import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareResponse;
import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareUpdate;
import be.cin.mycarenet._1_0.carenet.types.SinglePalliativeCareResponse;
import be.cin.mycarenet._1_0.carenet.types.SingleSpecificTechnicalCareResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import java.util.ArrayList;
import java.util.List;

public class M4AXmlProcessedMsgResponse extends ProcessedMsgResponse<Object> {
   private List<SingleNurseContractualCareResponse> singleNurseContractualCareResponses = new ArrayList();
   private List<SinglePalliativeCareResponse> singlePalliativeCareResponses = new ArrayList();
   private List<SingleSpecificTechnicalCareResponse> singleSpecificTechnicalCareResponses = new ArrayList();
   private List<SingleNurseContractualCareUpdate> singleNurseContractualCareUpdates = new ArrayList();

   public M4AXmlProcessedMsgResponse(ProcessedMsgResponse processedMsgResponse) {
      super(processedMsgResponse.getMsgResponse(), processedMsgResponse.getBusinessResponse(), processedMsgResponse.getRawDecryptedBlob(), processedMsgResponse.getSignatureVerificationResult(), processedMsgResponse.getSignedData());
   }

   public List<SingleNurseContractualCareResponse> getSingleNurseContractualCareResponses() {
      return this.singleNurseContractualCareResponses;
   }

   public List<SinglePalliativeCareResponse> getSinglePalliativeCareResponses() {
      return this.singlePalliativeCareResponses;
   }

   public List<SingleSpecificTechnicalCareResponse> getSingleSpecificTechnicalCareResponses() {
      return this.singleSpecificTechnicalCareResponses;
   }

   public List<SingleNurseContractualCareUpdate> getSingleNurseContractualCareUpdates() {
      return this.singleNurseContractualCareUpdates;
   }
}
