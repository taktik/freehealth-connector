package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SEXvalues"
)
@XmlEnum
public enum CDSEXvalues {
   @XmlEnumValue("changed")
   CHANGED("changed"),
   @XmlEnumValue("female")
   FEMALE("female"),
   @XmlEnumValue("male")
   MALE("male"),
   @XmlEnumValue("unknown")
   UNKNOWN("unknown"),
   @XmlEnumValue("undefined")
   UNDEFINED("undefined");

   private final String value;

   private CDSEXvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSEXvalues fromValue(String v) {
      CDSEXvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSEXvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
