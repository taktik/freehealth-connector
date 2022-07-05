package be.ehealth.businessconnector.mycarenet.attestv3.builders;

import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedEncryptedResponseHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedResponseHolder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;

public interface ResponseObjectBuilder {
   SignedEncryptedResponseHolder handleSendAttestionResponse(SendResponseType var1, EncryptedRequestHolder var2) throws TechnicalConnectorException;

   SignedResponseHolder handleCancelAttestationResponse(SendResponseType var1, SendRequestType var2) throws TechnicalConnectorException;
}
