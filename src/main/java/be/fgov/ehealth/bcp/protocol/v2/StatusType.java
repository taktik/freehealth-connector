package be.fgov.ehealth.bcp.protocol.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "StatusType"
)
@XmlEnum
public enum StatusType {
   ACTIVE,
   INACTIVE;

   public String value() {
      return this.name();
   }

   public static StatusType fromValue(String v) {
      return valueOf(v);
   }
}
