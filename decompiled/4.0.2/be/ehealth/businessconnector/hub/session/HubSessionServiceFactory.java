package be.ehealth.businessconnector.hub.session;

import be.ehealth.businessconnector.hub.session.impl.HubServiceCompleteImpl;
import be.ehealth.businessconnector.hub.session.impl.HubServiceCompleteImplementationFactory;
import be.ehealth.businessconnector.hub.session.impl.HubServiceImpl;
import be.ehealth.businessconnector.hub.session.impl.HubServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public class HubSessionServiceFactory extends AbstractSessionServiceFactory {
   public HubSessionServiceFactory() {
   }

   public static HubService getHubService() throws ConnectorException {
      return (HubService)getService(HubServiceImpl.class, new HubServiceImplementationFactory(), new String[0]);
   }

   public static HubServiceComplete getHubServiceComplete() throws ConnectorException {
      return (HubServiceComplete)getService(HubServiceCompleteImpl.class, new HubServiceCompleteImplementationFactory(), new String[0]);
   }
}
