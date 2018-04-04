package org.taktik.connector.technical.service.sso;

import org.taktik.connector.technical.enumeration.SsoProfile;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface SingleSignOnService {
   void signin(SsoProfile var1, String var2) throws TechnicalConnectorException;

   void signin(SsoProfile var1) throws TechnicalConnectorException;
}
