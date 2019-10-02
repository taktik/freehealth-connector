package org.taktik.connector.business.memberdata.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import org.apache.commons.compress.utils.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.memberdata.builders.ResponseObjectBuilder;
import org.taktik.connector.business.memberdata.domain.MemberDataBuilderResponse;
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion;
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.ExtensionsType;
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);

   public MemberDataBuilderResponse handleConsultationResponse(MemberDataConsultationResponse consultResponse, Crypto crypto) throws TechnicalConnectorException {
      try {
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer serializer = null;
         serializer = tf.newTransformer();
         serializer.setOutputProperty("omit-xml-declaration", "yes");

         BlobType blobType = consultResponse.getReturn().getDetail();
         Blob blob = BlobMapper.INSTANCE.mapBlobfromBlobType(blobType);
         if (blob.getContent().length <= 0) {
            return null;
         } else {
            byte[] data = blob.getContent();
            Document doc;
            if (blob.getContentEncryption() != null && !blob.getContentEncryption().isEmpty()) {
               byte[] unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).getContentAsByte();
               EncryptedKnownContent encryptedKnownContent = (new MarshallerHelper<>(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
               data = encryptedKnownContent.getBusinessContent().getValue();
               doc = ConnectorXmlUtils.toDocument(data);
            } else {
               doc = ConnectorXmlUtils.toDocument(consultResponse.getReturn().getDetail().getValue());
            }

            if (data != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages", false)) {
               LOG.debug("ResponseObjectBuilder : Blob content: " + new String(data));
            }

            NodeList nodes = doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");

            JAXBContext jaxbContext = JaxbContextFactory.getJaxbContextForClass(Assertion.class, ExtensionsType.class);
            SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
            Map<String, Object> options = Collections.emptyMap();
            Map<String, SignatureVerificationResult> signatureVerificationResults = new HashMap<>();

            List<Assertion> assertions = new LinkedList<>();

            for (int i = 0; i < nodes.getLength(); ++i) {
               Node node = nodes.item(i);
               Assertion assertion = (Assertion) jaxbContext.createUnmarshaller().unmarshal(new DOMSource(node));
               assertions.add(assertion);
               if (assertion != null) {
                  if (assertion.getSignature() != null) {
                     StringWriter sw = new StringWriter();
                     serializer.transform(new DOMSource(node), new StreamResult(sw));
                     SignatureVerificationResult signatureVerificationResult = builder.verify(sw.toString().getBytes(Charsets.UTF_8), options);
                     signatureVerificationResults.put(assertion.getID(), signatureVerificationResult);
                  }
               }
            }
            return new MemberDataBuilderResponse(assertions, consultResponse, (Response) JaxbContextFactory.getJaxbContextForClass(Response.class, ExtensionsType.class).createUnmarshaller().unmarshal(new DOMSource(doc)), signatureVerificationResults);
         }
      } catch (TransformerException | JAXBException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, e);
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
      JaxbContextFactory.initJaxbContext(Response.class);
      JaxbContextFactory.initJaxbContext(EncryptedKnownContent.class);
   }
}
