package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-DISCHARGETYPEvalues"
)
@XmlEnum
public enum CDDISCHARGETYPEvalues {
   @XmlEnumValue("alive")
   ALIVE("alive"),
   @XmlEnumValue("dead")
   DEAD("dead");

   private final String value;

   private CDDISCHARGETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDDISCHARGETYPEvalues fromValue(String v) {
      CDDISCHARGETYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDDISCHARGETYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
