package be.recipe.services.executor;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListAddressedPrescriptionItem"
)
public class ListNotificationsItem {
   private String sentBy;
   private Date sentDate;
   private byte[] content;

   public ListNotificationsItem(String sentBy, Date sentDate, byte[] content) {
      this.sentBy = sentBy;
      this.sentDate = sentDate;
      this.content = content;
   }

   public ListNotificationsItem() {
   }

   public String getSentBy() {
      return this.sentBy;
   }

   public void setSentBy(String sentBy) {
      this.sentBy = sentBy;
   }

   public Date getSentDate() {
      return this.sentDate;
   }

   public void setSentDate(Date sentDate) {
      this.sentDate = sentDate;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }
}
