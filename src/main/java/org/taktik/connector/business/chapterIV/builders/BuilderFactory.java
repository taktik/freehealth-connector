package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.business.chapterIV.builders.impl.AdmissionBuilderImpl;
import org.taktik.connector.business.chapterIV.builders.impl.ConsultationBuilderImpl;
import org.taktik.connector.business.chapterIV.builders.impl.ResponseBuilderImpl;
import org.taktik.connector.business.chapterIV.validators.Chapter4XmlValidator;
import org.taktik.connector.business.chapterIV.validators.KmehrValidator;
import org.taktik.connector.business.chapterIV.validators.impl.Chapter4XmlValidatorImpl;
import org.taktik.connector.business.chapterIV.validators.impl.KmehrValidatorImpl;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;

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

   public ResponseBuilder getResponseBuilder() throws TechnicalConnectorException {
      return new ResponseBuilderImpl(this.chapter4XmlValidator);
   }

   /** @deprecated */
   @Deprecated
   public ResponseBuilder createResponseBuilder() throws TechnicalConnectorException {
      return this.getResponseBuilder();
   }
}
