package be.fgov.ehealth.technicalconnector.distributedkeys.proxy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.technicalconnector.distributedkeys.DistributedSignerProxy;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.DocumentHash;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.KeySelector;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.SignRequest;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import org.apache.commons.lang.StringUtils;
import org.w3._2000._09.xmldsig.DigestMethod;
import org.w3._2000._09.xmldsig.KeyInfo;
import org.w3._2000._09.xmldsig.ObjectFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DigitalSignatureServiceProxy implements DistributedSignerProxy {
   private static Map<String, DigestMethod> digestAlgoToDigestMethod = new HashMap();
   private static ObjectFactory dsigObjectFactory = new ObjectFactory();
   private GenericRequest req;
   private String profile;
   private Map<String, List<X509Certificate>> certificates;

   public DigitalSignatureServiceProxy(GenericRequest req, String profile, Map<String, List<X509Certificate>> certificates) {
      this.req = req;
      this.profile = profile;
      this.certificates = certificates;
   }

   private static Element createKeySelector(String alias) {
      KeySelector selector = new KeySelector();
      KeyInfo keyInfo = new KeyInfo();
      keyInfo.getContent().add(dsigObjectFactory.createKeyName(alias));
      selector.setKeyInfo(keyInfo);
      MarshallerHelper<KeySelector, KeySelector> helper = new MarshallerHelper(KeySelector.class, KeySelector.class);
      return helper.toDocument(selector).getDocumentElement();
   }

   private static Element createSignatureType() throws ParserConfigurationException {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder builder = dbf.newDocumentBuilder();
      Document doc = builder.newDocument();
      Element signatureType = doc.createElementNS("urn:oasis:names:tc:dss:1.0:core:schema", "SignatureType");
      signatureType.setTextContent("urn:ietf:rfc:3447");
      return signatureType;
   }

   private static DigestMethod createDigestMethod(String digestAlgo) {
      DigestMethod method = new DigestMethod();
      method.setAlgorithm(digestAlgo);
      return method;
   }

   public byte[] sign(byte[] digestValue, String digestAlgo, String alias) throws SignatureException {
      try {
         if (!digestAlgoToDigestMethod.containsKey(digestAlgo)) {
            throw new IllegalArgumentException("Unsupported digest algo: " + digestAlgo);
         } else {
            SignRequest signRequest = new SignRequest();
            signRequest.setRequestID(IdGeneratorFactory.getIdGenerator("xsid").generateId());
            if (StringUtils.isNotBlank(this.profile)) {
               signRequest.setProfile(this.profile);
            }

            DocumentHash documentHash = new DocumentHash();
            documentHash.setDigestValue(digestValue);
            documentHash.setDigestMethod((DigestMethod)digestAlgoToDigestMethod.get(digestAlgo));
            InputDocuments inputDocuments = new InputDocuments();
            inputDocuments.getDocumentHash().add(documentHash);
            AnyType optionalInputs = new AnyType();
            optionalInputs.getAnies().add(createKeySelector(alias));
            optionalInputs.getAnies().add(createSignatureType());
            signRequest.setOptionalInputs(optionalInputs);
            SignResponse signResponse = (SignResponse)ServiceFactory.getGenericWsSender().send(this.req.setPayload((Object)signRequest)).asObject(SignResponse.class);
            Result result = signResponse.getResult();
            if ("urn:oasis:names:tc:dss:1.0:resultminor:valid:signature:OnAllDocuments".equals(result.getResultMajor())) {
               throw new SignatureException("Received incorrect status [" + ConnectorXmlUtils.toString((Object)result) + "]");
            } else {
               SignatureObject signatureObject = signResponse.getSignatureObject();
               Base64Signature base64Signature = signatureObject.getBase64Signature();
               return base64Signature.getValue();
            }
         }
      } catch (TechnicalConnectorException var12) {
         throw new SignatureException(var12);
      } catch (ParserConfigurationException var13) {
         throw new SignatureException(var13);
      } catch (SOAPException var14) {
         throw new SignatureException(var14);
      }
   }

   public Set<String> getAliases() {
      return this.certificates.keySet();
   }

   public List<X509Certificate> getCertificateChain(String alias) {
      return (List)this.certificates.get(alias);
   }

   public String getAlgorithm(String alias) {
      List<X509Certificate> certificateChain = this.getCertificateChain(alias);
      if (certificateChain != null && !certificateChain.isEmpty()) {
         return ((X509Certificate)certificateChain.get(0)).getPublicKey().getAlgorithm();
      } else {
         throw new IllegalArgumentException("Unable to determine Algorithm");
      }
   }

   static {
      digestAlgoToDigestMethod.put("SHA1", createDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1"));
      digestAlgoToDigestMethod.put("SHA-1", createDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1"));
      digestAlgoToDigestMethod.put("SHA-256", createDigestMethod("http://www.w3.org/2001/04/xmlenc#sha256"));
      digestAlgoToDigestMethod.put("SHA-512", createDigestMethod("http://www.w3.org/2001/04/xmlenc#sha512"));
   }
}
