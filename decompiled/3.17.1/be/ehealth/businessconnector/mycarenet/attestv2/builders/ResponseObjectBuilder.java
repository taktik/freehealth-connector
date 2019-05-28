package be.ehealth.businessconnector.mycarenet.attestv2.builders;

import be.ehealth.businessconnector.mycarenet.attestv2.domain.SignedBuilderResponse;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SignedEncryptedBuilderResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

public interface ResponseObjectBuilder {
   SignedEncryptedBuilderResponse handleSendResponseType(SendResponseType var1) throws TechnicalConnectorException;

   SignedBuilderResponse handleCancelResponseType(SendResponseType var1) throws TechnicalConnectorException;
}
