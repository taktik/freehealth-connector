package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.InstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public final class ConnectorXmlUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorXmlUtils.class);
   private static TransformerFactory trfactory = TransformerFactory.newInstance();

   public static DocumentBuilder getDocumentBuilder() {
      try {
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         return dbf.newDocumentBuilder();
      } catch (Exception var1) {
         throw new IllegalArgumentException(var1);
      }
   }

   private ConnectorXmlUtils() {
      throw new UnsupportedOperationException();
   }

   public static Element getFirstElementByTagNameNS(Element node, String namespaceURI, String localName) {
      NodeList nodeList = node.getElementsByTagNameNS(namespaceURI, localName);
      if (nodeList.getLength() == 0) {
         return null;
      } else {
         if (nodeList.getLength() > 1) {
            LOG.debug("{}:{} elements count: {} ,returning first one", new Object[]{namespaceURI, localName, nodeList.getLength()});
         }

         return (Element)nodeList.item(0);
      }
   }

   public static Element getFirstChildElement(Node node) {
      Node child;
      for(child = node.getFirstChild(); child != null && child.getNodeType() != 1; child = child.getNextSibling()) {
      }

      return child != null ? (Element)child : null;
   }

   public static void dump(Object obj) {
      if (LOG.isDebugEnabled()) {
         try {
            if (obj != null) {
               String xmlString = toString(obj);
               LOG.debug("Contents of " + obj.getClass().getCanonicalName() + "  : " + xmlString + "");
            }
         } catch (Exception var2) {
            LOG.error("Error occured while logging contents of object " + obj.getClass().getCanonicalName() + ". Reason: " + var2.getMessage());
         }
      }

   }

   public static byte[] toByteArray(Node node) {
      ByteArrayOutputStream out = null;

      try {
         Source source = new DOMSource(node);
         out = new ByteArrayOutputStream();
         Result result = new StreamResult(out);
         Transformer transformer = trfactory.newTransformer();
         transformer.transform(source, result);
         byte[] var5 = out.toByteArray();
         return var5;
      } catch (TransformerConfigurationException var10) {
         LOG.error(var10.getClass().getSimpleName() + ":" + var10.getMessage());
      } catch (TransformerException var11) {
         LOG.error(var11.getClass().getSimpleName() + ":" + var11.getMessage());
      } finally {
         ConnectorIOUtils.closeQuietly((Object)out);
      }

      return new byte[0];
   }

   public static byte[] toByteArray(Object obj) {
      MarshallerHelper marshallerHelper = new MarshallerHelper(obj.getClass(), obj.getClass());
      return marshallerHelper.toXMLByteArray(obj);
   }

   public static Document toDocument(byte[] data) throws TechnicalConnectorException {
      InputStream in = null;

      Document var2;
      try {
         in = new ByteArrayInputStream(data);
         var2 = getDocumentBuilder().parse(in);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[]{var6.getMessage()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)in);
      }

      return var2;
   }

   public static Document toDocument(Source source) throws TechnicalConnectorException {
      try {
         DOMResult result = new DOMResult();
         trfactory.newTransformer().transform(source, result);
         return result.getNode().getOwnerDocument();
      } catch (Exception var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var2, new Object[]{var2.getMessage()});
      }
   }

   public static Document toDocument(Object obj) {
      MarshallerHelper marshallerHelper = new MarshallerHelper(obj.getClass(), obj.getClass());
      return marshallerHelper.toDocument(obj);
   }

   public static Document toDocument(String xml) throws TechnicalConnectorException {
      try {
         return parseXmlFile(xml);
      } catch (Exception var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var2, new Object[]{var2.getMessage()});
      }
   }

   public static Element toElement(byte[] data) throws TechnicalConnectorException {
      return toDocument(data).getDocumentElement();
   }

   public static <T> T toObject(InputStream in, Class<T> clazz) {
      MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
      return helper.toObject(in);
   }

   public static <T> T toObject(byte[] in, Class<T> clazz) {
      MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
      return helper.toObject(in);
   }

   public static <T> T toObject(String xml, Class<T> clazz) {
      MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
      return helper.toObject(xml);
   }

   public static String toString(Object obj) {
      MarshallerHelper marshallerHelper = new MarshallerHelper(obj.getClass(), obj.getClass());
      return marshallerHelper.toString(obj);
   }

   public static String toString(Node node) throws TechnicalConnectorException {
      return toString((Source)(new DOMSource(node)));
   }

   public static String toString(Source source) throws TechnicalConnectorException {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      String var4;
      try {
         Transformer tf = trfactory.newTransformer();
         tf.setOutputProperty("omit-xml-declaration", "yes");
         Result result = new StreamResult(outputStream);
         tf.transform(source, result);
         var4 = new String(outputStream.toByteArray(), "UTF-8");
      } catch (Exception var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var8, new Object[]{var8.getMessage()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)outputStream);
      }

      return var4;
   }

   public static String flatten(String xml) {
      String result;
      for(result = xml.replaceAll("[\t\n\r]", ""); result.contains(" <"); result = result.replace(" <", "<")) {
      }

      return result;
   }

   public static String format(String unformattedXml) {
      return format(unformattedXml, (Source)null);
   }

   public static String format(String unformattedXml, Source xslt) {
      return xslt(new DOMSource(parseXmlFile(unformattedXml)), xslt);
   }

   public static String xslt(Source doc, Source xslt) {
      try {
         StringWriter writer = new StringWriter();
         StreamResult result = new StreamResult(writer);
         Transformer transformer = null;
         if (xslt != null) {
            transformer = trfactory.newTransformer(xslt);
         } else {
            transformer = trfactory.newTransformer();
         }

         LOG.debug("Transformer implementation {}", transformer.getClass());
         transformer.setOutputProperty("indent", "yes");
         transformer.setOutputProperty("omit-xml-declaration", "yes");
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(1));
         transformer.transform(doc, result);
         return writer.toString();
      } catch (Exception var5) {
         throw new InstantiationException(var5);
      }
   }

   private static Document parseXmlFile(String in) {
      try {
         InputSource is = new InputSource(new StringReader(in));
         return getDocumentBuilder().parse(is);
      } catch (Exception var2) {
         throw new InstantiationException(var2);
      }
   }
}
