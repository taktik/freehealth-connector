package org.taktik.connector.technical.ws.domain;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GenericResponse {
   private static final Logger LOG = LoggerFactory.getLogger(GenericResponse.class);
   private SOAPMessage message;

   public GenericResponse(SOAPMessage message) {
      this.message = message;
   }

   public Node asNode() throws SOAPException {
      return this.getFirstChildElement();
   }

   public String asString() throws TechnicalConnectorException, SOAPException {
      Node response = this.getFirstChildElement();
      if (response != null) {
         return ConnectorXmlUtils.toString((Node)response);
      } else {
         LOG.warn("An empty body is recieved, returning empty String");
         return "";
      }
   }

   public SOAPMessage getSOAPMessage() {
      return this.message;
   }

   public <T> T asObject(Class<T> clazz) throws SOAPException {
      if (!clazz.isAnnotationPresent(XmlRootElement.class)) {
         throw new IllegalArgumentException("Class [" + clazz + "] is not annotated with @XMLRootElement");
      } else {
         this.getSOAPException();
         MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
         helper.clearAttachmentPartMap();
         Iterator attachmentPartIterator = this.message.getAttachments();

         while(attachmentPartIterator.hasNext()) {
            AttachmentPart element = (AttachmentPart)attachmentPartIterator.next();
            helper.addAttachmentPart(this.getAttachmentPartId(element), element);
         }

         return helper.toObject((Node)this.getFirstChildElement());
      }
   }

   private String getAttachmentPartId(AttachmentPart element) {
      return "cid:" + StringUtils.substringBetween(element.getContentId(), "<", ">");
   }

   public byte[] getAttachment(String cid) throws SOAPException {
      Iterator attachmentPartIterator = this.message.getAttachments();

      AttachmentPart element;
      do {
         if (!attachmentPartIterator.hasNext()) {
            throw new SOAPException("Unable to find attachment with id [" + cid + "]");
         }

         element = (AttachmentPart)attachmentPartIterator.next();
      } while(!StringUtils.equals(cid, this.getAttachmentPartId(element)));

      return element.getRawContentBytes();
   }

   public Source asSource() throws SOAPException {
      return new DOMSource(this.getFirstChildElement());
   }

   private Element getFirstChildElement() throws SOAPException {
      this.getSOAPException();
      Node n = this.message.getSOAPPart().getEnvelope().getBody();
      return ConnectorXmlUtils.getFirstChildElement(n);
   }

   public void getSOAPException() throws SOAPException {
      if (this.message != null && this.message.getSOAPBody() != null) {
         SOAPFault fault = this.message.getSOAPBody().getFault();
         if (fault != null) {
            try {
               LOG.error("SOAPFault: " + ConnectorXmlUtils.flatten(ConnectorXmlUtils.toString((Node)fault)));
            } catch (TechnicalConnectorException var3) {
               LOG.debug("Unable to dump SOAPFault. Reason [" + var3.getMessage() + "]", var3);
            }

            throw new SOAPFaultException(fault);
         }
      } else {
         throw new SOAPException("No message SOAPmessage recieved");
      }
   }
}
