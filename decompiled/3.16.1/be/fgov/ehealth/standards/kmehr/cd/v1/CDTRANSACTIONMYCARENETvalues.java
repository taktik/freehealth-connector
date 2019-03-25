package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTION-MYCARENETvalues"
)
@XmlEnum
public enum CDTRANSACTIONMYCARENETvalues {
   @XmlEnumValue("gmd")
   GMD("gmd"),
   @XmlEnumValue("gmdclosure")
   GMDCLOSURE("gmdclosure"),
   @XmlEnumValue("gmdextension")
   GMDEXTENSION("gmdextension"),
   @XmlEnumValue("tariff")
   TARIFF("tariff"),
   @XmlEnumValue("tariffmediprima")
   TARIFFMEDIPRIMA("tariffmediprima"),
   @XmlEnumValue("cga")
   CGA("cga"),
   @XmlEnumValue("cgd")
   CGD("cgd"),
   @XmlEnumValue("mea")
   MEA("mea"),
   @XmlEnumValue("cgacancellation")
   CGACANCELLATION("cgacancellation"),
   @XmlEnumValue("maa")
   MAA("maa"),
   @XmlEnumValue("maaextension")
   MAAEXTENSION("maaextension"),
   @XmlEnumValue("maaappendix")
   MAAAPPENDIX("maaappendix"),
   @XmlEnumValue("maacancellation")
   MAACANCELLATION("maacancellation"),
   @XmlEnumValue("maaclosure")
   MAACLOSURE("maaclosure"),
   @XmlEnumValue("maaagreement")
   MAAAGREEMENT("maaagreement"),
   @XmlEnumValue("maarefusal")
   MAAREFUSAL("maarefusal"),
   @XmlEnumValue("maaintreatment")
   MAAINTREATMENT("maaintreatment");

   private final String value;

   private CDTRANSACTIONMYCARENETvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONMYCARENETvalues fromValue(String v) {
      CDTRANSACTIONMYCARENETvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTRANSACTIONMYCARENETvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
