package be.fgov.ehealth.technicalconnector.services.schematron;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.oclc.purl.dsdl.svrl.SchematronOutput;

public interface SchematronValidationResult {
   boolean isValid();

   String[] getReportMessages();

   String[] getFailedMessages();

   SchematronOutput getSVRL() throws TechnicalConnectorException;

   String getSVRLAsString();
}
