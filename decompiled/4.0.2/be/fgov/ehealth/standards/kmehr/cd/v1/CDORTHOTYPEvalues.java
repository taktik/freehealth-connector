package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-TYPEvalues"
)
@XmlEnum
public enum CDORTHOTYPEvalues {
   @XmlEnumValue("primaryprocedure")
   PRIMARYPROCEDURE("primaryprocedure"),
   @XmlEnumValue("revisionwithprosthesis")
   REVISIONWITHPROSTHESIS("revisionwithprosthesis"),
   @XmlEnumValue("osteosynthesis")
   OSTEOSYNTHESIS("osteosynthesis"),
   @XmlEnumValue("resection")
   RESECTION("resection"),
   @XmlEnumValue("arthrodesis")
   ARTHRODESIS("arthrodesis"),
   @XmlEnumValue("amputation")
   AMPUTATION("amputation");

   private final String value;

   private CDORTHOTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOTYPEvalues fromValue(String v) {
      CDORTHOTYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDORTHOTYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
