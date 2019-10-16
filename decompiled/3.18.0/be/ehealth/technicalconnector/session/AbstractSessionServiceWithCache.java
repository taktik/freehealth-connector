package be.ehealth.technicalconnector.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;

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
