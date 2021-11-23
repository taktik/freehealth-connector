package be.business.connector.core.handlers;

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


/**
 * The Class MustUnderstandHandler.
 */
public class MustUnderstandHandler implements SOAPHandler<SOAPMessageContext> {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(MustUnderstandHandler.class);

	/** The Constant WSSE. */
	private static final QName WSSE = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse");

	/**
	 * {@inheritDoc}
	 */
        @Override
	public void close(final MessageContext c) {
	}

	/**
	 * {@inheritDoc}
	 */
        @Override
	public boolean handleFault(final SOAPMessageContext c) {
		handleMessage(c);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
        @Override
	public boolean handleMessage(final SOAPMessageContext cxt) {
		final Boolean outbound = (Boolean) cxt.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outbound) {
			final SOAPMessage message = cxt.getMessage();
			try {
				final SOAPHeader header = message.getSOAPHeader();
				if(header != null) {
					final Iterator<SOAPElement> it = header.getChildElements(WSSE);
					while(it.hasNext()) {
						final SOAPElement el = it.next();
						el.removeAttributeNS(message.getSOAPPart().getEnvelope().getNamespaceURI(), "mustUnderstand");
						LOG.debug("Recipe hook: The mustunderstand in security header has succesfully been removed");
					}
					message.saveChanges();
				}
			} catch (final SOAPException e) {
				LOG.error("Recipe hook problem: " + e.getMessage(), e);
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}