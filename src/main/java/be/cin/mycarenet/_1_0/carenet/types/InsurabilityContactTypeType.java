package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "InsurabilityContactTypeType"
)
@XmlEnum
public enum InsurabilityContactTypeType {
   @XmlEnumValue("ambulatory_care")
   AMBULATORY_CARE("ambulatory_care"),
   @XmlEnumValue("hospitalized_for_day")
   HOSPITALIZED_FOR_DAY("hospitalized_for_day"),
   @XmlEnumValue("hospitalized_elsewhere")
   HOSPITALIZED_ELSEWHERE("hospitalized_elsewhere"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private InsurabilityContactTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static InsurabilityContactTypeType fromValue(String v) {
      InsurabilityContactTypeType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         InsurabilityContactTypeType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
