package be.business.connector.gfddpp.tipsystem.offline.queue;

import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import java.io.Serializable;

public class EHealthMessageQueueFacade {
   private MessageQueue<EHealthMessage> queue;

   public EHealthMessageQueueFacade(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) {
      try {
         this.queue = new MessageQueue(propertyHandler, encryptionUtils);
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public MessageQueue<EHealthMessage> getQueue() {
      return this.queue;
   }

   public void registerData(byte[] msg, String dGuidSGuid, String mGuid, String pharmacyId) {
      this.queue.add((Serializable)(new EHealthMessage(MethodName.REGISTERDATA.toString(), msg, dGuidSGuid, mGuid, pharmacyId)));
   }

   public void updateData(byte[] msg, String dGuidSGuid, String mGuid, String pharmacyId) {
      this.queue.add((Serializable)(new EHealthMessage(MethodName.UPDATEDATA.toString(), msg, dGuidSGuid, mGuid, pharmacyId)));
   }

   public void deleteData(byte[] msg, String dGuidSGuid, String mGuid, String pharmacyId) {
      this.queue.add((Serializable)(new EHealthMessage(MethodName.REVOKEDATA.toString(), msg, dGuidSGuid, mGuid, pharmacyId)));
   }
}
