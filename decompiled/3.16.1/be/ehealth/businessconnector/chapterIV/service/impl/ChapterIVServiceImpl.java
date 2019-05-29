package be.ehealth.businessconnector.chapterIV.service.impl;

import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.chapterIV.service.ChapterIVService;
import be.ehealth.businessconnector.chapterIV.service.ServiceFactory;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
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
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         ConsultChap4MedicalAdvisorAgreementResponse response = (ConsultChap4MedicalAdvisorAgreementResponse)xmlResponse.asObject(ConsultChap4MedicalAdvisorAgreementResponse.class);
         this.replyValidator.validateReplyStatus((ResponseType)response);
         return response;
      } catch (MalformedURLException var6) {
         String fileTypeDescription = "Chapter IV Consultation file";
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, var6, new Object[]{fileTypeDescription});
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public final AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreement(SAMLToken token, AskChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getAdmissionService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:chap4:protocol:v1:AskChap4MedicalAdvisorAgreement");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         AskChap4MedicalAdvisorAgreementResponse response = (AskChap4MedicalAdvisorAgreementResponse)xmlResponse.asObject(AskChap4MedicalAdvisorAgreementResponse.class);
         this.replyValidator.validateReplyStatus((ResponseType)response);
         return response;
      } catch (MalformedURLException var6) {
         String fileTypeDescription = "Chapter IV Admission file";
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, var6, new Object[]{fileTypeDescription});
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }
}
