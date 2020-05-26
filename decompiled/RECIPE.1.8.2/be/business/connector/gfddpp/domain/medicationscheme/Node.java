package be.business.connector.gfddpp.domain.medicationscheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {
   private PaginationInfo pagination;
   private List<DataEntry> dataEntries;
   private String name;
   private Integer version;
   private UpdatedBy updatedBy;

   public Node() {
   }

   public Node(String name) {
      this.name = name;
   }

   public PaginationInfo getPagination() {
      return this.pagination;
   }

   public void setPagination(PaginationInfo pagination) {
      this.pagination = pagination;
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

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getVersion() {
      return this.version;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   public UpdatedBy getUpdatedBy() {
      return this.updatedBy;
   }

   public void setUpdatedBy(UpdatedBy updatedBy) {
      this.updatedBy = updatedBy;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Node: ");
      sb.append(this.name);
      Iterator i = this.getDataEntries().iterator();

      while(i.hasNext()) {
         DataEntry de = (DataEntry)i.next();
         sb.append("\n");
         sb.append(de);
      }

      sb.append("\nVersion: ");
      sb.append(this.getVersion());
      return sb.toString();
   }
}
