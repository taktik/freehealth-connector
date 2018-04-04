package org.taktik.connector.business.recipeprojects.core.handlers;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;




import org.apache.log4j.Logger;


import com.sun.xml.wss.impl.SecurableSoapMessage;

public class MustUnderstandHandler implements SOAPHandler<SOAPMessageContext> {
	private static final Logger LOG = Logger.getLogger(MustUnderstandHandler.class);
	private static final QName WSSE = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse");

	public void close(MessageContext c) {
	}

	public boolean handleFault(SOAPMessageContext c) {
		handleMessage(c);
		return true;
	}

	public boolean handleMessage(SOAPMessageContext cxt) {
		Boolean outbound = (Boolean) cxt.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound.booleanValue()) {
			SOAPMessage message = cxt.getMessage();
			
			try {
				SOAPHeader header = message.getSOAPHeader();
				if(header != null) {
					Iterator<SOAPElement> it = header.getChildElements(WSSE);
					while(it.hasNext()) {
						SOAPElement el = (SOAPElement)it.next();
						el.removeAttributeNS(message.getSOAPPart().getEnvelope().getNamespaceURI(), "mustUnderstand");
						LOG.debug("Recipe hook: The mustunderstand in security header has succesfully been removed");
					}
					
					message.saveChanges();
				}
			} catch (SOAPException e) {
				throw SecurableSoapMessage.newSOAPFaultException("Recipe hook problem: " + e.getMessage(), e);
			}

		}

		return true;
	}

	public Set<QName> getHeaders() {
		return null;
	}
}
