package be.ehealth.businessconnector.therlink.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class TherlinkServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(TherLinkServiceImpl.class) && additionalParameters.length == 0) {
         return new TherLinkServiceImpl();
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
