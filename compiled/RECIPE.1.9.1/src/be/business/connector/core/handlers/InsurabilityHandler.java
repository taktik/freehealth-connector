/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package be.business.connector.core.handlers;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;

/**
 * The Class InsurabilityHandler.
 */
public class InsurabilityHandler implements SOAPHandler<SOAPMessageContext> {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(InsurabilityHandler.class);

	/** The insurability. */
	private static String insurability;

	/** The message id. */
	private static String messageId;
	
	/** {@inheritDoc} */
	@Override
	public Set<QName> getHeaders() {
		return null;
	}
	
	/** {@inheritDoc} */
	@Override
	public void close(final MessageContext arg0) {
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean handleFault(final SOAPMessageContext c) {
		handleMessage(c);
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean handleMessage(final SOAPMessageContext c) {
		try {
			final Boolean outboundProperty = (Boolean) c.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (outboundProperty == false) {
				final SOAPMessage msg = c.getMessage();
				
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				msg.writeTo(out);
				final Node elementsGetPrescriptionForExecutorResponse = (Node) msg.getSOAPBody().getChildElements().next();
				final NodeList elements = elementsGetPrescriptionForExecutorResponse.getChildNodes();
				for (int i = 0; i < elements.getLength(); i++) {
					final org.w3c.dom.Node element = elements.item(i);
					if (element.getLocalName() != null && (element.getLocalName().equals("GetInsurabilityForPharmacistResponse") || element.getLocalName().equals("InsurabilityResponse"))) {
						initMessageID(msg);
						final Transformer t = TransformerFactory.newInstance().newTransformer();
						final StringWriter sw = new StringWriter();
						t.transform(new DOMSource(element), new StreamResult(sw));
						insurability = sw.toString();
						break;
					}
				}
			}
		} catch (final Throwable t) {
			LOG.warn("SOAPException when retrieving insurability the message", t);
		}
		return true;
	}
	
	/**
	 * Inits the message ID.
	 *
	 * @param msg
	 *            the msg
	 * @throws SOAPException
	 *             the SOAP exception
	 */
	private static void initMessageID(final SOAPMessage msg) throws SOAPException {
		final SOAPPart part = msg.getSOAPPart();
		if (part != null) {
			final SOAPEnvelope soapEnvelope = part.getEnvelope();
			if (soapEnvelope != null) {
				final SOAPHeader header = soapEnvelope.getHeader();
				
				if (header != null && header.getChildElements().hasNext()) {
					final Node elementsResponseHeader = (Node) header.getChildElements().next();
					if (elementsResponseHeader != null && elementsResponseHeader.getLocalName() != null && elementsResponseHeader.getLocalName().equals("MessageID")) {
						final NodeList elementsheader = elementsResponseHeader.getChildNodes();
						final org.w3c.dom.Node element = elementsheader.item(0);
						messageId = element.getNodeValue();
						LOG.info("MyCareNet returned a response with messageId: " + messageId);
					}
				}
			}
		}
	}
	
	/**
	 * Sets the message id.
	 *
	 * @param messageId
	 *            the new message id
	 */
	public static void setMessageId(final String messageId) {
		InsurabilityHandler.messageId = messageId;
	}
	
	/**
	 * Gets the message id.
	 *
	 * @return the message id
	 */
	public static String getMessageId() {
		return messageId;
	}
	
	/**
	 * Sets the insurability.
	 *
	 * @param insurability
	 *            the new insurability
	 */
	public static void setInsurability(final String insurability) {
		InsurabilityHandler.insurability = insurability;
	}
	
	/**
	 * Gets the insurability.
	 *
	 * @return the insurability
	 */
	public static String getInsurability() {
		return insurability;
	}
}