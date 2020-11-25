package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.helper.PropertyHandler;
import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SingleMessageValidationXsd {
   private static final Logger LOG = Logger.getLogger(SingleMessageValidationXsd.class);
   private static final String module = "SingleMessageValidationXsd";
   private static final String SINGLEMESSAGE_XSD = "singlemessage.xsd";
   private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
   private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
   private static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";
   private URL xsdPath = null;
   private PropertyHandler propertyHandler;
   private String info = " ";

   public SingleMessageValidationXsd() {
      this.propertyHandler = new PropertyHandler("/smc.properties");
   }

   public SingleMessageValidationXsd(URL xsdPath) {
      this.xsdPath = xsdPath;
   }

   public void assertValidSingleMessage(InputStream xmlFile) throws SingleMessageValidationException {
      String xmlString = this.getString(xmlFile);
      this.assertValidSingleMessage(xmlString);
   }

   public void assertValidSingleMessage(String xmlDocument) throws SingleMessageValidationException {
      this.xsdValidate(xmlDocument);
   }

   public void assertValidSingleMessage(byte[] xmlDocument) throws SingleMessageValidationException {
      String procedure = "assertValidSingleMessage";
      LOG.debug("SingleMessageValidationXsd=-----= start " + procedure + " =$=" + this.info);

      try {
         this.xsdValidate(new String(xmlDocument, "UTF-8"));
      } catch (UnsupportedEncodingException var4) {
         LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + var4.getMessage(), var4);
      }

      LOG.debug("SingleMessageValidationXsd=-----= einde " + procedure + " =$=" + this.info);
   }

   public byte[] getBytes(InputStream inputStream) {
      String procedure = "getBytes";
      LOG.debug("SingleMessageValidationXsd=-----= start " + procedure + " =$=" + this.info);

      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = inputStream.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
         }

         LOG.debug("SingleMessageValidationXsd=-----= einde " + procedure + " =$=" + this.info);
         return baos.toByteArray();
      } catch (IOException var6) {
         LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + var6.getMessage(), var6);
         throw new IllegalArgumentException(var6);
      }
   }

   public String getString(InputStream inputStream) {
      String procedure = "getString";
      LOG.debug("SingleMessageValidationXsd=-----= start " + procedure + " =$=" + this.info);

      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = inputStream.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
         }

         LOG.debug("SingleMessageValidationXsd=-----= einde " + procedure + " =$=" + this.info);
         return baos.toString("UTF-8");
      } catch (IOException var6) {
         LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + var6.getMessage(), var6);
         throw new IllegalArgumentException(var6);
      }
   }

   private void xsdValidate(String xmlDocument) throws SingleMessageValidationException {
      String procedure = "xsdValidate";
      LOG.debug("SingleMessageValidationXsd=-----= start " + procedure + " =$=" + this.info);

      try {
         if (this.xsdPath == null && this.propertyHandler != null) {
            this.xsdPath = this.getClass().getResource(this.propertyHandler.getProperty("singlemessage.xsd"));
         }

         if (this.xsdPath == null) {
            LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + "error Property: " + "singlemessage.xsd");
            throw new RuntimeException("Property is not correctly set");
         }

         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         factory.setValidating(true);
         factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
         factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", new String[]{this.xsdPath.toString()});
         DocumentBuilder documentBuilder = factory.newDocumentBuilder();
         documentBuilder.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException arg0) throws SAXException {
               String procedure = "xsdValidate";
               SingleMessageValidationXsd.LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + SingleMessageValidationXsd.this.info + "error warning: ", arg0);
            }

            public void fatalError(SAXParseException arg0) throws SAXException {
               String procedure = "xsdValidate";
               SingleMessageValidationXsd.LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + SingleMessageValidationXsd.this.info + "error fatal: ", arg0);
               throw arg0;
            }

            public void error(SAXParseException arg0) throws SAXException {
               String procedure = "xsdValidate";
               SingleMessageValidationXsd.LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + SingleMessageValidationXsd.this.info + "error error: ", arg0);
               throw arg0;
            }
         });
         documentBuilder.parse(new ByteArrayInputStream(xmlDocument.getBytes("UTF-8")));
      } catch (Exception var5) {
         LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + "error SingleMessageValidationException: ", var5);
         throw new SingleMessageValidationException(var5);
      }

      LOG.debug("SingleMessageValidationXsd=-----= einde " + procedure + " =$=" + this.info);
   }

   public InputStream getResourceAsStream(String path) throws IOException {
      String procedure = "getResourceAsStream";
      File f = new File(path);
      if (f.exists()) {
         return new FileInputStream(f);
      } else {
         SingleMessageValidationXsd.class.getResource(path);
         InputStream stream = SingleMessageValidationXsd.class.getResourceAsStream(path);
         if (stream == null) {
            LOG.error("SingleMessageValidationXsd=-----= ----- " + procedure + " =$=" + this.info + "error Invalid resource: " + path);
            throw new IOException("Invalid resource " + path);
         } else {
            return stream;
         }
      }
   }
}
