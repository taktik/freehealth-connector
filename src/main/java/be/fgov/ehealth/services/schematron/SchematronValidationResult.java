package be.fgov.ehealth.services.schematron;

import org.oclc.purl.dsdl.svrl.SchematronOutput;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface SchematronValidationResult {
   boolean isValid();

   String[] getReportMessages();

   String[] getFailedMessages();

   SchematronOutput getSVRL() throws TechnicalConnectorException;

   String getSVRLAsString();
}
