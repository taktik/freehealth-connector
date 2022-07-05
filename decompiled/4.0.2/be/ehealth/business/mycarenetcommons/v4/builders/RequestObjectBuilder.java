package be.ehealth.business.mycarenetcommons.v4.builders;

import be.ehealth.business.mycarenetcommons.security.SignHelper;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobAttributeValues;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005._05.xmlmime.Base64Binary;

public class RequestObjectBuilder<T extends SendRequestType> {
   private static final Logger log = LoggerFactory.getLogger(RequestObjectBuilder.class);
   private RequestObjectBuilderHelper.Routing routing;

   public T buildRequest(Input input, Class<T> requestType) throws TechnicalConnectorException {
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      Blob blob = BlobBuilderFactory.getBlobBuilder(input.getProjectIdentifier()).build(input.getDetailContent(), BlobAttributeValues.builder().contentType("text/xml").encodingType("none").id(detailId).issuer(input.getIssuer()).messageVersion(input.getMessageVersion()).build());
      T sendRequestType = (new RequestObjectBuilderHelper()).buildSendRequestType(input.isTest, input.getInputReference().getInputReference(), input.getAttributes(), blob, input.getProjectIdentifier(), requestType);
      Base64Binary base64Binary = buildXades(blob, sendRequestType);
      sendRequestType.setXades(base64Binary);
      sendRequestType.setRouting(this.routing.createRouting());
      return sendRequestType;
   }

   public static Base64Binary buildXades(Blob blob, SendRequestType sendRequestType) throws TechnicalConnectorException {
      return DomainBlobMapper.mapB64fromByte(SignHelper.builder().signingOptions(new SignHelper.DefaultSigningOptions()).build().sign(ConnectorXmlUtils.toByteArray((Object)sendRequestType), blob.getId()));
   }

   RequestObjectBuilder(RequestObjectBuilderHelper.Routing routing) {
      this.routing = routing;
   }

   public static <T extends SendRequestType> RequestObjectBuilderBuilder<T> builder() {
      return new RequestObjectBuilderBuilder();
   }

   public static class RequestObjectBuilderBuilder<T extends SendRequestType> {
      private RequestObjectBuilderHelper.Routing routing;

      RequestObjectBuilderBuilder() {
      }

      public RequestObjectBuilderBuilder<T> routing(RequestObjectBuilderHelper.Routing routing) {
         this.routing = routing;
         return this;
      }

      public RequestObjectBuilder<T> build() {
         return new RequestObjectBuilder(this.routing);
      }

      public String toString() {
         return "RequestObjectBuilder.RequestObjectBuilderBuilder(routing=" + this.routing + ")";
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
            return "RequestObjectBuilder.Input.InputBuilder(isTest=" + this.isTest + ", inputReference=" + this.inputReference + ", detailContent=" + Arrays.toString(this.detailContent) + ", attributes=" + this.attributes + ", issuer=" + this.issuer + ", messageVersion=" + this.messageVersion + ", projectIdentifier=" + this.projectIdentifier + ")";
         }
      }
   }
}
