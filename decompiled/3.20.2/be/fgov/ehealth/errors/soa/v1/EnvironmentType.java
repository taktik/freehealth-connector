package be.fgov.ehealth.errors.soa.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "EnvironmentType"
)
@XmlEnum
public enum EnvironmentType {
   @XmlEnumValue("Development")
   DEVELOPMENT("Development"),
   @XmlEnumValue("Test")
   TEST("Test"),
   @XmlEnumValue("Integration")
   INTEGRATION("Integration"),
   @XmlEnumValue("Acceptation")
   ACCEPTATION("Acceptation"),
   @XmlEnumValue("Simulation")
   SIMULATION("Simulation"),
   @XmlEnumValue("Production")
   PRODUCTION("Production");

   private final String value;

   private EnvironmentType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static EnvironmentType fromValue(String v) {
      EnvironmentType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnvironmentType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
