package be.ehealth.businessconnector.ssinhistory.session;

import be.ehealth.businessconnector.ssinhistory.session.impl.SsinHistorySessionServiceImpl;
import be.ehealth.businessconnector.ssinhistory.session.impl.SsinHistorySessionServiceImplFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class SsinHistorySessionServiceFactory extends AbstractSessionServiceFactory {
   private SsinHistorySessionServiceFactory() {
      throw new UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used");
   }

   public static SsinHistorySessionService getSsinHistorySessionService() throws ConnectorException {
      return (SsinHistorySessionService)getService(SsinHistorySessionServiceImpl.class, new SsinHistorySessionServiceImplFactory(), new String[0]);
   }
}
