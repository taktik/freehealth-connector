package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PARAMETERvalues"
)
@XmlEnum
public enum CDPARAMETERvalues {
   @XmlEnumValue("weight")
   WEIGHT("weight"),
   @XmlEnumValue("height")
   HEIGHT("height"),
   @XmlEnumValue("bmi")
   BMI("bmi"),
   @XmlEnumValue("sbp")
   SBP("sbp"),
   @XmlEnumValue("dbp")
   DBP("dbp"),
   @XmlEnumValue("pulsecharacter")
   PULSECHARACTER("pulsecharacter"),
   @XmlEnumValue("heartrate")
   HEARTRATE("heartrate"),
   @XmlEnumValue("peakflow")
   PEAKFLOW("peakflow"),
   @XmlEnumValue("gpa")
   GPA("gpa"),
   @XmlEnumValue("headcircumference")
   HEADCIRCUMFERENCE("headcircumference"),
   @XmlEnumValue("hipcircumference")
   HIPCIRCUMFERENCE("hipcircumference"),
   @XmlEnumValue("apgar")
   APGAR("apgar"),
   @XmlEnumValue("katz")
   KATZ("katz"),
   @XmlEnumValue("belrai")
   BELRAI("belrai"),
   @XmlEnumValue("bloodsugar")
   BLOODSUGAR("bloodsugar");

   private final String value;

   private CDPARAMETERvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPARAMETERvalues fromValue(String v) {
      CDPARAMETERvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDPARAMETERvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
