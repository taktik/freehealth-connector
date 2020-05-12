package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ServiceType"
)
@XmlEnum
public enum ServiceType {
   PCDH,
   TIP,
   CM,
   UNCLASSIFIED;

   public String value() {
      return this.name();
   }

   public static ServiceType fromValue(String v) {
      return valueOf(v);
   }
}
