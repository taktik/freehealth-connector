package be.ehealth.businessconnector.ehbox.v3.builders;

import be.ehealth.businessconnector.ehbox.api.domain.Message;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse;

public interface ConsultationMessageBuilder {
   Message<be.fgov.ehealth.ehbox.consultation.protocol.v3.Message> buildMessage(be.fgov.ehealth.ehbox.consultation.protocol.v3.Message var1) throws TechnicalConnectorException, EhboxBusinessConnectorException;

   Message<GetFullMessageResponse> buildFullMessage(GetFullMessageResponse var1) throws EhboxBusinessConnectorException, TechnicalConnectorException;
}
