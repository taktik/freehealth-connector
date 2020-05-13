package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.soap.MimeHeaders;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAgentHandler extends AbstractSOAPHandler {
   private static final String HEADER_NAME = "User-Agent";
   private static final Logger LOG = LoggerFactory.getLogger(UserAgentHandler.class);
   private static Properties applicationProps = new Properties();
   private Configuration config = ConfigFactory.getConfigValidator();
   private static final String KEY_USER_AGENT = "be.ehealth.technicalconnector.handler.user-agent.value";
   private static final String DEFAULT_USER_AGENT = "Ehealth Technical";

   public boolean handleOutbound(SOAPMessageContext context) {
      if (context.getMessage() != null) {
         MimeHeaders mimeHeaders = context.getMessage().getMimeHeaders();
         if (mimeHeaders != null) {
            String[] agents = mimeHeaders.getHeader("User-Agent");
            if (ArrayUtils.isNotEmpty(agents)) {
               LOG.info("Removing MIME header [{}] with value [{}]", "User-Agent", StringUtils.join(agents, ","));
               mimeHeaders.removeHeader("User-Agent");
            }

            String value = this.config.getProperty("be.ehealth.technicalconnector.handler.user-agent.value", "Ehealth Technical") + " (" + applicationProps.getProperty("application.version", "unknown") + ")";
            LOG.debug("Adding MIME header [{}] with value [{}]", "User-Agent", value);
            mimeHeaders.addHeader("User-Agent", value);
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
         applicationProps.load(is);
      } catch (TechnicalConnectorException var7) {
         LOG.error(var7.getMessage(), var7);
      } catch (IOException var8) {
         LOG.error(var8.getMessage(), var8);
      } catch (UnsupportedOperationException var9) {
         throw new IllegalArgumentException(var9);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }
}
