package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MAA-TYPEvalues"
)
@XmlEnum
public enum CDMAATYPEvalues {
   @XmlEnumValue("chapter4")
   CHAPTER_4("chapter4");

   private final String value;

   private CDMAATYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMAATYPEvalues fromValue(String v) {
      CDMAATYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMAATYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
