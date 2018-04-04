package org.taktik.connector.business.genericasync.builders.impl;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.PostResponse;
import be.cin.nip.async.generic.TAck;
import be.cin.nip.async.generic.TAckResponse;
import org.taktik.connector.business.genericasync.builders.ResponseObjectBuilder;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import org.taktik.connector.business.genericasync.exception.GenAsyncSignatureValidationConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
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

            return hasWarning;
         }
      } else {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"the postResponse or postResponse.return was null"});
      }
   }

   public final Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse getResponse) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> validationResult = new HashMap();
      Iterator i$ = getResponse.getReturn().getTAckResponses().iterator();

      while(i$.hasNext()) {
         TAckResponse value = (TAckResponse)i$.next();
         LOG.debug("handleGetResponse : tackResponse : xades : " + value.getXadesT() + ", tack : " + value.getTAck());
         validationResult.putAll(this.validateXadesT(value, value.getXadesT().getValue()));
      }

      i$ = getResponse.getReturn().getMsgResponses().iterator();

      while(i$.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)i$.next();
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

   private void logValidationResult(Map<Object, SignatureVerificationResult> validationResults) {
      LOG.debug("validationResults : -------------------------");
      Iterator i$ = validationResults.keySet().iterator();

      while(i$.hasNext()) {
         Object key = i$.next();
         SignatureVerificationResult signatureVerificationResult = (SignatureVerificationResult)validationResults.get(key);
         StringBuilder errorsSb = new StringBuilder();
         Iterator ii$ = signatureVerificationResult.getErrors().iterator();

         while(ii$.hasNext()) {
            SignatureVerificationError error = (SignatureVerificationError)ii$.next();
            errorsSb.append(error).append(" ");
         }

         LOG.debug("key : " + key + "\t" + " validationResult errors : " + errorsSb.toString());
      }

      LOG.debug("--------------------------------------");
   }

   private Map<Object, SignatureVerificationResult> validateXadesT(Object value, byte[] xadesT) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> vResult = new HashMap();
      if (!ArrayUtils.isEmpty(xadesT)) {
         byte[] signedByteArray = ConnectorXmlUtils.toByteArray(value);
         HashMap options = new HashMap();

         try {
            SignatureVerificationResult result = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify(signedByteArray, xadesT, options);
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
