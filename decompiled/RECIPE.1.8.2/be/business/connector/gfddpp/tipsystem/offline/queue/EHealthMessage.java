package be.business.connector.gfddpp.tipsystem.offline.queue;

import java.io.Serializable;

public class EHealthMessage implements Serializable {
   private static final long serialVersionUID = 1L;
   private byte[] sealedMsgRequestParam;
   private String dGuidSGuid;
   private String messageId;
   private String pharmacyId;
   private String methodName;

   public EHealthMessage(String methodName, byte[] msg, String dGuidSGuid, String mGuid, String pharmacyId) {
      this.methodName = methodName;
      this.sealedMsgRequestParam = msg;
      this.dGuidSGuid = dGuidSGuid;
      this.messageId = mGuid;
      this.pharmacyId = pharmacyId;
   }

   public String getMethodName() {
      return this.methodName;
   }

   public byte[] getMessage() {
      return this.sealedMsgRequestParam;
   }

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String messageId) {
      this.messageId = messageId;
   }

   public String getPharmacyId() {
      return this.pharmacyId;
   }

   public void setPharmacyId(String pharmacyId) {
      this.pharmacyId = pharmacyId;
   }

   public String getdGuidSGuid() {
      return this.dGuidSGuid;
   }

   public void setdGuidSGuid(String dGuidSGuid) {
      this.dGuidSGuid = dGuidSGuid;
   }
}
