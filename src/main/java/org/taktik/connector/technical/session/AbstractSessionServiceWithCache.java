package org.taktik.connector.technical.session;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.utils.SAMLHelper;

public abstract class AbstractSessionServiceWithCache implements SessionServiceWithCache {
   private SAMLToken samlToken;

   public AbstractSessionServiceWithCache() {
      Session.getInstance().registerSessionService(this);
   }

   protected SAMLToken getSamlToken() throws TechnicalConnectorException {
      if (this.samlToken == null) {
         SessionManager instance = Session.getInstance();
         if (!instance.hasValidSession()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
         }

         this.samlToken = instance.getSession().getSAMLToken();
      } else if (SAMLHelper.getNotOnOrAfterCondition(this.samlToken.getAssertion()).isBeforeNow()) {
         this.samlToken = null;
         this.getSamlToken();
      }

      return this.samlToken;
   }

   public void flushCache() {
      this.samlToken = null;
   }
}
