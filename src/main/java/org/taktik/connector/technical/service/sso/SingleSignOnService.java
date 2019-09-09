package org.taktik.connector.technical.service.sso;

import org.taktik.connector.technical.enumeration.SsoProfile;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface SingleSignOnService {
   void signin(SsoProfile var1, String var2, SAMLToken samlToken) throws TechnicalConnectorException;

   void signin(SsoProfile var1, SAMLToken samlToken) throws TechnicalConnectorException;

   void setHandler(BrowserHandler var1);
}
