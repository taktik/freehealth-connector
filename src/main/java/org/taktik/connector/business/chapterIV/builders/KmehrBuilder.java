package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType;

public interface KmehrBuilder {
   HeaderType generateHeader(String var1) throws TechnicalConnectorException;

   AuthorType generateAuthor() throws TechnicalConnectorException;
}
