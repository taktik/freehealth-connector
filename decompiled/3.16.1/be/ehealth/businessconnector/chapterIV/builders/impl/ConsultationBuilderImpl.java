package be.ehealth.businessconnector.chapterIV.builders.impl;

import be.ehealth.businessconnector.chapterIV.builders.CommonBuilder;
import be.ehealth.businessconnector.chapterIV.builders.ConsultationBuilder;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVBuilderResponse;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVReferences;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.XmlObjectFactory;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.impl.ConsultationXmlObjectFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConsultationBuilderImpl implements ConsultationBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private CommonBuilder commonBuilder;
   private XmlObjectFactory xmlObjectFactory = new ConsultationXmlObjectFactory();
   private static final Logger LOG = LoggerFactory.getLogger(ConsultationBuilderImpl.class);

   public ConsultationBuilderImpl(CommonBuilder commonBuilder) {
      this.commonBuilder = commonBuilder;
   }

   public ConsultationBuilderImpl() {
      LOG.debug("creating ConsultationBuilderImpl for ModuleBootstrapping purposes");
   }

   public ConsultChap4MedicalAdvisorAgreementRequest buildRequest(FolderType folder, boolean isTest, String commonReference, String commonNIPReference, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      ChapterIVReferences references = new ChapterIVReferences(commonReference);
      references.setCommonNIPReference(commonNIPReference);
      return this.buildRequest(folder, isTest, references, agreementStartDate);
   }

   public ConsultChap4MedicalAdvisorAgreementRequest buildRequest(FolderType folder, boolean isTest, ChapterIVReferences references, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      ChapterIVBuilderResponse result = this.build(folder, isTest, references, agreementStartDate);
      return result.getConsultChap4MedicalAdvisorAgreementRequest();
   }

   public ChapterIVBuilderResponse build(FolderType folder, boolean isTest, ChapterIVReferences references, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.commonBuilder.createAgreementRequest(folder, isTest, references, this.xmlObjectFactory, agreementStartDate);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsultChap4MedicalAdvisorAgreementRequest.class);
   }
}
