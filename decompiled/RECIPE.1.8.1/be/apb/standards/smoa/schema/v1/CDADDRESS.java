package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ADDRESS"
)
@XmlEnum
public enum CDADDRESS {
   HOME,
   WORK,
   VACATION,
   OTHER;

   public String value() {
      return this.name();
   }

   public static CDADDRESS fromValue(String v) {
      return valueOf(v);
   }
}
