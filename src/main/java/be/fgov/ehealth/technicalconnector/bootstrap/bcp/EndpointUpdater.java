package be.fgov.ehealth.technicalconnector.bootstrap.bcp;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.parser.StatusPageParser;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.verifier.StatusPageSignatureVerifier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EndpointUpdater {
   private static final Logger LOG = LoggerFactory.getLogger(EndpointUpdater.class);
   public static final String PROP_BCP_STATUS_ENDPOINT = "org.taktik.technical.connector.bcp.status.endpoint";
   private static ConfigValidator config = ConfigFactory.getConfigValidator();
   private static String loadedSha2;
   private static String loadedSha2Location;
   private static String loadedXmlLocation;

   private EndpointUpdater() {
      throw new UnsupportedOperationException();
   }

   public static boolean update() throws TechnicalConnectorException {
      String endpoint = determineEndpoint();
      String onlineSha2 = ConnectorIOUtils.getResourceAsString(endpoint + ".sha2");
      if (!onlineSha2.equals(loadedSha2)) {
         String content = ConnectorIOUtils.getResourceAsString(endpoint + ".xml");
         update(content);
         write(content, loadedXmlLocation);
         write(onlineSha2, loadedSha2Location);
         loadedSha2 = onlineSha2;
         return true;
      } else {
         LOG.debug("No change detected");
         return false;
      }
   }

   public static boolean forceUpdate() throws TechnicalConnectorException {
      String endpoint = determineEndpoint();
      String onlineSha2 = ConnectorIOUtils.getResourceAsString(endpoint + ".sha2");
      String content = ConnectorIOUtils.getResourceAsString(endpoint + ".xml");
      update(content);
      write(content, loadedXmlLocation);
      write(onlineSha2, loadedSha2Location);
      loadedSha2 = onlineSha2;
      return true;
   }

   private static void write(String content, String location) {
      Validate.notEmpty(location);
      FileOutputStream fos = null;

      try {
         File output = new File(location);
         if (!output.exists() && !output.createNewFile()) {
            throw new IOException("Unable to create new file. [" + location + "]");
         }

         fos = new FileOutputStream(output);
         IOUtils.write(content.getBytes(Charset.UTF_8.getName()), fos);
      } catch (IOException var7) {
         LOG.error("Unable to write content to file [" + location + "]", var7);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   private static void update(String xml) throws TechnicalConnectorException {
      if (StatusPageSignatureVerifier.isValid(xml)) {
         EndpointDistributor.getInstance().update(StatusPageParser.parse(xml));
      } else {
         LOG.error("Unable to update endpoint. For more information see logs.");
      }

   }

   private static String determineEndpoint() {
      String env = config.getProperty("environment", "prd");
      String endpoint = config.getProperty("org.taktik.technical.connector.bcp.status.endpoint");
      if (StringUtils.isNotBlank(endpoint)) {
         return endpoint;
      } else if ("prd".equals(env)) {
         return "https://servicelist.ehealth.fgov.be/servicelist";
      } else if ("acc".equals(env)) {
         return "https://servicelist-acpt.ehealth.fgov.be/servicelist";
      } else if ("int".equals(env)) {
         return "https://bcp-int.ehealth.fgov.be/current_status";
      } else {
         throw new IllegalArgumentException("Unsupported Environment [" + env + "]");
      }
   }

   public static void reset() {
      delete(loadedSha2Location);
      delete(loadedXmlLocation);
      loadedSha2 = null;
      EndpointDistributor.getInstance().reset();
   }

   private static void delete(String location) {
      File file = new File(location);
      if (file.delete()) {
         LOG.debug("File [{}] deleted.", file.getName());
      } else {
         LOG.debug("Unable to delete [{}].", file.getName());
      }

   }

   static {
      try {
         loadedSha2Location = ConnectorIOUtils.getTempFileLocation(EndpointUpdater.class.getCanonicalName() + ".sha2");
         loadedXmlLocation = ConnectorIOUtils.getTempFileLocation(EndpointUpdater.class.getCanonicalName() + ".xml");
         loadedSha2 = ConnectorIOUtils.getResourceAsString(loadedSha2Location);
         update(ConnectorIOUtils.getResourceAsString(loadedXmlLocation));
      } catch (Exception var1) {
         LOG.error("Unable to load endpoints", var1);
      }

   }
}
