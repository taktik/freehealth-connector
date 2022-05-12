package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-VACCINEvalues"
)
@XmlEnum
public enum CDVACCINEvalues {
   @XmlEnumValue("polio")
   POLIO("polio"),
   @XmlEnumValue("diteper")
   DITEPER("diteper"),
   @XmlEnumValue("haemo")
   HAEMO("haemo"),
   @XmlEnumValue("mmr")
   MMR("mmr"),
   @XmlEnumValue("hepatitiesb")
   HEPATITIESB("hepatitiesb"),
   @XmlEnumValue("mmr12")
   MMR_12("mmr12"),
   @XmlEnumValue("dite12")
   DITE_12("dite12"),
   @XmlEnumValue("meningitisc")
   MENINGITISC("meningitisc"),
   @XmlEnumValue("influenza")
   INFLUENZA("influenza"),
   @XmlEnumValue("pneumonia")
   PNEUMONIA("pneumonia"),
   @XmlEnumValue("ditepro")
   DITEPRO("ditepro");

   private final String value;

   private CDVACCINEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDVACCINEvalues fromValue(String v) {
      CDVACCINEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDVACCINEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
