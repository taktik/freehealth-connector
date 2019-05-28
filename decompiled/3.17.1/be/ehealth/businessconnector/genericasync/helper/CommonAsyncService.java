package be.ehealth.businessconnector.genericasync.helper;

import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.TAckResponse;
import be.cin.types.v1.Blob;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedTAckResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public interface CommonAsyncService {
   ConfirmResponse confirmAll(ProcessedGetResponse var1) throws ConnectorException;

   ConfirmResponse confirmAllTAcks(ProcessedGetResponse var1) throws ConnectorException;

   ConfirmResponse confirmAllMessages(ProcessedGetResponse var1) throws ConnectorException;

   ConfirmResponse confirmTAck(ProcessedTAckResponse var1) throws ConnectorException;

   ConfirmResponse confirmMessage(ProcessedMsgResponse var1) throws ConnectorException;

   SignatureVerificationResult validateXadesTWithManifest(TAckResponse var1, Blob var2, byte[] var3) throws GenAsyncBusinessConnectorException;
}
