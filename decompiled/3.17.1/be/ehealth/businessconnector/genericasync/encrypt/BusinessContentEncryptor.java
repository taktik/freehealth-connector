package be.ehealth.businessconnector.genericasync.encrypt;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class BusinessContentEncryptor {
   private static final String IDENTIFIER_TYPE_PROPERTY = ".keydepot.identifiertype";
   private static final String IDENTIFIER_VALUE_PROPERTY = ".keydepot.identifiervalue";
   private static final String APPLICATION_ID_PROPERTY = ".keydepot.application";
   private static final long ETK_IDENTIFIER_DEFAULT_VALUE = 820563481L;
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static final String CIN_NAMESPACE = "urn:be:cin:encrypted";
   private static final String ENCRYPTED_KNOWN_CONTENT = "EncryptedKnownContent";

   private BusinessContentEncryptor() {
   }

   public static byte[] encrypt(String projectName, byte[] xmlByteArray, String plateform, String messageName) throws TechnicalConnectorException {
      return handleEncryption(xmlByteArray, SessionUtil.getHolderOfKeyCrypto(), projectName, plateform, messageName);
   }

   private static byte[] handleEncryption(byte[] request, Crypto crypto, String projectName, String plateform, String messageName) throws TechnicalConnectorException {
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      BusinessContent businessContent = new BusinessContent();
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      businessContent.setId(detailId);
      businessContent.setValue(request);
      businessContent.setContentEncoding("deflate");
      businessContent.setContentType(getContentType(projectName, plateform, messageName));
      encryptedKnownContent.setBusinessContent(businessContent);

      try {
         return encrypt(ConnectorXmlUtils.toDocument((Object)encryptedKnownContent), crypto, detailId, projectName);
      } catch (Exception var9) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var9, new Object[]{var9.getMessage()});
      }
   }

   private static String getContentType(String projectName, String plateform, String messageName) {
      String contentType = config.getProperty(plateform + ".blobbuilder." + projectName + "." + messageName + ".contenttype");
      if (contentType == null) {
         contentType = config.getProperty(plateform + ".blobbuilder." + projectName + ".contenttype");
      }

      return contentType;
   }

   private static byte[] encrypt(Document doc, Crypto crypto, String detailId, String projectName) throws TechnicalConnectorException, TransformerException, UnsupportedEncodingException {
      NodeList nodes = doc.getElementsByTagNameNS("urn:be:cin:encrypted", "EncryptedKnownContent");
      String content = toStringOmittingXmlDeclaration(nodes);
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      tranforms.add("urn:nippin:xml:sig:transform:optional-deflate");
      options.put("transformerList", tranforms);
      options.put("baseURI", detailId);
      EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)ConnectorXmlUtils.toObject(content.getBytes("UTF-8"), EncryptedKnownContent.class);
      encryptedKnownContent.getBusinessContent().setValue(ConnectorIOUtils.compress(encryptedKnownContent.getBusinessContent().getValue(), "deflate"));
      byte[] xades = builder.sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray((Object)encryptedKnownContent.getBusinessContent()), options);
      encryptedKnownContent.setXades(xades);
      return seal(crypto, ConnectorXmlUtils.toByteArray((Object)encryptedKnownContent), projectName);
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

   private static byte[] seal(Crypto crypto, byte[] content, String projectName) throws TechnicalConnectorException {
      return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, getHubEtk(projectName), content);
   }

   private static Set<EncryptionToken> getHubEtk(String projectName) throws TechnicalConnectorException {
      String identifierTypeString = config.getProperty(projectName + ".keydepot.identifiertype", "CBE");
      Long identifierValue = config.getLongProperty(projectName + ".keydepot.identifiervalue", 820563481L);
      String applicationId = config.getProperty(projectName + ".keydepot.application", "");
      int identifierSource = 48;
      IdentifierType identifier = IdentifierType.lookup(identifierTypeString, (String)null, identifierSource);
      if (identifier == null) {
         throw new IllegalStateException("invalid configuration : identifier with type ]" + identifierTypeString + "[ for source ETKDEPOT not found");
      } else {
         return KeyDepotManagerFactory.getKeyDepotManager().getEtkSet(IdentifierType.CBE, identifierValue, applicationId);
      }
   }
}
