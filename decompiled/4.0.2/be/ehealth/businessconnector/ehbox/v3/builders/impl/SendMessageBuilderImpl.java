package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.api.domain.Addressee;
import be.ehealth.businessconnector.ehbox.api.domain.Document;
import be.ehealth.businessconnector.ehbox.api.domain.DocumentMessage;
import be.ehealth.businessconnector.ehbox.api.domain.NewsMessage;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.v3.builders.SendMessageBuilder;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ByteArrayDatasource;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType;
import be.fgov.ehealth.ehbox.core.v3.FreeInformationsType;
import be.fgov.ehealth.ehbox.core.v3.Row;
import be.fgov.ehealth.ehbox.core.v3.Table;
import be.fgov.ehealth.ehbox.core.v3.User;
import be.fgov.ehealth.ehbox.publication.protocol.v3.ContentContextType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.ContentSpecificationType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.DestinationContextType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationAnnexType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationContentType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationDocumentType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.activation.DataHandler;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageBuilderImpl implements SendMessageBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(SendMessageBuilderImpl.class);
   public static final String DEFAULT_MIME_TYPE = "application/octet-stream";
   private KeyDepotManager keydepotManager;

   /** @deprecated */
   @Deprecated
   public SendMessageBuilderImpl(Crypto encryptionCrypto, KeyDepotManager keydepotManager) {
      this(keydepotManager);
   }

   public SendMessageBuilderImpl(KeyDepotManager keydepotManager) {
      this.keydepotManager = keydepotManager;
   }

   public final SendMessageRequest buildMessage(DocumentMessage<Message> document) throws IOException, EhboxBusinessConnectorException, TechnicalConnectorException, CMSException {
      return this.buildCommonMessage(document);
   }

   private SendMessageRequest buildCommonMessage(be.ehealth.businessconnector.ehbox.api.domain.Message<Message> document) throws TechnicalConnectorException, EhboxBusinessConnectorException, IOException, UnsealConnectorException, UnsupportedEncodingException {
      boolean isDocumentEncrypted = document.isEncrypted();
      Set<EncryptionToken> destinationEtkSet = new HashSet();
      SendMessageRequest sendMessageRequest = new SendMessageRequest();
      sendMessageRequest.setPublicationId(document.getPublicationId());
      this.processSender(sendMessageRequest, document.getSender());
      this.processDestinations(document, sendMessageRequest, destinationEtkSet);
      this.processContent(document, isDocumentEncrypted, sendMessageRequest, destinationEtkSet);
      return sendMessageRequest;
   }

   private void processContent(be.ehealth.businessconnector.ehbox.api.domain.Message<Message> document, boolean isDocumentEncrypted, SendMessageRequest sendMessageRequest, Set<EncryptionToken> destinationEtkSet) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsealConnectorException, UnsupportedEncodingException {
      ContentContextType content = new ContentContextType();
      String contentTypeString = this.getContentTypeStringForMessage(document);
      this.processContentSpecification(contentTypeString, document.isUsePublicationReceipt(), document.isUseReceivedReceipt(), document.isUseReadReceipt(), isDocumentEncrypted, content, document.isImportant());
      this.processCustomMetas(content, document.getCustomMetas());
      DocumentMessage<Message> documentMessage = (DocumentMessage)document;
      this.processCopyMailTo(documentMessage, sendMessageRequest);
      this.processPublicationContentTypeForDocumentMessage(documentMessage, isDocumentEncrypted, destinationEtkSet, content);
      sendMessageRequest.setContentContext(content);
   }

   private void processCopyMailTo(DocumentMessage<Message> documentMessage, SendMessageRequest sendMessageRequest) {
      sendMessageRequest.getCopyMailTos().addAll(documentMessage.getCopyMailTo());
   }

   private String getContentTypeStringForMessage(be.ehealth.businessconnector.ehbox.api.domain.Message<Message> document) {
      if (document instanceof NewsMessage) {
         return "NEWS";
      } else if (document instanceof DocumentMessage) {
         return "DOCUMENT";
      } else {
         throw new UnsupportedOperationException("getContentTypeStringForMessage : the type " + document.getClass() + " is not supported as a message to be send");
      }
   }

   private void processPublicationDocument(DocumentMessage<Message> message, Set<EncryptionToken> destinationEtkSet, PublicationContentType contentType) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsealConnectorException {
      PublicationDocumentType publicationDocumentType = new PublicationDocumentType();
      byte[] dataToSend = this.encode(message.getDocument().getContent(), message.isEncrypted(), destinationEtkSet);
      publicationDocumentType.setDigest(this.processDigest(dataToSend));
      publicationDocumentType.setEncryptableBinaryContent(new DataHandler(new ByteArrayDatasource(dataToSend, message.getDocument().getMimeType())));
      publicationDocumentType.setMimeType(message.getDocument().getMimeType());
      publicationDocumentType.setTitle(message.getDocument().getTitle());
      publicationDocumentType.setDownloadFileName(message.getDocument().getFilename());
      contentType.setDocument(publicationDocumentType);
   }

   private void processCustomMetas(ContentContextType content, Map<String, String> customMetas) {
      if (customMetas.keySet().size() > 0) {
         Set<String> keySet = customMetas.keySet();
         Iterator var4 = keySet.iterator();

         while(var4.hasNext()) {
            String key = (String)var4.next();
            String value = (String)customMetas.get(key);
            CustomMetaType meta = new CustomMetaType();
            meta.setKey(key);
            meta.setValue(value);
            content.getCustomMetas().add(meta);
         }
      }

   }

   private void processContentSpecification(String contentTypeString, Boolean publicationReceipt, Boolean receivedReceipt, Boolean readReceipt, boolean isDocumentEncrypted, ContentContextType content, boolean isImportant) {
      ContentSpecificationType contentSpecification = new ContentSpecificationType();
      contentSpecification.setApplicationName(ConfigFactory.getConfigValidator().getProperty("ehbox.application.name", "${package.name}"));
      contentSpecification.setIsEncrypted(isDocumentEncrypted);
      contentSpecification.setIsImportant(isImportant);
      if (publicationReceipt != null) {
         contentSpecification.setPublicationReceipt(publicationReceipt);
      }

      if (readReceipt != null) {
         contentSpecification.setReadReceipt(readReceipt);
      }

      if (receivedReceipt != null) {
         contentSpecification.setReceivedReceipt(receivedReceipt);
      }

      contentSpecification.setContentType(contentTypeString);
      content.setContentSpecification(contentSpecification);
   }

   private void processPublicationContentTypeForDocumentMessage(DocumentMessage<Message> document, boolean isDocumentEncrypted, Set<EncryptionToken> destinationEtkSet, ContentContextType content) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsealConnectorException, UnsupportedEncodingException {
      PublicationContentType contentType = new PublicationContentType();
      this.processPublicationDocumentTypeForDocument(document.getDocument(), document.getDocumentTitle(), isDocumentEncrypted, destinationEtkSet, contentType);
      this.processFreeTextAndFreeInformationTable(document.getFreeText(), document.getFreeInformationTableTitle(), document.getFreeInformationTableRows(), isDocumentEncrypted, destinationEtkSet, contentType);
      this.processPatientInss(destinationEtkSet, contentType, document.getPatientInss(), isDocumentEncrypted);
      this.processAnnexes(document.getAnnexList(), isDocumentEncrypted, destinationEtkSet, contentType);
      this.processPublicationDocument(document, destinationEtkSet, contentType);
      content.setContent(contentType);
   }

   private void processPublicationDocumentTypeForDocument(Document documentContent, String documentTitle, boolean isDocumentEncrypted, Set<EncryptionToken> destinationEtkSet, PublicationContentType contentType) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsealConnectorException {
      PublicationDocumentType documentType = new PublicationDocumentType();
      documentType.setTitle(documentTitle);
      if (documentContent != null) {
         documentType.setDownloadFileName(documentContent.getFilename());
         documentType.setMimeType(documentContent.getMimeType());
         byte[] dataToSend = this.encode(documentContent.getContent(), isDocumentEncrypted, destinationEtkSet);
         documentType.setDigest(this.processDigest(dataToSend));
         documentType.setEncryptableBinaryContent(new DataHandler(new ByteArrayDatasource(dataToSend, documentContent.getMimeType())));
      }

      contentType.setDocument(documentType);
   }

   private void processFreeTextAndFreeInformationTable(String freeText, String tableTitle, Map<String, String> tableRows, boolean isDocumentEncrypted, Set<EncryptionToken> destinationEtkSet, PublicationContentType contentType) throws UnsupportedEncodingException, IOException, TechnicalConnectorException, EhboxBusinessConnectorException {
      boolean hasFreeText = this.freeTextFilledOut(freeText);
      boolean hasFreeInformationTable = this.freeInformationTableFilledOut(tableTitle, tableRows);
      if (hasFreeInformationTable && hasFreeText) {
         throw new IllegalArgumentException("you cannot use both freeInformations.freeText and freeInformations.table together , they are mutually exclusive");
      } else {
         if (hasFreeText || hasFreeInformationTable) {
            FreeInformationsType freeInformation = new FreeInformationsType();
            if (hasFreeText) {
               freeInformation.setEncryptableFreeText(this.encode(ConnectorIOUtils.toBytes(freeText, Charset.UTF_8), isDocumentEncrypted, destinationEtkSet));
            }

            if (hasFreeInformationTable) {
               freeInformation.setTable(this.fillEncryptableTable(tableTitle, tableRows, isDocumentEncrypted, destinationEtkSet));
            }

            contentType.setFreeInformations(freeInformation);
         }

      }
   }

   private Table fillEncryptableTable(String tableTitle, Map<String, String> tableRows, boolean isDocumentEncrypted, Set<EncryptionToken> destinationEtkSet) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsupportedEncodingException {
      Table table = new Table();
      table.setTitle(tableTitle);
      Iterator var6 = tableRows.keySet().iterator();

      while(var6.hasNext()) {
         String rowKey = (String)var6.next();
         String rowValue = (String)tableRows.get(rowKey);
         Row row = new Row();
         row.setEncryptableLeftCell(this.encode(rowKey, isDocumentEncrypted, destinationEtkSet));
         row.setEncryptableRightCell(this.encode(rowValue, isDocumentEncrypted, destinationEtkSet));
         table.getRows().add(row);
      }

      return table;
   }

   private boolean freeInformationTableFilledOut(String tableTitle, Map<String, String> tableRows) {
      return tableTitle != null && tableRows != null && !tableRows.isEmpty();
   }

   private boolean freeTextFilledOut(String freeText) throws UnsupportedEncodingException {
      return freeText != null && freeText.length() > 0;
   }

   private void processAnnexes(List<Document> annexList, boolean isDocumentEncrypted, Set<EncryptionToken> destinationEtkSet, PublicationContentType contentType) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsealConnectorException, UnsupportedEncodingException {
      Iterator var5 = annexList.iterator();

      while(var5.hasNext()) {
         Document annex = (Document)var5.next();
         PublicationAnnexType annexType = new PublicationAnnexType();
         annexType.setDownloadFileName(annex.getFilename());
         byte[] dataToSend = this.encode(annex.getContent(), isDocumentEncrypted, destinationEtkSet);
         annexType.setDigest(this.processDigest(dataToSend));
         if (annex.getContent().length == 0) {
            annexType.setEncryptableTextContent(annex.getContent());
         } else {
            annexType.setEncryptableBinaryContent(new DataHandler(new ByteArrayDatasource(dataToSend, annex.getMimeType())));
         }

         annexType.setEncryptableTitle(this.encode(annex.getTitle(), isDocumentEncrypted, destinationEtkSet));
         annexType.setMimeType(annex.getMimeType());
         contentType.getAnnices().add(annexType);
      }

   }

   private void processPatientInss(Set<EncryptionToken> destinationEtkSet, PublicationContentType contentType, String patientInss, Boolean isEncrypted) throws IOException, TechnicalConnectorException, EhboxBusinessConnectorException, UnsupportedEncodingException {
      if (patientInss != null) {
         contentType.setEncryptableINSSPatient(this.encode(ConnectorIOUtils.toBytes(patientInss, Charset.UTF_8), isEncrypted, destinationEtkSet));
      }

   }

   private void processDestinations(be.ehealth.businessconnector.ehbox.api.domain.Message<Message> document, SendMessageRequest sendMessageRequest, Set<EncryptionToken> destinationEtkSet) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      Iterator var4 = document.getDestinations().iterator();

      while(var4.hasNext()) {
         Addressee addressee = (Addressee)var4.next();
         DestinationContextType destination = this.buildDestination(addressee);
         sendMessageRequest.getDestinationContexts().add(destination);
         if (document.isEncrypted()) {
            destinationEtkSet.addAll(this.getETKForAddressee(addressee));
            destinationEtkSet.add(this.keydepotManager.getETK(KeyDepotManager.EncryptionTokenType.ENCRYPTION));
         }
      }

   }

   private void processSender(SendMessageRequest sendMessageRequest, Addressee sender) {
      if (sender != null) {
         sendMessageRequest.setBoxId(new BoxIdType());
         sendMessageRequest.getBoxId().setId(sender.getId());
         sendMessageRequest.getBoxId().setQuality(sender.getQuality());
         sendMessageRequest.getBoxId().setSubType(sender.getSubType());
         sendMessageRequest.getBoxId().setType(sender.getType());
      }

   }

   private String processDigest(byte[] data) throws TechnicalConnectorException {
      return new String(Base64.encode(ConnectorCryptoUtils.calculateDigest("SHA-256", data)));
   }

   private Set<EncryptionToken> getETKForAddressee(Addressee addressee) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      if (!"ALL".equals(addressee.getId())) {
         if (SessionUtil.getEncryptionCrypto() == null && Session.getInstance().getSession().getEncryptionCredential() == null) {
            LOG.debug(TechnicalConnectorExceptionValues.NO_ENCRYPTIONKEYS.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_ENCRYPTIONKEYS, new Object[0]);
         } else {
            Set<EncryptionToken> etkSet = this.keydepotManager.getETKs(addressee.getIdentifierTypeHelper(), addressee.getIdAsLong(), addressee.getApplicationId());
            if (etkSet.isEmpty()) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"could not retrieve Etk for known addressee " + addressee});
            } else {
               return etkSet;
            }
         }
      } else {
         return new HashSet();
      }
   }

   public final SendMessageRequest buildMessage(NewsMessage<Message> document) throws IOException, EhboxBusinessConnectorException, TechnicalConnectorException {
      return this.buildCommonMessage(document);
   }

   private DestinationContextType buildDestination(Addressee addressee) {
      DestinationContextType destination = new DestinationContextType();
      destination.setId(addressee.getId());
      destination.setQuality(addressee.getQuality());
      destination.setType(addressee.getType());
      destination.setSubType(addressee.getSubType());
      destination.setOoOProcessed(addressee.isOoOProcessed());
      if (addressee.getFirstName() != null && addressee.getLastName() != null) {
         User user = new User();
         user.setFirstName(addressee.getFirstName());
         user.setLastName(addressee.getLastName());
         user.setValue(addressee.getId());
         destination.setUser(user);
      }

      return destination;
   }

   private byte[] encode(byte[] content, boolean encrypted, Set<EncryptionToken> tokens) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      byte[] byteVal = null;
      byte[] byteVal;
      if (encrypted && content != null && content.length != 0) {
         byteVal = SessionUtil.getEncryptionCrypto().seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, tokens, content);
      } else {
         byteVal = content;
      }

      return byteVal;
   }

   private byte[] encode(String content, boolean encrypted, Set<EncryptionToken> tokens) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      return this.encode(ConnectorIOUtils.toBytes(content, Charset.UTF_8), encrypted, tokens);
   }
}
