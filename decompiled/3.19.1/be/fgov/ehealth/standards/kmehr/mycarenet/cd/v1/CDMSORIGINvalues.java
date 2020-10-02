package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MS-ORIGINvalues"
)
@XmlEnum
public enum CDMSORIGINvalues {
   @XmlEnumValue("regularprocess")
   REGULARPROCESS("regularprocess"),
   @XmlEnumValue("recorded")
   RECORDED("recorded");

   private final String value;

   private CDMSORIGINvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMSORIGINvalues fromValue(String v) {
      CDMSORIGINvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMSORIGINvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
