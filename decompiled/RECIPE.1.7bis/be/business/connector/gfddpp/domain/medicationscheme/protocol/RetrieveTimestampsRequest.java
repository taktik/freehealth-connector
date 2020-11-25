package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import java.util.ArrayList;
import java.util.List;

public class RetrieveTimestampsRequest extends BaseRequest {
   private List<String> subjectIDs;

   public RetrieveTimestampsRequest() {
   }

   public RetrieveTimestampsRequest(List<String> subjectIDs) {
      this.subjectIDs = subjectIDs;
   }

   public RetrieveTimestampsRequest(String... subjectID) {
      for(int i = 0; i < subjectID.length; ++i) {
         String s = subjectID[i];
         this.getSubjectIDs().add(s);
      }

   }

   public List<String> getSubjectIDs() {
      if (this.subjectIDs == null) {
         this.subjectIDs = new ArrayList();
      }

      return this.subjectIDs;
   }

   public void setSubjectIDs(List<String> subjectIDs) {
      this.subjectIDs = subjectIDs;
   }
}
