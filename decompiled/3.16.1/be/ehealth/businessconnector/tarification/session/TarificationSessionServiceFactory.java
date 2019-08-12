package be.ehealth.businessconnector.tarification.session;

import be.ehealth.businessconnector.tarification.session.impl.TarificationImplementationClassFactory;
import be.ehealth.businessconnector.tarification.session.impl.TarificationSessionServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class TarificationSessionServiceFactory extends AbstractSessionServiceFactory {
   public static TarificationSessionService getTarificationSession() throws ConnectorException {
      return (TarificationSessionService)getService(TarificationSessionServiceImpl.class, new TarificationImplementationClassFactory(), new String[0]);
   }
}
