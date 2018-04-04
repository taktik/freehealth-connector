package org.taktik.connector.technical.ws.impl;

import java.util.HashMap;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext.Scope;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public final class SOAPMessageContextImpl extends HashMap<String, Object> implements SOAPMessageContext {
   private static final long serialVersionUID = 1L;
   private transient SOAPMessage soapMessage;

   public SOAPMessageContextImpl(SOAPMessage soapMessage) {
      this.setMessage(soapMessage);
   }

   public SOAPMessage getMessage() {
      return this.soapMessage;
   }

   public void setMessage(SOAPMessage paramSOAPMessage) {
      this.soapMessage = paramSOAPMessage;
   }

   public void setScope(String paramString, Scope paramScope) {
      throw new UnsupportedOperationException();
   }

   public Scope getScope(String paramString) {
      throw new UnsupportedOperationException();
   }

   public Object[] getHeaders(QName paramQName, JAXBContext paramJAXBContext, boolean paramBoolean) {
      throw new UnsupportedOperationException();
   }

   public Set<String> getRoles() {
      throw new UnsupportedOperationException();
   }
}
