package be.ehealth.technicalconnector.validator;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;

public interface SessionValidator {
   boolean validateSession() throws SessionManagementException;

   boolean validateToken(SAMLToken var1) throws SessionManagementException;
}
