package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SEVERITYvalues"
)
@XmlEnum
public enum CDSEVERITYvalues {
   @XmlEnumValue("abnormal")
   ABNORMAL("abnormal"),
   @XmlEnumValue("high")
   HIGH("high"),
   @XmlEnumValue("low")
   LOW("low"),
   @XmlEnumValue("normal")
   NORMAL("normal"),
   @XmlEnumValue("resistent")
   RESISTENT("resistent"),
   @XmlEnumValue("susceptible")
   SUSCEPTIBLE("susceptible"),
   @XmlEnumValue("susceptibleintermediate")
   SUSCEPTIBLEINTERMEDIATE("susceptibleintermediate"),
   @XmlEnumValue("veryabnormal")
   VERYABNORMAL("veryabnormal"),
   @XmlEnumValue("veryhigh")
   VERYHIGH("veryhigh"),
   @XmlEnumValue("verylow")
   VERYLOW("verylow"),
   @XmlEnumValue("extremelyhigh")
   EXTREMELYHIGH("extremelyhigh"),
   @XmlEnumValue("extremelylow")
   EXTREMELYLOW("extremelylow"),
   @XmlEnumValue("verysusceptible")
   VERYSUSCEPTIBLE("verysusceptible");

   private final String value;

   private CDSEVERITYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSEVERITYvalues fromValue(String v) {
      CDSEVERITYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSEVERITYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
