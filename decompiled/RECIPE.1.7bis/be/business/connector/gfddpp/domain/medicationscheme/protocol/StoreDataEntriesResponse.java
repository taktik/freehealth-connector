package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.DataEntry;
import be.business.connector.gfddpp.domain.medicationscheme.Status;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StoreDataEntriesResponse extends BaseResponse {
   private String subjectID;
   private Calendar lastUpdated;
   private int version;
   private List<DataEntry> dataEntries;

   public StoreDataEntriesResponse() {
   }

   public StoreDataEntriesResponse(String serverMessageID, Status status) {
      this.serverMessageID = serverMessageID;
      this.status = status;
   }

   public List<DataEntry> getDataEntries() {
      if (this.dataEntries == null) {
         this.dataEntries = new ArrayList();
      }

      return this.dataEntries;
   }

   public void setDataEntries(List<DataEntry> dataEntries) {
      this.dataEntries = dataEntries;
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
      sb.append("Store Data Entries Response");
      sb.append("\n\t status: ");
      sb.append(this.status);
      sb.append("\n\t subjectID: ");
      sb.append(this.subjectID);
      sb.append("\n\t lastUpdated: ");
      sb.append(this.lastUpdated);
      sb.append("\n\t version: ");
      sb.append(this.version);
      sb.append("\n\t dataEntries: ");
      sb.append(this.dataEntries);
      return sb.toString();
   }
}
