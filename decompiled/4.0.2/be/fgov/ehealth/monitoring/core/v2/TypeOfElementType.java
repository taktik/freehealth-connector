package be.fgov.ehealth.monitoring.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "TypeOfElementType"
)
@XmlEnum
public enum TypeOfElementType {
   ARRAY,
   INTEGER,
   HASH,
   BLOB,
   FLOAT,
   XML,
   STRING;

   private TypeOfElementType() {
   }

   public String value() {
      return this.name();
   }

   public static TypeOfElementType fromValue(String v) {
      return valueOf(v);
   }
}
