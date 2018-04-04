package org.taktik.connector.business.mycarenet.attest.security;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.IdentifierType;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.transformers.EncapsulationTransformer;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class BusinessContentEncryptor {
   private static final String IDENTIFIER_VALUE_PROPERTY = "attest.keydepot.identifiervalue";
   private static final String APPLICATION_PROPERTY = "attest.keydepot.application";
   private static List<String> expectedProps = new ArrayList();
   private static Configuration config;
   private static final String CIN_NAMESPACE = "urn:be:cin:encrypted";
   private static final String ENCRYPTED_KNOWN_CONTENT = "EncryptedKnownContent";
   private static final Logger LOG = LoggerFactory.getLogger(BusinessContentEncryptor.class.getName());

   public static byte[] encrypt(Document doc, Crypto crypto, String detailId) throws TechnicalConnectorException, TransformerException, UnsupportedEncodingException {
      NodeList nodes = doc.getElementsByTagNameNS("urn:be:cin:encrypted", "EncryptedKnownContent");
      String content = toStringOmittingXmlDeclaration(nodes);
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      tranforms.add("http://www.w3.org/2001/10/xml-exc-c14n#");
      options.put("transformerList", tranforms);
      options.put("baseURI", detailId);
      options.put("encapsulate", true);
      options.put("encapsulate-transformer", new EncapsulationTransformer() {
         public Node transform(Node signature) {
            Element result = signature.getOwnerDocument().createElementNS("urn:be:cin:encrypted", "Xades");
            result.setTextContent(Base64.encodeBase64String(ConnectorXmlUtils.toByteArray(signature)));
            return result;
         }
      });
      byte[] encryptedKnowContent = builder.sign(Session.getInstance().getSession().getEncryptionCredential(), content.getBytes("UTF-8"), options);
      return seal(crypto, encryptedKnowContent);
   }

   private static String toStringOmittingXmlDeclaration(NodeList nodes) throws TransformerException {
      StringBuilder sb = new StringBuilder();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer serializer = tf.newTransformer();
      serializer.setOutputProperty("omit-xml-declaration", "yes");

      for(int i = 0; i < nodes.getLength(); ++i) {
         StringWriter sw = new StringWriter();
         serializer.transform(new DOMSource(nodes.item(i)), new StreamResult(sw));
         sb.append(sw.toString());
      }

      return sb.toString();
   }

   private static byte[] seal(Crypto crypto, byte[] content) throws TechnicalConnectorException {
      return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, getHubEtk(), content);
   }

   private static Set<EncryptionToken> getHubEtk() throws TechnicalConnectorException {
      return KeyDepotManagerFactory.getKeyDepotManager().getEtkSet(IdentifierType.CBE, 820563481L, "MYCARENET");
   }

   static {
      expectedProps.add("attest.keydepot.application");
      expectedProps.add("attest.keydepot.identifiervalue");
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
