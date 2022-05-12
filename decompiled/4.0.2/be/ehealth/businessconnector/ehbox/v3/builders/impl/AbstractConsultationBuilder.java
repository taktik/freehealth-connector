package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.api.domain.AcknowledgeMessage;
import be.ehealth.businessconnector.ehbox.api.domain.Addressee;
import be.ehealth.businessconnector.ehbox.api.domain.DocumentMessage;
import be.ehealth.businessconnector.ehbox.api.domain.ErrorMessage;
import be.ehealth.businessconnector.ehbox.api.domain.Message;
import be.ehealth.businessconnector.ehbox.api.domain.NewsMessage;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.ehbox.v3.exception.EhboxCryptoException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorExceptionUtils;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.ehbox.core.v3.ContentInfoType;
import be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType;
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType;
import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType;
import be.fgov.ehealth.ehbox.core.v3.MessageInfoType;
import be.fgov.ehealth.ehbox.core.v3.SenderType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConsultationBuilder<T> {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractConsultationBuilder.class);

   /** @deprecated */
   @Deprecated
   public AbstractConsultationBuilder(Crypto crypto) {
      this();
   }

   public AbstractConsultationBuilder() {
   }

   protected void processCustomMetas(List<CustomMetaType> metas, Message<T> message) {
      if (metas != null) {
         Iterator var3 = metas.iterator();

         while(var3.hasNext()) {
            CustomMetaType meta = (CustomMetaType)var3.next();
            message.getCustomMetas().put(meta.getKey(), meta.getValue());
         }
      }

   }

   protected void processSender(SenderType inSender, ContentSpecificationType contentspec, Message<T> message) {
      if (inSender != null) {
         Addressee addressee = this.buildAddressee(inSender);
         if (contentspec != null) {
            addressee.setApplicationId(contentspec.getApplicationName());
         }

         message.setSender(addressee);
      }

   }

   protected void processContentInfo(ContentInfoType contentInfo, Message<T> message) {
      if (contentInfo != null) {
         message.setHasAnnex(contentInfo.isHasAnnex());
         message.setHasFreeInformations(contentInfo.isHasFreeInformations());
      }

   }

   public final Addressee buildAddressee(SenderType sender) {
      IdentifierType identifierType = IdentifierType.lookup(sender.getType(), sender.getSubType(), 49);
      Addressee destination = new Addressee(identifierType);
      destination.setId(sender.getId());
      if (StringUtils.isEmpty(sender.getPersonInOrganisation())) {
         destination.setFirstName(sender.getFirstName());
         destination.setLastName(sender.getName());
      } else {
         destination.setOrganizationName(sender.getName());
         destination.setPersonInOrganisation(sender.getPersonInOrganisation());
      }

      destination.setQuality(sender.getQuality());
      return destination;
   }

   public final Addressee buildAddressee(EhboxIdentifierType identifier) {
      IdentifierType identifierType = IdentifierType.lookup(identifier.getType(), identifier.getSubType(), 49);
      Addressee destination = new Addressee(identifierType);
      destination.setId(identifier.getId());
      if (identifier.getUser() != null) {
         destination.setFirstName(identifier.getUser().getFirstName());
         destination.setLastName(identifier.getUser().getLastName());
      }

      destination.setQuality(identifier.getQuality());
      return destination;
   }

   protected void processMessageInfo(MessageInfoType response, Message<T> message) {
      if (response != null) {
         message.setExpirationDateTime(response.getExpirationDate());
         message.setPublicationDateTime(response.getPublicationDate());
         message.setSize(response.getSize());
      }

   }

   protected void processContentSpecification(ContentSpecificationType contentspec, Message<T> message) {
      if (contentspec != null) {
         message.setImportant(contentspec.isIsImportant());
         message.setEncrypted(contentspec.isIsEncrypted());
         if (message.getSender() != null) {
            message.getSender().setApplicationId(contentspec.getApplicationName());
         }
      }

   }

   protected final byte[] handleAndDecryptIfNeeded(byte[] data, boolean encrypted, ExceptionContainer<?> container) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      if (ArrayUtils.isEmpty(data)) {
         return data;
      } else {
         byte[] byteVal = ArrayUtils.clone(data);
         if (ConfigFactory.getConfigValidator().getBooleanProperty("ehboxv3.try.to.base64decode.content", true)) {
            byteVal = ConnectorIOUtils.base64Decode(byteVal, false);
         }

         if (encrypted) {
            if (SessionUtil.getEncryptionCrypto() == null) {
               throw new EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.CRYPTO_NOT_PROPERLY_INITIALIZED, (Throwable)null, new Object[0]);
            }

            try {
               byteVal = SessionUtil.getEncryptionCrypto().unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, byteVal).getContentAsByte();
            } catch (UnsealConnectorException var8) {
               UnsealConnectorException e = var8;
               container.exceptions.add(var8);

               try {
                  byteVal = ConnectorExceptionUtils.processUnsealConnectorException(e);
               } catch (UnsealConnectorException var7) {
                  LOG.error("unrecoverable unsealException occurred while decrypting ehbox content , returning null as message , error : " + var7.getMessage());
                  throw new EhboxCryptoException(var7, (Message)null);
               }
            }
         }

         return byteVal;
      }
   }

   protected Message<T> createMessage(ContentSpecificationType content, T responseMsg, String id, String publicationId) throws EhboxBusinessConnectorException {
      Message<T> message = null;
      if ("DOCUMENT".equals(content.getContentType())) {
         message = new DocumentMessage();
      } else if ("NEWS".equals(content.getContentType())) {
         message = new NewsMessage();
      } else if ("ERROR".equals(content.getContentType())) {
         message = new ErrorMessage();
      } else {
         if (!"ACKNOWLEDGMENT".equals(content.getContentType())) {
            throw new EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, new Object[]{"Unsupported contentType", content.getContentType()});
         }

         message = new AcknowledgeMessage();
      }

      ((Message)message).setOriginal(responseMsg);
      ((Message)message).setId(id);
      ((Message)message).setPublicationId(publicationId);
      return (Message)message;
   }

   static class ExceptionContainer<T> {
      private Message<T> message;
      private List<UnsealConnectorException> exceptions = new ArrayList();

      public ExceptionContainer(Message<T> message) {
         this.message = message;
      }

      public Message<T> getMessage() throws EhboxCryptoException {
         if (this.exceptions.isEmpty()) {
            return this.message;
         } else {
            throw new EhboxCryptoException(this.exceptions, this.message);
         }
      }
   }
}
