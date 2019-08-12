package be.ehealth.businessconnector.consultrn.exception.phoneticsearch;

import be.ehealth.business.common.exception.EhealthServiceException;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;

public class ConsultrnPhoneticSearchException extends EhealthServiceException {
   private static final long serialVersionUID = 1L;
   private final SearchPhoneticReply searchPhoneticReply;

   public ConsultrnPhoneticSearchException(SearchPhoneticReply response) {
      super(response.getStatus());
      this.searchPhoneticReply = response;
   }

   public SearchPhoneticReply getSearchPhoneticReply() {
      return this.searchPhoneticReply;
   }
}
