package org.taktik.connector.business.dmg.session;

import org.taktik.connector.business.dmg.session.impl.DmgServiceImpl;
import org.taktik.connector.business.dmg.session.impl.DmgServiceImplementationFactory;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class DmgSessionServiceFactory extends AbstractSessionServiceFactory {
   public static DmgService getDmgService() throws ConnectorException {
      return (DmgService)getService(DmgServiceImpl.class, new DmgServiceImplementationFactory(), new String[0]);
   }
}
