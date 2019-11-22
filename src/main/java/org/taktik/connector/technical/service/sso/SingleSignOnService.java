package org.taktik.connector.technical.service.sso;

import org.taktik.connector.technical.enumeration.SsoProfile;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface SingleSignOnService {
   String signin(SsoProfile profile, SAMLToken samlToken) throws TechnicalConnectorException;
}
