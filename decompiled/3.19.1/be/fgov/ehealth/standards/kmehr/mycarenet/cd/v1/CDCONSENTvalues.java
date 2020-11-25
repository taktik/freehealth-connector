package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CONSENTvalues"
)
@XmlEnum
public enum CDCONSENTvalues {
   @XmlEnumValue("local")
   LOCAL("local"),
   @XmlEnumValue("prospective")
   PROSPECTIVE("prospective"),
   @XmlEnumValue("retrospective")
   RETROSPECTIVE("retrospective");

   private final String value;

   private CDCONSENTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCONSENTvalues fromValue(String v) {
      CDCONSENTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDCONSENTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
