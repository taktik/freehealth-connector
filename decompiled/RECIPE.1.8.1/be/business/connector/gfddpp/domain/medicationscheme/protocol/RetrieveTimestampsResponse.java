package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.Status;
import be.business.connector.gfddpp.domain.medicationscheme.SubjectTimestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RetrieveTimestampsResponse extends BaseResponse {
   private Calendar currentDateTime;
   private List<SubjectTimestamp> subjects;

   public RetrieveTimestampsResponse() {
   }

   public RetrieveTimestampsResponse(String serverMessageID, Status status, Calendar currentDateTime, List<SubjectTimestamp> subjects) {
      this.serverMessageID = serverMessageID;
      this.status = status;
      this.currentDateTime = currentDateTime;
      this.subjects = subjects;
   }

   public RetrieveTimestampsResponse(String serverMessageID, Status status) {
      this.serverMessageID = serverMessageID;
      this.status = status;
   }

   public List<SubjectTimestamp> getSubjects() {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      return this.subjects;
   }

   public void setSubjects(List<SubjectTimestamp> subjects) {
      this.subjects = subjects;
   }

   public Calendar getCurrentDateTime() {
      return this.currentDateTime;
   }

   public void setCurrentDateTime(Calendar currentDateTime) {
      this.currentDateTime = currentDateTime;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Retrieve Timestamps Response");
      sb.append("\n\t status: ");
      sb.append(this.status);
      sb.append("\n\t current date time: ");
      sb.append(this.currentDateTime);
      sb.append("\n\t subjects: ");
      sb.append(this.subjects);
      return sb.toString();
   }
}
