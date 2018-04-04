package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.business.chapterIV.domain.ChapterIVBuilderResponse;
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public interface CommonBuilder {
   ChapterIVBuilderResponse createAgreementRequest(FolderType var1, boolean var2, ChapterIVReferences var3, XmlObjectFactory var4, DateTime var5) throws TechnicalConnectorException, ChapterIVBusinessConnectorException;
}
