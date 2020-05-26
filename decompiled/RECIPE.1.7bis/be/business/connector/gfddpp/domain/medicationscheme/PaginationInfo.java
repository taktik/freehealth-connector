package be.business.connector.gfddpp.domain.medicationscheme;

public class PaginationInfo extends Pagination {
   private int paginationSize;
   private int recordCount;

   public PaginationInfo() {
   }

   public PaginationInfo(String uri, int paginationIndex, int paginationSize, int recordCount) {
      super(uri, paginationIndex);
      this.paginationSize = paginationSize;
      this.recordCount = recordCount;
   }

   public int getPaginationSize() {
      return this.paginationSize;
   }

   public void setPaginationSize(int paginationSize) {
      this.paginationSize = paginationSize;
   }

   public int getRecordCount() {
      return this.recordCount;
   }

   public void setRecordCount(int recordCount) {
      this.recordCount = recordCount;
   }

   public boolean hasNextPage() {
      int index = this.getPaginationIndex();
      int count = this.getRecordCount();
      if (index == 0 && count > 0) {
         return true;
      } else {
         int page = this.getPaginationSize();
         int startIndex = index + page;
         return startIndex <= count;
      }
   }

   public Pagination getNextPagination() {
      if (this.hasNextPage()) {
         if (this.getPaginationIndex() == 0) {
            return new Pagination(this.getUri(), 1);
         } else {
            int index = this.getPaginationIndex();
            int page = this.getPaginationSize();
            int startIndex = index + page;
            return new Pagination(this.getUri(), startIndex);
         }
      } else {
         return null;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString());
      sb.append("\n\t size: ");
      sb.append(this.paginationSize);
      sb.append("\n\t count: ");
      sb.append(this.recordCount);
      return sb.toString();
   }
}
