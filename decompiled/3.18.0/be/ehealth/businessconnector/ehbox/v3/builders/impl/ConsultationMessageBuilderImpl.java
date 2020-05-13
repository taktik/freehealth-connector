package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.api.domain.Message;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.v3.builders.ConsultationMessageBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse;

public class ConsultationMessageBuilderImpl implements ConsultationMessageBuilder {
   private ConsultationFullMessageBuilder fullBuilder;
   private ConsultationReducedMessageBuilder reducedBuilder;

   /** @deprecated */
   @Deprecated
   public ConsultationMessageBuilderImpl(Crypto crypto) {
      this();
   }

   public ConsultationMessageBuilderImpl() {
      this.fullBuilder = new ConsultationFullMessageBuilder();
      this.reducedBuilder = new ConsultationReducedMessageBuilder();
   }

   public Message<GetFullMessageResponse> buildFullMessage(GetFullMessageResponse response) throws EhboxBusinessConnectorException, TechnicalConnectorException {
      return this.fullBuilder.buildFullMessage(response);
   }

   public Message<be.fgov.ehealth.ehbox.consultation.protocol.v3.Message> buildMessage(be.fgov.ehealth.ehbox.consultation.protocol.v3.Message response) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      return this.reducedBuilder.buildMessage(response);
   }
}
