package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "FormatCode"
)
@XmlEnum
public enum FormatCode {
   KMEHR_20120401;

   public String value() {
      return this.name();
   }

   public static FormatCode fromValue(String v) {
      return valueOf(v);
   }
}
