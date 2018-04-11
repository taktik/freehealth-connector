package org.taktik.connector.technical.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.SchemaValidatorHandler;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.impl.handler.ErrorCollectorHandler;
import org.taktik.connector.technical.validator.impl.handler.ForkContentHandler;
import org.taktik.connector.technical.validator.impl.handler.XOPValidationHandler;
import com.gc.iotools.stream.is.InputStreamFromOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.ValidatorHandler;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;

public final class ValidatorHelper {
   private static final Logger LOG = LoggerFactory.getLogger(ValidatorHelper.class);
   private static final SAXParserFactory SAF = SAXParserFactory.newInstance();
   private static final TransformerFactory TRF = TransformerFactory.newInstance();

   private ValidatorHelper() {
      throw new UnsupportedOperationException();
   }

   public static void validate(Source source, boolean xop, String... schemaLocations) throws TechnicalConnectorException {
      try {
         XOPValidationHandler handler = new XOPValidationHandler(xop);
         ValidatorHandler validator = createValidatorForSchemaFiles(schemaLocations);
         ErrorCollectorHandler collector = new ErrorCollectorHandler(handler);
         validator.setErrorHandler(collector);
         SAXParser parser = SAF.newSAXParser();
         parser.parse(convert(source), new ForkContentHandler(new ContentHandler[]{handler, validator}));
         handleValidationResult(collector);
      } catch (Exception var7) {
         throw handleException(var7);
      }
   }

   public static void validate(Source source, String... schemaLocations) throws TechnicalConnectorException {
      validate(source, false, schemaLocations);
   }

   public static void validate(Object jaxbObj, String rootSchemaFileLocation) throws TechnicalConnectorException {
      validate(jaxbObj, jaxbObj.getClass(), rootSchemaFileLocation);
   }

   public static void validate(Object jaxbObj, Class xmlClass, String rootSchemaFileLocation) throws TechnicalConnectorException {
      if (jaxbObj == null) {
         LOG.error("Message is null");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, "Message is null");
      } else {
         ConnectorXmlUtils.dump(jaxbObj);
         LOG.debug("Validating with schema [" + rootSchemaFileLocation + "]");

         try {
            JAXBContext jaxbContext = JaxbContextFactory.getJaxbContextForClass(xmlClass);
            JAXBSource payload = new JAXBSource(jaxbContext, jaxbObj);
            validate((Source)payload, rootSchemaFileLocation);
         } catch (Exception var5) {
            throw handleException(var5);
         }

         LOG.debug("Message is valid.");
      }
   }

   private static TechnicalConnectorException handleException(Exception e) {
      if (e instanceof TechnicalConnectorException) {
         return (TechnicalConnectorException)e;
      } else {
         LOG.error("Unable to validate object.", e);
         return new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, e, new Object[]{e.getMessage()});
      }
   }

   protected static ValidatorHandler createValidatorForSchemaFiles(String... schemaFiles) throws TechnicalConnectorException {
      SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

      try {
         Source[] sources = new Source[0];

         for(int i = 0; i < schemaFiles.length; ++i) {
            String schemaFile = schemaFiles[i];
            InputStream in = SchemaValidatorHandler.class.getResourceAsStream(schemaFile);
            if (in == null) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{"Unable to find schemaFile " + schemaFile});
            }

            if (schemaFiles.length == 1) {
               sources = (Source[])((Source[])ArrayUtils.add(sources, new StreamSource(SchemaValidatorHandler.class.getResource(schemaFile).toExternalForm())));
            } else {
               Source source = new StreamSource(in);
               sources = (Source[])((Source[])ArrayUtils.add(sources, source));
            }
         }

         return schemaFactory.newSchema(sources).newValidatorHandler();
      } catch (Exception var7) {
         throw handleException(var7);
      }
   }

   private static void handleValidationResult(ErrorCollectorHandler collector) throws TechnicalConnectorException {
      if (collector.hasExceptions("WARN")) {
         List<String> validationWarning = collector.getExceptionList("WARN");
         Iterator i$ = validationWarning.iterator();

         while(i$.hasNext()) {
            String exception = (String)i$.next();
            LOG.warn(exception);
         }
      }

      if (collector.hasExceptions("ERROR", "FATAL")) {
         StringBuilder sb = new StringBuilder();
         List<String> validationErrors = collector.getExceptionList("ERROR", "FATAL");
         Iterator i$ = validationErrors.iterator();

         while(i$.hasNext()) {
            String exception = (String)i$.next();
            LOG.error(exception);
            sb.append(exception);
            sb.append(", ");
         }

         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{sb.toString()});
      }
   }

   private static InputStream convert(final Source source) {
      try {
         InputStreamFromOutputStream<Void> isOs = new InputStreamFromOutputStream<Void>() {
            protected Void produce(OutputStream sink) throws Exception {
               Result result = new StreamResult(sink);
               Transformer transformer = ValidatorHelper.TRF.newTransformer();
               transformer.setOutputProperty("omit-xml-declaration", "yes");
               transformer.transform(source, result);
               return null;
            }
         };
         return isOs;
      } catch (Exception var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   static {
      SAF.setNamespaceAware(true);
   }
}
