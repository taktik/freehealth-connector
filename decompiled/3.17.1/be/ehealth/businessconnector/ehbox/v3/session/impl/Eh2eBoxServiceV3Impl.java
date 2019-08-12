package be.ehealth.businessconnector.ehbox.v3.session.impl;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.v3.service.PublicationService;
import be.ehealth.businessconnector.ehbox.v3.service.impl.PublicationEh2EboxServiceImpl;
import be.ehealth.businessconnector.ehbox.v3.session.Eh2eBoxServiceV3;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;

public class Eh2eBoxServiceV3Impl implements Eh2eBoxServiceV3 {
   private PublicationService publicationService;

   public Eh2eBoxServiceV3Impl(SessionValidator sessionValidator, EhboxReplyValidator replyValidator) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      this.publicationService = new PublicationEh2EboxServiceImpl(sessionValidator, replyValidator);
   }

   public final SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest) throws ConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      SendMessageResponse response = this.publicationService.sendMessage(token, sendMessageRequest);
      return response;
   }
}
