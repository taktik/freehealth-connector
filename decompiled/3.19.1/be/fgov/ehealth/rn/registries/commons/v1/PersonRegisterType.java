package be.fgov.ehealth.rn.registries.commons.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "PersonRegisterType"
)
@XmlEnum
public enum PersonRegisterType {
   NR,
   BIS,
   RAD,
   RAN;

   public String value() {
      return this.name();
   }

   public static PersonRegisterType fromValue(String v) {
      return valueOf(v);
   }
}
