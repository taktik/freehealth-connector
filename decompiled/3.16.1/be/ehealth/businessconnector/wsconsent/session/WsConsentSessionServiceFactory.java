package be.ehealth.businessconnector.wsconsent.session;

import be.ehealth.businessconnector.wsconsent.session.impl.WsConsentServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class WsConsentSessionServiceFactory extends AbstractSessionServiceFactory {
   private static WsConsentServiceImplementationFactory implFac = new WsConsentServiceImplementationFactory();

   public static WsConsentService getWsConsentService() throws ConnectorException {
      return (WsConsentService)getService(WsConsentServiceImpl.class, implFac, new String[0]);
   }
}
