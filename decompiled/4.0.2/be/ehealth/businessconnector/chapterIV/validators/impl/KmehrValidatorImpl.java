package be.ehealth.businessconnector.chapterIV.validators.impl;

import be.ehealth.business.kmehrcommons.validator.ValidatorFactory;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.chapterIV.validators.KmehrValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.errors.soa.v1.SOAErrorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KmehrValidatorImpl implements KmehrValidator {
   private static final Logger LOG = LoggerFactory.getLogger(KmehrValidatorImpl.class);

   public KmehrValidatorImpl() {
   }

   public void validateKmehrMessage(Kmehrmessage msg) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      be.ehealth.business.kmehrcommons.validator.KmehrValidator kmehrvalidator = ValidatorFactory.getKmehrValidator();

      try {
         kmehrvalidator.validateKmehrMessage(msg);
         if (msg.getFolders().size() > 1) {
            LOG.debug("\t## XML is not correct: Only 1 folder is supported : throwing Chapter IV business connector exception");
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{(SOAErrorType)null, "Only 1 folder is supported"});
         } else if (((FolderType)msg.getFolders().get(0)).getPatient().getIds().size() < 1) {
            LOG.debug("\t## XML is not correct: Patient must have at least one Id : throwing Chapter IV business connector exception");
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{(SOAErrorType)null, "Patient must have at least one Id"});
         }
      } catch (TechnicalConnectorException var4) {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_XML_KMEHRVALIDATOR, var4, (SOAErrorType)null, new Object[]{var4.getMessage()});
      }
   }
}
