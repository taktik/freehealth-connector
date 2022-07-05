package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ContractualTypeType"
)
@XmlEnum
public enum ContractualTypeType {
   A("A"),
   B("B"),
   C("C"),
   @XmlEnumValue("toilet")
   TOILET("toilet"),
   @XmlEnumValue("nomenclature")
   NOMENCLATURE("nomenclature");

   private final String value;

   private ContractualTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ContractualTypeType fromValue(String v) {
      ContractualTypeType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ContractualTypeType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
