package be.business.connector.gfddpp.domain.medicationscheme;

import java.util.Calendar;

public class SubjectTimestamp {
   private String subjectID;
   private Calendar lastUpdated;
   private int version;

   public SubjectTimestamp() {
   }

   public SubjectTimestamp(String subjectId, Calendar lastUpdated, int version) {
      this.subjectID = subjectId;
      this.lastUpdated = lastUpdated;
      this.version = version;
   }

   public String getSubjectID() {
      return this.subjectID;
   }

   public void setSubjectID(String subjectID) {
      this.subjectID = subjectID;
   }

   public Calendar getLastUpdated() {
      return this.lastUpdated;
   }

   public void setLastUpdated(Calendar lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Subject of Timestamp: ");
      sb.append(this.subjectID);
      sb.append("\n\t lastUpdated: ");
      sb.append(this.lastUpdated);
      sb.append("\n\t version: ");
      sb.append(this.version);
      return sb.toString();
   }
}
