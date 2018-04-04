package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.w3c.dom.Element;

public interface SAMLToken extends ExtendedCredential {
   Element getAssertion();

   String getAssertionID();

   void checkValidity() throws TechnicalConnectorException;
}
