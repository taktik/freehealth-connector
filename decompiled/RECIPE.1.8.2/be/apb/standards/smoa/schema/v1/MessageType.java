package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "MessageType"
)
@XmlEnum
public enum MessageType {
   ERROR,
   WARNING,
   INFO,
   REQUEST,
   UNCLASSIFIED;

   public String value() {
      return this.name();
   }

   public static MessageType fromValue(String v) {
      return valueOf(v);
   }
}
