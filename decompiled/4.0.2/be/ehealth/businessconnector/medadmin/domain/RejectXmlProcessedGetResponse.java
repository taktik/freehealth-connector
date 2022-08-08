package be.ehealth.businessconnector.medadmin.domain;

import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import java.util.ArrayList;
import java.util.List;

public class RejectXmlProcessedGetResponse extends ProcessedGetResponse {
   private List<RejectXmlProcessedMsgResponse> msgResponses = new ArrayList();

   public RejectXmlProcessedGetResponse() {
   }

   public List<RejectXmlProcessedMsgResponse> getMsgResponses() {
      return this.msgResponses;
   }
}
