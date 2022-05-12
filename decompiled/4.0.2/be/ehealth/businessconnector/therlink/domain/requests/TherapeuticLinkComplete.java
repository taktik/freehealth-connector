package be.ehealth.businessconnector.therlink.domain.requests;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.OperationContext;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TherapeuticLinkComplete extends TherapeuticLink {
   private static final long serialVersionUID = 1L;
   private List<OperationContext> operationContext;

   public TherapeuticLinkComplete(Patient patient, HcParty hcParty, String type, List<OperationContext> operationContext) {
      super(patient, hcParty, type);
      this.operationContext = operationContext;
   }

   public List<OperationContext> getOperationContext() {
      return this.operationContext;
   }

   public void setOperationContext(List<OperationContext> operationContext) {
      this.operationContext = operationContext;
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
      builder.append("TherapeuticLink [HCP = ");
      builder.append(this.hcParty);
      builder.append(", Patient = ");
      builder.append(this.patient);
      if (this.startDate != null) {
         builder.append(", Startdate = ");
         builder.append(df.format(this.startDate.toDateMidnight()));
      }

      if (this.endDate != null) {
         builder.append(", Enddate = ");
         builder.append(df.format(this.endDate.toDateMidnight()));
      }

      builder.append(", Comment = ");
      builder.append(this.comment);
      builder.append(", Status = ");
      builder.append(this.status);
      builder.append(", OperationContexts = ");
      Iterator var3 = this.operationContext.iterator();

      while(var3.hasNext()) {
         OperationContext oc = (OperationContext)var3.next();
         builder.append("[");
         builder.append(oc.getOperation());
         builder.append("]");
      }

      builder.append("]");
      return builder.toString();
   }

   public int hashCode() {
      int prime = true;
      int result = super.hashCode();
      result = 31 * result + (this.operationContext == null ? 0 : this.operationContext.hashCode());
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
         TherapeuticLinkComplete other = (TherapeuticLinkComplete)obj;
         if (this.operationContext == null) {
            if (other.operationContext != null) {
               return false;
            }
         } else if (!this.operationContext.equals(other.operationContext)) {
            return false;
         }

         return true;
      }
   }
}
