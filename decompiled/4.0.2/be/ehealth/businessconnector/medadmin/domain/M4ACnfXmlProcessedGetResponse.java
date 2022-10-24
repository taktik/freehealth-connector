package be.ehealth.businessconnector.medadmin.domain;

import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import java.util.ArrayList;
import java.util.List;

public class M4ACnfXmlProcessedGetResponse extends ProcessedGetResponse {
   private List<M4ACnfXmlProcessedMsgResponse> msgResponses = new ArrayList();

   public M4ACnfXmlProcessedGetResponse() {
   }

   public List<M4ACnfXmlProcessedMsgResponse> getMsgResponses() {
      return this.msgResponses;
   }
}
