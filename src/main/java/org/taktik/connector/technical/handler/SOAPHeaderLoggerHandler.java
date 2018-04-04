package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import java.util.Iterator;
import java.util.List;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class SOAPHeaderLoggerHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SOAPHeaderLoggerHandler.class);
   private static final String PROP_HEADER_LOGGER = "org.taktik.connector.technical.handler.SOAPHeaderLoggerHandler.";
   private List<String> propList = ConfigFactory.getConfigValidator().getMatchingProperties("org.taktik.connector.technical.handler.SOAPHeaderLoggerHandler.");

   public boolean handleMessage(SOAPMessageContext ctx) {
      try {
         SOAPHeader header = ctx.getMessage().getSOAPHeader();
         if (header != null) {
            Iterator it = ctx.getMessage().getSOAPHeader().examineAllHeaderElements();

            while(it.hasNext()) {
               Object obj = it.next();
               if (obj instanceof Element) {
                  Element el = (Element)obj;
                  String nameValue = "{" + el.getNamespaceURI() + "}" + el.getLocalName();
                  if (this.propList.contains(nameValue)) {
                     LOG.info(ConnectorXmlUtils.toString((Source)(new DOMSource(el))));
                  }
               } else {
                  LOG.error("Unsupported Object with name: [" + obj.getClass().getName() + "]");
               }
            }
         }
      } catch (SOAPException var7) {
         LOG.error("SOAPException: " + var7.getMessage(), var7);
      } catch (TechnicalConnectorException var8) {
         LOG.error("TechnicalConnectorException: " + var8.getMessage(), var8);
      }

      return true;
   }

   public boolean handleFault(SOAPMessageContext ctx) {
      return this.handleMessage(ctx);
   }
}
