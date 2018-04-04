package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.business.chapterIV.domain.ChapterIVBuilderResponse;
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public interface ConsultationBuilder {
   /** @deprecated */
   @Deprecated
   ConsultChap4MedicalAdvisorAgreementRequest buildRequest(FolderType var1, boolean var2, String var3, String var4, DateTime var5) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;

   ConsultChap4MedicalAdvisorAgreementRequest buildRequest(FolderType var1, boolean var2, ChapterIVReferences var3, DateTime var4) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;

   ChapterIVBuilderResponse build(FolderType var1, boolean var2, ChapterIVReferences var3, DateTime var4) throws ChapterIVBusinessConnectorException, TechnicalConnectorException;
}
