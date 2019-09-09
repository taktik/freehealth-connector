package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.utils.SOAPFaultFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoapActionHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SoapActionHandler.class);

   public boolean handleOutbound(SOAPMessageContext context) {
      try {
         boolean hasSoapAction = false;
         if (context.containsKey("javax.xml.ws.soap.http.soapaction.use")) {
            hasSoapAction = (Boolean) context.get("javax.xml.ws.soap.http.soapaction.use");
         }

         if (hasSoapAction) {
            String soapAction = (String)context.get("javax.xml.ws.soap.http.soapaction.uri");
            LOG.debug("Adding SOAPAction to mimeheader");
            SOAPMessage msg = context.getMessage();
            String[] headers = msg.getMimeHeaders().getHeader("SOAPAction");
            if (headers != null) {
               LOG.warn("Removing SOAPAction with values: " + ArrayUtils.toString(headers));
               msg.getMimeHeaders().removeHeader("SOAPAction");
            }

            msg.getMimeHeaders().addHeader("SOAPAction", soapAction);
            msg.saveChanges();
         }

         return true;
      } catch (SOAPException var6) {
         throw SOAPFaultFactory.newSOAPFaultException("WSSecurity problem: [SOAPACTION]" + var6.getMessage(), var6);
      }
   }
}
