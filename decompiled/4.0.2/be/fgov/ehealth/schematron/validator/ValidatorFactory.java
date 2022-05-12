package be.fgov.ehealth.schematron.validator;

import be.fgov.ehealth.schematron.utils.Interim;
import be.fgov.ehealth.schematron.utils.XSLTURIFinder;
import be.fgov.ehealth.schematron.validator.impl.ValidatorImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

public final class ValidatorFactory {
   private static final String INCLUDE_PREPROCESSOR = "/iso_dsdl_include.xsl";
   private static final String ABSTRACT_PREPROCESSOR = "/iso_abstract_expand.xsl";
   private static final String PREPROCESSOR_MESSAGE_OLD = "/schematron-message1-6_XT.xsl";
   private static final String PREPROCESSOR_MESSAGE_XSLT1 = "/iso_schematron_message.xsl";
   private static final String PREPROCESSOR_MESSAGE_XSLT2 = "/iso_schematron_message_xslt2.xsl";
   private static final String PREPROCESSOR_SVRL_XSLT1 = "/iso_svrl_for_xslt1.xsl";
   private static final String PREPROCESSOR_SVRL_XSLT2 = "/iso_svrl_for_xslt2.xsl";
   private static final String PREPROCESSOR_SVRL_OLD = "/iso_svrl_1.6.xsl";
   private String thePreprocessor = "/iso_svrl_for_xslt1.xsl";
   private TransformerFactory factory = TransformerFactory.newInstance();
   private Class<?> resolver;
   private ErrorListener listener;
   private Hashtable<String, Object> parameters;
   private final Source abstract_preprocessor;
   private final Source include_preprocessor;
   private final Source preprocessor;
   private boolean debugMode;

   public ValidatorFactory() {
      this.listener = this.factory.getErrorListener();
      this.parameters = new Hashtable();
      this.debugMode = false;
      this.preprocessor = this.resolveDefaultPreprocessor();
      this.include_preprocessor = this.resolvePreprocessor("/iso_dsdl_include.xsl");
      this.abstract_preprocessor = this.resolvePreprocessor("/iso_abstract_expand.xsl");
   }

   public ValidatorFactory(Source preprocessor) throws IllegalArgumentException {
      this.listener = this.factory.getErrorListener();
      this.parameters = new Hashtable();
      this.debugMode = false;
      if (preprocessor == null) {
         throw new IllegalArgumentException("The preprocessor must not be null.");
      } else {
         this.preprocessor = preprocessor;
         this.include_preprocessor = this.resolvePreprocessor("/iso_dsdl_include.xsl");
         this.abstract_preprocessor = this.resolvePreprocessor("/iso_abstract_expand.xsl");
      }
   }

   public ValidatorFactory(String preprocessor, String formatter) throws IllegalArgumentException {
      this.listener = this.factory.getErrorListener();
      this.parameters = new Hashtable();
      this.debugMode = false;
      if (preprocessor == null) {
         throw new IllegalArgumentException("The preprocessor must not be null.");
      } else {
         if (!preprocessor.equalsIgnoreCase("xslt2") && !preprocessor.equalsIgnoreCase("xpath2")) {
            if (!preprocessor.equalsIgnoreCase("xslt") && !preprocessor.equalsIgnoreCase("xpath") && !preprocessor.equalsIgnoreCase("xslt1")) {
               if (!preprocessor.equalsIgnoreCase("1.5") && !preprocessor.equalsIgnoreCase("1.6") && !preprocessor.equalsIgnoreCase("old")) {
                  if (formatter.equalsIgnoreCase("message")) {
                     this.thePreprocessor = "/iso_schematron_message.xsl";
                  } else if (formatter.equalsIgnoreCase("terminate")) {
                     this.thePreprocessor = "/iso_svrl_for_xslt1.xsl";
                  } else {
                     this.thePreprocessor = "/iso_svrl_for_xslt1.xsl";
                  }
               } else if (formatter.equalsIgnoreCase("message")) {
                  this.thePreprocessor = "/schematron-message1-6_XT.xsl";
               } else if (formatter.equalsIgnoreCase("message")) {
                  this.thePreprocessor = "/iso_svrl_1.6.xsl";
               } else {
                  this.thePreprocessor = "/iso_svrl_1.6.xsl";
               }
            } else if (formatter.equalsIgnoreCase("message")) {
               this.thePreprocessor = "/iso_schematron_message.xsl";
            } else if (formatter.equalsIgnoreCase("message")) {
               this.thePreprocessor = "/iso_svrl_for_xslt1.xsl";
            } else {
               this.thePreprocessor = "/iso_svrl_for_xslt1.xsl";
            }
         } else if (formatter.equalsIgnoreCase("message")) {
            this.thePreprocessor = "/iso_schematron_message_xslt2.xsl";
         } else if (formatter.equalsIgnoreCase("message")) {
            this.thePreprocessor = "/iso_svrl_for_xslt2.xsl";
         } else {
            this.thePreprocessor = "/iso_svrl_for_xslt2.xsl";
         }

         this.preprocessor = this.resolvePreprocessor();
         this.include_preprocessor = this.resolvePreprocessor("/iso_dsdl_include.xsl");
         this.abstract_preprocessor = this.resolvePreprocessor("/iso_abstract_expand.xsl");
      }
   }

   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
      if (listener == null) {
         throw new IllegalArgumentException("The error listener must not be null.");
      } else {
         this.listener = listener;
      }
   }

   public ErrorListener getErrorListener() {
      return this.listener;
   }

   public void setParameter(String name, Object value) {
      this.parameters.put(name, value);
   }

   public Object getParameter(String name) {
      return this.parameters.get(name);
   }

   public void setDebugMode(boolean debugMode) {
      this.debugMode = debugMode;
   }

   public void setResolver(Class<?> theResolver) {
      this.resolver = theResolver;
   }

   private Source resolveDefaultPreprocessor() throws TransformerFactoryConfigurationError {
      return this.resolvePreprocessor("/iso_svrl_for_xslt1.xsl");
   }

   private Source resolvePreprocessor() throws TransformerFactoryConfigurationError {
      return this.resolvePreprocessor(this.thePreprocessor);
   }

   private Source resolvePreprocessor(String stylesheet) throws TransformerFactoryConfigurationError {
      InputStream is = ValidatorFactory.class.getResourceAsStream(stylesheet);
      if (is == null) {
         throw new TransformerFactoryConfigurationError("preprocessor '" + stylesheet + "' cannot be found in the classpath.");
      } else {
         return new StreamSource(is);
      }
   }

   public Validator newValidator(Source schema) throws TransformerException, IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
      Transformer include_transformer = this.factory.newTransformer(this.include_preprocessor);
      Transformer abstract_transformer = this.factory.newTransformer(this.abstract_preprocessor);
      this.factory.setURIResolver(new XSLTURIFinder());
      Transformer transformer = this.factory.newTransformer(this.preprocessor);
      include_transformer.setErrorListener(this.listener);
      abstract_transformer.setErrorListener(this.listener);
      transformer.setErrorListener(this.listener);
      if (!this.parameters.isEmpty()) {
         Enumeration<String> names = this.parameters.keys();

         while(names.hasMoreElements()) {
            String name = (String)names.nextElement();
            transformer.setParameter(name, this.parameters.get(name));
            abstract_transformer.setParameter(name, this.parameters.get(name));
            include_transformer.setParameter(name, this.parameters.get(name));
         }
      }

      if (!this.factory.getFeature("http://javax.xml.transform.stream.StreamResult/feature")) {
         throw new TransformerConfigurationException("The XSLT processor must support following feature: http://javax.xml.transform.stream.StreamResult/feature");
      } else {
         Interim interim = new Interim(schema.getSystemId());
         DOMResult r1 = new DOMResult();
         include_transformer.transform(schema, r1);
         DOMResult r2 = new DOMResult();
         DOMSource s2 = new DOMSource(r1.getNode());
         abstract_transformer.transform(s2, r2);
         DOMSource s3 = new DOMSource(r2.getNode());
         transformer.transform(s3, interim.makeEmptyResult());
         if (this.debugMode) {
            interim.saveAs(File.createTempFile("debug", ".xslt"));
         }

         if (this.resolver != null) {
            this.factory.setURIResolver((URIResolver)this.resolver.newInstance());
         }

         Templates validator = this.factory.newTemplates(interim.getSource());
         return new ValidatorImpl(validator);
      }
   }
}
