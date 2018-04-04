package org.taktik.connector.technical.validator;

import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface SessionValidator {
   boolean validateSession() throws SessionManagementException;

   boolean validateToken(SAMLToken var1) throws SessionManagementException;
}
