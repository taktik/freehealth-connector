package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-REV-COMPONENTvalues"
)
@XmlEnum
public enum CDREVCOMPONENTvalues {
   @XmlEnumValue("patella")
   PATELLA("patella"),
   @XmlEnumValue("femur")
   FEMUR("femur"),
   @XmlEnumValue("tibia")
   TIBIA("tibia"),
   @XmlEnumValue("insert")
   INSERT("insert");

   private final String value;

   private CDREVCOMPONENTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDREVCOMPONENTvalues fromValue(String v) {
      CDREVCOMPONENTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDREVCOMPONENTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
