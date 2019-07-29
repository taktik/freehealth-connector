package org.taktik.connector.business.mycarenet.attestv2.builders;

import org.taktik.connector.business.mycarenet.attestv2.domain.SignedBuilderResponse;
import org.taktik.connector.business.mycarenet.attestv2.domain.SignedEncryptedBuilderResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

public interface ResponseObjectBuilder {
   SignedEncryptedBuilderResponse handleSendResponseType(SendResponseType var1) throws TechnicalConnectorException;

   SignedBuilderResponse handleCancelResponseType(SendResponseType var1) throws TechnicalConnectorException;
}
