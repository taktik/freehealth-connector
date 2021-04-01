package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "StatusType"
)
@XmlEnum
public enum StatusType {
   OK,
   ERROR_AA,
   ERROR_DAO,
   ERROR_FEDICT,
   ERROR_OTHER;

   public String value() {
      return this.name();
   }

   public static StatusType fromValue(String v) {
      return valueOf(v);
   }
}
