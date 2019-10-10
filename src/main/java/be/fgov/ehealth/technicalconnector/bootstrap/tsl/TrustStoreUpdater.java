package be.fgov.ehealth.technicalconnector.bootstrap.tsl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.DateUtils;
import be.fgov.ehealth.technicalconnector.bootstrap.tsl.parser.TrustServiceStatusListParser;
import be.fgov.ehealth.technicalconnector.bootstrap.tsl.signature.TrustServiceStatusListSignatureVerifier;
import be.fgov.ehealth.technicalconnector.bootstrap.utils.BootStrapUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TrustStoreUpdater {
   private static final String PROP_CA_STOREPWD = "CAKEYSTORE_PASSWORD";
   private static final String PROP_CA_STORELOCATION = "CAKEYSTORE_LOCATION";
   private static final String CA_QC = "http://uri.etsi.org/TrstSvc/Svctype/CA/QC";
   private static final String CA_PKC = "http://uri.etsi.org/TrstSvc/Svctype/CA/PKC";
   private static final String TSA = "http://uri.etsi.org/TrstSvc/Svctype/TSA";
   private static final String TSL = "http://uri.etsi.org/TrstSvd/Svctype/TLIssuer";
   private static final String NEXT_UPDATE_SUFFIX = "-nextUpdate";
   private static final Logger LOG = LoggerFactory.getLogger(TrustStoreUpdater.class);
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static Properties shaCache = new Properties();
   private static String shaCacheLocation;

   private TrustStoreUpdater() {
      throw new UnsupportedOperationException();
   }

   public static void launch() throws TechnicalConnectorException {
      init();
      BootStrapUtils.merge(location("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.location"), pwd("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.pwd"), location("CAKEYSTORE_LOCATION"), pwd("CAKEYSTORE_PASSWORD"));
      if (config.getBooleanProperty("be.fgov.ehealth.technicalconnector.tsupdater.ssl", true)) {
         update("ssl", determineEndpoint(TrustStoreUpdater.TrustedServiceType.TRANSPORT), location("truststore_location"), pwd("truststore_password"), "http://uri.etsi.org/TrstSvc/Svctype/CA/PKC", "http://uri.etsi.org/TrstSvc/Svctype/CA/QC");
         store();
      }
      if (config.getBooleanProperty("be.fgov.ehealth.technicalconnector.tsupdater.ca", true)) {
         update("ca", determineEndpoint(TrustStoreUpdater.TrustedServiceType.PERSON), location("CAKEYSTORE_LOCATION"), pwd("CAKEYSTORE_PASSWORD"), "http://uri.etsi.org/TrstSvc/Svctype/CA/PKC", "http://uri.etsi.org/TrstSvc/Svctype/CA/QC");
         store();
      }
      if (config.getBooleanProperty("be.fgov.ehealth.technicalconnector.tsupdater.tsl", true)) {
         update("tsl", determineEndpoint(TrustStoreUpdater.TrustedServiceType.APPLICATION), location("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.location"), pwd("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.pwd"), "http://uri.etsi.org/TrstSvc/Svctype/CA/PKC", "http://uri.etsi.org/TrstSvc/Svctype/CA/QC", "http://uri.etsi.org/TrstSvd/Svctype/TLIssuer");
         store();
      }
      if (config.getBooleanProperty("be.fgov.ehealth.technicalconnector.tsupdater.tsa", true)) {
         update("tsa", determineEndpoint(TrustStoreUpdater.TrustedServiceType.APPLICATION), location("timestamp.signature.keystore.path"), pwd("timestamp.signature.keystore.pwd"), "http://uri.etsi.org/TrstSvc/Svctype/CA/PKC", "http://uri.etsi.org/TrstSvc/Svctype/CA/QC", "http://uri.etsi.org/TrstSvc/Svctype/TSA");
         store();
      }
      BootStrapUtils.merge(location("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.location"), pwd("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.pwd"), location("CAKEYSTORE_LOCATION"), pwd("CAKEYSTORE_PASSWORD"));
      config.invalidate();
      config.reload();
   }

   private static String determineEndpoint(TrustStoreUpdater.TrustedServiceType type) {
      String env = config.getProperty("environment", "prd");
      String endpoint = config.getProperty(type.getKey());
      if (StringUtils.isNotBlank(endpoint)) {
         return endpoint;
      } else if ("prd".equals(env)) {
         return "https://tsl.ehealth.fgov.be/" + type.getPath();
      } else if ("acc".equals(env)) {
         return "https://tsl-acpt.ehealth.fgov.be/" + type.getPath();
      } else if ("int".equals(env)) {
         return "https://tsl-int.ehealth.fgov.be/" + type.getPath();
      } else {
         throw new IllegalArgumentException("Unsupported Environment [" + env + "]");
      }
   }

   private static void init() {
      InputStream is = null;

      try {
         shaCacheLocation = config.getProperty("truststoreupdater.local.cache", System.getProperty("java.io.tmpdir").replaceAll("[/\\\\]?$","") + File.separator + TrustStoreUpdater.class.getCanonicalName() + ".properties");
         is = ConnectorIOUtils.getResourceAsStream(shaCacheLocation);
         shaCache.load(is);
      } catch (Exception ex) {
         LOG.warn("Unable to load sha cache");
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }

   private static void store() {
      if (StringUtils.isNotEmpty(shaCacheLocation)) {
         FileOutputStream fos = null;

         try {
            fos = new FileOutputStream(new File(shaCacheLocation));
            shaCache.store(fos, "eHealth TSL cache");
         } catch (Exception var5) {
            LOG.error("Unable to store fingerprints to cache", var5);
         } finally {
            ConnectorIOUtils.closeQuietly((Object)fos);
         }
      }

   }

   private static String location(String key) throws TechnicalConnectorException {
      String location = config.getProperty(key);

      try {
         ConnectorIOUtils.getResourceAsStream(location);
         return location;
      } catch (TechnicalConnectorException ex) {
         LOG.error("Cannot access location location [" + location + "] Reason " + ExceptionUtils.getRootCauseMessage(ex));
         throw ex;
      }
   }

   private static char[] pwd(String key) {
      return config.getProperty(key).toCharArray();
   }

   private static void update(String type, String tslEndpoint, String storeLocation, char[] storepwd, String... serviceTypeIdentifiers) throws TechnicalConnectorException {
      String sha = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream(tslEndpoint + ".sha2"));
      String xml;
      if (shaCache.containsKey(type)) {
         xml = shaCache.getProperty(type);
         if (xml.equals(sha) && DateUtils.parseDateTime(shaCache.getProperty(type + "-nextUpdate")).isAfterNow()) {
            LOG.info("Truststore already up-to-date. Skipping TSL file [" + tslEndpoint + "]");
            return;
         }
      }

      xml = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream(tslEndpoint + ".xml"));
      if (TrustServiceStatusListSignatureVerifier.isValid(xml)) {
         TrustServiceStatusListParser parser = new TrustServiceStatusListParser();
         parser.parse(xml, serviceTypeIdentifiers);
         BootStrapUtils.writeKeyStore(parser.getTrustedList(), storeLocation, storepwd);
         if (!StringUtils.isEmpty(sha)) {
            shaCache.put(type, sha);
            shaCache.put(type + "-nextUpdate", DateUtils.printDateTime(parser.nextUpdate()));
         }
      } else {
         LOG.warn("Invalid TSL file on [" + tslEndpoint + "], skipping update");
      }

   }

   private static enum TrustedServiceType {
      APPLICATION("tsl-ehpbe-application", "endpoint.tsl.appl"),
      PERSON("tsl-ehpbe-person", "endpoint.tsl.person"),
      TRANSPORT("tsl-ehpbe-transport", "endpoint.tsl.transport");

      private String path;
      private String propKey;

      private TrustedServiceType(String path, String propKey) {
         this.path = path;
         this.propKey = propKey;
      }

      public String getPath() {
         return this.path;
      }

      public String getKey() {
         return this.propKey;
      }
   }
}
