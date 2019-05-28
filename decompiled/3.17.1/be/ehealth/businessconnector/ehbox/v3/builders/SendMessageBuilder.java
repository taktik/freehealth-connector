package be.ehealth.businessconnector.ehbox.v3.builders;

import be.ehealth.businessconnector.ehbox.api.domain.DocumentMessage;
import be.ehealth.businessconnector.ehbox.api.domain.NewsMessage;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import java.io.IOException;
import org.bouncycastle.cms.CMSException;

public interface SendMessageBuilder {
   SendMessageRequest buildMessage(DocumentMessage<Message> var1) throws IOException, EhboxBusinessConnectorException, TechnicalConnectorException, CMSException;

   SendMessageRequest buildMessage(NewsMessage<Message> var1) throws IOException, EhboxBusinessConnectorException, TechnicalConnectorException;
}
