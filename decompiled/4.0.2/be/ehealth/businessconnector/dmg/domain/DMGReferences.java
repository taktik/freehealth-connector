package be.ehealth.businessconnector.dmg.domain;

import be.ehealth.business.kmehrcommons.util.KmehrIdGenerator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import java.io.Serializable;

public final class DMGReferences implements Serializable {
   private static final long serialVersionUID = -2988858804530750823L;
   private String inputReference;
   private String blobId;

   public DMGReferences(boolean init) throws TechnicalConnectorException {
      if (init) {
         String id = IdGeneratorFactory.getIdGenerator("kmehr").generateId();
         this.inputReference = id;
         this.blobId = IdGeneratorFactory.getIdGenerator("uuid").generateId();
      }

   }

   public DMGReferences(String id) {
      this.inputReference = id;
   }

   public String getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(String inputReference) {
      this.inputReference = inputReference;
   }

   public String getKmehrIdSuffix() {
      return this.inputReference;
   }

   public String getBlobId() {
      return this.blobId;
   }

   public void setBlobId(String blobId) {
      this.blobId = blobId;
   }

   public int hashCode() {
      int prime = true;
      int result = 1;
      result = 31 * result + (this.blobId == null ? 0 : this.blobId.hashCode());
      result = 31 * result + (this.inputReference == null ? 0 : this.inputReference.hashCode());
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
         DMGReferences other = (DMGReferences)obj;
         if (this.blobId == null) {
            if (other.blobId != null) {
               return false;
            }
         } else if (!this.blobId.equals(other.blobId)) {
            return false;
         }

         if (this.inputReference == null) {
            if (other.inputReference != null) {
               return false;
            }
         } else if (!this.inputReference.equals(other.inputReference)) {
            return false;
         }

         return true;
      }
   }

   static {
      IdGeneratorFactory.registerDefaultImplementation("kmehr", KmehrIdGenerator.class);
   }
}
