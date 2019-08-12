package be.ehealth.technicalconnector.validator;

import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public interface EhealthReplyValidator {
   boolean validateReplyStatus(ResponseType var1) throws SoaErrorException;

   boolean validateReplyStatus(be.fgov.ehealth.commons._1_0.protocol.ResponseType var1) throws SoaErrorException;

   boolean validateReplyStatus(StatusResponseType var1) throws SoaErrorException;
}
