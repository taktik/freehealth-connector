package org.taktik.connector.business.recipe.executor.domain;

import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;

public class GetPrescriptionForExecutorResult extends GetPrescriptionForExecutorResultSealed {
   private byte[] sealedContent;
   private byte[] encryptionKey;
   private String insurabilityResponse;
   private String messageId;

   public GetPrescriptionForExecutorResult() {
   }

   public GetPrescriptionForExecutorResult(GetPrescriptionForExecutorResultSealed root) {
      this.setCreationDate(root.getCreationDate());
      this.setEncryptionKeyId(root.getEncryptionKeyId());
      this.setFeedbackAllowed(root.isFeedbackAllowed());
      this.setPatientId(root.getPatientId());
      this.setPrescriberId(root.getPrescriberId());
      this.setPrescription(root.getPrescription());
      this.setPrescriptionType(root.getPrescriptionType());
      this.setRid(root.getRid());
      this.setTimestampingId(root.getTimestampingId());
   }

   public byte[] getSealedContent() {
      return this.sealedContent;
   }

   public void setSealedContent(byte[] sealedContent) {
      this.sealedContent = sealedContent;
   }

   public byte[] getEncryptionKey() {
      return this.encryptionKey;
   }

   public void setEncryptionKey(byte[] encryptionKey) {
      this.encryptionKey = encryptionKey;
   }

   public void setInsurabilityResponse(String insurabilityResponse) {
      this.insurabilityResponse = insurabilityResponse;
   }

   public String getInsurabilityResponse() {
      return this.insurabilityResponse;
   }

   public void setMessageId(String messageId) {
      this.messageId = messageId;
   }

   public String getMessageId() {
      return this.messageId;
   }
}
