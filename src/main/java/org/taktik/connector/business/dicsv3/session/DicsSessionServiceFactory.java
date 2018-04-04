package org.taktik.connector.business.dicsv3.session;

import org.taktik.connector.business.dicsv3.session.impl.DicsImplementationClassFactory;
import org.taktik.connector.business.dicsv3.session.impl.DicsSessionServiceImpl;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class DicsSessionServiceFactory extends AbstractSessionServiceFactory {
   public static DicsSessionService getDicsSession() throws ConnectorException {
      return (DicsSessionService)getService(DicsSessionServiceImpl.class, new DicsImplementationClassFactory(), new String[0]);
   }
}
