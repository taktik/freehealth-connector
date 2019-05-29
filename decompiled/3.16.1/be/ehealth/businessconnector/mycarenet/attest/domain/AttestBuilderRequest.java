package be.ehealth.businessconnector.mycarenet.attest.domain;

import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;

public class AttestBuilderRequest {
   private SendTransactionRequest sendTransactionRequest;
   private SendAttestationRequest sendAttestationRequest;

   public AttestBuilderRequest(SendAttestationRequest sendAttestationRequest, SendTransactionRequest sendTransactionRequest) {
      this.sendAttestationRequest = sendAttestationRequest;
      this.sendTransactionRequest = sendTransactionRequest;
   }

   public SendTransactionRequest getSendTransactionRequest() {
      return this.sendTransactionRequest;
   }

   public SendAttestationRequest getSendAttestationRequest() {
      return this.sendAttestationRequest;
   }
}
