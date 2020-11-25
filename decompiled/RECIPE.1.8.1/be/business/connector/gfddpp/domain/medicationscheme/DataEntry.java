package be.business.connector.gfddpp.domain.medicationscheme;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataEntry {
   private Map<String, String> metadata;
   private byte[] businessData;
   private String dataEntryURI;
   private String reference;
   private Integer nodeVersion;

   public DataEntry() {
   }

   public DataEntry(String dataEntryURI) {
      this.dataEntryURI = dataEntryURI;
   }

   public DataEntry(String dataEntryURI, byte[] businessData, String reference) {
      this(dataEntryURI);
      this.setBusinessData(businessData);
      this.setReference(reference);
   }

   public DataEntry(String dataEntryURI, byte[] businessData, String reference, Integer nodeVersion) {
      this(dataEntryURI);
      this.setBusinessData(businessData);
      this.setReference(reference);
      this.setNodeVersion(nodeVersion);
   }

   public DataEntry withMetadata(Map<String, String> metadata) {
      this.metadata = metadata;
      return this;
   }

   public Map<String, String> getMetadata() {
      if (this.metadata == null) {
         this.metadata = new HashMap();
      }

      return this.metadata;
   }

   public void setMetadata(Map<String, String> metadata) {
      this.metadata = metadata;
   }

   public byte[] getBusinessData() {
      return this.businessData == null ? null : (byte[])this.businessData.clone();
   }

   public void setBusinessData(byte[] businessData) {
      if (businessData != null) {
         this.businessData = (byte[])businessData.clone();
      }

   }

   public String getDataEntryURI() {
      return this.dataEntryURI;
   }

   public void setDataEntryURI(String dataEntryURI) {
      this.dataEntryURI = dataEntryURI;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public Integer getNodeVersion() {
      return this.nodeVersion;
   }

   public void setNodeVersion(Integer nodeVersion) {
      this.nodeVersion = nodeVersion;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("DataEntry: ");
      sb.append(this.dataEntryURI);
      sb.append("\nReference: ");
      sb.append(this.reference);
      if (this.metadata != null) {
         sb.append("\nMetadata: ");
         Iterator i = this.metadata.keySet().iterator();

         while(i.hasNext()) {
            String key = (String)i.next();
            sb.append("\n\t");
            sb.append(key);
            sb.append(": ");
            sb.append((String)this.metadata.get(key));
         }
      }

      if (this.businessData != null) {
         sb.append("\nBusiness Data size: ");
         sb.append(this.businessData.length);
      }

      return sb.toString();
   }
}
