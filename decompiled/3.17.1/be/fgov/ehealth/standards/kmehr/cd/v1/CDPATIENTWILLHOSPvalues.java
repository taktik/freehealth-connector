package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PATIENTWILL-HOSPvalues"
)
@XmlEnum
public enum CDPATIENTWILLHOSPvalues {
   @XmlEnumValue("hos1")
   HOS_1("hos1"),
   @XmlEnumValue("hos2")
   HOS_2("hos2");

   private final String value;

   private CDPATIENTWILLHOSPvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPATIENTWILLHOSPvalues fromValue(String v) {
      CDPATIENTWILLHOSPvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDPATIENTWILLHOSPvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
