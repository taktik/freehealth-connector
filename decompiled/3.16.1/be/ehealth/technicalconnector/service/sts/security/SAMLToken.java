package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.w3c.dom.Element;

public interface SAMLToken extends ExtendedCredential {
   Element getAssertion();

   String getAssertionID();

   void checkValidity() throws TechnicalConnectorException;
}
