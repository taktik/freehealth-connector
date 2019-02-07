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
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.utils.MessageDumper;

/**
 * The Class LoggingHandler.
 */
public class LoggingHandler  implements SOAPHandler<SOAPMessageContext> {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(LoggingHandler.class);

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

	/** {@inheritDoc} */
	public boolean handleMessage(SOAPMessageContext c) {
	
		SOAPMessage msg = c.getMessage();
		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();			
			msg.writeTo(out);			
			LOG.debug(out.size() + " bytes - " + out.toString());


			if( MessageDumper.getInstance().isDumpEnabled() ){
				final Boolean outboundProperty = (Boolean) c.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
				if (outboundProperty) {
					MessageDumper.getInstance().dump(out, MessageDumper.getOperationName(c), MessageDumper.OUT);
				} else {
					MessageDumper.getInstance().dump(out, MessageDumper.getOperationName(c), MessageDumper.IN);
				}
			}
			out.close();
		} catch (Throwable t) {
			LOG.warn("SOAPException when logging the message: ",t);
		}
		return true;
	}

}
