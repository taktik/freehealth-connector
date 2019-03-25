package org.taktik.connector.business.intrahubcommons.security;

import org.apache.commons.lang3.ArrayUtils;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPBody;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
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
   private static final byte[] KMEHRMESSAGE_START_NODE = "<kmehrmessage xmlns=\"http://www.ehealth.fgov.be/standards/kmehr/schema/v1\" >".getBytes();
   private static final byte[] KMEHRMESSAGE_END_NODE = "</kmehrmessage>".getBytes();

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

            IntStream.range(0, folders.getLength()).mapToObj(folders::item).forEach((Node e) -> {
               Element folderElement = (Element) e;
               Element folder = base64EncryptedDataParentNode.getOwnerDocument().createElementNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "folder");
               NodeList folderChildren = folderElement.getChildNodes();
               IntStream.range(0, folderChildren.getLength()).mapToObj(folderChildren::item).forEach((Node fn) -> {
                  folder.appendChild(base64EncryptedDataParentNode.getOwnerDocument().importNode(fn, true));
               });
               NamedNodeMap m = folderElement.getAttributes();
               for (int ii = 0; ii < m.getLength(); ii++) {
                  Node attr = m.item(ii);
                  folder.setAttribute(attr.getNodeName(), attr.getNodeValue());
               }
               base64EncryptedDataParentNode.appendChild(folder);
            });
         } catch (SAXException var13) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SAX_EXCEPTION, "SAXException when decrypting the SOAP folder", var13);
         } catch (IOException var14) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, "IOException when decrypting the SOAP folder", var14);
         }
      } else if (folderNodes.getLength() == 0) {
         LOG.debug("No node with name Base64EncryptedDatafound to decrypt");
      } else if (folderNodes.getLength() > 1) {
         LOG.debug("More then one node with name Base64EncryptedDatafound to decrypt");
      }

   }

   private static NodeList getFolders(byte[] decryptedMessage) throws SAXException, IOException {
      if (decryptedMessage != null) {
         if (!(new String(decryptedMessage, StandardCharsets.UTF_8)).trim().startsWith("<?xml")) {
            byte[] kmehr = ArrayUtils.addAll(null, KMEHRMESSAGE_START_NODE);
            kmehr = ArrayUtils.addAll(kmehr, decryptedMessage);
            kmehr = ArrayUtils.addAll(kmehr, KMEHRMESSAGE_END_NODE);
            decryptedMessage = kmehr;
         }
         Document doc = builder.parse(new InputSource(new ByteArrayInputStream(decryptedMessage)));

         NodeList folder = doc.getElementsByTagNameNS("http://www.ehealth.fgov.be/standards/kmehr/schema/v1","folder");
         if (folder.getLength()==0) {
            folder = doc.getElementsByTagName("folder");
         }
         return folder;
      }
      return null;
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
