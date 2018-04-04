package org.taktik.connector.technical.service.sts;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute;
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator;
import org.taktik.connector.technical.service.sts.security.Credential;
import java.util.List;
import org.w3c.dom.Element;

public interface STSService {
   Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException;

   Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String authenticationMethod, String nameQualifier, String value, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException;

   Element renewToken(Credential headerCredentials, Credential bodyCredentials, Element samlToken, int validity) throws TechnicalConnectorException;
}
