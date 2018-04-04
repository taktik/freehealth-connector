package org.taktik.connector.technical.validator;

import org.taktik.connector.technical.exception.SoaErrorException;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public interface EhealthReplyValidator {
   boolean validateReplyStatus(ResponseType var1) throws SoaErrorException;

   boolean validateReplyStatus(StatusResponseType var1) throws SoaErrorException;
}
