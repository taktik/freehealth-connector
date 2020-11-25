package be.business.connector.gfddpp.domain.medicationscheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Status extends BaseStatus {
   private static final long serialVersionUID = -6509733550610165087L;
   private List<Error> errors;

   public Status() {
   }

   public Status(int code) {
      super(code);
   }

   public Status(int code, String message) {
      super(code, message);
   }

   public void setErrors(List<Error> errors) {
      this.errors = errors;
   }

   public List<Error> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString());
      if (this.errors != null && this.errors.size() > 0) {
         sb.append("\nWith Errors");
         Iterator i = this.errors.iterator();

         while(i.hasNext()) {
            Error e = (Error)i.next();
            sb.append(e.toString());
         }
      }

      return sb.toString();
   }
}
