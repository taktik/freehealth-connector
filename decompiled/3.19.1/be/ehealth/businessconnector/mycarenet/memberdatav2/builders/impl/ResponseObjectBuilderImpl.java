package be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.mapper.v3.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.memberdatav2.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.memberdatav2.domain.MemberDataBuilderResponse;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils;
import java.util.HashMap;
import java.util.Map;
import oasis.names.tc.saml._2_0.protocol.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);
   private static final String PROP_DUMP_MESSAGES = "be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages";

   public MemberDataBuilderResponse handleConsultationResponse(MemberDataConsultationResponse consultResponse) throws TechnicalConnectorException {
      BlobType blobType = consultResponse.getReturn().getDetail();
      Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
      if (blob.getContent().length > 0) {
         byte[] data = blob.getContent();
         if (blob.getContentEncryption() != null && !blob.getContentEncryption().isEmpty()) {
            byte[] unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).getContentAsByte();
            EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
            data = encryptedKnownContent.getBusinessContent().getValue();
         }

         if (data != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages", false)) {
            LOG.debug("ResponseObjectBuilder : Blob content: {}", new String(data));
         }

         try {
            Map<String, SignatureVerificationResult> signatureVerificationResults = this.verifyAll(data);
            return new MemberDataBuilderResponse(consultResponse, data, signatureVerificationResults);
         } catch (Exception var7) {
            LOG.error("Error processing MemberDataConsultationResponse with id {0}", consultResponse.getId(), var7);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var7, new Object[0]);
         }
      } else {
         return null;
      }
   }

   public Map<String, SignatureVerificationResult> verifyAll(byte[] signedByteArray) throws TechnicalConnectorException {
      Document signedContent = ConnectorXmlUtils.toDocument(signedByteArray);
      NodeList signatureList = DomUtils.getMatchingChilds(signedContent, "http://www.w3.org/2000/09/xmldsig#", "Signature");
      Map<String, SignatureVerificationResult> signatureVerificationResults = new HashMap();

      for(int i = 0; i < signatureList.getLength(); ++i) {
         Element signatureElement = (Element)signatureList.item(i);
         SignatureVerificationResult signatureVerificationResult = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).verify((Document)signedContent, (Element)signatureElement, (Map)null);
         signatureVerificationResults.put(this.deriveXPathExpressionFrom(signatureElement), signatureVerificationResult);
      }

      return signatureVerificationResults;
   }

   private String deriveXPathExpressionFrom(Node node) {
      if (node != null && node.getNodeType() == 1) {
         Node parent = node.getParentNode();
         NodeList childNodes = parent.getChildNodes();
         int index = 0;
         int found = 0;

         for(int i = 0; i < childNodes.getLength(); ++i) {
            Node current = childNodes.item(i);
            if (current.getNodeName().equals(node.getNodeName())) {
               if (current == node) {
                  found = index + 1;
               }

               ++index;
            }
         }

         String strIdx = "[" + found + "]";
         if (index == 1) {
            strIdx = "";
         }

         StringBuilder xPathExpression = (new StringBuilder("/")).append("*[local-name() = '").append(node.getLocalName()).append("' and namespace-uri()='").append(node.getNamespaceURI()).append("']").append(strIdx);
         return this.deriveXPathExpressionFrom(node.getParentNode()) + xPathExpression.toString();
      } else {
         return "";
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
      JaxbContextFactory.initJaxbContext(Response.class);
      JaxbContextFactory.initJaxbContext(EncryptedKnownContent.class);
   }
}
