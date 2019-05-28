package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-INCAPACITYvalues"
)
@XmlEnum
public enum CDINCAPACITYvalues {
   @XmlEnumValue("work")
   WORK("work"),
   @XmlEnumValue("swim")
   SWIM("swim"),
   @XmlEnumValue("sport")
   SPORT("sport"),
   @XmlEnumValue("school")
   SCHOOL("school"),
   @XmlEnumValue("schoolsports")
   SCHOOLSPORTS("schoolsports"),
   @XmlEnumValue("travel")
   TRAVEL("travel"),
   @XmlEnumValue("vote")
   VOTE("vote"),
   @XmlEnumValue("heavyphysicalactivity")
   HEAVYPHYSICALACTIVITY("heavyphysicalactivity");

   private final String value;

   private CDINCAPACITYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDINCAPACITYvalues fromValue(String v) {
      CDINCAPACITYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDINCAPACITYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
