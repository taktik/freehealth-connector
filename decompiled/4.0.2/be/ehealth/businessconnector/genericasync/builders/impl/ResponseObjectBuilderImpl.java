package be.ehealth.businessconnector.genericasync.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.PostResponse;
import be.cin.nip.async.generic.TAck;
import be.cin.nip.async.generic.TAckResponse;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import be.ehealth.businessconnector.genericasync.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedTAckResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncSignatureValidationConnectorException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);
   public static final String GENERICASYNC = "genericasync.";
   private static Configuration config = ConfigFactory.getConfigValidator();

   public ResponseObjectBuilderImpl() {
   }

   public final boolean handlePostResponse(PostResponse postResponse) throws GenAsyncBusinessConnectorException {
      if (postResponse != null && postResponse.getReturn() != null) {
         TAck tack = postResponse.getReturn();
         if (!tack.getResultMajor().equals("urn:nip:tack:result:major:success")) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SEND_REQUEST_FAILED, new Object[]{"message from tack -> " + tack.getResultMinor()});
         } else {
            boolean hasWarning = false;
            if (tack.getResultMinor() != null && !tack.getResultMinor().isEmpty()) {
               hasWarning = true;
               LOG.info("handlePostResponse : warning : " + tack.getResultMinor());
               LOG.info("handlePostResponse : resultMessage  : " + tack.getResultMessage());
            }

            LOG.info("handlePostResponse : success : " + tack.getResultMajor());
            return hasWarning;
         }
      } else {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"the postResponse or postResponse.return was null"});
      }
   }

   public final Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse getResponse) throws GenAsyncBusinessConnectorException {
      return this.handleGetResponse(getResponse, (String)null);
   }

   public final Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse getResponse, String projectName) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> validationResult = new HashMap();
      Iterator var4 = getResponse.getReturn().getTAckResponses().iterator();

      while(var4.hasNext()) {
         TAckResponse value = (TAckResponse)var4.next();
         LOG.debug("handleGetResponse : tackResponse : xades : " + value.getXadesT() + ", tack : " + value.getTAck());
         validationResult.putAll(this.validateXadesT(value, value.getXadesT().getValue()));
      }

      var4 = getResponse.getReturn().getMsgResponses().iterator();

      while(var4.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)var4.next();
         if (msgResponse.getXadesT() != null) {
            validationResult.putAll(this.validateXadesT(msgResponse, msgResponse.getXadesT().getValue()));
         }
      }

      if (!validationResult.isEmpty()) {
         if (LOG.isDebugEnabled()) {
            this.logValidationResult(validationResult);
         }

         throw new GenAsyncSignatureValidationConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, validationResult);
      } else {
         return validationResult;
      }
   }

   public <T> ProcessedGetResponse processResponse(GetResponse getResponse, Class<T> clazz, String projectName, String schemaLocation) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      ProcessedGetResponse processedGetResponse = new ProcessedGetResponse();
      Iterator var6 = getResponse.getReturn().getTAckResponses().iterator();

      while(var6.hasNext()) {
         TAckResponse value = (TAckResponse)var6.next();
         LOG.debug("handleGetResponse : tackResponse : xades : " + value.getXadesT() + ", tack : " + value.getTAck());
         ProcessedTAckResponse tAckResponse = new ProcessedTAckResponse(value, this.validateTAckXadesT(value, value.getXadesT().getValue(), projectName));
         processedGetResponse.getTAckResponses().add(tAckResponse);
      }

      var6 = getResponse.getReturn().getMsgResponses().iterator();

      while(var6.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)var6.next();
         ProcessedMsgResponse processedMsgResponse;
         if (msgResponse.getDetail().getContentEncryption() == null) {
            byte[] unwrappedMessageByteArray = this.getContent(msgResponse, projectName);
            LOG.debug("Content of decrypted business message:[{}]", ConnectorIOUtils.toString(unwrappedMessageByteArray, Charset.UTF_8));
            processedMsgResponse = new ProcessedMsgResponse(msgResponse, this.toBusinessResponse(clazz, unwrappedMessageByteArray), this.validateMsgXadesT(msgResponse, msgResponse.getXadesT().getValue(), projectName), ArrayUtils.isEmpty(msgResponse.getXadesT().getValue()) ? null : unwrappedMessageByteArray);
         } else {
            processedMsgResponse = this.processEncryptedResponse(msgResponse, projectName, clazz);
         }

         processedGetResponse.getMsgResponses().add(processedMsgResponse);
         if (schemaLocation != null && config.getBooleanProperty("genericasync." + projectName + ".validation.incoming.businessresponse", true)) {
            ValidatorHelper.validate(processedMsgResponse.getBusinessResponse(), schemaLocation);
         }
      }

      return processedGetResponse;
   }

   public <T> ProcessedMsgResponse<T> processEncryptedResponse(MsgResponse msgResponse, String projectName, Class<T> clazz) throws TechnicalConnectorException, GenAsyncBusinessConnectorException {
      LOG.debug("Analysing msgResponse {}", ConnectorXmlUtils.toString((Object)msgResponse));
      byte[] unwrappedMessageByteArray = this.getContent(msgResponse, projectName);
      byte[] unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, unwrappedMessageByteArray).getContentAsByte();
      LOG.debug("Unsealed data [{}].", new String(unsealedData));
      EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)ConnectorXmlUtils.toObject(unsealedData, EncryptedKnownContent.class);
      byte[] decryptedMessageByteArray;
      if ("deflate".equals(encryptedKnownContent.getBusinessContent().getContentEncoding())) {
         decryptedMessageByteArray = ConnectorIOUtils.decompress(encryptedKnownContent.getBusinessContent().getValue());
      } else {
         decryptedMessageByteArray = encryptedKnownContent.getBusinessContent().getValue();
      }

      LOG.debug("Content of decrypted business message:[{}]", ConnectorIOUtils.toString(decryptedMessageByteArray, Charset.UTF_8));
      ProcessedMsgResponse<T> processedMsgResponse = new ProcessedMsgResponse(msgResponse, this.toBusinessResponse(clazz, decryptedMessageByteArray), encryptedKnownContent, this.validateMsgXadesT(encryptedKnownContent, encryptedKnownContent.getXades(), projectName), ArrayUtils.isEmpty(encryptedKnownContent.getXades()) ? null : unsealedData);
      return processedMsgResponse;
   }

   private <T> T toBusinessResponse(Class<T> clazz, byte[] unwrappedMessageByteArray) {
      return clazz.equals(byte[].class) ? unwrappedMessageByteArray : ConnectorXmlUtils.toObject(unwrappedMessageByteArray, clazz);
   }

   public byte[] getContent(MsgResponse msgResponse, String projectName) throws TechnicalConnectorException {
      Blob mappedBlob = DomainBlobMapper.mapToBlob(msgResponse.getDetail());
      mappedBlob.setHashTagRequired(config.getBooleanProperty("genericasync" + projectName + ".hashtagrequired", false));
      return BlobBuilderFactory.getBlobBuilder("genericasync").checkAndRetrieveContent(mappedBlob);
   }

   private SignatureVerificationResult validateXadesT(Object value, byte[] xadesT, boolean followNestedManifest) throws GenAsyncBusinessConnectorException {
      if (!ArrayUtils.isEmpty(xadesT)) {
         byte[] signedByteArray = ConnectorXmlUtils.toByteArray(value);
         Map<String, Object> options = new HashMap();
         options.put("followNestedManifest", followNestedManifest);

         try {
            return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify((byte[])signedByteArray, (byte[])xadesT, options);
         } catch (TechnicalConnectorException var7) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, var7, new Object[]{var7.getMessage()});
         }
      } else {
         return null;
      }
   }

   public SignatureVerificationResult validateTAckXadesT(Object value, byte[] xadesT, String projectName) throws GenAsyncBusinessConnectorException {
      return this.validateXadesT(value, xadesT, projectName == null ? false : config.getBooleanProperty("GENERICASYNC" + projectName + ".validation.xades.tack.follownestedmanifest", false));
   }

   public SignatureVerificationResult validateMsgXadesT(Object value, byte[] xadesT, String projectName) throws GenAsyncBusinessConnectorException {
      return this.validateXadesT(value, xadesT, projectName == null ? false : config.getBooleanProperty("GENERICASYNC" + projectName + ".validation.xades.msg.follownestedmanifest", false));
   }

   private void logValidationResult(Map<Object, SignatureVerificationResult> validationResults) {
      LOG.debug("validationResults : -------------------------");
      Iterator var2 = validationResults.keySet().iterator();

      while(var2.hasNext()) {
         Object key = var2.next();
         SignatureVerificationResult signatureVerificationResult = (SignatureVerificationResult)validationResults.get(key);
         StringBuilder errorsSb = new StringBuilder();
         Iterator var6 = signatureVerificationResult.getErrors().iterator();

         while(var6.hasNext()) {
            SignatureVerificationError error = (SignatureVerificationError)var6.next();
            errorsSb.append(error).append(" ");
         }

         LOG.debug("key : " + key + "\t validationResult errors : " + errorsSb.toString());
      }

      LOG.debug("--------------------------------------");
   }

   private Map<Object, SignatureVerificationResult> validateXadesT(Object value, byte[] xadesT) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> vResult = new HashMap();
      if (!ArrayUtils.isEmpty(xadesT)) {
         byte[] signedByteArray = ConnectorXmlUtils.toByteArray(value);
         Map<String, Object> options = new HashMap();

         try {
            SignatureVerificationResult result = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify((byte[])signedByteArray, (byte[])xadesT, options);
            if (!result.isValid()) {
               vResult.put(value, result);
            }
         } catch (TechnicalConnectorException var7) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, var7, new Object[]{var7.getMessage()});
         }
      }

      return vResult;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
   }
}
