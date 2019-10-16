package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.api.domain.AcknowledgeMessage;
import be.ehealth.businessconnector.ehbox.api.domain.Addressee;
import be.ehealth.businessconnector.ehbox.api.domain.Document;
import be.ehealth.businessconnector.ehbox.api.domain.DocumentMessage;
import be.ehealth.businessconnector.ehbox.api.domain.ErrorMessage;
import be.ehealth.businessconnector.ehbox.api.domain.Message;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationAnnexType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationContentType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationDocumentType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationMessageType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ContentContextType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DestinationContextType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse;
import be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType;
import be.fgov.ehealth.ehbox.core.v3.ErrorType;
import be.fgov.ehealth.ehbox.core.v3.FreeInformationsType;
import be.fgov.ehealth.ehbox.core.v3.Row;
import be.fgov.ehealth.ehbox.core.v3.Table;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.activation.DataHandler;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultationFullMessageBuilder extends AbstractConsultationBuilder<GetFullMessageResponse> {
   private static final Logger LOG = LoggerFactory.getLogger(ConsultationFullMessageBuilder.class);

   /** @deprecated */
   @Deprecated
   public ConsultationFullMessageBuilder(Crypto crypto) {
      this();
   }

   public ConsultationFullMessageBuilder() {
   }

   public final Message<GetFullMessageResponse> buildFullMessage(GetFullMessageResponse response) throws EhboxBusinessConnectorException, TechnicalConnectorException {
      ConsultationMessageType recievedMsg = response.getMessage();
      Message<GetFullMessageResponse> message = this.createMessage(recievedMsg.getContentContext().getContentSpecification(), response, recievedMsg.getMessageId(), response.getMessage().getPublicationId());
      AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container = new AbstractConsultationBuilder.ExceptionContainer(message);
      this.processMessageInfo(response.getMessageInfo(), message);
      this.processSender(response.getSender(), (ContentSpecificationType)null, message);
      this.processDestinationContext(recievedMsg.getDestinationContexts(), message);
      this.processContentContext(recievedMsg.getContentContext(), message, container);
      return container.getMessage();
   }

   private void processContentContext(ContentContextType context, Message<GetFullMessageResponse> message, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      this.processContentSpecification(context.getContentSpecification(), message);
      this.processCustomMetas(context.getCustomMetas(), message);
      if (message instanceof DocumentMessage) {
         this.processDocument(context.getContent(), message, container);
      } else if (message instanceof AcknowledgeMessage) {
         this.processAcknowledge(context.getContent(), message);
      } else if (message instanceof ErrorMessage) {
         this.processError(context.getContent(), message);
      }

   }

   private void processAcknowledge(ConsultationContentType response, Message<?> message) throws EhboxBusinessConnectorException, TechnicalConnectorException {
      LOG.debug("processAcknowledge : processing acknowledge for message " + message.getId() + " and response acknowledgement" + response.getAcknowledgment() + " : no special processing needed");
   }

   private void processError(ConsultationContentType response, Message<?> message) throws EhboxBusinessConnectorException, TechnicalConnectorException {
      if (message instanceof ErrorMessage) {
         ErrorMessage<?> errorMessage = (ErrorMessage)message;
         ErrorType error = response.getError();
         if (error != null) {
            errorMessage.setErrorCode(error.getCode());
            errorMessage.setErrorPublicationId(error.getPublicationId());
            Iterator i$;
            String failureMessage;
            if (error.getMessages() != null) {
               i$ = error.getMessages().iterator();

               while(i$.hasNext()) {
                  failureMessage = (String)i$.next();
                  errorMessage.getErrorMsg().add("error:" + failureMessage);
               }
            }

            if (error.getFailures() != null) {
               i$ = error.getFailures().iterator();

               while(i$.hasNext()) {
                  failureMessage = (String)i$.next();
                  errorMessage.getErrorMsg().add("failure:" + failureMessage);
               }
            }
         }
      }

   }

   private void processDocument(ConsultationContentType response, Message<?> message, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      DocumentMessage<?> documentMessage = (DocumentMessage)message;
      this.processINSSPatient(response.getEncryptableINSSPatient(), documentMessage, container);
      this.processFreeText(response.getFreeInformations(), documentMessage, container);
      this.processMainDocument(response.getDocument(), documentMessage, container);
      if (!response.getAnnices().isEmpty()) {
         documentMessage.setHasAnnex(true);
         Iterator i$ = response.getAnnices().iterator();

         while(i$.hasNext()) {
            ConsultationAnnexType annexType = (ConsultationAnnexType)i$.next();
            this.processAnnex(documentMessage, annexType, container);
         }
      }

   }

   private void processDestinationContext(List<DestinationContextType> destinationList, Message<?> message) {
      Iterator i$ = destinationList.iterator();

      while(i$.hasNext()) {
         DestinationContextType destinationContext = (DestinationContextType)i$.next();
         IdentifierType identityType = IdentifierType.lookup(destinationContext.getType(), destinationContext.getSubType(), 49);
         Addressee destination = new Addressee(identityType);
         destination.setId(destinationContext.getId());
         if (destinationContext.getUser() != null) {
            destination.setFirstName(destinationContext.getUser().getFirstName());
            destination.setLastName(destinationContext.getUser().getLastName());
         }

         destination.setQuality(destinationContext.getQuality());
         message.getDestinations().add(destination);
      }

   }

   private void processAnnex(DocumentMessage<?> documentMessage, ConsultationAnnexType annexType, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      Document annex = new Document();
      annex.setFilename(annexType.getDownloadFileName());
      annex.setMimeType(annexType.getMimeType());
      byte[] annexTitle = this.handleAndDecryptIfNeeded(annexType.getEncryptableTitle(), documentMessage.isEncrypted(), container);
      if (annexTitle != null) {
         annex.setTitle(ConnectorIOUtils.toString(annexTitle, Charset.UTF_8));
      }

      DataHandler annexHandler = null;
      if (annexType.getEncryptableBinaryContent() != null) {
         annexHandler = annexType.getEncryptableBinaryContent();
         if (annexHandler != null) {
            annex.setContent(this.base64decoding(annexHandler, documentMessage.isEncrypted(), container));
         }
      } else if (annexType.getEncryptableTextContent() != null) {
         annex.setContent(this.handleAndDecryptIfNeeded(annexType.getEncryptableTextContent(), documentMessage.isEncrypted(), container));
      }

      documentMessage.getAnnexList().add(annex);
   }

   private void processMainDocument(ConsultationDocumentType response, DocumentMessage<?> documentMessage, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      Document document = new Document();
      document.setFilename(response.getDownloadFileName());
      document.setMimeType(response.getMimeType());
      document.setTitle(response.getTitle());
      DataHandler responseHandler = null;
      if (response.getEncryptableBinaryContent() != null) {
         responseHandler = response.getEncryptableBinaryContent();
         if (responseHandler != null) {
            try {
               document.setContent(this.base64decoding(responseHandler, documentMessage.isEncrypted(), container));
            } catch (UnsealConnectorException var7) {
               document.setException(var7);
            }
         }
      } else if (response.getEncryptableTextContent() != null) {
         document.setContent(this.handleAndDecryptIfNeeded(response.getEncryptableTextContent(), documentMessage.isEncrypted(), container));
      }

      documentMessage.setDocument(document);
   }

   private void processINSSPatient(byte[] encryptableINSSPatient, DocumentMessage<?> documentMessage, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      if (encryptableINSSPatient != null) {
         boolean encrypted = documentMessage.isEncrypted();
         byte[] patientInss = this.handleAndDecryptIfNeeded(encryptableINSSPatient, encrypted, container);
         if (patientInss != null) {
            documentMessage.setPatientInss(ConnectorIOUtils.toString(patientInss, Charset.UTF_8));
            documentMessage.setEncrypted(encrypted);
         }
      }

   }

   private <T> void processFreeText(FreeInformationsType response, DocumentMessage<?> documentMessage, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      if (response != null) {
         byte[] freeText = this.handleAndDecryptIfNeeded(response.getEncryptableFreeText(), documentMessage.isEncrypted(), container);
         if (freeText != null && freeText.length > 0) {
            documentMessage.setFreeText(ConnectorIOUtils.toString(freeText, Charset.UTF_8));
         }

         Table table = response.getTable();
         if (table != null) {
            documentMessage.setFreeInformationTableTitle(table.getTitle());
            Iterator i$ = table.getRows().iterator();

            while(i$.hasNext()) {
               Row row = (Row)i$.next();
               byte[] decryptedKeyBytes = this.handleAndDecryptIfNeeded(row.getEncryptableLeftCell(), documentMessage.isEncrypted(), container);
               byte[] decryptedValueBytes = this.handleAndDecryptIfNeeded(row.getEncryptableRightCell(), documentMessage.isEncrypted(), container);
               String decryptedKey = ConnectorIOUtils.toString(decryptedKeyBytes, Charset.UTF_8);
               String decryptedValue = ConnectorIOUtils.toString(decryptedValueBytes, Charset.UTF_8);
               documentMessage.getFreeInformationTableRows().put(decryptedKey, decryptedValue);
            }
         }
      }

   }

   private <T> byte[] base64decoding(DataHandler dataHandler, boolean encrypted, AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      InputStream is = null;

      byte[] var6;
      try {
         is = dataHandler.getInputStream();
         byte[] byteVal = ConnectorIOUtils.getBytes(is);
         if (ArrayUtils.isEmpty(byteVal)) {
            Object var12 = null;
            return (byte[])var12;
         }

         var6 = this.handleAndDecryptIfNeeded(byteVal, encrypted, container);
      } catch (IOException var10) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var10, new Object[]{"Unable to decode datahandler"});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

      return var6;
   }
}
