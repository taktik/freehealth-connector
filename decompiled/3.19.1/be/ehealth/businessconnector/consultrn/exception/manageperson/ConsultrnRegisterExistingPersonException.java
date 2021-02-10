package be.ehealth.businessconnector.consultrn.exception.manageperson;

import be.ehealth.business.common.exception.EhealthServiceV2Exception;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public class ConsultrnRegisterExistingPersonException extends EhealthServiceV2Exception {
   private static final long serialVersionUID = 1L;
   private final RegisterPersonResponse registerPersonResponse;

   public ConsultrnRegisterExistingPersonException(RegisterPersonResponse response) {
      super(response);
      this.registerPersonResponse = response;
   }

   public RegisterPersonResponse getRegisterPersonResponse() {
      return this.registerPersonResponse;
   }
}
