package be.ehealth.technicalconnector.validator;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public interface XMLValidator {
   void validate(Object var1) throws TechnicalConnectorException;
}
