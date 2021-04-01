package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.businessconnector.chapterIV.builders.impl.AdmissionBuilderImpl;
import be.ehealth.businessconnector.chapterIV.builders.impl.CommonBuilderImpl;
import be.ehealth.businessconnector.chapterIV.builders.impl.ConsultationBuilderImpl;
import be.ehealth.businessconnector.chapterIV.builders.impl.ResponseBuilderImpl;
import be.ehealth.businessconnector.chapterIV.validators.Chapter4XmlValidator;
import be.ehealth.businessconnector.chapterIV.validators.KmehrValidator;
import be.ehealth.businessconnector.chapterIV.validators.impl.Chapter4XmlValidatorImpl;
import be.ehealth.businessconnector.chapterIV.validators.impl.KmehrValidatorImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;

public final class BuilderFactory {
   private KmehrValidator kmehrValidator;
   private Chapter4XmlValidator chapter4XmlValidator;
   private CommonBuilder commonBuilder;

   /** @deprecated */
   @Deprecated
   public BuilderFactory(Crypto holderOfKeyCrypto, Crypto encryptionCrypto, KmehrValidator validator, Chapter4XmlValidator chapter4XmlValidator) {
      this.kmehrValidator = validator;
      this.chapter4XmlValidator = chapter4XmlValidator;
   }

   public BuilderFactory(KmehrValidator validator, Chapter4XmlValidator chapter4XmlValidator) {
      this.kmehrValidator = validator;
      this.chapter4XmlValidator = chapter4XmlValidator;
   }

   public static BuilderFactory getBuilderFactoryForSession(SessionItem sessionItem) throws TechnicalConnectorException {
      if (sessionItem == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else if (sessionItem.getHolderOfKeyCredential() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"No holderOfKey credentials found."});
      } else if (sessionItem.getEncryptionCredential() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"No Encryption credentials found."});
      } else {
         return new BuilderFactory(new KmehrValidatorImpl(), new Chapter4XmlValidatorImpl());
      }
   }

   public static BuilderFactory getBuilderFactoryForSession() throws TechnicalConnectorException {
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else {
         SessionItem sessionItem = Session.getInstance().getSession();
         return getBuilderFactoryForSession(sessionItem);
      }
   }

   private CommonBuilder getCommonBuilder() throws TechnicalConnectorException {
      if (this.commonBuilder == null) {
         this.commonBuilder = new CommonBuilderImpl(this.chapter4XmlValidator, this.kmehrValidator, KmehrBuilderFactory.getKmehrBuilder());
      }

      return this.commonBuilder;
   }

   public ConsultationBuilder getConsultationBuilder() throws TechnicalConnectorException {
      return new ConsultationBuilderImpl(this.getCommonBuilder());
   }

   public AdmissionBuilder getAdmissionBuilder() throws TechnicalConnectorException {
      return new AdmissionBuilderImpl(this.getCommonBuilder());
   }

   public ResponseBuilder getResponseBuilder() throws TechnicalConnectorException {
      return new ResponseBuilderImpl(this.chapter4XmlValidator);
   }

   /** @deprecated */
   @Deprecated
   public ResponseBuilder createResponseBuilder() throws TechnicalConnectorException {
      return this.getResponseBuilder();
   }
}
