package be.business.connector.gfddpp.domain.medicationscheme;

public class BreakTheGlass {
   private boolean confirmation;
   private String reason;

   public BreakTheGlass(boolean confirmation, String reason) {
      this.confirmation = confirmation;
      this.reason = reason;
   }

   public boolean isConfirmed() {
      return this.confirmation;
   }

   public String getReason() {
      return this.reason;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Break The Glass Confirmed: ");
      sb.append(this.confirmation);
      if (this.reason != null && !this.reason.equalsIgnoreCase("")) {
         sb.append(" - reason: ");
         sb.append(this.reason);
      }

      return sb.toString();
   }
}
