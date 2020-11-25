package org.taktik.connector.business.genericasync.encrypt;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.CryptoFactory;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.IdentifierType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class BusinessContentEncryptor {
   private static Configuration config = ConfigFactory.getConfigValidator();

   private BusinessContentEncryptor() {
   }

   public static byte[] encrypt(String projectName, byte[] xmlByteArray, KeyStoreCredential credential, KeyDepotManager keyDepotManager, String plateform, String messageName) throws TechnicalConnectorException {
      return handleEncryption(xmlByteArray, credential, keyDepotManager, projectName, plateform, messageName);
   }

   private static byte[] handleEncryption(byte[] request, KeyStoreCredential credential, KeyDepotManager keyDepotManager, String projectName, String plateform, String messageName) throws TechnicalConnectorException {
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(keyDepotManager.getETK(credential, credential.getKeystoreId()).getEncoded());
      BusinessContent businessContent = new BusinessContent();
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      businessContent.setId(detailId);
      businessContent.setValue(request);
      businessContent.setContentEncoding("deflate");
      businessContent.setContentType(getContentType(projectName, plateform, messageName));
      encryptedKnownContent.setBusinessContent(businessContent);

      try {
         String isXadesEncrypted = config.getProperty(plateform + "." + projectName + ".request" + ".xadesencrypted");
         boolean isEncrypt = isXadesEncrypted == null || isXadesEncrypted.equals("true");
         return encrypt(ConnectorXmlUtils.toDocument(encryptedKnownContent), credential, keyDepotManager, detailId, projectName, isEncrypt);
      } catch (Exception ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, ex, ex.getMessage());
      }
   }

   private static String getContentType(String projectName, String plateform, String messageName) {
      String contentType = config.getProperty(plateform + ".blobbuilder." + projectName + "." + messageName + ".contenttype");
      if (contentType == null) {
         contentType = config.getProperty(plateform + ".blobbuilder." + projectName + ".contenttype");
      }

      return contentType;
   }

   private static byte[] encrypt(Document doc, KeyStoreCredential credential, KeyDepotManager keyDepotManager, String detailId, String projectName, boolean xadesEncrypted) throws TechnicalConnectorException, TransformerException {
      NodeList nodes = doc.getElementsByTagNameNS("urn:be:cin:encrypted", "EncryptedKnownContent");
      String content = toStringOmittingXmlDeclaration(nodes);
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
      Map<String, Object> options = new HashMap<>();
      List<String> tranforms = new ArrayList<>();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      tranforms.add("urn:nippin:xml:sig:transform:optional-deflate");
      options.put("transformerList", tranforms);
      options.put("baseURI", detailId);
      EncryptedKnownContent encryptedKnownContent = ConnectorXmlUtils.toObject(content.getBytes(StandardCharsets.UTF_8), EncryptedKnownContent.class);
      encryptedKnownContent.getBusinessContent().setValue(ConnectorIOUtils.compress(encryptedKnownContent.getBusinessContent().getValue(), "deflate"));
      if (xadesEncrypted) {
         byte[] xades = builder.sign(credential, ConnectorXmlUtils.toByteArray(encryptedKnownContent.getBusinessContent()), options);
         encryptedKnownContent.setXades(xades);
      }

      return seal(getCrypto(credential), ConnectorXmlUtils.toByteArray(encryptedKnownContent), projectName, keyDepotManager, credential.getKeystoreId());
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

   private static byte[] seal(Crypto crypto, byte[] content, String projectName, KeyDepotManager keyDepotManager, UUID keystoreId) throws TechnicalConnectorException {
      return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, getHubEtk(projectName, keyDepotManager, keystoreId), content);
   }

   private static Set<EncryptionToken> getHubEtk(String projectName, KeyDepotManager keyDepotManager, UUID keystoreId) throws TechnicalConnectorException {
      String identifierTypeString = config.getProperty(projectName + ".keydepot.identifiertype", "CBE");
      Long identifierValue = config.getLongProperty(projectName + ".keydepot.identifiervalue", 820563481L);
      String applicationId = config.getProperty(projectName + ".keydepot.application", "");
      int identifierSource = 48;
      IdentifierType identifier = IdentifierType.lookup(identifierTypeString, null, identifierSource);
      if (identifier == null) {
         throw new IllegalStateException("invalid configuration : identifier with type ]" + identifierTypeString + "[ for source ETKDEPOT not found");
      } else {
         return keyDepotManager.getEtkSet(IdentifierType.CBE, identifierValue, applicationId, keystoreId, false);
      }
   }

   private static Crypto getCrypto(KeyStoreCredential credential) throws TechnicalConnectorException {
      Map<String, PrivateKey> hokPrivateKeys = KeyManager.getDecryptionKeys(credential.getKeyStore(), credential.getPassword());
      return CryptoFactory.getCrypto(credential, hokPrivateKeys);
   }
}
