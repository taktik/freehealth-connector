package org.taktik.connector.business.chapterIV.service.impl;

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import org.taktik.connector.business.chapterIV.service.ChapterIVService;
import org.taktik.connector.business.chapterIV.service.ServiceFactory;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;

public class ChapterIVServiceImpl implements ChapterIVService {
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public ChapterIVServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
   }

   public final ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(SAMLToken token, ConsultChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getConsultationService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:chap4:protocol:v1:ConsultChap4MedicalAdvisorAgreement");
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         ConsultChap4MedicalAdvisorAgreementResponse response = xmlResponse.asObject(ConsultChap4MedicalAdvisorAgreementResponse.class);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (MalformedURLException var6) {
         String fileTypeDescription = "Chapter IV Consultation file";
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, var6, new Object[]{fileTypeDescription});
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.getMessage());
      }
   }

   public final AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreement(SAMLToken token, AskChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getAdmissionService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:chap4:protocol:v1:AskChap4MedicalAdvisorAgreement");
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         AskChap4MedicalAdvisorAgreementResponse response = xmlResponse.asObject(AskChap4MedicalAdvisorAgreementResponse.class);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (MalformedURLException var6) {
         String fileTypeDescription = "Chapter IV Admission file";
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, var6, new Object[]{fileTypeDescription});
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }
}
