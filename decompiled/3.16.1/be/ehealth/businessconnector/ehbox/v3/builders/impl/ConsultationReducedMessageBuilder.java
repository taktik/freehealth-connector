package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.api.domain.Addressee;
import be.ehealth.businessconnector.ehbox.api.domain.Document;
import be.ehealth.businessconnector.ehbox.api.domain.DocumentMessage;
import be.ehealth.businessconnector.ehbox.api.domain.ErrorMessage;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message;
import be.fgov.ehealth.ehbox.core.v3.ContentInfoType;

public class ConsultationReducedMessageBuilder extends AbstractConsultationBuilder<Message> {
   /** @deprecated */
   @Deprecated
   public ConsultationReducedMessageBuilder(Crypto crypto) {
   }

   public ConsultationReducedMessageBuilder() {
   }

   public final be.ehealth.businessconnector.ehbox.api.domain.Message<Message> buildMessage(Message response) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      be.ehealth.businessconnector.ehbox.api.domain.Message<Message> message = this.createMessage(response.getContentSpecification(), response, response.getMessageId(), (String)null);
      AbstractConsultationBuilder.ExceptionContainer<Message> container = new AbstractConsultationBuilder.ExceptionContainer(message);
      this.processMessageInfo(response.getMessageInfo(), message);
      this.processContentSpecification(response.getContentSpecification(), message);
      this.processContentInfo(response.getContentInfo(), message);
      this.processContent(response.getContentInfo(), message, container);
      this.processCustomMetas(response.getCustomMetas(), message);
      this.processDestination(response, message);
      this.processSender(response.getSender(), response.getContentSpecification(), message);
      return container.getMessage();
   }

   private void processDestination(Message response, be.ehealth.businessconnector.ehbox.api.domain.Message<Message> message) {
      if (response.getDestination() != null) {
         Addressee destination = this.buildAddressee(response.getDestination());
         message.getDestinations().add(destination);
      }

   }

   private void processContent(ContentInfoType response, be.ehealth.businessconnector.ehbox.api.domain.Message<Message> message, AbstractConsultationBuilder.ExceptionContainer<Message> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      if (message instanceof DocumentMessage) {
         DocumentMessage<Message> documentMessage = (DocumentMessage)message;
         Document document = new Document();
         document.setTitle(response.getTitle());
         documentMessage.setDocument(document);
         byte[] decodedInss = this.handleAndDecryptIfNeeded(response.getEncryptableINSSPatient(), documentMessage.isEncrypted(), container);
         if (decodedInss != null) {
            documentMessage.setPatientInss(ConnectorIOUtils.toString(decodedInss, Charset.UTF_8));
         }
      } else if (message instanceof ErrorMessage) {
         ErrorMessage<Message> errorMessage = (ErrorMessage)message;
         errorMessage.setTitle(response.getTitle());
      }

   }
}
