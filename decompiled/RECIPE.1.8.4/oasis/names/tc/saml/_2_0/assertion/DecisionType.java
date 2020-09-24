package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "DecisionType"
)
@XmlEnum
public enum DecisionType {
   @XmlEnumValue("Permit")
   PERMIT("Permit"),
   @XmlEnumValue("Deny")
   DENY("Deny"),
   @XmlEnumValue("Indeterminate")
   INDETERMINATE("Indeterminate");

   private final String value;

   private DecisionType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static DecisionType fromValue(String v) {
      DecisionType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         DecisionType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
