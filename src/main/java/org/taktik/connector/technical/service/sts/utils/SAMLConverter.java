package org.taktik.connector.technical.service.sts.utils;

import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public final class SAMLConverter {
   public static Element toElement(String assertion) throws TechnicalConnectorException {
      try {
         InputStream sbis = new ByteArrayInputStream(assertion.getBytes(Charset.UTF_8.getName()));
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         DocumentBuilder db = factory.newDocumentBuilder();
         Document doc = db.parse(sbis);
         return doc.getDocumentElement();
      } catch (SAXException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, var5, new Object[0]);
      } catch (IOException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, var6, new Object[0]);
      } catch (ParserConfigurationException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, var7, new Object[0]);
      }
   }

   public static String toXMLString(Element element) throws TechnicalConnectorException {
      try {
         Source source = new DOMSource(element);
         StringWriter stringWriter = new StringWriter();
         Result result = new StreamResult(stringWriter);
         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer();
         transformer.transform(source, result);
         return stringWriter.getBuffer().toString();
      } catch (TransformerException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, var6, new Object[0]);
      }
   }

   public static Element convert(Source stsResponse) throws TechnicalConnectorException {
      try {
         StringWriter stringWriter = new StringWriter();
         Result result = new StreamResult(stringWriter);
         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer();
         transformer.transform(stsResponse, result);
         String xmlResponse = stringWriter.getBuffer().toString();
         return toElement(xmlResponse);
      } catch (TransformerException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.SAMLCONVERTER_ERROR, var6, new Object[0]);
      }
   }
}
