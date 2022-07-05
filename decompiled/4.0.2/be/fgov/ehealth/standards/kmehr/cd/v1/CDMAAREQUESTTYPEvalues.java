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
      CDMAAREQUESTTYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMAAREQUESTTYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
