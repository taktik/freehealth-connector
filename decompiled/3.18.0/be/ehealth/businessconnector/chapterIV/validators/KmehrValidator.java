package be.ehealth.businessconnector.chapterIV.validators;

import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

public interface KmehrValidator {
   void validateKmehrMessage(Kmehrmessage var1) throws TechnicalConnectorException, ChapterIVBusinessConnectorException;
}
