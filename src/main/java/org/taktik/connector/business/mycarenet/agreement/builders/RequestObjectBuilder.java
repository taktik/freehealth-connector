package org.taktik.connector.business.mycarenet.agreement.builders;

import org.joda.time.DateTime;
import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.mycarenet.agreement.domain.AskAgreementBuilderRequest;
import org.taktik.connector.business.mycarenet.agreement.domain.ConsultAgreementBuilderRequest;
import org.taktik.connector.business.mycarenet.agreement.exception.AgreementBusinessConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.domain.InputReference;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface RequestObjectBuilder {
   AskAgreementBuilderRequest buildAskAgreementRequest(boolean var1, InputReference var2, Patient var3, DateTime var4, byte[] var5) throws TechnicalConnectorException, AgreementBusinessConnectorException;

   ConsultAgreementBuilderRequest buildConsultAgreementRequest(boolean var1, InputReference var2, Patient var3, DateTime var4, byte[] var5) throws TechnicalConnectorException, AgreementBusinessConnectorException;
}
