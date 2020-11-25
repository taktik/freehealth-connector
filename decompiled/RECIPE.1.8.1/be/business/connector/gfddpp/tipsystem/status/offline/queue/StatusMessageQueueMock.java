package be.business.connector.gfddpp.tipsystem.status.offline.queue;

import be.apb.gfddpp.common.utils.SingleMessageBuilder;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import java.util.ArrayList;

public class StatusMessageQueueMock extends StatusMessageQueue {
   private SingleMessageBuilder smb = new SingleMessageBuilder();
   private static ArrayList<StatusMessageType> queue = new ArrayList();

   public boolean add(StatusMessageType statusMessage) {
      queue.add(statusMessage);
      return true;
   }

   public StatusMessageType remove() {
      return (StatusMessageType)queue.remove(0);
   }

   public StatusMessageType poll() {
      return !this.isEmpty() ? this.remove() : null;
   }

   public boolean isEmpty() {
      return queue.isEmpty();
   }
}
