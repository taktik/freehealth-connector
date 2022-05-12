package be.fgov.ehealth.monitoring.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "EnvironmentType"
)
@XmlEnum
public enum EnvironmentType {
   ACC,
   PROD,
   LOCAL,
   DEV,
   TEST,
   SIM;

   private EnvironmentType() {
   }

   public String value() {
      return this.name();
   }

   public static EnvironmentType fromValue(String v) {
      return valueOf(v);
   }
}
