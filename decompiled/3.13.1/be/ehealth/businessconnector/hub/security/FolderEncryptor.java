package be.ehealth.businessconnector.hub.security;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.utils.IdentifierType;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class FolderEncryptor {
   private static final String PROP_HUBID = "hub.id";
   private static final String PROP_HUBAPPID = "hub.application";
   private static List<String> expectedProps = new ArrayList();
   private static Configuration config;
   private static final String KMERH_NAMESPACE = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1";
   private static final String FOLDER_NODE = "folder";
   private static final String BASE64_ENCRYPTED_DATA = "Base64EncryptedData";
   private static final Logger LOG = LoggerFactory.getLogger(FolderEncryptor.class.getName());

   public static Document encryptFolder(Document doc, Crypto crypto) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      Node kmerhmessage = null;
      NodeList folderNodes = doc.getElementsByTagNameNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "folder");
      if (folderNodes.getLength() > 0) {
         kmerhmessage = folderNodes.item(0).getParentNode();

         try {
            String folders = serializeFolders(folderNodes);
            String encryptedMessageString = sealFolders(crypto, folders);
            removeNodes(kmerhmessage, folderNodes);
            kmerhmessage.appendChild(createBase64EncryptedData(doc, encryptedMessageString));
         } catch (UnsupportedEncodingException var6) {
            LOG.error("ETKException when encrypting the SOAP folder", var6);
         } catch (TransformerException var7) {
            LOG.error("TransformerException when encrypting the SOAP folder", var7);
         }
      } else {
         LOG.info("No folders present in document.");
      }

      return doc;
   }

   private static String serializeFolders(NodeList folderNodes) throws TransformerException {
      StringBuffer sb = new StringBuffer();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer serializer = tf.newTransformer();
      serializer.setOutputProperty("omit-xml-declaration", "yes");

      for(int i = 0; i < folderNodes.getLength(); ++i) {
         StringWriter sw = new StringWriter();
         serializer.transform(new DOMSource(folderNodes.item(i)), new StreamResult(sw));
         sb.append(sw.toString());
      }

      return sb.toString();
   }

   private static Element createBase64EncryptedData(Document doc, String encryptedMessageString) {
      Element base64EncryptedData = doc.createElementNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Base64EncryptedData");
      Element cd = doc.createElementNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "cd");
      cd.setAttribute("SV", "1.0");
      cd.setAttribute("S", "CD-ENCRYPTION-METHOD");
      cd.setTextContent("CMS");
      base64EncryptedData.appendChild(cd);
      Element base64EncryptedValue = doc.createElementNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Base64EncryptedValue");
      base64EncryptedValue.setTextContent(encryptedMessageString);
      base64EncryptedData.appendChild(base64EncryptedValue);
      return base64EncryptedData;
   }

   private static void removeNodes(Node kmerhmessage, NodeList folderNodes) {
      int folderCount = folderNodes.getLength();

      for(int i = 0; i < folderCount; ++i) {
         kmerhmessage.removeChild(folderNodes.item(0));
      }

   }

   private static String sealFolders(Crypto crypto, String folders) throws TechnicalConnectorException, UnsupportedEncodingException {
      byte[] encryptedMessage = crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, getHubEtk(), folders.getBytes(Charset.UTF_8.getName()));
      encryptedMessage = Base64.encode(encryptedMessage);
      return new String(encryptedMessage);
   }

   private static EncryptionToken getHubEtk() throws TechnicalConnectorException {
      Long hubId = config.getLongProperty("hub.id", (Long)null);
      String hubApplication = config.getProperty("hub.application");
      if (hubApplication == null) {
         hubApplication = "";
      }

      return KeyDepotManagerFactory.getKeyDepotManager().getEtk(IdentifierType.EHP, hubId, hubApplication);
   }

   static {
      expectedProps.add("hub.id");
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
