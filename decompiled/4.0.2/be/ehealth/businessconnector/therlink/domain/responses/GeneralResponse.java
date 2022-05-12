package be.ehealth.businessconnector.therlink.domain.responses;

import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

public class GeneralResponse {
   protected String externalID;
   protected Author externalAuthor;
   protected DateTime dateTime;
   protected TherapeuticLinkRequestType originalRequest;
   protected Acknowledge acknowledge;

   public GeneralResponse() {
   }

   public String getExternalID() {
      return this.externalID;
   }

   public void setExternalID(String externalID) {
      this.externalID = externalID;
   }

   public Acknowledge getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(Acknowledge acknowledge) {
      this.acknowledge = acknowledge;
   }

   public Author getExternalAuthor() {
      return this.externalAuthor;
   }

   public void setExternalAuthor(Author externalAuthor) {
      this.externalAuthor = externalAuthor;
   }

   public DateTime getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(DateTime dateTime) {
      this.dateTime = dateTime;
   }

   public TherapeuticLinkRequestType getOriginalRequest() {
      return this.originalRequest;
   }

   public void setOriginalRequest(TherapeuticLinkRequestType originalRequest) {
      this.originalRequest = originalRequest;
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      builder.append("GeneralResponse [externalID = ");
      builder.append(this.externalID);
      builder.append(", externalAuthor = ");
      builder.append(this.externalAuthor.toString());
      if (this.dateTime != null) {
         builder.append(", dateTime = ");
         builder.append(df.format(this.dateTime.toDate()));
      }

      builder.append(", originalRequest = ");
      builder.append(this.originalRequest.toString());
      builder.append(", acknowledge = ");
      builder.append(this.acknowledge.toString());
      builder.append("]");
      return builder.toString();
   }
}
