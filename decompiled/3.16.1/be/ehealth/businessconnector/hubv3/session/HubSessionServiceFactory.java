package be.ehealth.businessconnector.hubv3.session;

import be.ehealth.businessconnector.hubv3.session.impl.HubServiceImpl;
import be.ehealth.businessconnector.hubv3.session.impl.HubServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public class HubSessionServiceFactory extends AbstractSessionServiceFactory {
   public static HubService getHubService() throws ConnectorException {
      return (HubService)getService(HubServiceImpl.class, new HubServiceImplementationFactory(), new String[0]);
   }
}
