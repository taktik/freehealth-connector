package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.RetryNextEndpointException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class MessageLevelRetryHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(MessageLevelRetryHandler.class);
   private Configuration config = ConfigFactory.getConfigValidator();
   public static final String PROP_MESSAGELEVEL_RETRY_ACTIVATED = "be.ehealth.technicalconnector.handler.message.level.retry.activated";
   public static final String PROP_MESSAGELEVEL_RETRY_XSLT_LOCATION = "be.ehealth.technicalconnector.handler.message.level.retry.xslt.location";

   public boolean handleInbound(SOAPMessageContext context) throws RetryNextEndpointException {
      if (Boolean.TRUE.equals(this.config.getBooleanProperty("be.ehealth.technicalconnector.handler.message.level.retry.activated", Boolean.TRUE))) {
         try {
            Element body = ConnectorXmlUtils.getFirstChildElement(context.getMessage().getSOAPBody());
            String xsltLocation = this.config.getProperty("be.ehealth.technicalconnector.handler.message.level.retry.xslt.location", "/templates/bcp.switch.xslt");
            String xlstResult = ConnectorXmlUtils.xslt(new DOMSource(body), new StreamSource(ConnectorIOUtils.getResourceAsStream(xsltLocation)));
            if ("SWITCH".equals(xlstResult)) {
               LOG.info("Activating switch mechanism.");
               throw new RetryNextEndpointException(context);
            }
         } catch (RetryNextEndpointException var5) {
            throw var5;
         } catch (Exception var6) {
            LOG.error("Unable to determine retry, skipping logic", var6);
         }
      }

      return true;
   }
}
