package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.BreakTheGlass;
import be.business.connector.gfddpp.domain.medicationscheme.Pagination;

public class FetchDataEntriesRequest extends BaseRequest {
   private String subjectID;
   private Pagination pagination;
   private BreakTheGlass breakTheGlass;
   private boolean includeBusinessData;
   private String searchCriteria;
   private Integer version;

   public FetchDataEntriesRequest() {
   }

   public FetchDataEntriesRequest(String subjectID, boolean includeBusinessData) {
      this.subjectID = subjectID;
      this.includeBusinessData = includeBusinessData;
   }

   public String getSubjectID() {
      return this.subjectID;
   }

   public void setSubjectID(String subjectID) {
      this.subjectID = subjectID;
   }

   public Pagination getPagination() {
      return this.pagination;
   }

   public void setPagination(Pagination pagination) {
      this.pagination = pagination;
   }

   public BreakTheGlass getBreakTheGlass() {
      return this.breakTheGlass;
   }

   public void setBreakTheGlass(BreakTheGlass breakTheGlass) {
      this.breakTheGlass = breakTheGlass;
   }

   public boolean includeBusinessData() {
      return this.includeBusinessData;
   }

   public void setIncludeBusinessData(boolean includeBusinessData) {
      this.includeBusinessData = includeBusinessData;
   }

   public boolean isIncludeBusinessData() {
      return this.includeBusinessData;
   }

   public String getSearchCriteria() {
      return this.searchCriteria;
   }

   public void setSearchCriteria(String searchCriteria) {
      this.searchCriteria = searchCriteria;
   }

   public Integer getVersion() {
      return this.version;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Fetch Data Entries Request");
      sb.append("\n\t subjectID: ");
      sb.append(this.subjectID);
      sb.append(super.toString());
      sb.append("\n\t includeBusinessData: ");
      sb.append(this.includeBusinessData);
      if (this.searchCriteria != null && !"".equalsIgnoreCase(this.searchCriteria)) {
         sb.append("\n\t searchCriteria: ");
         sb.append(this.searchCriteria);
      }

      if (this.breakTheGlass != null) {
         sb.append("\n\t breakTheGlass: ");
         sb.append(this.breakTheGlass);
      }

      if (this.pagination != null) {
         sb.append("\n\t pagination: ");
         sb.append(this.pagination);
      }

      return sb.toString();
   }
}
