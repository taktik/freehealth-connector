package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HEADINGvalues"
)
@XmlEnum
public enum CDHEADINGvalues {
   @XmlEnumValue("assessment")
   ASSESSMENT("assessment"),
   @XmlEnumValue("clinical")
   CLINICAL("clinical"),
   @XmlEnumValue("clinicalplan")
   CLINICALPLAN("clinicalplan"),
   @XmlEnumValue("subjective")
   SUBJECTIVE("subjective"),
   @XmlEnumValue("technical")
   TECHNICAL("technical"),
   @XmlEnumValue("technicalplan")
   TECHNICALPLAN("technicalplan"),
   @XmlEnumValue("treatment")
   TREATMENT("treatment"),
   @XmlEnumValue("userdefined")
   USERDEFINED("userdefined"),
   @XmlEnumValue("history")
   HISTORY("history"),
   @XmlEnumValue("familyhistory")
   FAMILYHISTORY("familyhistory"),
   @XmlEnumValue("prescription")
   PRESCRIPTION("prescription"),
   @XmlEnumValue("medication")
   MEDICATION("medication"),
   @XmlEnumValue("anamnesis")
   ANAMNESIS("anamnesis"),
   @XmlEnumValue("examination")
   EXAMINATION("examination"),
   @XmlEnumValue("plan")
   PLAN("plan"),
   @XmlEnumValue("outcomeevaluation")
   OUTCOMEEVALUATION("outcomeevaluation");

   private final String value;

   private CDHEADINGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHEADINGvalues fromValue(String v) {
      CDHEADINGvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDHEADINGvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
