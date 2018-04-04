package org.taktik.connector.business.chapterIV.builders.impl;

import org.taktik.connector.business.chapterIV.builders.AdmissionBuilder;
import org.taktik.connector.business.chapterIV.builders.CommonBuilder;
import org.taktik.connector.business.chapterIV.domain.ChapterIVBuilderResponse;
import org.taktik.connector.business.chapterIV.domain.ChapterIVReferences;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory;
import org.taktik.connector.business.chapterIV.wrapper.factory.impl.AskXmlObjectFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import org.joda.time.DateTime;

public class AdmissionBuilderImpl implements AdmissionBuilder {
   private CommonBuilder commonBuilder;
   private XmlObjectFactory xmlObjectFactory = new AskXmlObjectFactory();

   public AdmissionBuilderImpl(CommonBuilder commonBuilder) {
      this.commonBuilder = commonBuilder;
   }

   public final AskChap4MedicalAdvisorAgreementRequest buildRequest(FolderType folder, boolean isTest, String commonReference, String commonNIPReference, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      ChapterIVReferences references = new ChapterIVReferences(commonReference);
      references.setCommonNIPReference(commonNIPReference);
      return this.buildRequest(folder, isTest, references, agreementStartDate);
   }

   public AskChap4MedicalAdvisorAgreementRequest buildRequest(FolderType folder, boolean isTest, ChapterIVReferences references, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      ChapterIVBuilderResponse result = this.build(folder, isTest, references, agreementStartDate);
      return result.getAskChap4MedicalAdvisorAgreementRequest();
   }

   public ChapterIVBuilderResponse build(FolderType folder, boolean isTest, ChapterIVReferences references, DateTime agreementStartDate) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.commonBuilder.createAgreementRequest(folder, isTest, references, this.xmlObjectFactory, agreementStartDate);
   }
}
