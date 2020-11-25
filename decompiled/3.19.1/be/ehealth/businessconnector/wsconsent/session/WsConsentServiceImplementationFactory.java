package be.ehealth.businessconnector.wsconsent.session;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.businessconnector.wsconsent.session.impl.WsConsentServiceImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class WsConsentServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws WsConsentBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(WsConsentServiceImpl.class) && additionalParameters.length == 0) {
         return new WsConsentServiceImpl();
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
