package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-MSvalues"
)
@XmlEnum
public enum CDITEMMSvalues {
   @XmlEnumValue("origin")
   ORIGIN("origin"),
   @XmlEnumValue("adaptationflag")
   ADAPTATIONFLAG("adaptationflag"),
   @XmlEnumValue("medicationuse")
   MEDICATIONUSE("medicationuse"),
   @XmlEnumValue("medicationtype")
   MEDICATIONTYPE("medicationtype"),
   @XmlEnumValue("begincondition")
   BEGINCONDITION("begincondition"),
   @XmlEnumValue("endcondition")
   ENDCONDITION("endcondition");

   private final String value;

   private CDITEMMSvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMMSvalues fromValue(String v) {
      CDITEMMSvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMMSvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
