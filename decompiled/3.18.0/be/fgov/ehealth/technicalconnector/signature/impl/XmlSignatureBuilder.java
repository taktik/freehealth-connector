package be.fgov.ehealth.technicalconnector.signature.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.extractor.Extractor;
import be.fgov.ehealth.technicalconnector.signature.impl.extractor.X509DataExctractor;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesHandler;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification;
import be.fgov.ehealth.technicalconnector.signature.resolvers.DocumentResolver;
import be.fgov.ehealth.technicalconnector.signature.transformers.EncapsulationTransformer;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.xml.security.Init;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlSignatureBuilder extends AbstractSignatureBuilder implements SignatureBuilder {
   public static final String XMLNS_DS = "http://www.w3.org/2000/09/xmldsig#";
   private static final Logger LOG = LoggerFactory.getLogger(XmlSignatureBuilder.class);
   private XadesSpecification[] specs;
   private AdvancedElectronicSignatureEnumeration aes;

   public XmlSignatureBuilder(AdvancedElectronicSignatureEnumeration aes, XadesSpecification... specs) {
      this.specs = specs;
      this.aes = aes;
   }

   private static void addKeyInfo(Credential signatureCredential, XMLSignature sig) throws TechnicalConnectorException, XMLSecurityException {
      if (signatureCredential.getCertificateChain() != null) {
         Certificate[] arr$ = signatureCredential.getCertificateChain();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Certificate cert = arr$[i$];
            if (sig.getKeyInfo().itemX509Data(0) == null) {
               X509Data x509data = new X509Data(sig.getDocument());
               sig.getKeyInfo().add(x509data);
            }

            sig.getKeyInfo().itemX509Data(0).addCertificate((X509Certificate)cert);
         }
      }

   }

   private static boolean mustEncapsulate(List<String> transformerList) {
      return transformerList.contains("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
   }

   private static List<String> getTransformerList(Map<String, Object> optionMap) {
      boolean encapsulate = (Boolean)SignatureUtils.getOption("encapsulate", optionMap, Boolean.FALSE);
      List<String> transformerList = (List)SignatureUtils.getOption("transformerList", optionMap, new ArrayList());
      if (encapsulate && !transformerList.contains("http://www.w3.org/2000/09/xmldsig#enveloped-signature")) {
         transformerList.add(0, "http://www.w3.org/2000/09/xmldsig#enveloped-signature");
      }

      return transformerList;
   }

   private static byte[] transform(boolean encapsulate, String xpathLocation, EncapsulationTransformer encapsulationTranformer, Document doc, XMLSignature sig) {
      if (!encapsulate) {
         return ConnectorXmlUtils.toByteArray((Node)sig.getElement());
      } else {
         Node toInsert = doc.adoptNode(encapsulationTranformer.transform(sig.getElement()));
         Node insertBeforeNode = null;
         if (StringUtils.isNotBlank(xpathLocation)) {
            try {
               XPath xPath = XPathFactory.newInstance().newXPath();
               NodeList nodes = (NodeList)xPath.evaluate(xpathLocation, doc.getDocumentElement(), XPathConstants.NODESET);
               if (nodes.getLength() == 1) {
                  LOG.debug("1 node found, inserting at location [" + xpathLocation + "]");
                  insertBeforeNode = nodes.item(0);
               } else {
                  LOG.warn("XPATH error: " + nodes.getLength() + "found at location [" + xpathLocation + "],using default.");
               }
            } catch (XPathExpressionException var9) {
               LOG.info("Unable to determine XPath Location, using default.", var9);
            }
         } else {
            LOG.debug("Using default location (last child tag)");
         }

         doc.getFirstChild().insertBefore(toInsert, insertBeforeNode);
         return ConnectorXmlUtils.toByteArray((Node)doc);
      }
   }

   private static Transforms transforms(List<String> tranformerList, Document doc) throws TransformationException {
      Transforms baseDocTransform = new Transforms(doc);
      Iterator i$ = tranformerList.iterator();

      while(i$.hasNext()) {
         String transform = (String)i$.next();
         baseDocTransform.addTransform(transform);
      }

      return baseDocTransform;
   }

   private static String ref(String id) {
      return "#" + id;
   }

   public byte[] sign(Credential signatureCredential, byte[] byteArrayToSign) throws TechnicalConnectorException {
      return this.sign(signatureCredential, byteArrayToSign, new HashMap());
   }

   public byte[] sign(Credential signatureCredential, byte[] byteArrayToSign, Map<String, Object> options) throws TechnicalConnectorException {
      Map<String, Object> optionMap = new HashMap();
      if (options != null) {
         optionMap.putAll(options);
      }

      this.validateInput(signatureCredential, byteArrayToSign);

      try {
         String xmldsigId = "xmldsig-" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
         String baseURI = (String)SignatureUtils.getOption("baseURI", optionMap, "");
         String signatureMethodURI = (String)SignatureUtils.getOption("signatureMethodURI", optionMap, "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
         String canonicalizationMethodURI = (String)SignatureUtils.getOption("canonicalizationMethodURI", optionMap, "http://www.w3.org/2001/10/xml-exc-c14n#");
         String digestURI = (String)SignatureUtils.getOption("digestURI", optionMap, "http://www.w3.org/2001/04/xmlenc#sha256");
         String encapsulateLocation = (String)SignatureUtils.getOption("encapsulate-xpath", optionMap, (Object)null);
         EncapsulationTransformer encapsulationTranformer = (EncapsulationTransformer)SignatureUtils.getOption("encapsulate-transformer", optionMap, new XmlSignatureBuilder.PassthroughEncapsulationTransformer());
         List<String> transformerList = getTransformerList(optionMap);
         Document doc = ConnectorXmlUtils.toDocument(byteArrayToSign);
         XMLSignature sig = new XMLSignature(doc, baseURI, signatureMethodURI, canonicalizationMethodURI);
         sig.addResourceResolver(new DocumentResolver(doc));
         sig.addDocument(ref(baseURI), transforms(transformerList, doc), digestURI);
         addKeyInfo(signatureCredential, sig);
         XadesHandler handler = new XadesHandler(sig, signatureCredential, options, this.specs);
         handler.before();
         sig.sign(signatureCredential.getPrivateKey());
         sig.setId(xmldsigId);
         handler.after();
         return transform(mustEncapsulate(transformerList), encapsulateLocation, encapsulationTranformer, doc, sig);
      } catch (Exception var16) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var16, new Object[]{var16.getMessage()});
      }
   }

   public SignatureVerificationResult verify(byte[] signedByteArray, Map<String, Object> options) throws TechnicalConnectorException {
      Document signedContent = ConnectorXmlUtils.toDocument(signedByteArray);
      NodeList signatureList = DomUtils.getMatchingChilds(signedContent, "http://www.w3.org/2000/09/xmldsig#", "Signature");
      if (signatureList != null && signatureList.getLength() != 0) {
         if (signatureList.getLength() > 1) {
            LOG.info("Multiple signature found, using first one.");
         }

         return this.verify(signedContent, (Element)signatureList.item(0), options);
      } else {
         LOG.info("No signature found in signedContent");
         SignatureVerificationResult result = new SignatureVerificationResult();
         result.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
         return result;
      }
   }

   public SignatureVerificationResult verify(byte[] signedByteArray, byte[] signature, Map<String, Object> options) throws TechnicalConnectorException {
      Element sigElement = ConnectorXmlUtils.toElement(signature);
      Document signedContent = ConnectorXmlUtils.toDocument(signedByteArray);
      return this.verify(signedContent, sigElement, options);
   }

   public SignatureVerificationResult verify(Document signedContent, Element sigElement, Map<String, Object> options) throws TechnicalConnectorException {
      Map<String, Object> optionMap = new HashMap();
      if (options != null) {
         optionMap.putAll(options);
      }

      SignatureVerificationResult result = new SignatureVerificationResult();
      NodeList signatureList = DomUtils.getMatchingChilds(signedContent, "http://www.w3.org/2000/09/xmldsig#", "Signature");
      if (signatureList == null || signatureList.getLength() == 0) {
         LOG.info("Adding signature to signedContent");
         signedContent.getFirstChild().appendChild(signedContent.importNode(sigElement, true));
      }

      this.verifyXmlDsigSignature(result, sigElement, signedContent, optionMap);
      this.verifyManifest(result, sigElement, optionMap);
      XadesSpecification[] arr$ = this.specs;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         XadesSpecification spec = arr$[i$];
         spec.verify(result, sigElement);
      }

      this.validateChain(result, options);
      return result;
   }

   private void verifyManifest(SignatureVerificationResult result, Element sigElement, Map<String, Object> options) {
      Boolean followNestedManifest = (Boolean)SignatureUtils.getOption("followNestedManifest", options, Boolean.FALSE);
      if (followNestedManifest) {
         Element signedInfo = (Element)DomUtils.getMatchingChilds(sigElement, "http://www.w3.org/2000/09/xmldsig#", "SignedInfo").item(0);
         NodeList referencesList = DomUtils.getMatchingChilds(signedInfo, "http://www.w3.org/2000/09/xmldsig#", "Reference");

         for(int i = 0; i < referencesList.getLength(); ++i) {
            Element reference = (Element)referencesList.item(i);
            String refType = reference.getAttribute("Type");
            if (refType.endsWith("Manifest") && !refType.equalsIgnoreCase("http://www.w3.org/2000/09/xmldsig#Manifest")) {
               result.getErrors().add(SignatureVerificationError.SIGNATURE_MANIFEST_COULD_NOT_BE_VERIFIED);
            }
         }
      }

   }

   private void verifyXmlDsigSignature(SignatureVerificationResult result, Element sigElement, Document signedContent, Map<String, Object> options) {
      try {
         String uri = IdGeneratorFactory.getIdGenerator("uuid").generateId();
         XMLSignature xmlSignature = new XMLSignature(sigElement, uri);
         Boolean followNestedManifest = (Boolean)SignatureUtils.getOption("followNestedManifest", options, Boolean.FALSE);
         xmlSignature.setFollowNestedManifests(followNestedManifest);
         xmlSignature.addResourceResolver(new DocumentResolver(signedContent));
         KeyInfo keyInfo = xmlSignature.getKeyInfo();
         keyInfo.setSecureValidation(false);
         Extractor extractor = new X509DataExctractor();
         result.getCertChain().addAll(extractor.extract(keyInfo));
         X509Certificate signingCert = this.extractEndCertificate(result.getCertChain());
         result.setSigningCert(signingCert);
         if (!xmlSignature.checkSignatureValue(signingCert)) {
            result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
         }
      } catch (Exception var11) {
         LOG.error("Unable to verify XmlDsig Signature", var11);
         result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
      }

   }

   public AdvancedElectronicSignatureEnumeration getSupportedAES() {
      return this.aes;
   }

   static {
      if (!Init.isInitialized()) {
         Init.init();
      }

   }

   private static class PassthroughEncapsulationTransformer implements EncapsulationTransformer {
      private PassthroughEncapsulationTransformer() {
      }

      public Node transform(Node signature) {
         return signature;
      }

      // $FF: synthetic method
      PassthroughEncapsulationTransformer(Object x0) {
         this();
      }
   }
}
