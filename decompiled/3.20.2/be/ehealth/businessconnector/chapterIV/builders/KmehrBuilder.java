package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType;

public interface KmehrBuilder {
   HeaderType generateHeader(String var1) throws TechnicalConnectorException;

   AuthorType generateAuthor() throws TechnicalConnectorException;
}
