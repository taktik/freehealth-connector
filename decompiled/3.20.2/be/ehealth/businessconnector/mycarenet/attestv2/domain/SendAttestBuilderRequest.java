package be.ehealth.businessconnector.mycarenet.attestv2.domain;

import be.cin.encrypted.BusinessContent;
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest;

public class SendAttestBuilderRequest {
   private SendTransactionRequest sendTransactionRequest;
   private SendAttestationRequest sendAttestationRequest;
   private BusinessContent businessContent;

   public SendAttestBuilderRequest(SendAttestationRequest sendAttestationRequest, SendTransactionRequest sendTransactionRequest, BusinessContent businessContent) {
      this.sendAttestationRequest = sendAttestationRequest;
      this.sendTransactionRequest = sendTransactionRequest;
      this.businessContent = businessContent;
   }

   public SendTransactionRequest getSendTransactionRequest() {
      return this.sendTransactionRequest;
   }

   public SendAttestationRequest getSendAttestationRequest() {
      return this.sendAttestationRequest;
   }

   public BusinessContent getBusinessContent() {
      return this.businessContent;
   }
}
