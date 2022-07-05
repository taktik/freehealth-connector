package be.fgov.ehealth.technicalconnector.services.validation;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidationResult;
import java.io.InputStream;

public interface ProjectMessageValidator {
   String SCHEMATRON_VALIDATOR = "schematronValidator";
   String SCHEMATRON_PROJECT = "schematronProject";

   SchematronValidationResult validate(String var1) throws TechnicalConnectorException, InitialisationException;

   SchematronValidationResult validate(InputStream var1) throws TechnicalConnectorException, InitialisationException;
}
