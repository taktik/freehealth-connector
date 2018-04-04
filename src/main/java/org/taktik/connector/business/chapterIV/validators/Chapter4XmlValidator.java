package org.taktik.connector.business.chapterIV.validators;

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.io.Serializable;

public interface Chapter4XmlValidator extends Serializable {
   void validate(Object var1) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;
}
