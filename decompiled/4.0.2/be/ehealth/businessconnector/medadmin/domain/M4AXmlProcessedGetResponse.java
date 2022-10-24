package be.ehealth.businessconnector.medadmin.domain;

import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import java.util.ArrayList;
import java.util.List;

public class M4AXmlProcessedGetResponse extends ProcessedGetResponse {
   private List<M4AXmlProcessedMsgResponse> msgResponses = new ArrayList();

   public M4AXmlProcessedGetResponse() {
   }

   public List<M4AXmlProcessedMsgResponse> getMsgResponses() {
      return this.msgResponses;
   }
}
