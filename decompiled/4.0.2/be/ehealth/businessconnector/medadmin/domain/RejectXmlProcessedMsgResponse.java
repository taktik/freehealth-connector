package be.ehealth.businessconnector.medadmin.domain;

import be.cin.nip.async.generic.RejectInb;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import java.util.ArrayList;
import java.util.List;

public class RejectXmlProcessedMsgResponse extends ProcessedMsgResponse<Object> {
   private List<RejectInb> rejectInbResponses = new ArrayList();

   public RejectXmlProcessedMsgResponse(ProcessedMsgResponse processedMsgResponse) {
      super(processedMsgResponse.getMsgResponse(), processedMsgResponse.getBusinessResponse(), processedMsgResponse.getRawDecryptedBlob(), processedMsgResponse.getSignatureVerificationResult(), processedMsgResponse.getSignedData());
   }

   public List<RejectInb> getRejectInbResponses() {
      return this.rejectInbResponses;
   }
}
