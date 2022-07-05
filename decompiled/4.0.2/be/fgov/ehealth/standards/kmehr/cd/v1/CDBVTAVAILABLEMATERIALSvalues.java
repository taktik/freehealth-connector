package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-AVAILABLEMATERIALSvalues"
)
@XmlEnum
public enum CDBVTAVAILABLEMATERIALSvalues {
   @XmlEnumValue("cytology")
   CYTOLOGY("cytology"),
   @XmlEnumValue("dna")
   DNA("dna"),
   @XmlEnumValue("rna")
   RNA("rna"),
   @XmlEnumValue("proteins")
   PROTEINS("proteins"),
   @XmlEnumValue("correspondingnormaltissue")
   CORRESPONDINGNORMALTISSUE("correspondingnormaltissue"),
   @XmlEnumValue("tissue")
   TISSUE("tissue"),
   @XmlEnumValue("serum")
   SERUM("serum"),
   @XmlEnumValue("plasma")
   PLASMA("plasma"),
   @XmlEnumValue("blood")
   BLOOD("blood"),
   @XmlEnumValue("urine")
   URINE("urine"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDBVTAVAILABLEMATERIALSvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTAVAILABLEMATERIALSvalues fromValue(String v) {
      CDBVTAVAILABLEMATERIALSvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDBVTAVAILABLEMATERIALSvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
