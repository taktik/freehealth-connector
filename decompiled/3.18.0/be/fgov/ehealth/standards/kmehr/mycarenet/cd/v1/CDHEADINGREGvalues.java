package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HEADING-REGvalues"
)
@XmlEnum
public enum CDHEADINGREGvalues {
   @XmlEnumValue("coronaryanatomy")
   CORONARYANATOMY("coronaryanatomy"),
   @XmlEnumValue("chapter4")
   CHAPTER_4("chapter4"),
   @XmlEnumValue("non-biologic")
   NON_BIOLOGIC("non-biologic"),
   @XmlEnumValue("bmi")
   BMI("bmi"),
   @XmlEnumValue("primarykneeprocedure")
   PRIMARYKNEEPROCEDURE("primarykneeprocedure"),
   @XmlEnumValue("approachtechnic")
   APPROACHTECHNIC("approachtechnic"),
   @XmlEnumValue("instrumentation")
   INSTRUMENTATION("instrumentation"),
   @XmlEnumValue("orthopedicanatomy")
   ORTHOPEDICANATOMY("orthopedicanatomy"),
   @XmlEnumValue("interface")
   INTERFACE("interface"),
   @XmlEnumValue("revisionplan")
   REVISIONPLAN("revisionplan"),
   @XmlEnumValue("material")
   MATERIAL("material"),
   @XmlEnumValue("notified-material")
   NOTIFIED_MATERIAL("notified-material"),
   @XmlEnumValue("not-notified-material")
   NOT_NOTIFIED_MATERIAL("not-notified-material"),
   @XmlEnumValue("not-notified-ortho-device")
   NOT_NOTIFIED_ORTHO_DEVICE("not-notified-ortho-device"),
   @XmlEnumValue("criteria")
   CRITERIA("criteria"),
   @XmlEnumValue("comorbidities")
   COMORBIDITIES("comorbidities"),
   @XmlEnumValue("comorbiditiesinformation")
   COMORBIDITIESINFORMATION("comorbiditiesinformation"),
   @XmlEnumValue("results")
   RESULTS("results"),
   @XmlEnumValue("resynchronisationinfo")
   RESYNCHRONISATIONINFO("resynchronisationinfo"),
   @XmlEnumValue("crtp")
   CRTP("crtp"),
   @XmlEnumValue("infiltration")
   INFILTRATION("infiltration"),
   @XmlEnumValue("das28")
   DAS_28("das28"),
   @XmlEnumValue("haq")
   HAQ("haq"),
   @XmlEnumValue("posology")
   POSOLOGY("posology");

   private final String value;

   private CDHEADINGREGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHEADINGREGvalues fromValue(String v) {
      CDHEADINGREGvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDHEADINGREGvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
