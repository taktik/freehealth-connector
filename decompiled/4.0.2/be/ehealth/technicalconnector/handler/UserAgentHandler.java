package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAgentHandler extends AbstractMimeHeaderManipulator {
   private static final Logger LOG = LoggerFactory.getLogger(UserAgentHandler.class);
   private static final String HEADER_NAME = "User-Agent";
   private static final String PROP_PARTS_REGEX = "be.ehealth.technicalconnector.handler.mimeheaders.user-agent.parts-regex";
   private static final String DEFAULT_PARTS_REGEX = "[a-zA-Z0-9]*\\/[0-9a-zA-Z-_.]*";
   private static final String KEY_USER_AGENT = "be.ehealth.technicalconnector.handler.mimeheaders.user-agent.prefix";
   private static final String CONNECTOR_USER_AGENT_PART_1 = "eHealthTechnical/";
   private static String CONNECTOR_USER_AGENT;
   private final Configuration config = ConfigFactory.getConfigValidator();
   private Pattern PREFIX_REGEX = Pattern.compile(System.getProperty("be.ehealth.technicalconnector.handler.mimeheaders.user-agent.parts-regex", "[a-zA-Z0-9]*\\/[0-9a-zA-Z-_.]*"));

   public UserAgentHandler() {
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      this.addToHeader(context, "User-Agent", new String[]{this.getUserAgent()});
      return true;
   }

   private String getUserAgent() {
      StringBuilder sb = new StringBuilder();
      if (this.config.hasProperty("be.ehealth.technicalconnector.handler.mimeheaders.user-agent.prefix")) {
         String prefix = this.config.getProperty("be.ehealth.technicalconnector.handler.mimeheaders.user-agent.prefix");
         if (this.validPrefix(prefix)) {
            sb.append(prefix);
            sb.append(" ");
         } else {
            LOG.error("Invalid user-agent prefix [{}], skipping.", prefix);
         }
      }

      sb.append(CONNECTOR_USER_AGENT);
      return sb.toString();
   }

   private boolean validPrefix(String prefix) {
      String[] softwareVersionParts = prefix.split(" ");
      String[] var3 = softwareVersionParts;
      int var4 = softwareVersionParts.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String softwareVersionPart = var3[var5];
         if (!this.PREFIX_REGEX.matcher(softwareVersionPart).matches()) {
            return false;
         }
      }

      return true;
   }

   public boolean handleFault(SOAPMessageContext context) {
      return true;
   }

   static {
      InputStream is = null;

      try {
         is = ConnectorIOUtils.getResourceAsStream("/application.properties");
         Properties applicationProps = new Properties();
         applicationProps.load(is);
         CONNECTOR_USER_AGENT = "eHealthTechnical/" + applicationProps.getProperty("application.version", "unknown");
      } catch (Exception var5) {
         CONNECTOR_USER_AGENT = "eHealthTechnical/unknown";
         LOG.error("Unable to determine version using [" + CONNECTOR_USER_AGENT + "]", var5);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }
}
