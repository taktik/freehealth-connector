package org.taktik.connector.business.mycarenet.attest.builders;

import org.taktik.connector.business.mycarenet.attest.domain.AttestBuilderResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import java.io.UnsupportedEncodingException;

public interface ResponseObjectBuilder {
   AttestBuilderResponse handleSendResponseType(SendResponseType var1, SendAttestationRequest var2) throws TechnicalConnectorException, UnsupportedEncodingException;
}
