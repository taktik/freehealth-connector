package be.ehealth.businessconnector.therlink.domain.responses;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.OperationContext;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

public class TherapeuticLinkResponse extends TherapeuticLink {
   private static final long serialVersionUID = 1L;
   private List<OperationContext> operationContexts;

   public TherapeuticLinkResponse(Patient patient, HcParty hcParty, String type) {
      super(patient, hcParty, type);
   }

   private TherapeuticLinkResponse() {
   }

   public List<OperationContext> getOperationContexts() {
      if (this.operationContexts == null) {
         this.operationContexts = new ArrayList();
      }

      return this.operationContexts;
   }

   public void setOperationContexts(List<OperationContext> operationContexts) {
      this.operationContexts = operationContexts;
   }

   public static long getSerialversionuid() {
      return 1L;
   }

   public int hashCode() {
      int prime = true;
      int result = super.hashCode();
      result = 31 * result + (this.operationContexts == null ? 0 : this.operationContexts.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         TherapeuticLinkResponse other = (TherapeuticLinkResponse)obj;
         if (this.operationContexts == null) {
            if (other.operationContexts != null) {
               return false;
            }
         } else if (!this.operationContexts.equals(other.operationContexts)) {
            return false;
         }

         return true;
      }
   }

   // $FF: synthetic method
   TherapeuticLinkResponse(TherapeuticLinkResponse.SyntheticClass_1 x0) {
      this();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   public static class Builder {
      private TherapeuticLinkResponse therlink = new TherapeuticLinkResponse();

      public TherapeuticLinkResponse.Builder withPatient(Patient patient) {
         this.therlink.setPatient(patient);
         return this;
      }

      public TherapeuticLinkResponse.Builder withHcParty(HcParty hcp) {
         this.therlink.setHcParty(hcp);
         return this;
      }

      public TherapeuticLinkResponse.Builder addOperationContext(OperationContext oc) {
         this.therlink.getOperationContexts().add(oc);
         return this;
      }

      public TherapeuticLinkResponse.Builder withStartDate(LocalDate startDate) {
         this.therlink.setStartDate(startDate);
         return this;
      }

      public TherapeuticLinkResponse.Builder withEndDate(LocalDate endDate) {
         this.therlink.setEndDate(endDate);
         return this;
      }

      public TherapeuticLinkResponse.Builder withType(String string) {
         this.therlink.setType(string);
         return this;
      }

      public TherapeuticLinkResponse build() {
         return this.therlink;
      }
   }
}
