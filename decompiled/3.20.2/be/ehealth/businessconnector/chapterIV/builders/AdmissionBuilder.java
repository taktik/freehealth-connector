package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.businessconnector.chapterIV.domain.ChapterIVBuilderResponse;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVReferences;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public interface AdmissionBuilder {
   /** @deprecated */
   @Deprecated
   AskChap4MedicalAdvisorAgreementRequest buildRequest(FolderType var1, boolean var2, String var3, String var4, DateTime var5) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;

   AskChap4MedicalAdvisorAgreementRequest buildRequest(FolderType var1, boolean var2, ChapterIVReferences var3, DateTime var4) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;

   ChapterIVBuilderResponse build(FolderType var1, boolean var2, ChapterIVReferences var3, DateTime var4) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;
}
