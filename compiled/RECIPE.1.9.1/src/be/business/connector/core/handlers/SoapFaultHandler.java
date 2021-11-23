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

import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.utils.I18nHelper;

/**
 * The Class LoggingHandler.
 */
public class SoapFaultHandler  implements SOAPHandler<SOAPMessageContext> {

	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(SoapFaultHandler.class);

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

	/**
	 * Gets the soap fault code.
	 *
	 * @param msg
	 *            the msg
	 * @return the soap fault code
	 * @throws SOAPException
	 *             the SOAP exception
	 */
	private String getSoapFaultCode(final SOAPMessage msg) throws SOAPException {
		final SOAPPart part = msg.getSOAPPart();
		if(part !=null){
			final SOAPEnvelope soapEnvelope = part.getEnvelope();
			if(soapEnvelope !=null){
				final SOAPBody body = soapEnvelope.getBody();
				if(body !=null){
					final SOAPFault fault = body.getFault();
					if(fault !=null && !StringUtils.isEmpty(fault.getFaultString()) && fault.getFaultString().contains("SOA-")){
						return fault.getFaultString();
					}
				}
			}
		}
		return null;
    }
	
	/** {@inheritDoc} */
	@Override
	public boolean handleMessage(final SOAPMessageContext c) {
		
		final SOAPMessage msg = c.getMessage();

		try {
			if(getSoapFaultCode(msg) !=null){
				throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.ehealth.technical", new Object[]{getSoapFaultCode(msg)}));
			}
		} catch (final SOAPException e) {
			LOG.error(e.getMessage(), e);
		}
		
		return true;
	}

}
