package org.taktik.connector.business.chapterIV.service;

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;

public interface ChapterIVService {
   ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(SAMLToken token, ConsultChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreement(SAMLToken token, AskChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException;
}
