package be.fgov.ehealth.schematron;

import be.fgov.ehealth.schematron.domain.SchematronConfig;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import be.fgov.ehealth.schematron.impl.SchematronSessionImpl;

public final class SchematronSessionFactory {
   public static final String JAVAX_XML_TRANSFORM_TRANSFORMER_FACTORY = "javax.xml.transform.TransformerFactory";
   public static final String SAXON_TRANSFORMER_FACTORY = "net.sf.saxon.TransformerFactoryImpl";
   private static String currentTransformerFactory;

   public SchematronSessionFactory() {
   }

   public static void initSaxon() throws InitialisationException {
      String saxonTransformerFactory = "net.sf.saxon.TransformerFactoryImpl";

      try {
         Class.forName(saxonTransformerFactory, false, SchematronSessionFactory.class.getClassLoader());
         currentTransformerFactory = System.getProperty("javax.xml.transform.TransformerFactory");
         System.setProperty("javax.xml.transform.TransformerFactory", saxonTransformerFactory);
      } catch (ClassNotFoundException var2) {
         throw new InitialisationException(saxonTransformerFactory + " not available on classpath.");
      }
   }

   public static void stopSaxon() throws InitialisationException {
      if (currentTransformerFactory == null) {
         System.clearProperty("javax.xml.transform.TransformerFactory");
      } else {
         System.setProperty("javax.xml.transform.TransformerFactory", currentTransformerFactory);
      }

   }

   public static SchematronSession newInstance(SchematronConfig config) {
      return new SchematronSessionImpl(config);
   }
}
