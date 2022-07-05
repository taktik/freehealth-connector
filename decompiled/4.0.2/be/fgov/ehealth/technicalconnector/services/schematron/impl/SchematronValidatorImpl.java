package be.fgov.ehealth.technicalconnector.services.schematron.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fgov.ehealth.schematron.SchematronSession;
import be.fgov.ehealth.schematron.SchematronSessionFactory;
import be.fgov.ehealth.schematron.domain.SchematronConfig;
import be.fgov.ehealth.schematron.domain.SchematronResult;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidationResult;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidator;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import org.oclc.purl.dsdl.svrl.SchematronOutput;

public class SchematronValidatorImpl implements SchematronValidator {
   public SchematronValidatorImpl() {
   }

   public SchematronValidationResult validate(String inputLocation, String schematronLocation) throws TechnicalConnectorException, InitialisationException {
      return this.validate(ConnectorIOUtils.getResourceAsStream(inputLocation), ConnectorIOUtils.getResourceAsStream(schematronLocation));
   }

   public SchematronValidationResult validate(InputStream input, InputStream schematron) throws TechnicalConnectorException, InitialisationException {
      SchematronConfig config = new SchematronConfig();
      config.setQueryLanguageBinding("xslt2");
      config.setSchema(schematron);
      config.setDebugMode(true);
      SchematronSession session = SchematronSessionFactory.newInstance(config);

      try {
         SchematronSessionFactory.initSaxon();
         SchematronResult result = session.validate(input);
         SchematronSessionFactory.stopSaxon();
         return new SchematronValidationResultImpl(result);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[0]);
      }
   }

   private static class SchematronValidationResultImpl implements SchematronValidationResult {
      private SchematronResult result;

      public SchematronValidationResultImpl(SchematronResult result) {
         this.result = result;
      }

      public boolean isValid() {
         return this.result.isValid();
      }

      public String[] getReportMessages() {
         return this.result.getReportMessages();
      }

      public String[] getFailedMessages() {
         return this.result.getFailedMessages();
      }

      public SchematronOutput getSVRL() throws TechnicalConnectorException {
         try {
            return this.result.getSVRL();
         } catch (JAXBException var2) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var2, new Object[0]);
         }
      }

      public String getSVRLAsString() {
         return this.result.getSVRLAsString();
      }
   }
}
