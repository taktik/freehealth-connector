package be.ehealth.businessconnector.mycarenet.agreement.domain;

import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;

public class AskAgreementBuilderRequest extends AgreementBuilderRequest<AskAgreementRequest> {
   public AskAgreementBuilderRequest() {
      super(new AskAgreementRequest());
   }
}
