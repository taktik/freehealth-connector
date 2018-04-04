package be.ehealth.businessconnector.civicsv2.session;

import be.ehealth.businessconnector.civicsv2.session.impl.CivicsImplementationClassFactory;
import be.ehealth.businessconnector.civicsv2.session.impl.CivicsSessionServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class CivicsSessionServiceFactory extends AbstractSessionServiceFactory {
   public static CivicsSessionService getCivicsSession() throws ConnectorException {
      return (CivicsSessionService)getService(CivicsSessionServiceImpl.class, new CivicsImplementationClassFactory(), new String[0]);
   }
}
