package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-STENT-BRIDGETYPEvalues"
)
@XmlEnum
public enum CDSTENTBRIDGETYPEvalues {
   @XmlEnumValue("saphena1")
   SAPHENA_1("saphena1"),
   @XmlEnumValue("saphena2")
   SAPHENA_2("saphena2"),
   @XmlEnumValue("saphena3")
   SAPHENA_3("saphena3"),
   @XmlEnumValue("saphena4")
   SAPHENA_4("saphena4"),
   @XmlEnumValue("saphena5")
   SAPHENA_5("saphena5"),
   @XmlEnumValue("lima")
   LIMA("lima"),
   @XmlEnumValue("rima")
   RIMA("rima"),
   @XmlEnumValue("gepa")
   GEPA("gepa"),
   @XmlEnumValue("freeima")
   FREEIMA("freeima"),
   @XmlEnumValue("radialis")
   RADIALIS("radialis");

   private final String value;

   private CDSTENTBRIDGETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSTENTBRIDGETYPEvalues fromValue(String v) {
      CDSTENTBRIDGETYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDSTENTBRIDGETYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
