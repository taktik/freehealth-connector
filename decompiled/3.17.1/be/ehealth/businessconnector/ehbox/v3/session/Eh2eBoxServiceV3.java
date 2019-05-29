package be.ehealth.businessconnector.ehbox.v3.session;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;

public interface Eh2eBoxServiceV3 {
   SendMessageResponse sendMessage(SendMessageRequest var1) throws ConnectorException;
}
