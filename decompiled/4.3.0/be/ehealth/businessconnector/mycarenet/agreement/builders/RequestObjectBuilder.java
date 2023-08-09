package be.ehealth.businessconnector.mycarenet.agreement.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.agreement.domain.AskAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.ConsultAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.exception.AgreementBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   AskAgreementBuilderRequest buildAskAgreementRequest(boolean var1, InputReference var2, Patient var3, DateTime var4, byte[] var5) throws TechnicalConnectorException, AgreementBusinessConnectorException;

   ConsultAgreementBuilderRequest buildConsultAgreementRequest(boolean var1, InputReference var2, Patient var3, DateTime var4, byte[] var5) throws TechnicalConnectorException, AgreementBusinessConnectorException;
}
