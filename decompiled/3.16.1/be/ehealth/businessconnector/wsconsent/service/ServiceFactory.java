package be.ehealth.businessconnector.wsconsent.service;

import be.ehealth.businessconnector.wsconsent.service.impl.WsConsentServiceImpl;

public final class ServiceFactory {
   private static WsConsentService service;

   public static WsConsentService getWsConsentService() {
      if (service == null) {
         service = new WsConsentServiceImpl();
      }

      return service;
   }
}
