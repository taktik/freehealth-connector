package org.taktik.connector.business.dmg.domain;

import org.taktik.connector.technical.utils.ByteArrayDatasource;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendResponseType;
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionResponse;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public class DmgBuilderResponse {
   public static final String RESULT_EHEALTH_STATUS = "result.ehealth.status";
   public static final String RESULT_ORGINAL = "result.original";
   public static final String RESULT_SIGINATURE_VERIFICATION = "result.signature.verification";
   public static final String RESULT_HAS_SIGNATURE = "result.has.signature";
   public static final String RESULT_AS_BYTE = "result.business.byte";
   public static final String RESULT_AS_OBJ_RETRIEVE = "result.business.RetrieveTransactionResponse";
   public static final String RESULT_AS_OBJ_SEND = "result.business.SendTransactionResponse";
   public static final String RESULT_AS_KMEHR_MESSAGE = "result.business.KmehrMessage";
   private Map<String, Object> result;

   public DmgBuilderResponse(Map<String, Object> result) {
      this.result = result;
   }

   private <T> T transform(String key, Class<T> clazz) {
      if (this.result.containsKey(key)) {
         Object resultObj = this.result.get(key);
         if (resultObj instanceof IllegalArgumentException) {
            throw (IllegalArgumentException)resultObj;
         }

         if (clazz.isInstance(resultObj)) {
            return (T) resultObj;
         }
      }

      return null;
   }

   public Map<String, Object> getResult() {
      return this.result;
   }

   public String getEhealthStatus() {
      return (String)this.transform("result.ehealth.status", String.class);
   }

   public SendResponseType getOriginalResponse() {
      return (SendResponseType)this.transform("result.original", SendResponseType.class);
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return (SignatureVerificationResult)this.transform("result.signature.verification", SignatureVerificationResult.class);
   }

   public RetrieveTransactionResponse getRetrieveTransactionResponse() {
      return (RetrieveTransactionResponse)this.transform("result.business.RetrieveTransactionResponse", RetrieveTransactionResponse.class);
   }

   public Kmehrmessage getKmehrmessage() {
      return (Kmehrmessage)this.transform("result.business.KmehrMessage", Kmehrmessage.class);
   }

   public SendTransactionResponse getSendTransactionResponse() {
      return (SendTransactionResponse)this.transform("result.business.SendTransactionResponse", SendTransactionResponse.class);
   }

   public byte[] getResponse() {
      return ((ByteArrayDatasource)this.transform("result.business.byte", ByteArrayDatasource.class)).getByteArray();
   }
}
