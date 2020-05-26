package be.business.connector.gfddpp.tipsystem.offline.queue;

public class OfflineQueueAssitant {
   private boolean removeFromQueue;
   private boolean createStatusMessage;
   private boolean stopOfflineQueue;
   private boolean addBackToQueue;
   private boolean moveToUnencryptable;
   private boolean moveToInvalid;

   public boolean isMoveToUnencryptable() {
      return this.moveToUnencryptable;
   }

   public void setMoveToUnencryptable(boolean moveToUnencryptable) {
      this.moveToUnencryptable = moveToUnencryptable;
   }

   public boolean isMoveToInvalid() {
      return this.moveToInvalid;
   }

   public void setMoveToInvalid(boolean moveToInvalid) {
      this.moveToInvalid = moveToInvalid;
   }

   public OfflineQueueAssitant() {
      this.removeFromQueue = false;
      this.createStatusMessage = false;
      this.stopOfflineQueue = false;
      this.addBackToQueue = false;
      this.moveToUnencryptable = false;
      this.moveToInvalid = false;
   }

   public OfflineQueueAssitant(boolean removeFromQueue, boolean createStatusMessage, boolean stopOfflineQueue) {
      this.removeFromQueue = removeFromQueue;
      this.createStatusMessage = createStatusMessage;
      this.stopOfflineQueue = stopOfflineQueue;
   }

   public boolean isRemoveFromQueue() {
      return this.removeFromQueue;
   }

   public boolean isAddBackToQueue() {
      return this.addBackToQueue;
   }

   public void setAddBackToQueue(boolean addBackToQueue) {
      this.addBackToQueue = addBackToQueue;
   }

   public void setRemoveFromQueue(boolean removeFromQueue) {
      this.removeFromQueue = removeFromQueue;
   }

   public boolean isCreateStatusMessage() {
      return this.createStatusMessage;
   }

   public void setCreateStatusMessage(boolean createStatusMessage) {
      this.createStatusMessage = createStatusMessage;
   }

   public boolean isStopOfflineQueue() {
      return this.stopOfflineQueue;
   }

   public void setStopOfflineQueue(boolean stopOfflineQueue) {
      this.stopOfflineQueue = stopOfflineQueue;
   }

   public void removeAndCreateStatusMessage() {
      this.createStatusMessage = true;
      this.removeFromQueue = true;
   }
}
