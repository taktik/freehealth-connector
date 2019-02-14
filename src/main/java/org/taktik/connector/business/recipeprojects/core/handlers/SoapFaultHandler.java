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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleEhealthException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;

/**
 * The Class LoggingHandler.
 */
public class SoapFaultHandler  implements SOAPHandler<SOAPMessageContext> {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(SoapFaultHandler.class);

	/** {@inheritDoc} */
	public Set<QName> getHeaders() {
		return null;
	}

	/** {@inheritDoc} */
	public void close(MessageContext arg0) {
	}

	/** {@inheritDoc} */
	public boolean handleFault(SOAPMessageContext c) {	
		handleMessage(c);
		return true;
	}

	private String getSoapFaultCode(SOAPMessage msg) throws SOAPException{
    	SOAPPart part = msg.getSOAPPart();
		if(part !=null){
			SOAPEnvelope soapEnvelope = part.getEnvelope();
			if(soapEnvelope !=null){
			SOAPBody body = soapEnvelope.getBody();
				if(body !=null){
					SOAPFault fault=body.getFault();
					if(fault !=null && !StringUtils.isEmpty(fault.getFaultString()) && fault.getFaultString().contains("SOA-")){
						return fault.getFaultString();
					}
				}
			}
		}
		return null;
    }
	
	/** {@inheritDoc} */
	public boolean handleMessage(SOAPMessageContext c) {
		
		SOAPMessage msg = c.getMessage();

		try {
			if(getSoapFaultCode(msg) !=null){
				throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.ehealth.technical", new Object[]{getSoapFaultCode(msg)}));
			}
		} catch (SOAPException e) {
			LOG.error(e.getMessage(), e);
		}
		
		return true;
	}

}
