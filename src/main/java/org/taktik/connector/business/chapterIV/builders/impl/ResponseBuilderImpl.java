package org.taktik.connector.business.chapterIV.builders.impl;

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response;
import org.taktik.connector.business.chapterIV.builders.ResponseBuilder;
import org.taktik.connector.business.chapterIV.builders.WrappedResponseBuilder;
import org.taktik.connector.business.chapterIV.common.ConversationType;
import org.taktik.connector.business.chapterIV.domain.ChapterIVKmehrResponseWithTimeStampInfo;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import org.taktik.connector.business.chapterIV.validators.Chapter4XmlValidator;
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedResponseWrapper;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.InvalidTimeStampException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.exception.UnsealConnectorException;
import org.taktik.connector.technical.exception.UnsealConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.domain.UnsealedData;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.utils.ConnectorCryptoUtils;
import org.taktik.connector.technical.utils.ConnectorExceptionUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.impl.TimeStampValidatorFactory;
import be.fgov.ehealth.chap4.core.v1.FaultType;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.tsp.TSPAlgorithms;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseBuilderImpl implements ResponseBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final long serialVersionUID = 7170496556230629079L;
   private static final Logger LOG = LoggerFactory.getLogger(ResponseBuilderImpl.class);
   private static final String PROP_VALIDATION_INCOMING_CONS_CHIV = "validation.incoming.chapterIV.consultation.message";
   private static final String PROP_VALIDATION_INCOMING_ADM_CHIV = "validation.incoming.chapterIV.admission.message";
   private static Configuration config;
   private Chapter4XmlValidator validator;

   /** @deprecated */
   @Deprecated
   public ResponseBuilderImpl(Crypto crypto, Chapter4XmlValidator validator) {
      this.validator = validator;
   }

   public ResponseBuilderImpl() {
      LOG.debug("ResponseBuilderImpl : constructor needed for ModuleBootstrapHook");
   }

   public ResponseBuilderImpl(Chapter4XmlValidator validator) {
      this.validator = validator;
   }

   public FaultType retrieveReturnInfo(ResponseType response) {
      if (response == null) {
         throw new IllegalArgumentException("null value not supported as input parameter");
      } else if (response instanceof AskChap4MedicalAdvisorAgreementResponse) {
         AskChap4MedicalAdvisorAgreementResponse askResponse = (AskChap4MedicalAdvisorAgreementResponse)response;
         return this.retrieveReturnInfo(WrappedResponseBuilder.wrap(askResponse));
      } else if (response instanceof ConsultChap4MedicalAdvisorAgreementResponse) {
         ConsultChap4MedicalAdvisorAgreementResponse consultResponse = (ConsultChap4MedicalAdvisorAgreementResponse)response;
         return this.retrieveReturnInfo(WrappedResponseBuilder.wrap(consultResponse));
      } else {
         throw new UnsupportedOperationException("ResponseType subtype of " + response.getClass() + "not supported");
      }
   }

   private FaultType retrieveReturnInfo(Chap4MedicalAdvisorAgreementResponseWrapper<?> wrap) {
      return wrap.getReturnInfo();
   }

   public ChapterIVKmehrResponseWithTimeStampInfo validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(AskChap4MedicalAdvisorAgreementResponse response) throws UnsealConnectorException, ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.ADMISSION, false);
   }

   public ChapterIVKmehrResponseWithTimeStampInfo validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(AskChap4MedicalAdvisorAgreementResponse response, boolean ignoreWarnings) throws UnsealConnectorException, ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.ADMISSION, ignoreWarnings);
   }

   public TimeStampResponse convertToTimeStampResponse(byte[] bytes) throws TechnicalConnectorException {
      try {
         TimeStampResponse timeStampResponse = new TimeStampResponse(bytes);
         return timeStampResponse;
      } catch (TSPException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, var4, new Object[0]);
      } catch (IOException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, var5, new Object[0]);
      }
   }

   public Kmehrresponse convertToKmehrResKmehrresponse(byte[] bytes) throws ChapterIVBusinessConnectorException {
      MarshallerHelper<Kmehrresponse, Kmehrresponse> kmehrResponseMarshallerHelper = new MarshallerHelper(Kmehrresponse.class, Kmehrresponse.class);
      return bytes != null && bytes.length > 0 ? (Kmehrresponse)kmehrResponseMarshallerHelper.toObject(bytes) : null;
   }

   public ChapterIVKmehrResponseWithTimeStampInfo validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(ConsultChap4MedicalAdvisorAgreementResponse response) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.CONSULT, false);
   }

   public ChapterIVKmehrResponseWithTimeStampInfo validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(ConsultChap4MedicalAdvisorAgreementResponse response, boolean ignoreWarnings) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      return this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.CONSULT, ignoreWarnings);
   }

   private ChapterIVKmehrResponseWithTimeStampInfo validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(Chap4MedicalAdvisorAgreementResponseWrapper<?> agreementResponse, ConversationType responseType, boolean ignoreWarnings) throws ChapterIVBusinessConnectorException, UnsealConnectorException, TechnicalConnectorException {
      byte[] unsealedSecuredContent = this.unsealSecuredContent(agreementResponse, ignoreWarnings);
      LOG.debug("unsealedSecuredContent : " + new String(unsealedSecuredContent));
      UnsealedResponseWrapper unsealedResponse = this.getUnsealedResponse(unsealedSecuredContent, responseType);
      if (this.isValidationNeeded(responseType)) {
         this.validator.validate(unsealedResponse.getXmlObject());
      }

      TimeStampRequest tsRequest = this.generateTimeStampRequest(unsealedResponse.getKmehrResponseBytes());
      TimeStampResponse timeStampResponse = this.convertToTimeStampResponse(unsealedResponse.getTimestampReplyBytes());
      this.validateTimeStamp(tsRequest, timeStampResponse);
      Kmehrresponse kmehrResponse = this.convertToKmehrResKmehrresponse(unsealedResponse.getKmehrResponseBytes());
      if (kmehrResponse != null && this.isValidationNeeded(responseType)) {
         this.validator.validate(kmehrResponse);
      }

      return new ChapterIVKmehrResponseWithTimeStampInfo(unsealedResponse.getTimestampReplyBytes(), unsealedResponse.getKmehrResponseBytes());
   }

   private boolean isValidationNeeded(ConversationType conversationType) {
      if (ConversationType.ADMISSION.equals(conversationType)) {
         return this.getBooleanPropertyDefaultingToTrue("validation.incoming.chapterIV.admission.message");
      } else if (ConversationType.CONSULT.equals(conversationType)) {
         return this.getBooleanPropertyDefaultingToTrue("validation.incoming.chapterIV.consultation.message");
      } else {
         throw new IllegalArgumentException("unhandled converstationType + [" + conversationType + "]");
      }
   }

   private boolean getBooleanPropertyDefaultingToTrue(String configProperty) {
      return "true".equalsIgnoreCase(config.getProperty(configProperty, "true"));
   }

   private void validateTimeStamp(TimeStampRequest tsRequest, TimeStampResponse timeStampResponse) throws UnsealConnectorException, ChapterIVBusinessConnectorException {
      try {
         LOG.trace("validating timestamp response");
         timeStampResponse.validate(tsRequest);
         LOG.trace(" timestamp response validated , now validating timestamp token");
         this.validateTimeStampToken(timeStampResponse);
         LOG.trace(" timestamp token validated");
      } catch (TSPException var4) {
         throw new UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, var4, new Object[]{"time stamp was not valid :" + var4.getMessage()});
      } catch (TechnicalConnectorException var5) {
         throw new UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, var5, new Object[]{" error while validating timestamptoken :" + var5.getMessage()});
      }
   }

   private void validateTimeStampToken(TimeStampResponse timeStampResponse) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      try {
         TimeStampValidatorFactory.getInstance().validateTimeStampToken(timeStampResponse.getTimeStampToken());
      } catch (InvalidTimeStampException var3) {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.TIMESTAMP_NOT_CORRECT, var3, new Object[0]);
      }
   }

   private TimeStampRequest generateTimeStampRequest(byte[] bs) throws TechnicalConnectorException {
      TimeStampRequestGenerator generator = new TimeStampRequestGenerator();
      return generator.generate(TSPAlgorithms.SHA256, ConnectorCryptoUtils.calculateDigest("SHA-256", bs));
   }

   protected UnsealedResponseWrapper<?> getUnsealedResponse(byte[] unsealedSecuredContent, ConversationType type) {
      MarshallerHelper helper;
      if (ConversationType.ADMISSION.equals(type)) {
         helper = new MarshallerHelper(Response.class, Response.class);
         Response response = (Response)helper.toObject(unsealedSecuredContent);
         return WrappedResponseBuilder.wrap(response);
      } else if (ConversationType.CONSULT.equals(type)) {
         helper = new MarshallerHelper(be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response.class, be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response.class);
         be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response response = (be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response)helper.toObject(unsealedSecuredContent);
         return WrappedResponseBuilder.wrap(response);
      } else {
         throw new IllegalArgumentException("unexpected error : code called with unsupported type " + type);
      }
   }

   protected byte[] unsealSecuredContent(Chap4MedicalAdvisorAgreementResponseWrapper<?> agreementResponse, boolean ignoreWarnings) throws ChapterIVBusinessConnectorException, UnsealConnectorException, TechnicalConnectorException {
      byte[] securedContent = this.getSecuredContent(agreementResponse);
      this.validateSessionForHolderOfKeyCrypto();

      try {
         UnsealedData unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, securedContent);
         return unsealedData != null ? unsealedData.getContentAsByte() : null;
      } catch (UnsealConnectorException var5) {
         if (ignoreWarnings) {
            return ConnectorExceptionUtils.processUnsealConnectorException(var5);
         } else {
            throw var5;
         }
      }
   }

   private void validateSessionForHolderOfKeyCrypto() throws TechnicalConnectorException {
      SessionItem session = Session.getInstance().getSession();
      if (session == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no active session found"});
      } else if (session.getHolderOfKeyCrypto() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no holder of key crypto found in the session"});
      }
   }

   protected byte[] getSecuredContent(Chap4MedicalAdvisorAgreementResponseWrapper<?> agreementResponse) throws ChapterIVBusinessConnectorException {
      byte[] result = null;
      if (agreementResponse != null && agreementResponse.getResponse() != null) {
         result = agreementResponse.getResponse().getSecuredContent();
      }

      if (result == null) {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_RESPONSE_XML, "the AgreementResponse did not contain a securedContent");
      } else {
         return result;
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsultChap4MedicalAdvisorAgreementRequest.class);
      JaxbContextFactory.initJaxbContext(ConsultChap4MedicalAdvisorAgreementResponse.class);
      JaxbContextFactory.initJaxbContext(AskChap4MedicalAdvisorAgreementResponse.class);
      JaxbContextFactory.initJaxbContext(Kmehrresponse.class);
   }

   static {
      List<String> expectedProps = new ArrayList();
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
