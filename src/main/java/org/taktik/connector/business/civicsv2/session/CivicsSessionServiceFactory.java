package org.taktik.connector.business.civicsv2.session;

import org.taktik.connector.business.civicsv2.session.impl.CivicsImplementationClassFactory;
import org.taktik.connector.business.civicsv2.session.impl.CivicsSessionServiceImpl;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class CivicsSessionServiceFactory extends AbstractSessionServiceFactory {
   public static CivicsSessionService getCivicsSession() throws ConnectorException {
      return (CivicsSessionService)getService(CivicsSessionServiceImpl.class, new CivicsImplementationClassFactory(), new String[0]);
   }
}
