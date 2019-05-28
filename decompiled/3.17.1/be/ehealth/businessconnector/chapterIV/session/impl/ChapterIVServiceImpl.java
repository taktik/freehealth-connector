package be.ehealth.businessconnector.chapterIV.session.impl;

import be.ehealth.businessconnector.chapterIV.builders.AdmissionBuilder;
import be.ehealth.businessconnector.chapterIV.builders.BuilderFactory;
import be.ehealth.businessconnector.chapterIV.builders.ConsultationBuilder;
import be.ehealth.businessconnector.chapterIV.builders.ResponseBuilder;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVReferences;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.session.ChapterIVService;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public class ChapterIVServiceImpl implements ChapterIVService {
   private be.ehealth.businessconnector.chapterIV.service.ChapterIVService service;
   private BuilderFactory factory;

   /** @deprecated */
   @Deprecated
   public ChapterIVServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator, Crypto systemCrypto, Crypto personalCrypto) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      this.service = new be.ehealth.businessconnector.chapterIV.service.impl.ChapterIVServiceImpl(sessionValidator, replyValidator);
      this.factory = BuilderFactory.getBuilderFactoryForSession();
   }

   public ChapterIVServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      this.service = new be.ehealth.businessconnector.chapterIV.service.impl.ChapterIVServiceImpl(sessionValidator, replyValidator);
      this.factory = BuilderFactory.getBuilderFactoryForSession();
   }

   public ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(ConsultChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.consultChap4MedicalAdvisorAgreement(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreementResponse(AskChap4MedicalAdvisorAgreementRequest request) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return this.service.askChap4MedicalAdvisorAgreement(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public ConsultChap4MedicalAdvisorAgreementResponse consultChap4MedicalAdvisorAgreement(FolderType folder, String inputReference, boolean isTest, String commonReference, String commonNIPReference, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      ConsultationBuilder builder = this.factory.getConsultationBuilder();
      ChapterIVReferences references = new ChapterIVReferences(commonReference);
      references.setCommonNIPReference(commonNIPReference);
      return this.consultChap4MedicalAdvisorAgreement(builder.buildRequest(folder, isTest, references, agreementStartDate));
   }

   public ConsultationBuilder getConsultationBuilder() throws TechnicalConnectorException {
      return this.factory.getConsultationBuilder();
   }

   public AskChap4MedicalAdvisorAgreementResponse askChap4MedicalAdvisorAgreementResponse(FolderType folder, String inputReference, boolean isTest, String commonReference, String commonNIPReference, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      AdmissionBuilder builder = this.factory.getAdmissionBuilder();
      ChapterIVReferences references = new ChapterIVReferences(commonReference);
      references.setCommonNIPReference(commonNIPReference);
      return this.askChap4MedicalAdvisorAgreementResponse(builder.buildRequest(folder, isTest, references, agreementStartDate));
   }

   public AdmissionBuilder getAdmissionBuilder() throws TechnicalConnectorException {
      return this.factory.getAdmissionBuilder();
   }

   public ResponseBuilder getResponseBuilder() throws TechnicalConnectorException {
      return this.factory.getResponseBuilder();
   }
}
