package be.ehealth.businessconnector.therlink.domain.responses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Acknowledge {
   private boolean isComplete;
   private List<TherapeuticLinkResponseError> listOfErrors;

   public Acknowledge() {
   }

   public boolean isComplete() {
      return this.isComplete;
   }

   public void setComplete(boolean isComplete) {
      this.isComplete = isComplete;
   }

   public List<TherapeuticLinkResponseError> getListOfErrors() {
      if (this.listOfErrors == null) {
         this.listOfErrors = new ArrayList();
      }

      return this.listOfErrors;
   }

   public void setListOfErrors(List<TherapeuticLinkResponseError> listOfErrors) {
      this.listOfErrors = listOfErrors;
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("Complete? : " + this.isComplete);
      if (this.listOfErrors != null) {
         Iterator<TherapeuticLinkResponseError> it = this.listOfErrors.iterator();

         while(it.hasNext()) {
            TherapeuticLinkResponseError next = (TherapeuticLinkResponseError)it.next();
            builder.append("[");
            builder.append("Error code : " + next.getErrorCode());
            builder.append("ErrorDescription : " + next.getErrorDescription());
            builder.append("]");
            if (it.hasNext()) {
               builder.append(", ");
            }
         }
      }

      return builder.toString();
   }

   public static class Builder {
      private Acknowledge ack = new Acknowledge();

      public Builder() {
      }

      public Builder withComplete(boolean complete) {
         this.ack.setComplete(complete);
         return this;
      }

      public Builder addError(TherapeuticLinkResponseError error) {
         this.ack.getListOfErrors().add(error);
         return this;
      }

      public Acknowledge build() {
         return this.ack;
      }
   }
}
