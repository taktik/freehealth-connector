package be.business.connector.core.handlers;

import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.utils.I18nHelper;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SoapFaultHandler implements SOAPHandler<SOAPMessageContext> {
   private static final Logger LOG = Logger.getLogger(SoapFaultHandler.class);

   public Set<QName> getHeaders() {
      return null;
   }

   public void close(MessageContext arg0) {
   }

   public boolean handleFault(SOAPMessageContext c) {
      this.handleMessage(c);
      return true;
   }

   private String getSoapFaultCode(SOAPMessage msg) throws SOAPException {
      SOAPPart part = msg.getSOAPPart();
      if (part != null) {
         SOAPEnvelope soapEnvelope = part.getEnvelope();
         if (soapEnvelope != null) {
            SOAPBody body = soapEnvelope.getBody();
            if (body != null) {
               SOAPFault fault = body.getFault();
               if (fault != null && !StringUtils.isEmpty(fault.getFaultString()) && fault.getFaultString().contains("SOA-")) {
                  return fault.getFaultString();
               }
            }
         }
      }

      return null;
   }

   public boolean handleMessage(SOAPMessageContext c) {
      SOAPMessage msg = c.getMessage();

      try {
         if (this.getSoapFaultCode(msg) != null) {
            throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.ehealth.technical", new Object[]{this.getSoapFaultCode(msg)}));
         }
      } catch (SOAPException var4) {
         LOG.error(var4.getMessage(), var4);
      }

      return true;
   }
}
