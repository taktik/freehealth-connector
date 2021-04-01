package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BEARING-SURFACEvalues"
)
@XmlEnum
public enum CDBEARINGSURFACEvalues {
   @XmlEnumValue("metalpoly")
   METALPOLY("metalpoly"),
   @XmlEnumValue("cerampoly")
   CERAMPOLY("cerampoly"),
   @XmlEnumValue("metalmetal")
   METALMETAL("metalmetal"),
   @XmlEnumValue("ceramceram")
   CERAMCERAM("ceramceram"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDBEARINGSURFACEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBEARINGSURFACEvalues fromValue(String v) {
      CDBEARINGSURFACEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBEARINGSURFACEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
