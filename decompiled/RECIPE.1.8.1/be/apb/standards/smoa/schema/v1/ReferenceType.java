package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "referenceType"
)
@XmlEnum
public enum ReferenceType {
   SUBJECT_SSIN,
   VALIDATION_ERROR;

   public String value() {
      return this.name();
   }

   public static ReferenceType fromValue(String v) {
      return valueOf(v);
   }
}
