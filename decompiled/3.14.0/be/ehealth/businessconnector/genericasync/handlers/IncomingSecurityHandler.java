package be.ehealth.businessconnector.genericasync.handlers;

import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.handler.AbstractSOAPHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.WSSConfig;
import org.apache.wss4j.dom.WSSecurityEngine;
import org.apache.wss4j.dom.WSSecurityEngineResult;
import org.apache.wss4j.dom.handler.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class IncomingSecurityHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(IncomingSecurityHandler.class);
   private static final QName WSSE = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse");
   private static final Set<QName> QNAME_LIST = new HashSet();
   private WSSConfig config;

   /** @deprecated */
   @Deprecated
   private IncomingSecurityHandler() {
      this.config = WSSConfig.getNewInstance();
   }

   public IncomingSecurityHandler(Duration timestampTTL, Duration timeStampFutureTTL) {
      this();
      this.config.setTimeStampTTL((int)timestampTTL.convert(TimeUnit.SECONDS));
      this.config.setTimeStampFutureTTL((int)timeStampFutureTTL.convert(TimeUnit.SECONDS));
   }

   public boolean handleInbound(SOAPMessageContext context) {
      SOAPMessage message = context.getMessage();
      WSSecurityEngine secEngine = new WSSecurityEngine();
      RequestData requestData = new RequestData();
      requestData.setWssConfig(this.config);

      try {
         SOAPHeader header = message.getSOAPHeader();
         if (header != null) {
            NodeList list = header.getElementsByTagNameNS(WSSE.getNamespaceURI(), WSSE.getLocalPart());
            if (list != null) {
               LOG.debug("Verify WS Security Header");

               for(int j = 0; j < list.getLength(); ++j) {
                  List<WSSecurityEngineResult> results = secEngine.processSecurityHeader((Element)list.item(j), requestData);
                  Iterator i$ = results.iterator();

                  while(i$.hasNext()) {
                     WSSecurityEngineResult result = (WSSecurityEngineResult)i$.next();
                     if (!((Boolean)result.get("validated-token"))) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("Unable to validate incoming soap message. Action [");
                        sb.append(result.get("action"));
                        sb.append("].");
                        throw new ProtocolException(sb.toString());
                     }
                  }
               }
            }
         }

         return true;
      } catch (WSSecurityException var12) {
         throw new ProtocolException(var12);
      } catch (SOAPException var13) {
         throw new ProtocolException(var13);
      }
   }

   public Set<QName> getHeaders() {
      return QNAME_LIST;
   }

   static {
      QNAME_LIST.add(WSSE);
   }
}
