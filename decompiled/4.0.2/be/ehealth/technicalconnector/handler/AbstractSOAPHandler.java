package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
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

   public AbstractSOAPHandler() {
   }

   public boolean handleMessage(SOAPMessageContext context) {
      return Boolean.TRUE.equals(context.get("javax.xml.ws.handler.message.outbound")) ? this.handleOutbound(context) : this.handleInbound(context);
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
               log.debug("[{}] - {} bytes - {}", new Object[]{mode, size, content});
            } else {
               log.warn("[{}] - {} bytes - message to large to log", mode, size);
            }
         } catch (Exception var5) {
            log.debug("Unable to dump message", var5);
         }

      }
   }
}
