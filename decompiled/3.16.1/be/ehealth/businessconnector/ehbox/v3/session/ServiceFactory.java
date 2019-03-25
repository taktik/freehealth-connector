package be.ehealth.businessconnector.ehbox.v3.session;

import be.ehealth.businessconnector.ehbox.v3.session.impl.Eh2eBoxServiceV3Impl;
import be.ehealth.businessconnector.ehbox.v3.session.impl.EhboxServiceImplementationFactory;
import be.ehealth.businessconnector.ehbox.v3.session.impl.EhealthBoxServiceV3Impl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class ServiceFactory extends AbstractSessionServiceFactory {
   public static EhealthBoxServiceV3 getEhealthBoxServiceV3() throws ConnectorException {
      return (EhealthBoxServiceV3)getService(EhealthBoxServiceV3Impl.class, new EhboxServiceImplementationFactory(), new String[0]);
   }

   public static Eh2eBoxServiceV3 getEhealthBox2eBoxServiceV3() throws ConnectorException {
      return (Eh2eBoxServiceV3)getService(Eh2eBoxServiceV3Impl.class, new EhboxServiceImplementationFactory(), new String[0]);
   }
}
