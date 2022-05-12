package be.fgov.ehealth.services.schematron;

import be.fgov.ehealth.schematron.exception.InitialisationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

import java.io.InputStream;

public interface SchematronValidator {
   SchematronValidationResult validate(String inputLocation, String schematronLocation) throws TechnicalConnectorException, InitialisationException;

   SchematronValidationResult validate(InputStream input, InputStream schematron) throws TechnicalConnectorException, InitialisationException;
}
