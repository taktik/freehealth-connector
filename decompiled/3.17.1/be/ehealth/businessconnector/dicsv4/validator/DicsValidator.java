package be.ehealth.businessconnector.dicsv4.validator;

import be.ehealth.businessconnector.dicsv4.exception.DicsDataNotFoundException;
import be.ehealth.businessconnector.dicsv4.exception.DicsException;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public interface DicsValidator {
   void validateResponse(StatusResponseType var1) throws DicsDataNotFoundException, DicsException;
}
