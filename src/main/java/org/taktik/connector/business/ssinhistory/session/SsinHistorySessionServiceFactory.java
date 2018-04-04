package org.taktik.connector.business.ssinhistory.session;

import org.taktik.connector.business.ssinhistory.session.impl.SsinHistorySessionServiceImpl;
import org.taktik.connector.business.ssinhistory.session.impl.SsinHistorySessionServiceImplFactory;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class SsinHistorySessionServiceFactory extends AbstractSessionServiceFactory {
   private SsinHistorySessionServiceFactory() {
      throw new UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used");
   }

   public static SsinHistorySessionService getSsinHistorySessionService() throws ConnectorException {
      return (SsinHistorySessionService)getService(SsinHistorySessionServiceImpl.class, new SsinHistorySessionServiceImplFactory(), new String[0]);
   }
}
