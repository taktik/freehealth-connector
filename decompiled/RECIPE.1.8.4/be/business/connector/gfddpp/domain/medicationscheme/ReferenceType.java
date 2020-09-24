package be.business.connector.gfddpp.domain.medicationscheme;

public enum ReferenceType {
   SUBJECT_SSIN,
   VALIDATION_ERROR,
   CORRELATION_ID;

   public String value() {
      return this.name();
   }

   public static ReferenceType fromValue(String v) {
      return valueOf(v);
   }
}
