package be.ehealth.businessconnector.mycarenet.attestv2.domain;

import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;

public class CancelAttestBuilderRequest {
   private SendTransactionRequest sendTransactionRequest;
   private CancelAttestationRequest cancelAttestationRequest;

   public CancelAttestBuilderRequest(CancelAttestationRequest cancelAttestationRequest, SendTransactionRequest sendTransactionRequest) {
      this.cancelAttestationRequest = cancelAttestationRequest;
      this.sendTransactionRequest = sendTransactionRequest;
   }

   public SendTransactionRequest getSendTransactionRequest() {
      return this.sendTransactionRequest;
   }

   public CancelAttestationRequest getCancelAttestationRequest() {
      return this.cancelAttestationRequest;
   }
}
