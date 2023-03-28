package org.taktik.connector.technical.handler;

import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.soap.MimeHeaders;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.freehealth.middleware.web.UserAgentInterceptorFilter;

public class UserAgentHandler extends AbstractSOAPHandler {
   private static final String USER_AGENT_HEADER_NAME = "User-Agent";
   private static final String FROM_HEADER_NAME = "From";
   private static final Logger LOG = LoggerFactory.getLogger(UserAgentHandler.class);
   private static Properties applicationProps = new Properties();
   private Configuration config = ConfigFactory.getConfigValidator();
   private static final String KEY_USER_AGENT = "org.taktik.connector.technical.handler.user-agent.value";
   private static final String DEFAULT_USER_AGENT = "Ehealth Technical";

   public boolean handleOutbound(SOAPMessageContext context) {
      if (context.getMessage() != null) {
         MimeHeaders mimeHeaders = context.getMessage().getMimeHeaders();
         if (mimeHeaders != null) {
            String[] agents = mimeHeaders.getHeader(USER_AGENT_HEADER_NAME);
            if (ArrayUtils.isNotEmpty(agents)) {
               LOG.info("Removing MIME header ["+USER_AGENT_HEADER_NAME+"] with value [" + StringUtils.join(agents, ",") + "]");
               mimeHeaders.removeHeader(USER_AGENT_HEADER_NAME);
            }

            String value = this.config.getProperty(KEY_USER_AGENT, DEFAULT_USER_AGENT);

            String fromRequest = UserAgentInterceptorFilter.Companion.getUserAgent();

            String combinedValue = fromRequest != null ? fromRequest + "/" + value : value;
            LOG.debug("Adding MIME header ["+USER_AGENT_HEADER_NAME+"] with value [" + combinedValue + "]");
            mimeHeaders.addHeader(USER_AGENT_HEADER_NAME, combinedValue);

            String fromValue = UserAgentInterceptorFilter.Companion.getXfrom();
            if (fromValue != null) {
               String[] froms = mimeHeaders.getHeader(FROM_HEADER_NAME);
               if (ArrayUtils.isNotEmpty(froms)) {
                  LOG.info("Removing MIME header ["+FROM_HEADER_NAME+"] with value [" + StringUtils.join(froms, ",") + "]");
                  mimeHeaders.removeHeader(FROM_HEADER_NAME);
               }

               LOG.debug("Adding MIME header ["+FROM_HEADER_NAME+"] with value [" + fromValue + "]");
               mimeHeaders.addHeader(FROM_HEADER_NAME, fromValue);
            }
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
