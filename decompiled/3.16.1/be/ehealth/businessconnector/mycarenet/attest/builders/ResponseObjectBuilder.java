package be.ehealth.businessconnector.mycarenet.attest.builders;

import be.ehealth.businessconnector.mycarenet.attest.domain.AttestBuilderResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import java.io.UnsupportedEncodingException;

public interface ResponseObjectBuilder {
   AttestBuilderResponse handleSendResponseType(SendResponseType var1) throws TechnicalConnectorException, UnsupportedEncodingException;
}
