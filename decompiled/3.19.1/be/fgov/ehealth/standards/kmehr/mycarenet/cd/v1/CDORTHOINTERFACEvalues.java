package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-INTERFACEvalues"
)
@XmlEnum
public enum CDORTHOINTERFACEvalues {
   @XmlEnumValue("hacoated")
   HACOATED("hacoated"),
   @XmlEnumValue("porous")
   POROUS("porous"),
   @XmlEnumValue("smouth")
   SMOUTH("smouth"),
   @XmlEnumValue("cementwithab")
   CEMENTWITHAB("cementwithab"),
   @XmlEnumValue("cementwithoutab")
   CEMENTWITHOUTAB("cementwithoutab"),
   @XmlEnumValue("allpoly")
   ALLPOLY("allpoly"),
   @XmlEnumValue("none")
   NONE("none"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("metalbacked")
   METALBACKED("metalbacked");

   private final String value;

   private CDORTHOINTERFACEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOINTERFACEvalues fromValue(String v) {
      CDORTHOINTERFACEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDORTHOINTERFACEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
