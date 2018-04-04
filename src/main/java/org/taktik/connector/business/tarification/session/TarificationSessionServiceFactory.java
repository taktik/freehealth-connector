package org.taktik.connector.business.tarification.session;

import org.taktik.connector.business.tarification.session.impl.TarificationImplementationClassFactory;
import org.taktik.connector.business.tarification.session.impl.TarificationSessionServiceImpl;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class TarificationSessionServiceFactory extends AbstractSessionServiceFactory {
   public static TarificationSessionService getTarificationSession() throws ConnectorException {
      return (TarificationSessionService)getService(TarificationSessionServiceImpl.class, new TarificationImplementationClassFactory(), new String[0]);
   }
}
