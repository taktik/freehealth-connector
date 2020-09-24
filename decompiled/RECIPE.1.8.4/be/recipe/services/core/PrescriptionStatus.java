package be.recipe.services.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "prescriptionStatus"
)
@XmlEnum
public enum PrescriptionStatus {
   NotDelivered,
   InProcess,
   Delivered,
   Revoked,
   Expired,
   Archived;

   public String value() {
      return this.name();
   }

   public static PrescriptionStatus fromValue(String v) {
      return valueOf(v);
   }
}
