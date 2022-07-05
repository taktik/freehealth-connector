package be.ehealth.technicalconnector.session.renew;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;
import be.ehealth.technicalconnector.session.SessionItem;
import org.joda.time.DateTime;

public class TokenExpiredRenew extends AbstractRenewStrategy {
   public TokenExpiredRenew() {
   }

   public void renew(SessionItem session) throws SessionManagementException {
      SAMLToken samlToken = session.getSAMLToken();
      DateTime end = SAMLHelper.getNotOnOrAfterCondition(samlToken.getAssertion());
      boolean valid = end.isAfterNow();
      if (!valid) {
         executeReload(session, this.getCacheServices());
      }

   }
}
