package be.ehealth.businessconnector.ehbox.v3.validator;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;

public interface EhboxReplyValidator {
   boolean validateReplyStatus(ResponseType var1) throws ConnectorException;
}
