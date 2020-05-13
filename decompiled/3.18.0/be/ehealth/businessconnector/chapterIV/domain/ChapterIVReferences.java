package be.ehealth.businessconnector.chapterIV.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;

public final class ChapterIVReferences implements Serializable {
   private static final long serialVersionUID = 5945745713020351653L;
   private String commonReference;
   private String commonNIPReference;
   private String kmehrIdSuffix;
   private String recordCommonInputId;

   public ChapterIVReferences(boolean init) throws TechnicalConnectorException {
      if (init) {
         String id = IdGeneratorFactory.getIdGenerator().generateId();
         this.commonReference = id;
         this.recordCommonInputId = id;
         this.kmehrIdSuffix = id;
      }

   }

   public ChapterIVReferences(String id) {
      this.commonReference = id;
      this.recordCommonInputId = id;
      this.kmehrIdSuffix = id;
   }

   public String getCommonReference() {
      return this.commonReference;
   }

   public void setCommonReference(String commonReference) {
      this.commonReference = commonReference;
   }

   public String getCommonNIPReference() {
      return this.commonNIPReference;
   }

   public void setCommonNIPReference(String commonNIPReference) {
      this.commonNIPReference = commonNIPReference;
   }

   public String getKmehrIdSuffix() {
      return this.kmehrIdSuffix;
   }

   public void setKmehrIdSuffix(String kmehrIdSuffix) {
      this.kmehrIdSuffix = kmehrIdSuffix;
   }

   public String getRecordCommonInputId() {
      return this.recordCommonInputId;
   }

   public void setRecordCommonInputId(String recordCommonInputId) {
      this.recordCommonInputId = recordCommonInputId;
   }

   public int hashCode() {
      int prime = true;
      int result = 1;
      int result = 31 * result + (this.commonNIPReference == null ? 0 : this.commonNIPReference.hashCode());
      result = 31 * result + (this.commonReference == null ? 0 : this.commonReference.hashCode());
      result = 31 * result + (this.kmehrIdSuffix == null ? 0 : this.kmehrIdSuffix.hashCode());
      result = 31 * result + (this.recordCommonInputId == null ? 0 : this.recordCommonInputId.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ChapterIVReferences other = (ChapterIVReferences)obj;
         return (new EqualsBuilder()).append(this.commonNIPReference, other.getCommonNIPReference()).append(this.commonReference, other.getCommonReference()).append(this.getKmehrIdSuffix(), other.getKmehrIdSuffix()).append(this.recordCommonInputId, other.getRecordCommonInputId()).isEquals();
      }
   }
}
