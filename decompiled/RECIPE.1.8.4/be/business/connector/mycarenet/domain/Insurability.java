package be.business.connector.mycarenet.domain;

public class Insurability {
   private String xml;
   private String messageId;

   public Insurability(String xml, String messageId) {
      this.xml = xml;
      this.messageId = messageId;
   }

   public void setXml(String xml) {
      this.xml = xml;
   }

   public String getXml() {
      return this.xml;
   }

   public void setMessageId(String messageId) {
      this.messageId = messageId;
   }

   public String getMessageId() {
      return this.messageId;
   }
}
