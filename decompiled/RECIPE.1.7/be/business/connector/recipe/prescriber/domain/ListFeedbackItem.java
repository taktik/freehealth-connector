package org.taktik.connector.business.recipe.prescriber.domain;

import java.util.Calendar;

public class ListFeedbackItem extends be.recipe.services.prescriber.ListFeedbackItem {
   be.recipe.services.prescriber.ListFeedbackItem root = null;
   Throwable linkedException = null;

   public Throwable getLinkedException() {
      return this.linkedException;
   }

   public void setLinkedException(Throwable linkedException) {
      this.linkedException = linkedException;
   }

   public ListFeedbackItem(be.recipe.services.prescriber.ListFeedbackItem root) {
      this.root = root;
   }

   public boolean equals(Object obj) {
      return this.root.equals(obj);
   }

   public byte[] getContent() {
      if (this.linkedException != null) {
         throw new RuntimeException(this.linkedException);
      } else {
         return this.root.getContent();
      }
   }

   public String getRid() {
      return this.root.getRid();
   }

   public String getSentBy() {
      return this.root.getSentBy();
   }

   public Calendar getSentDate() {
      return this.root.getSentDate();
   }

   public int hashCode() {
      return this.root.hashCode();
   }

   public void setContent(byte[] arg0) {
      this.root.setContent(arg0);
   }

   public void setRid(String arg0) {
      this.root.setRid(arg0);
   }

   public void setSentBy(String arg0) {
      this.root.setSentBy(arg0);
   }

   public void setSentDate(Calendar arg0) {
      this.root.setSentDate(arg0);
   }

   public String toString() {
      return this.root.toString();
   }
}
