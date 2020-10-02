package be.fgov.ehealth.certra.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CertificateStatusType"
)
@XmlEnum
public enum CertificateStatusType {
   IN_PROGRESS,
   VALID_ACTIVE,
   REFUSED,
   REVOKED,
   EXPIRED;

   public String value() {
      return this.name();
   }

   public static CertificateStatusType fromValue(String v) {
      return valueOf(v);
   }
}
