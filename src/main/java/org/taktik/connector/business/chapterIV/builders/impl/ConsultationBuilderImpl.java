package org.taktik.connector.business.chapterIV.builders.impl;

import org.taktik.connector.business.chapterIV.builders.CommonBuilder;
import org.taktik.connector.business.chapterIV.builders.ConsultationBuilder;
import org.taktik.connector.business.chapterIV.domain.ChapterIVBuilderResponse;
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory;
import org.taktik.connector.business.chapterIV.wrapper.factory.impl.ConsultationXmlObjectFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
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
