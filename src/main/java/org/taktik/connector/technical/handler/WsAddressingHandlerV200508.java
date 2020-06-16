package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.handler.domain.WsAddressingRelatesTo;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WsAddressingHandlerV200508 extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(WsAddressingHandlerV200508.class);
   public static final String WS_ADDRESSING_V200508_USE = "org.taktik.connector.technical.handler.WsAddressingHandlerV200508.use";
   public static final String MESSAGECONTEXT_WS_ADDRESSING_V200508 = "org.taktik.connector.technical.handler.WsAddressingHandlerV200508";
   private static final String NAMESPACE = "http://www.w3.org/2005/08/addressing";
   private static final String WSA_PREFIX = "wsa";
   private static final QName MESSAGEID = new QName("http://www.w3.org/2005/08/addressing", "MessageID", "wsa");
   private static final QName RELATESTO = new QName("http://www.w3.org/2005/08/addressing", "RelatesTo", "wsa");
   private static final QName RELATIONSHIPTYPE = new QName("http://www.w3.org/2005/08/addressing", "RelationshipType", "wsa");
   private static final QName TO = new QName("http://www.w3.org/2005/08/addressing", "To", "wsa");
   private static final QName ACTION = new QName("http://www.w3.org/2005/08/addressing", "Action", "wsa");
   private static final QName FROM = new QName("http://www.w3.org/2005/08/addressing", "From", "wsa");
   private static final QName REPLYTO = new QName("http://www.w3.org/2005/08/addressing", "ReplyTo", "wsa");
   private static final QName MUST_UNDERSTAND = new QName("http://schemas.xmlsoap.org/soap/envelope/", "mustUnderstand", "S");
   private static final QName ADDRESS = new QName("http://www.w3.org/2005/08/addressing", "Address", "wsa");
   private static final QName FAULTTO = new QName("http://www.w3.org/2005/08/addressing", "FaultTo", "wsa");

   public boolean handleOutbound(SOAPMessageContext context) {
      Boolean wsAddressingUse = context.get("org.taktik.connector.technical.handler.WsAddressingHandlerV200508.use") == null ? Boolean.FALSE : (Boolean)context.get("org.taktik.connector.technical.handler.WsAddressingHandlerV200508.use");
      if (wsAddressingUse) {
         try {
            WsAddressingHeader header = (WsAddressingHeader)context.get("org.taktik.connector.technical.handler.WsAddressingHandlerV200508");
            if (header == null) {
               LOG.warn("No WsAddressingHeader in the requestMap. Skipping the WsAddressingHandler.");
               return true;
            }

            SOAPHeader soapHeader = getSOAPHeader(context);
            this.processRequiredElements(header, soapHeader);
            this.processOptionalElements(header, soapHeader);
            context.getMessage().saveChanges();
         } catch (SOAPException var5) {
            LOG.error("Error while generating WS-Addressing header", var5);
         }
      } else {
         LOG.warn("WsAddressingHandler is configured but org.taktik.connector.technical.handler.WsAddressingHandlerV200508.useproperty was not present or set to FALSE.");
      }

      return true;
   }

   private void processOptionalElements(WsAddressingHeader header, SOAPHeader soapHeader) throws SOAPException {
      if (header.getTo() != null) {
         soapHeader.addChildElement(TO).setTextContent(header.getTo().toString());
      }

      if (header.getMessageID() != null) {
         soapHeader.addChildElement(MESSAGEID).setTextContent(header.getMessageID().toString());
      }

      Iterator i$ = header.getRelatesTo().iterator();

      while(i$.hasNext()) {
         WsAddressingRelatesTo relateTo = (WsAddressingRelatesTo)i$.next();
         this.generateRelateToElement(soapHeader, relateTo);
      }

      if (header.getFrom() != null && !header.getFrom().isEmpty()) {
         soapHeader.addChildElement(FROM).setTextContent(header.getFrom());
      }

      if (header.getReplyTo() != null && !header.getReplyTo().isEmpty()) {
         soapHeader.addChildElement(REPLYTO).addChildElement(ADDRESS).setTextContent(header.getReplyTo());
      }

      if (header.getFaultTo() != null && !header.getFaultTo().isEmpty()) {
         soapHeader.addChildElement(FAULTTO).addChildElement(ADDRESS).setTextContent(header.getFaultTo());
      }

   }

   private void generateRelateToElement(SOAPHeader soapHeader, WsAddressingRelatesTo relateTo) throws SOAPException {
      SOAPElement relateToElement = soapHeader.addChildElement(RELATESTO);
      if (relateTo.getRelationshipType() != null && !relateTo.getRelationshipType().isEmpty()) {
         relateToElement.addAttribute(RELATIONSHIPTYPE, relateTo.getRelationshipType());
      }

      if (relateTo.getRelationshipType() != null) {
         relateToElement.setTextContent(relateTo.getReleatesTo().toString());
      }

   }

   private void processRequiredElements(WsAddressingHeader header, SOAPHeader soapHeader) throws SOAPException {
      SOAPElement actionElement = soapHeader.addChildElement(ACTION);
      actionElement.addAttribute(MUST_UNDERSTAND, header.getMustUnderstand());
      actionElement.setTextContent(header.getAction().toString());
   }

   private static SOAPHeader getSOAPHeader(SOAPMessageContext context) throws SOAPException {
      SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
      if (soapHeader == null) {
         context.getMessage().getSOAPPart().getEnvelope().addHeader();
         soapHeader = context.getMessage().getSOAPHeader();
      }

      return soapHeader;
   }

   public boolean handleFault(SOAPMessageContext context) {
      this.handleMessage(context);
      return false;
   }
}
