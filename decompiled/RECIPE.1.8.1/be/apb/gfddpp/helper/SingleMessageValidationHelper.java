package be.apb.gfddpp.helper;

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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SingleMessageValidationHelper {
   private static final String SINGLEMESSAGE_XSD = "singlemessage.xsd";
   private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
   private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
   private static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";
   private URL xsdPath = null;
   private PropertyHandler propertyHandler;

   public SingleMessageValidationHelper() {
      this.propertyHandler = new PropertyHandler("/smc.properties");
   }

   public SingleMessageValidationHelper(URL xsdPath) {
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
      try {
         this.xsdValidate(new String(xmlDocument, "UTF-8"));
      } catch (UnsupportedEncodingException var3) {
      }

   }

   public byte[] getBytes(InputStream inputStream) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = inputStream.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
         }

         return baos.toByteArray();
      } catch (IOException var5) {
         throw new IllegalArgumentException(var5);
      }
   }

   public String getString(InputStream inputStream) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = inputStream.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
         }

         return baos.toString("UTF-8");
      } catch (IOException var5) {
         throw new IllegalArgumentException(var5);
      }
   }

   private void xsdValidate(String xmlDocument) throws SingleMessageValidationException {
      try {
         if (this.xsdPath == null && this.propertyHandler != null) {
            this.xsdPath = this.getClass().getResource(this.propertyHandler.getProperty("singlemessage.xsd"));
         }

         if (this.xsdPath == null) {
            throw new RuntimeException("Property is not correctly set");
         } else {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", new String[]{this.xsdPath.toString()});
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            documentBuilder.setErrorHandler(new ErrorHandler() {
               public void warning(SAXParseException arg0) throws SAXException {
               }

               public void fatalError(SAXParseException arg0) throws SAXException {
                  throw arg0;
               }

               public void error(SAXParseException arg0) throws SAXException {
                  throw arg0;
               }
            });
            documentBuilder.parse(new ByteArrayInputStream(xmlDocument.getBytes("UTF-8")));
         }
      } catch (Exception var4) {
         throw new SingleMessageValidationException(var4);
      }
   }

   public InputStream getResourceAsStream(String path) throws IOException {
      File f = new File(path);
      System.out.println(f.getAbsolutePath());
      if (f.exists()) {
         return new FileInputStream(f);
      } else {
         URL url = SingleMessageValidationHelper.class.getResource(path);
         InputStream stream = SingleMessageValidationHelper.class.getResourceAsStream(path);
         System.out.println(SingleMessageValidationHelper.class.getPackage().getName());
         if (stream == null) {
            throw new IOException("Invalid resource " + path);
         } else {
            return stream;
         }
      }
   }
}
