package be.recipe.services.prescriber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
   private static final Logger LOG = LogManager.getLogger(LoggingHandler.class);

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
      ByteArrayOutputStream out = null;

      try {
         out = new ByteArrayOutputStream();
         msg.writeTo(out);
         LOG.debug(out.size() + " bytes - " + out.toString());
         out.flush();
      } catch (Throwable var16) {
         LOG.warn("SOAPException when logging the message", var16);

         try {
            this.getMessageID(msg);
         } catch (SOAPException var15) {
            LOG.error(var15.getMessage(), var15);
         }
      } finally {
         if (out != null) {
            try {
               out.close();
            } catch (IOException var14) {
               LOG.warn(var14.getMessage(), var14);
            }
         }

      }

      return true;
   }

   private void getMessageID(SOAPMessage msg) throws SOAPException {
      SOAPHeader header = msg.getSOAPHeader();
      if (header != null && header.getChildElements().hasNext()) {
         Node elementsResponseHeader = (Node)header.getChildElements().next();
         NodeList elementsheader = elementsResponseHeader.getChildNodes();

         for(int i = 0; i < elementsheader.getLength(); ++i) {
            org.w3c.dom.Node element = elementsheader.item(i);
            if (element.getLocalName() != null && element.getLocalName().equals("MessageID")) {
               LOG.info("The message id of the response: " + element.getNodeValue());
               break;
            }
         }
      }

   }
}
