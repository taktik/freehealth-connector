package be.ehealth.businessconnector.chapterIV.validators;

import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.io.Serializable;

public interface Chapter4XmlValidator extends Serializable {
   void validate(Object var1) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;
}
