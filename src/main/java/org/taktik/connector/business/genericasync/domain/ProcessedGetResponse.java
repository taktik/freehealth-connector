package org.taktik.connector.business.genericasync.domain;

import java.util.ArrayList;
import java.util.List;

public class ProcessedGetResponse<T> {
   private List<ProcessedMsgResponse<T>> msgResponses = new ArrayList();
   private List<ProcessedTAckResponse> tAckResponses = new ArrayList();

   public List<ProcessedMsgResponse<T>> getMsgResponses() {
      return this.msgResponses;
   }

   public List<ProcessedTAckResponse> getTAckResponses() {
      return this.tAckResponses;
   }
}
