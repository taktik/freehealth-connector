package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import java.util.Iterator;
import java.util.List;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SOAPHeaderLoggerHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SOAPHeaderLoggerHandler.class);
   private static final String PROP_HEADER_LOGGER = "be.ehealth.technicalconnector.handler.SOAPHeaderLoggerHandler.";
   private List<String> propList = ConfigFactory.getConfigValidator().getMatchingProperties("be.ehealth.technicalconnector.handler.SOAPHeaderLoggerHandler.");

   public boolean handleMessage(SOAPMessageContext ctx) {
      try {
         SOAPHeader header = ctx.getMessage().getSOAPHeader();
         if (header != null) {
            Iterator it = ctx.getMessage().getSOAPHeader().examineAllHeaderElements();

            while(it.hasNext()) {
               this.analyse(it.next());
            }
         }
      } catch (SOAPException var4) {
         LOG.error("SOAPException: {}", var4.getMessage(), var4);
      } catch (TechnicalConnectorException var5) {
         LOG.error("TechnicalConnectorException: {}", var5.getMessage(), var5);
      }

      return true;
   }

   private void analyse(Object obj) throws TechnicalConnectorException {
      if (obj instanceof Element) {
         Element el = (Element)obj;
         String nameValue = "{" + el.getNamespaceURI() + "}" + el.getLocalName();
         if (this.propList.contains(nameValue) && LOG.isInfoEnabled()) {
            LOG.info(ConnectorXmlUtils.toString((Node)el));
         }
      } else {
         LOG.error("Unsupported Object with name: [{}]", obj.getClass().getName());
      }

   }

   public boolean handleFault(SOAPMessageContext ctx) {
      return this.handleMessage(ctx);
   }
}
