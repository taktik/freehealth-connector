package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SpecificTechnicalCareTypeType"
)
@XmlEnum
public enum SpecificTechnicalCareTypeType {
   @XmlEnumValue("perfusion")
   PERFUSION("perfusion"),
   @XmlEnumValue("parenteral")
   PARENTERAL("parenteral"),
   @XmlEnumValue("catheter")
   CATHETER("catheter");

   private final String value;

   private SpecificTechnicalCareTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static SpecificTechnicalCareTypeType fromValue(String v) {
      SpecificTechnicalCareTypeType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SpecificTechnicalCareTypeType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
