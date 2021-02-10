package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-REFSCOPEvalues"
)
@XmlEnum
public enum CDREFSCOPEvalues {
   @XmlEnumValue("age")
   AGE("age"),
   @XmlEnumValue("sex")
   SEX("sex"),
   @XmlEnumValue("gestationnal")
   GESTATIONNAL("gestationnal");

   private final String value;

   private CDREFSCOPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDREFSCOPEvalues fromValue(String v) {
      CDREFSCOPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDREFSCOPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
