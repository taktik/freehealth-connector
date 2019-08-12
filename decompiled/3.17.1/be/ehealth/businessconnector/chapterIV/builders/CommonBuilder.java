package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.businessconnector.chapterIV.domain.ChapterIVBuilderResponse;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVReferences;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.XmlObjectFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public interface CommonBuilder {
   ChapterIVBuilderResponse createAgreementRequest(FolderType var1, boolean var2, ChapterIVReferences var3, XmlObjectFactory var4, DateTime var5) throws TechnicalConnectorException, ChapterIVBusinessConnectorException;
}
