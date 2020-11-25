package be.recipe.services.prescriber;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListFeedbackItem"
)
public class ListFeedbackItem {
   private String rid = null;
   private String sentBy = null;
   private Calendar sentDate = null;
   private byte[] content = null;

   public ListFeedbackItem(String rid, String sentBy, Date sentDate, byte[] content) {
      this.rid = rid;
      this.sentBy = sentBy;
      Calendar c = GregorianCalendar.getInstance();
      c.setTime(sentDate);
      this.sentDate = c;
      this.content = content;
   }

   public ListFeedbackItem() {
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getSentBy() {
      return this.sentBy;
   }

   public void setSentBy(String sentBy) {
      this.sentBy = sentBy;
   }

   public Calendar getSentDate() {
      return this.sentDate;
   }

   public void setSentDate(Calendar sentDate) {
      this.sentDate = sentDate;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }
}
