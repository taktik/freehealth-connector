package be.ehealth.businessconnector.ehbox.v3.service;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;

public interface PublicationService {
   SendMessageResponse sendMessage(SAMLToken var1, SendMessageRequest var2) throws EhboxBusinessConnectorException, ConnectorException;
}
