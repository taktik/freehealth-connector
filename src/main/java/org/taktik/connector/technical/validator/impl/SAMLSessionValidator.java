package org.taktik.connector.technical.validator.impl;

import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.SessionManagementExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.utils.SAMLHelper;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.SessionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAMLSessionValidator implements SessionValidator {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLSessionValidator.class);

   public boolean validateSession() throws SessionManagementException {
      if (!Session.getInstance().hasValidSession()) {
         LOG.error("No valid session found");
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_NOSESSION);
      } else {
         return true;
      }
   }

   public boolean validateToken(SAMLToken samlToken) throws SessionManagementException {
      if (samlToken != null && samlToken.getAssertion() != null) {
         return SAMLHelper.getNotOnOrAfterCondition(samlToken.getAssertion()).isAfterNow();
      } else {
         LOG.error("No valid samlToken");
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_NOTOKEN);
      }
   }
}
