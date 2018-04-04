package org.taktik.connector.business.chapterIV.session.impl;

import org.taktik.connector.business.chapterIV.builders.AdmissionBuilder;
import org.taktik.connector.business.chapterIV.builders.BuilderFactory;
import org.taktik.connector.business.chapterIV.builders.ConsultationBuilder;
import org.taktik.connector.business.chapterIV.builders.ResponseBuilder;
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.session.ChapterIVService;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public class ChapterIVServiceImpl implements ChapterIVService {
   private org.taktik.connector.business.chapterIV.service.ChapterIVService service;
   private BuilderFactory factory;

   /** @deprecated */
   @Deprecated
   public ChapterIVServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator, Crypto systemCrypto, Crypto personalCrypto) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      this.service = new org.taktik.connector.business.chapterIV.service.impl.ChapterIVServiceImpl(sessionValidator, replyValidator);
      this.factory = BuilderFactory.getBuilderFactoryForSession();
   }

   public ChapterIVServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      this.service = new org.taktik.connector.business.chapterIV.service.impl.ChapterIVServiceImpl(sessionValidator, replyValidator);
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
