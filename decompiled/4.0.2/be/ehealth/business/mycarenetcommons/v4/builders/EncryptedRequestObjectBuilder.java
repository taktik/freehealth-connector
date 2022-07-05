package be.ehealth.business.mycarenetcommons.v4.builders;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.business.mycarenetcommons.security.EncryptionHelper;
import be.ehealth.business.mycarenetcommons.security.SignHelper;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobAttributeValues;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptedRequestObjectBuilder<T extends SendRequestType> {
   private static final Logger log = LoggerFactory.getLogger(EncryptedRequestObjectBuilder.class);
   private RequestObjectBuilderHelper.Routing routing;

   public EncryptedRequestHolder<T> buildRequest(Input input, Class<T> clazz) throws TechnicalConnectorException {
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      BusinessContent businessContent = this.buildBusinessContent(input.getDetailContent(), detailId);
      EncryptedKnownContent encryptedKnownContent = this.buildEncryptedKnownContent(businessContent, detailId);

      try {
         Blob blob = this.buildBlobWithEncryptedKnownContent(detailId, input.getIssuer(), input.getMessageVersion(), encryptedKnownContent, input.getProjectIdentifier());
         T sendRequestType = (new RequestObjectBuilderHelper()).buildSendRequestType(input.isTest(), input.getInputReference().getInputReference(), input.getAttributes(), blob, input.getProjectIdentifier(), clazz);
         sendRequestType.setRouting(this.routing.createRouting());
         return new EncryptedRequestHolder(sendRequestType, businessContent);
      } catch (Exception var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var8, new Object[0]);
      }
   }

   private BusinessContent buildBusinessContent(byte[] request, String detailId) {
      BusinessContent businessContent = new BusinessContent();
      businessContent.setId(detailId);
      businessContent.setValue(request);
      return businessContent;
   }

   private Blob buildBlobWithEncryptedKnownContent(String detailId, String issuer, String messageVersion, EncryptedKnownContent encryptedKnownContent, String projectIdentifier) throws TechnicalConnectorException {
      byte[] encryptedByteArray = (new EncryptionHelper()).encrypt(ConnectorXmlUtils.toByteArray((Object)encryptedKnownContent), SessionUtil.getHolderOfKeyCrypto(), projectIdentifier);
      if (encryptedByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.attestv3.builders.impl.dumpMessages", false)) {
         log.debug("RequestObjectBuilder : created blob content: {}", new String(encryptedByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder(projectIdentifier).build(encryptedByteArray, BlobAttributeValues.builder().contentEncryption("encryptedForKnownBED").contentType("text/xml").encodingType("none").id(detailId).issuer(issuer).messageVersion(messageVersion).build());
      return blob;
   }

   private EncryptedKnownContent buildEncryptedKnownContent(BusinessContent businessContent, String detailId) throws TechnicalConnectorException {
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      encryptedKnownContent.setBusinessContent(businessContent);
      encryptedKnownContent.setXades(SignHelper.builder().signingOptions(new SignHelper.DefaultSigningOptions()).build().sign(ConnectorXmlUtils.toByteArray((Object)encryptedKnownContent), detailId));
      return encryptedKnownContent;
   }

   EncryptedRequestObjectBuilder(RequestObjectBuilderHelper.Routing routing) {
      this.routing = routing;
   }

   public static <T extends SendRequestType> EncryptedRequestObjectBuilderBuilder<T> builder() {
      return new EncryptedRequestObjectBuilderBuilder();
   }

   public static class EncryptedRequestObjectBuilderBuilder<T extends SendRequestType> {
      private RequestObjectBuilderHelper.Routing routing;

      EncryptedRequestObjectBuilderBuilder() {
      }

      public EncryptedRequestObjectBuilderBuilder<T> routing(RequestObjectBuilderHelper.Routing routing) {
         this.routing = routing;
         return this;
      }

      public EncryptedRequestObjectBuilder<T> build() {
         return new EncryptedRequestObjectBuilder(this.routing);
      }

      public String toString() {
         return "EncryptedRequestObjectBuilder.EncryptedRequestObjectBuilderBuilder(routing=" + this.routing + ")";
      }
   }

   public static class Input {
      boolean isTest;
      InputReference inputReference;
      byte[] detailContent;
      List<Attribute> attributes;
      String issuer;
      String messageVersion;
      String projectIdentifier;

      Input(boolean isTest, InputReference inputReference, byte[] detailContent, List<Attribute> attributes, String issuer, String messageVersion, String projectIdentifier) {
         this.isTest = isTest;
         this.inputReference = inputReference;
         this.detailContent = detailContent;
         this.attributes = attributes;
         this.issuer = issuer;
         this.messageVersion = messageVersion;
         this.projectIdentifier = projectIdentifier;
      }

      public static InputBuilder builder() {
         return new InputBuilder();
      }

      public boolean isTest() {
         return this.isTest;
      }

      public InputReference getInputReference() {
         return this.inputReference;
      }

      public byte[] getDetailContent() {
         return this.detailContent;
      }

      public List<Attribute> getAttributes() {
         return this.attributes;
      }

      public String getIssuer() {
         return this.issuer;
      }

      public String getMessageVersion() {
         return this.messageVersion;
      }

      public String getProjectIdentifier() {
         return this.projectIdentifier;
      }

      public static class InputBuilder {
         private boolean isTest;
         private InputReference inputReference;
         private byte[] detailContent;
         private List<Attribute> attributes;
         private String issuer;
         private String messageVersion;
         private String projectIdentifier;

         InputBuilder() {
         }

         public InputBuilder isTest(boolean isTest) {
            this.isTest = isTest;
            return this;
         }

         public InputBuilder inputReference(InputReference inputReference) {
            this.inputReference = inputReference;
            return this;
         }

         public InputBuilder detailContent(byte[] detailContent) {
            this.detailContent = detailContent;
            return this;
         }

         public InputBuilder attributes(List<Attribute> attributes) {
            this.attributes = attributes;
            return this;
         }

         public InputBuilder issuer(String issuer) {
            this.issuer = issuer;
            return this;
         }

         public InputBuilder messageVersion(String messageVersion) {
            this.messageVersion = messageVersion;
            return this;
         }

         public InputBuilder projectIdentifier(String projectIdentifier) {
            this.projectIdentifier = projectIdentifier;
            return this;
         }

         public Input build() {
            return new Input(this.isTest, this.inputReference, this.detailContent, this.attributes, this.issuer, this.messageVersion, this.projectIdentifier);
         }

         public String toString() {
            return "EncryptedRequestObjectBuilder.Input.InputBuilder(isTest=" + this.isTest + ", inputReference=" + this.inputReference + ", detailContent=" + Arrays.toString(this.detailContent) + ", attributes=" + this.attributes + ", issuer=" + this.issuer + ", messageVersion=" + this.messageVersion + ", projectIdentifier=" + this.projectIdentifier + ")";
         }
      }
   }
}
