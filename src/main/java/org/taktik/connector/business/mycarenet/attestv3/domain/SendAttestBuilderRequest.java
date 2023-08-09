package org.taktik.connector.business.mycarenet.attestv3.domain;

import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationRequest;

public class SendAttestBuilderRequest {
   private SendTransactionRequest sendTransactionRequest;
   private SendAttestationRequest sendAttestationRequest;

   public SendAttestBuilderRequest(SendAttestationRequest sendAttestationRequest, SendTransactionRequest sendTransactionRequest) {
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
