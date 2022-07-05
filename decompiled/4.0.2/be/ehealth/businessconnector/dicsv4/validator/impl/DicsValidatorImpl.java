package be.ehealth.businessconnector.dicsv4.validator.impl;

import be.ehealth.businessconnector.dicsv4.exception.DicsDataNotFoundException;
import be.ehealth.businessconnector.dicsv4.validator.DicsValidator;
import be.fgov.ehealth.commons.core.v2.StatusCode;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public class DicsValidatorImpl implements DicsValidator {
   public DicsValidatorImpl() {
   }

   public void validateResponse(StatusResponseType response) throws DicsDataNotFoundException {
      StatusCode secondStatusCode = response.getStatus().getStatusCode().getStatusCode();
      if (secondStatusCode != null && "urn:be:fgov:ehealth:2.0:status:DataNotFound".equals(secondStatusCode.getValue())) {
         throw new DicsDataNotFoundException(response);
      }
   }
}
