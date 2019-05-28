package be.ehealth.businessconnector.therlink.session;

import be.ehealth.businessconnector.therlink.session.impl.TherLinkServiceImpl;
import be.ehealth.businessconnector.therlink.session.impl.TherlinkServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class TherlinkSessionServiceFactory extends AbstractSessionServiceFactory {
   public static TherLinkService getTherlinkService() throws ConnectorException {
      return (TherLinkService)getService(TherLinkServiceImpl.class, new TherlinkServiceImplementationFactory(), new String[0]);
   }
}
