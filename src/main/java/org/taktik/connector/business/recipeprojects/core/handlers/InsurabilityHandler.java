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

package org.taktik.connector.business.recipeprojects.core.handlers;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.NodeList;

public class InsurabilityHandler implements SOAPHandler<SOAPMessageContext> {
	private final static Logger LOG = LogManager.getLogger(InsurabilityHandler.class);
	private static String insurability;
	private static String messageId;
	
	/** {@inheritDoc} */
	public Set<QName> getHeaders() {
		return null;
	}
	
	/** {@inheritDoc} */
	public void close(MessageContext arg0) {}
	
	/** {@inheritDoc} */
	public boolean handleFault(SOAPMessageContext c) {
		handleMessage(c);
		return true;
	}
	
	/** {@inheritDoc} */
	public boolean handleMessage(SOAPMessageContext c) {
		try {
			final Boolean outboundProperty = (Boolean) c.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (outboundProperty == false) {
				SOAPMessage msg = c.getMessage();
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				msg.writeTo(out);
				Node elementsGetPrescriptionForExecutorResponse = (Node) msg.getSOAPBody().getChildElements().next();
				NodeList elements = elementsGetPrescriptionForExecutorResponse.getChildNodes();
				for (int i = 0; i < elements.getLength(); i++) {
					org.w3c.dom.Node element = elements.item(i);
					if (element.getLocalName() != null && (element.getLocalName().equals("GetInsurabilityForPharmacistResponse") || element.getLocalName().equals("InsurabilityResponse"))) {
						initMessageID(msg);
						Transformer t = TransformerFactory.newInstance().newTransformer();
						// t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
						// "yes");
						StringWriter sw = new StringWriter();
						t.transform(new DOMSource(element), new StreamResult(sw));
						insurability = sw.toString();
						break;
					}
				}
			}
		} catch (Throwable t) {
			LOG.warn("SOAPException when retrieving insurability the message", t);
		}
		return true;
	}
	
	private static void initMessageID(SOAPMessage msg) throws SOAPException {
		SOAPPart part = msg.getSOAPPart();
		if (part != null) {
			SOAPEnvelope soapEnvelope = part.getEnvelope();
			if (soapEnvelope != null) {
				SOAPHeader header = soapEnvelope.getHeader();
				
				if (header != null && header.getChildElements().hasNext()) {
					Node elementsResponseHeader = (Node) header.getChildElements().next();
					if (elementsResponseHeader != null && elementsResponseHeader.getLocalName() != null && elementsResponseHeader.getLocalName().equals("MessageID")) {
						NodeList elementsheader = elementsResponseHeader.getChildNodes();
						org.w3c.dom.Node element = elementsheader.item(0);
						messageId = element.getNodeValue();
						LOG.info("MyCareNet returned a response with messageId: " + messageId);
					}
				}
			}
		}
	}
	
	public static void setMessageId(String messageId) {
		InsurabilityHandler.messageId = messageId;
	}
	
	public static String getMessageId() {
		return messageId;
	}
	
	public static void setInsurability(String insurability) {
		InsurabilityHandler.insurability = insurability;
	}
	
	public static String getInsurability() {
		return insurability;
	}
}
