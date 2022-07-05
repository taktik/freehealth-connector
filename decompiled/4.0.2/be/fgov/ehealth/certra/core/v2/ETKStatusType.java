package be.fgov.ehealth.certra.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ETKStatusType"
)
@XmlEnum
public enum ETKStatusType {
   NOT_REQUESTED,
   IN_REQUEST,
   WAIT_FOR_ACTIVATION,
   VALID_ACTIVE,
   REVOKED,
   EXPIRED;

   private ETKStatusType() {
   }

   public String value() {
      return this.name();
   }

   public static ETKStatusType fromValue(String v) {
      return valueOf(v);
   }
}
