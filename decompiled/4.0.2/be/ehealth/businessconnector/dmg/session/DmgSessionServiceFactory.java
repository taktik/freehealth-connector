package be.ehealth.businessconnector.dmg.session;

import be.ehealth.businessconnector.dmg.session.impl.DmgServiceImpl;
import be.ehealth.businessconnector.dmg.session.impl.DmgServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class DmgSessionServiceFactory extends AbstractSessionServiceFactory {
   private DmgSessionServiceFactory() {
   }

   public static DmgService getDmgService() throws ConnectorException {
      return (DmgService)getService(DmgServiceImpl.class, new DmgServiceImplementationFactory(), new String[0]);
   }
}
