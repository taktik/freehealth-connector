package be.ehealth.businessconnector.medadmin.session;

import be.ehealth.businessconnector.medadmin.session.impl.MedadminServiceImpl;
import be.ehealth.businessconnector.medadmin.session.impl.MedadminServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MedadminSessionServiceFactory extends AbstractSessionServiceFactory {
   private MedadminSessionServiceFactory() {
   }

   public static MedAdminService getMedadminService() throws ConnectorException {
      return (MedAdminService)getService(MedadminServiceImpl.class, new MedadminServiceImplementationFactory(), new String[0]);
   }
}
