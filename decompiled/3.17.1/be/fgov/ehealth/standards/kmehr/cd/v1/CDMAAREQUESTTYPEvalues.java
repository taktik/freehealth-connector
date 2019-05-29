package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MAA-REQUESTTYPEvalues"
)
@XmlEnum
public enum CDMAAREQUESTTYPEvalues {
   @XmlEnumValue("newrequest")
   NEWREQUEST("newrequest"),
   @XmlEnumValue("extension")
   EXTENSION("extension"),
   @XmlEnumValue("noncontinuousextension")
   NONCONTINUOUSEXTENSION("noncontinuousextension"),
   @XmlEnumValue("complimentaryannex")
   COMPLIMENTARYANNEX("complimentaryannex"),
   @XmlEnumValue("cancellation")
   CANCELLATION("cancellation"),
   @XmlEnumValue("closure")
   CLOSURE("closure");

   private final String value;

   private CDMAAREQUESTTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMAAREQUESTTYPEvalues fromValue(String v) {
      CDMAAREQUESTTYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMAAREQUESTTYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
