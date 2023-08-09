package be.ehealth.businessconnector.mycarenet.agreement.domain;

import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;

public class ConsultAgreementBuilderRequest extends AgreementBuilderRequest<ConsultAgreementRequest> {
   public ConsultAgreementBuilderRequest() {
      super(new ConsultAgreementRequest());
   }
}
