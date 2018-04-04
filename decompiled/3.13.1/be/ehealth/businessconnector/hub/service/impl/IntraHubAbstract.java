package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

abstract class IntraHubAbstract {
   private IntraHubService hubService;
   private HubReplyValidator replyValidator;

   protected IntraHubService getService() {
      return this.hubService;
   }

   protected HubReplyValidator getReplyValidator() {
      return this.replyValidator;
   }

   public IntraHubAbstract(IntraHubService hubService, HubReplyValidator replyValidator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.hubService = hubService;
      this.replyValidator = replyValidator;
   }

   protected IntraHubAbstract() {
   }
}
