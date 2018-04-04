package be.business.connector.gfddpp.domain.medicationscheme;

public class Error extends BaseStatus {
   private static final long serialVersionUID = 18717890642416580L;
   private String reference;
   private String referenceType;

   public Error() {
   }

   public Error(int code, String referenceType, String reference) {
      super(code);
      this.referenceType = referenceType;
      this.reference = reference;
   }

   public Error(int code, String message, String referenceType, String reference) {
      super(code, message);
      this.referenceType = referenceType;
      this.reference = reference;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public String getReferenceType() {
      return this.referenceType;
   }

   public void setReferenceType(String referenceType) {
      this.referenceType = referenceType;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("\nError: ");
      sb.append(this.reference);
      sb.append("\n\t");
      sb.append(super.toString());
      return sb.toString();
   }
}
