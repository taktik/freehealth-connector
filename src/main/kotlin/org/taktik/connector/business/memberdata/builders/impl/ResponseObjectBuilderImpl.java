package org.taktik.connector.business.memberdata.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.mapper.v3.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.memberdata.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.memberdata.domain.MemberDataBuilderResponse;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import oasis.names.tc.saml._2_0.assertion.Assertion;
import oasis.names.tc.saml._2_0.protocol.Response;
import org.apache.commons.compress.utils.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);

   public MemberDataBuilderResponse handleConsultationResponse(MemberDataConsultationResponse consultResponse) throws TechnicalConnectorException {
      BlobType blobType = consultResponse.getReturn().getDetail();
      Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
      if (blob.getContent().length <= 0) {
         return null;
      } else {
         byte[] data = blob.getContent();
         Document doc;
         if (blob.getContentEncryption() != null && !blob.getContentEncryption().isEmpty()) {
            byte[] unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).getContentAsByte();
            EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
            data = encryptedKnownContent.getBusinessContent().getValue();
            doc = ConnectorXmlUtils.toDocument(data);
         } else {
            doc = ConnectorXmlUtils.toDocument(consultResponse.getReturn().getDetail().getValue());
         }

         if (data != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages", false)) {
            LOG.debug("ResponseObjectBuilder : Blob content: " + new String(data));
         }

         NodeList nodes = doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");

         try {
            List<String> assertions = this.toStringOmittingXmlDeclaration(nodes);
            Map<String, SignatureVerificationResult> signatureVerificationResults = this.checkAssertions(assertions);
            return new MemberDataBuilderResponse(consultResponse, (Response)ConnectorXmlUtils.toObject(data, Response.class), signatureVerificationResults);
         } catch (Exception var9) {
            LOG.error("Error processing MemberDataConsultationResponse with id {0}", var9, consultResponse.getId());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var9, new Object[0]);
         }
      }
   }

   private Map<String, SignatureVerificationResult> checkAssertions(List<String> result) throws Exception {
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
      Map<String, Object> options = Collections.emptyMap();
      Map<String, SignatureVerificationResult> signatureVerificationResults = new HashMap();
      Iterator i$ = result.iterator();

      while(i$.hasNext()) {
         String assertionXml = (String)i$.next();
         if (assertionXml != null) {
            Assertion assertion = (Assertion)ConnectorXmlUtils.toObject(assertionXml, Assertion.class);
            if (assertion != null && assertion.getSignature() != null) {
               SignatureVerificationResult signatureVerificationResult = builder.verify(assertionXml.getBytes(Charsets.UTF_8), options);
               signatureVerificationResults.put(assertion.getID(), signatureVerificationResult);
            }
         }
      }

      return signatureVerificationResults;
   }

   private List<String> toStringOmittingXmlDeclaration(NodeList nodes) throws TransformerException {
      List<String> assertions = new ArrayList();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer serializer = tf.newTransformer();
      serializer.setOutputProperty("omit-xml-declaration", "yes");

      for(int i = 0; i < nodes.getLength(); ++i) {
         StringWriter sw = new StringWriter();
         serializer.transform(new DOMSource(nodes.item(i)), new StreamResult(sw));
         assertions.add(sw.toString());
      }

      return assertions;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
      JaxbContextFactory.initJaxbContext(Response.class);
      JaxbContextFactory.initJaxbContext(EncryptedKnownContent.class);
   }
}
