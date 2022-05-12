package be.cin.nip.sync.reg.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "registrationStatus"
)
@XmlEnum
public enum RegistrationStatus {
   SUCCESS,
   ERROR;

   private RegistrationStatus() {
   }

   public String value() {
      return this.name();
   }

   public static RegistrationStatus fromValue(String v) {
      return valueOf(v);
   }
}
