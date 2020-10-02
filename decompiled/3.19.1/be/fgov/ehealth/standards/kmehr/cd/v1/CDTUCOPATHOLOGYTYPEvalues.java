package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TUCO-PATHOLOGYTYPEvalues"
)
@XmlEnum
public enum CDTUCOPATHOLOGYTYPEvalues {
   @XmlEnumValue("diabetedietarycontrol")
   DIABETEDIETARYCONTROL("diabetedietarycontrol"),
   @XmlEnumValue("diabeteoralmedication")
   DIABETEORALMEDICATION("diabeteoralmedication"),
   @XmlEnumValue("diabeteinsulin")
   DIABETEINSULIN("diabeteinsulin"),
   @XmlEnumValue("diabetenewlydiagnosed")
   DIABETENEWLYDIAGNOSED("diabetenewlydiagnosed"),
   @XmlEnumValue("diabete")
   DIABETE("diabete"),
   @XmlEnumValue("renalfailurelessthan30ml")
   RENALFAILURELESSTHAN_30_ML("renalfailurelessthan30ml"),
   @XmlEnumValue("instentrestenosis")
   INSTENTRESTENOSIS("instentrestenosis"),
   @XmlEnumValue("cardioshockatstartpci")
   CARDIOSHOCKATSTARTPCI("cardioshockatstartpci"),
   @XmlEnumValue("stroke")
   STROKE("stroke"),
   @XmlEnumValue("peripheralvasculardisease")
   PERIPHERALVASCULARDISEASE("peripheralvasculardisease"),
   @XmlEnumValue("stentthrombosis")
   STENTTHROMBOSIS("stentthrombosis");

   private final String value;

   private CDTUCOPATHOLOGYTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTUCOPATHOLOGYTYPEvalues fromValue(String v) {
      CDTUCOPATHOLOGYTYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTUCOPATHOLOGYTYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
