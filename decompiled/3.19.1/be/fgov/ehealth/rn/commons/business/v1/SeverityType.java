package be.fgov.ehealth.rn.commons.business.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SeverityType"
)
@XmlEnum
public enum SeverityType {
   INFORMATION,
   WARNING,
   FATAL;

   public String value() {
      return this.name();
   }

   public static SeverityType fromValue(String v) {
      return valueOf(v);
   }
}
