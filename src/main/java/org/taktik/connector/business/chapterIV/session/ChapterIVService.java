package org.taktik.connector.business.chapterIV.session;

import org.taktik.connector.business.chapterIV.builders.AdmissionBuilder;
import org.taktik.connector.business.chapterIV.builders.ConsultationBuilder;
import org.taktik.connector.business.chapterIV.builders.ResponseBuilder;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public interface ChapterIVService {
   ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(ConsultChap4MedicalAdvisorAgreementRequest var1) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   /** @deprecated */
   @Deprecated
   ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(FolderType var1, String var2, boolean var3, String var4, String var5, DateTime var6) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   ConsultationBuilder getConsultationBuilder() throws TechnicalConnectorException;

   AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreementResponse(AskChap4MedicalAdvisorAgreementRequest var1) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   /** @deprecated */
   @Deprecated
   AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreementResponse(FolderType var1, String var2, boolean var3, String var4, String var5, DateTime var6) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   AdmissionBuilder getAdmissionBuilder() throws TechnicalConnectorException;

   ResponseBuilder getResponseBuilder() throws TechnicalConnectorException;
}
