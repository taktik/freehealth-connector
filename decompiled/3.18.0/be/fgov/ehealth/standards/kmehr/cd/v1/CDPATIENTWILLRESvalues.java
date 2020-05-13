package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PATIENTWILL-RESvalues"
)
@XmlEnum
public enum CDPATIENTWILLRESvalues {
   @XmlEnumValue("dnr0")
   DNR_0("dnr0"),
   @XmlEnumValue("dnr1")
   DNR_1("dnr1"),
   @XmlEnumValue("dnr2")
   DNR_2("dnr2"),
   @XmlEnumValue("dnr3")
   DNR_3("dnr3");

   private final String value;

   private CDPATIENTWILLRESvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPATIENTWILLRESvalues fromValue(String v) {
      CDPATIENTWILLRESvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDPATIENTWILLRESvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
