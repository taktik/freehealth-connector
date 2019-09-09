package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import org.taktik.connector.technical.exception.InvalidTimeStampException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.timestamp.TimestampUtil;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.validator.impl.TimeStampValidatorFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.SignatureUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.tsa.TimestampGeneratorFactory;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.Transform;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XadesTSpecification implements XadesSpecification {
   private static final String DEFAULT_C14N_METHOD = "http://www.w3.org/2001/10/xml-exc-c14n#";
   private static final Logger LOG = LoggerFactory.getLogger(XadesBesSpecification.class);

   public void addOptionalBeforeSignatureParts(SignedPropertiesBuilder signedProps, XMLSignature sig, Credential signing, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature sig, Credential credential, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
      String c14nMethod = SignatureUtils.getOption("SignatureTimeStampCanonicalizationMethodURI", options, "http://www.w3.org/2001/10/xml-exc-c14n#");
      byte[] tsToken = this.generateSignatureTimestamp(sig, options, c14nMethod, credential);
      unsignedProps.addSignatureTimestamp(tsToken, c14nMethod);
   }

   public void verify(SignatureVerificationResult result, Element sigElement) {
      this.verifySignatureTimeStamp(result, sigElement);
   }

   private byte[] generateSignatureTimestamp(XMLSignature sig, Map<String, Object> options, String c14nMethodValue, Credential credential) throws TechnicalConnectorException {
      byte[] digest = this.generateTimestampDigest(sig.getElement(), c14nMethodValue);
      String digestAlgoUri = SignatureUtils.getOption("SignatureTimestampAlgorithmURI", options, "http://www.w3.org/2001/04/xmlenc#sha256");
      return TimestampGeneratorFactory.getInstance(options).generate(sig.getId(), credential, digestAlgoUri, digest);
   }

   private void verifySignatureTimeStamp(SignatureVerificationResult result, Element baseElement) {
      try {
         NodeList signatureTimeStampList = DomUtils.getMatchingChilds(baseElement, "http://uri.etsi.org/01903/v1.3.2#", "SignatureTimeStamp");
         if (signatureTimeStampList != null && signatureTimeStampList.getLength() > 0) {
            for(int i = 0; i < signatureTimeStampList.getLength(); ++i) {
               Element signatureTimeStamp = (Element)signatureTimeStampList.item(i);
               NodeList timestampList = DomUtils.getMatchingChilds(signatureTimeStamp, "http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedTimeStamp");
               NodeList c14nNodeList = DomUtils.getMatchingChilds(signatureTimeStamp, "http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
               String c14nMethodValue = this.getCanonicalizationMethod(c14nNodeList);
               this.verifyTimestampList(result, baseElement, timestampList, c14nMethodValue);
            }
         } else {
            result.getErrors().add(SignatureVerificationError.XADES_ENCAPSULATED_TIMESTAMP_NOT_FOUND);
         }
      } catch (Exception var9) {
         LOG.error("Unable to verify Timestamp", var9);
         result.getErrors().add(SignatureVerificationError.XADES_ENCAPSULATED_TIMESTAMP_NOT_VERIFIED);
      }

   }

   private void verifyTimestampList(SignatureVerificationResult result, Element baseElement, NodeList timestampList, String c14nMethodValue) throws TechnicalConnectorException {
      if (timestampList != null && timestampList.getLength() > 0) {
         for(int j = 0; j < timestampList.getLength(); ++j) {
            try {
               Node timestampNode = timestampList.item(j);
               byte[] digestValue = this.generateTimestampDigest(baseElement, c14nMethodValue);
               TimeStampToken tsToken = TimestampUtil.getTimeStampToken(Base64.decode(timestampNode.getTextContent().getBytes()));
               TimeStampValidatorFactory.Companion.getInstance().validateTimeStampToken(digestValue, tsToken);
               result.getTimestampGenTimes().add(new DateTime(tsToken.getTimeStampInfo().getGenTime()));
               result.getTsTokens().add(tsToken);
            } catch (InvalidTimeStampException var9) {
               LOG.error(var9.getMessage(), var9);
               result.getErrors().add(SignatureVerificationError.XADES_ENCAPSULATED_TIMESTAMP_NOT_VALID);
            }
         }
      } else {
         result.getErrors().add(SignatureVerificationError.XADES_ENCAPSULATED_TIMESTAMP_NOT_FOUND);
      }

   }

   private String getCanonicalizationMethod(NodeList c14nNodeList) {
      String c14nMethodValue;
      if (c14nNodeList != null && c14nNodeList.getLength() != 0) {
         c14nMethodValue = c14nNodeList.item(0).getAttributes().getNamedItem("Algorithm").getTextContent();
      } else {
         LOG.info("Unable to detect CanonicalizationMethod, using default [http://www.w3.org/2001/10/xml-exc-c14n#]");
         c14nMethodValue = "http://www.w3.org/2001/10/xml-exc-c14n#";
      }

      return c14nMethodValue;
   }

   private byte[] generateTimestampDigest(Element baseElement, String c14nMethodValue) {
      try {
         Node signatureValue = DomUtils.getMatchingChilds(baseElement, "http://www.w3.org/2000/09/xmldsig#", "SignatureValue").item(0);
         Transform transform = new Transform(signatureValue.getOwnerDocument(), c14nMethodValue);
         XMLSignatureInput refData = transform.performTransform(new XMLSignatureInput(signatureValue));
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         if (refData.isByteArray()) {
            baos.write(refData.getBytes());
         } else if (refData.isOctetStream()) {
            baos.write(ConnectorIOUtils.getBytes(refData.getOctetStream()));
         }

         return baos.toByteArray();
      } catch (Exception var7) {
         throw new IllegalArgumentException("Unable to calculateDigest", var7);
      }
   }
}
