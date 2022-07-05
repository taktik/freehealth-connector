package be.fgov.ehealth.genericinsurability.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "TransferDirectionType"
)
@XmlEnum
public enum TransferDirectionType {
   @XmlEnumValue("toOtherIo")
   TO_OTHER_IO("toOtherIo"),
   @XmlEnumValue("fromOtherIo")
   FROM_OTHER_IO("fromOtherIo");

   private final String value;

   private TransferDirectionType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static TransferDirectionType fromValue(String v) {
      TransferDirectionType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         TransferDirectionType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
