package be.business.connector.gfddpp.domain.medicationscheme;

public enum Source {
   CBE,
   EHP,
   NIHII,
   SSIN;

   public String value() {
      return this.name();
   }

   public static Source fromValue(String v) {
      return valueOf(v);
   }
}
