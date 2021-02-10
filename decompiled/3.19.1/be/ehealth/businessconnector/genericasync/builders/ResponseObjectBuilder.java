package be.ehealth.businessconnector.genericasync.builders;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public interface ResponseObjectBuilder extends ConfigurableImplementation {
   boolean handlePostResponse(PostResponse var1) throws GenAsyncBusinessConnectorException;

   Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse var1) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse var1, String var2) throws GenAsyncBusinessConnectorException;

   <T> ProcessedGetResponse processResponse(GetResponse var1, Class<T> var2, String var3, String var4) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   <T> ProcessedMsgResponse<T> processEncryptedResponse(MsgResponse var1, String var2, Class<T> var3) throws TechnicalConnectorException, GenAsyncBusinessConnectorException;

   SignatureVerificationResult validateTAckXadesT(Object var1, byte[] var2, String var3) throws GenAsyncBusinessConnectorException;

   SignatureVerificationResult validateMsgXadesT(Object var1, byte[] var2, String var3) throws GenAsyncBusinessConnectorException;
}
