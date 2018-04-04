package be.ehealth.business.intrahubcommons.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPBody;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

final class FolderDecryptor {
   private static final Logger LOG = LoggerFactory.getLogger(FolderDecryptor.class);
   private static final String KMEHR_NAMESPACE = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1";
   private static final String FOLDER = "folder";
   private static final String BASE64_ENCRYPTED_DATA = "Base64EncryptedData";
   private static final String BASE64_ENCRYPTED_VALUE = "Base64EncryptedValue";
   private static final DocumentBuilder builder;

   public static void decryptFolder(SOAPBody soapBody, Crypto crypto) throws TechnicalConnectorException {
      NodeList folderNodes = soapBody.getElementsByTagNameNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Base64EncryptedData");
      if (folderNodes.getLength() == 1) {
         Node base64EncryptedDataNode = folderNodes.item(0);
         Node base64EncryptedDataParentNode = base64EncryptedDataNode.getParentNode();

         try {
            NodeList encryptedContent = ((Element)base64EncryptedDataNode).getElementsByTagNameNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Base64EncryptedValue");
            if (encryptedContent.getLength() == 0 || encryptedContent.getLength() > 1) {
               LOG.debug("Base64EncryptedValue is not a valid content. Nothing to decrypt.");
               return;
            }

            String encryptedData = encryptedContent.item(0).getTextContent();
            byte[] b64decryptedData = Base64.decode(encryptedData.getBytes());
            byte[] decryptedMessage = crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, b64decryptedData).getContentAsByte();
            base64EncryptedDataParentNode.removeChild(base64EncryptedDataNode);
            ConnectorXmlUtils.dump(decryptedMessage);
            NodeList folders = getFolders(decryptedMessage);

            for(int i = 0; i < folders.getLength(); ++i) {
               Element folderElement = (Element)folders.item(i);
               Node folder = base64EncryptedDataParentNode.getOwnerDocument().importNode(folderElement, true);
               base64EncryptedDataParentNode.appendChild(folder);
            }
         } catch (SAXException var13) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SAX_EXCEPTION, new Object[]{"SAXException when decrypting the SOAP folder", var13});
         } catch (IOException var14) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, new Object[]{"IOException when decrypting the SOAP folder", var14});
         }
      } else if (folderNodes.getLength() == 0) {
         LOG.debug("No node with name Base64EncryptedDatafound to decrypt");
      } else if (folderNodes.getLength() > 1) {
         LOG.debug("More then one node with name Base64EncryptedDatafound to decrypt");
      }

   }

   private static NodeList getFolders(byte[] decryptedMessage) throws SAXException, IOException {
      Document doc = builder.parse(new InputSource(new ByteArrayInputStream(decryptedMessage)));
      return doc.getElementsByTagNameNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "folder");
   }

   static {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         builder = factory.newDocumentBuilder();
      } catch (ParserConfigurationException var1) {
         throw new RuntimeException("Unable to instaniate a Documentbuilder", var1);
      }
   }
}
