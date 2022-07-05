package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MAA-COVERAGETYPEvalues"
)
@XmlEnum
public enum CDMAACOVERAGETYPEvalues {
   @XmlEnumValue("chapter4limited")
   CHAPTER_4_LIMITED("chapter4limited"),
   @XmlEnumValue("chapter4unlimited")
   CHAPTER_4_UNLIMITED("chapter4unlimited");

   private final String value;

   private CDMAACOVERAGETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMAACOVERAGETYPEvalues fromValue(String v) {
      CDMAACOVERAGETYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMAACOVERAGETYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
