package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "DementiaTypeType"
)
@XmlEnum
public enum DementiaTypeType {
   @XmlEnumValue("light")
   LIGHT("light"),
   @XmlEnumValue("medium")
   MEDIUM("medium"),
   @XmlEnumValue("serious")
   SERIOUS("serious");

   private final String value;

   private DementiaTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static DementiaTypeType fromValue(String v) {
      DementiaTypeType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         DementiaTypeType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
