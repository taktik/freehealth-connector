package be.ehealth.businessconnector.registration.session;

import be.ehealth.businessconnector.registration.session.impl.RegistrationSessionImpl;
import be.ehealth.businessconnector.registration.session.impl.RegsitrationImplementationClassFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class RegistrationSessionFactory extends AbstractSessionServiceFactory {
   public static RegistrationSession getRegistrationSession() throws ConnectorException {
      return (RegistrationSession)getService(RegistrationSessionImpl.class, new RegsitrationImplementationClassFactory(), new String[0]);
   }
}
