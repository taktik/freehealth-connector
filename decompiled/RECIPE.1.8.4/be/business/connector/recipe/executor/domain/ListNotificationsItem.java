package be.business.connector.recipe.executor.domain;

import be.business.connector.core.exceptions.IntegrationModuleException;
import java.util.Calendar;
import java.util.Date;

public class ListNotificationsItem extends be.recipe.services.executor.ListNotificationsItem {
   be.recipe.services.executor.ListNotificationsItem root = null;
   Throwable linkedException = null;

   public Throwable getLinkedException() {
      return this.linkedException;
   }

   public ListNotificationsItem(be.recipe.services.executor.ListNotificationsItem root) {
      this.root = root;
   }

   public ListNotificationsItem() {
   }

   public void setLinkedException(Throwable linkedException) {
      this.linkedException = linkedException;
   }

   public byte[] getContent() {
      if (this.linkedException != null) {
         throw new RuntimeException(this.linkedException);
      } else {
         return this.root != null ? this.root.getContent() : null;
      }
   }

   public String getSentBy() {
      return this.root.getSentBy();
   }

   public void setContent(byte[] arg0) {
      this.root.setContent(arg0);
   }

   public void setSentBy(String arg0) {
      this.root.setSentBy(arg0);
   }

   public void setSentDate(Date arg0) throws IntegrationModuleException {
      Calendar c = Calendar.getInstance();
      c.setTime(arg0);
      this.root.setSentDate(c);
   }

   public boolean equals(Object obj) {
      return this.root.equals(obj);
   }

   public int hashCode() {
      return this.root.hashCode();
   }

   public String toString() {
      return this.root.toString();
   }
}
