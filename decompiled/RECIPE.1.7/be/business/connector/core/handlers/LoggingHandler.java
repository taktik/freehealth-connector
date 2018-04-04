package org.taktik.connector.business.recipeprojects.core.handlers;

import org.taktik.connector.business.recipeprojects.core.utils.MessageDumper;
import java.io.ByteArrayOutputStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
   private static final Logger LOG = Logger.getLogger(LoggingHandler.class);

   public Set<QName> getHeaders() {
      return null;
   }

   public void close(MessageContext arg0) {
   }

   public boolean handleFault(SOAPMessageContext c) {
      this.handleMessage(c);
      return true;
   }

   public boolean handleMessage(SOAPMessageContext c) {
      SOAPMessage msg = c.getMessage();

      try {
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         msg.writeTo(out);
         LOG.debug(out.size() + " bytes - " + out.toString());
         if (MessageDumper.getInstance().isDumpEnabled()) {
            Boolean outboundProperty = (Boolean)c.get("javax.xml.ws.handler.message.outbound");
            if (outboundProperty.booleanValue()) {
               MessageDumper.getInstance().dump(out, MessageDumper.getOperationName(c), "OUT");
            } else {
               MessageDumper.getInstance().dump(out, MessageDumper.getOperationName(c), "IN");
            }
         }

         out.close();
      } catch (Throwable var5) {
         LOG.warn("SOAPException when logging the message: ", var5);
      }

      return true;
   }
}
