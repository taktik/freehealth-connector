package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "MessageNameType"
)
@XmlEnum
public enum MessageNameType {
   @XmlEnumValue("M801000")
   M_801000("M801000"),
   @XmlEnumValue("M801000ERR")
   M_801000_ERR("M801000ERR"),
   @XmlEnumValue("M801001")
   M_801001("M801001"),
   @XmlEnumValue("M801900")
   M_801900("M801900"),
   @XmlEnumValue("M410000")
   M_410000("M410000"),
   @XmlEnumValue("M410001")
   M_410001("M410001"),
   @XmlEnumValue("M410000ERR")
   M_410000_ERR("M410000ERR"),
   @XmlEnumValue("M410900")
   M_410900("M410900"),
   @XmlEnumValue("M410908")
   M_410908("M410908"),
   @XmlEnumValue("M410999")
   M_410999("M410999"),
   @XmlEnumValue("M420000")
   M_420000("M420000"),
   @XmlEnumValue("M420001")
   M_420001("M420001"),
   @XmlEnumValue("M420000ERR")
   M_420000_ERR("M420000ERR"),
   @XmlEnumValue("M420900")
   M_420900("M420900"),
   @XmlEnumValue("M420999")
   M_420999("M420999"),
   @XmlEnumValue("M430000")
   M_430000("M430000"),
   @XmlEnumValue("M430001")
   M_430001("M430001"),
   @XmlEnumValue("M430000ERR")
   M_430000_ERR("M430000ERR"),
   @XmlEnumValue("M891000")
   M_891000("M891000");

   private final String value;

   private MessageNameType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static MessageNameType fromValue(String v) {
      MessageNameType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         MessageNameType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
