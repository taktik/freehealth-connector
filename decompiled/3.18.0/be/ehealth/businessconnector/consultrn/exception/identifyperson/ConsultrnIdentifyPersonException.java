package be.ehealth.businessconnector.consultrn.exception.identifyperson;

import be.ehealth.business.common.exception.EhealthServiceException;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;

public class ConsultrnIdentifyPersonException extends EhealthServiceException {
   private static final long serialVersionUID = 1L;
   private final SearchBySSINReply searchBySSINReply;

   public ConsultrnIdentifyPersonException(SearchBySSINReply response) {
      super(response.getStatus());
      this.searchBySSINReply = response;
   }

   public SearchBySSINReply getSearchBySSINReply() {
      return this.searchBySSINReply;
   }
}
