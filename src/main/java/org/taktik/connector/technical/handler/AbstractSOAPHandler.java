package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.w3c.dom.Node;

public abstract class AbstractSOAPHandler implements SOAPHandler<SOAPMessageContext> {
   protected static final String IN = "IN";
   protected static final String OUT = "OUT";
   private static final int BLOCK = 1024;

   public boolean handleMessage(SOAPMessageContext context) {
      return (Boolean) context.get("javax.xml.ws.handler.message.outbound") ? this.handleOutbound(context) : this.handleInbound(context);
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      return true;
   }

   public boolean handleInbound(SOAPMessageContext context) {
      return true;
   }

   public boolean handleFault(SOAPMessageContext context) {
      return false;
   }

   public void close(MessageContext context) {
   }

   public Set<QName> getHeaders() {
      return new HashSet();
   }

   public static void dumpMessage(SOAPMessage msg, String mode, Logger log) {
      if (msg != null) {
         try {
            String content = ConnectorXmlUtils.toString((Node)msg.getSOAPPart().getEnvelope());
            int size = content.getBytes(Charset.UTF_8.getName()).length;
            if (content.getBytes().length < 1048576) {
               log.debug("[" + mode + "] - " + size + " bytes - " + content);
            } else {
               log.warn("[" + mode + "] - " + size + " bytes - " + "message to large to log");
            }
         } catch (Exception var5) {
            log.debug("Unable to dump message", var5);
         }

      }
   }
}
