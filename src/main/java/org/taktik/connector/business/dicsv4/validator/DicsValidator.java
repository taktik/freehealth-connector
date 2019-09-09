package org.taktik.connector.business.dicsv4.validator;

import org.taktik.connector.business.dicsv4.exception.DicsDataNotFoundException;
import org.taktik.connector.business.dicsv4.exception.DicsException;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public interface DicsValidator {
   void validateResponse(StatusResponseType var1) throws DicsDataNotFoundException, DicsException;
}
