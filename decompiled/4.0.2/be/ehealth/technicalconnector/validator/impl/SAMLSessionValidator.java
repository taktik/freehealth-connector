package be.ehealth.technicalconnector.validator.impl;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.SessionManagementExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAMLSessionValidator implements SessionValidator {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLSessionValidator.class);

   public SAMLSessionValidator() {
   }

   public boolean validateSession() throws SessionManagementException {
      if (!Session.getInstance().hasValidSession()) {
         LOG.error("No valid session found");
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_NOSESSION, new Object[0]);
      } else {
         return true;
      }
   }

   public boolean validateToken(SAMLToken samlToken) throws SessionManagementException {
      if (samlToken != null && samlToken.getAssertion() != null) {
         return SAMLHelper.getNotOnOrAfterCondition(samlToken.getAssertion()).isAfterNow();
      } else {
         LOG.error("No valid samlToken");
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_NOTOKEN, new Object[0]);
      }
   }
}
