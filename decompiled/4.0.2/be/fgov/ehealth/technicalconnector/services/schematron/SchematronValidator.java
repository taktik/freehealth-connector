package be.fgov.ehealth.technicalconnector.services.schematron;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import java.io.InputStream;

public interface SchematronValidator {
   SchematronValidationResult validate(String var1, String var2) throws TechnicalConnectorException, InitialisationException;

   SchematronValidationResult validate(InputStream var1, InputStream var2) throws TechnicalConnectorException, InitialisationException;
}
