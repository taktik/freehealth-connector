package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MICROORGANISMvalues"
)
@XmlEnum
public enum CDMICROORGANISMvalues {
   @XmlEnumValue("legionella")
   LEGIONELLA("legionella"),
   @XmlEnumValue("salmonella")
   SALMONELLA("salmonella");

   private final String value;

   private CDMICROORGANISMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMICROORGANISMvalues fromValue(String v) {
      CDMICROORGANISMvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMICROORGANISMvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
