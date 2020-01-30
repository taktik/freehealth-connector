package be.business.connector.gfddpp.domain.medicationscheme;

public class Pagination {
   private String uri;
   private int paginationIndex;

   public Pagination() {
   }

   public Pagination(String uri, int paginationIndex) {
      this.uri = uri;
      this.paginationIndex = paginationIndex;
   }

   public String getUri() {
      return this.uri;
   }

   public void setUri(String uri) {
      this.uri = uri;
   }

   public int getPaginationIndex() {
      return this.paginationIndex;
   }

   public void setPaginationIndex(int paginationIndex) {
      this.paginationIndex = paginationIndex;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Pagination: ");
      sb.append(this.uri);
      sb.append("\n\t index: ");
      sb.append(this.paginationIndex);
      return sb.toString();
   }
}
