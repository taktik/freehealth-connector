package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.DataEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreDataEntriesRequest extends BaseRequest {
   private String subjectID;
   private List<DataEntry> dataEntries;

   public StoreDataEntriesRequest() {
   }

   public StoreDataEntriesRequest(String subjectID) {
      this.subjectID = subjectID;
   }

   public StoreDataEntriesRequest(String subjectID, List<DataEntry> dataEntries) {
      this.subjectID = subjectID;
      this.dataEntries = dataEntries;
   }

   public StoreDataEntriesRequest(String subjectID, DataEntry... dataEntry) {
      this.subjectID = subjectID;
      this.getDataEntries().addAll(Arrays.asList(dataEntry));
   }

   public String getSubjectID() {
      return this.subjectID;
   }

   public void setSubjectID(String subjectID) {
      this.subjectID = subjectID;
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

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Store Data Entries Request");
      sb.append("\n\t subjectID: ");
      sb.append(this.subjectID);
      sb.append(super.toString());
      sb.append("\n\t dataEntries: ");
      sb.append(this.dataEntries);
      return sb.toString();
   }
}
