package be.business.connector.core.handlers;

import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

public class IdentityHandler implements SOAPHandler<SOAPMessageContext> {
   private static final Logger LOG = Logger.getLogger(IdentityHandler.class);
   private static String insurability;
   private static String messageId;

   public Set<QName> getHeaders() {
      return null;
   }

   public void close(MessageContext arg0) {
   }

   public boolean handleFault(SOAPMessageContext c) {
      this.handleMessage(c);
      return true;
   }

   public boolean handleMessage(SOAPMessageContext smc) {
      try {
         Boolean outboundProperty = (Boolean)smc.get("javax.xml.ws.handler.message.outbound");
         if (outboundProperty) {
            SOAPMessage message = smc.getMessage();

            try {
               SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
               SOAPHeader header = envelope.addHeader();
               SOAPElement username = header.addChildElement("Username", "wsse");
               username.addTextNode("username");
               SOAPElement password = header.addChildElement("Password", "wsse");
               password.addTextNode("test321");
               message.saveChanges();
               message.writeTo(System.out);
               System.out.println("");
            } catch (Throwable var8) {
               LOG.warn("SOAPException when retrieving insurability the message", var8);
            }
         }
      } catch (Throwable var9) {
         LOG.warn("SOAPException when retrieving insurability the message", var9);
      }

      return true;
   }

   public static void setMessageId(String messageId) {
      IdentityHandler.messageId = messageId;
   }

   public static String getMessageId() {
      return messageId;
   }
}
