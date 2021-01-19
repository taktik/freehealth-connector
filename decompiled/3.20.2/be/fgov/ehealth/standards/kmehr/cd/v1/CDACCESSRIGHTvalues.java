package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACCESSRIGHTvalues"
)
@XmlEnum
public enum CDACCESSRIGHTvalues {
   @XmlEnumValue("allow")
   ALLOW("allow"),
   @XmlEnumValue("disallow")
   DISALLOW("disallow");

   private final String value;

   private CDACCESSRIGHTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACCESSRIGHTvalues fromValue(String v) {
      CDACCESSRIGHTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDACCESSRIGHTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
