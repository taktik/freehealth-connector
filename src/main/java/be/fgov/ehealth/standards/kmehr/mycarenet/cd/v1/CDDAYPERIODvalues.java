package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-DAYPERIODvalues"
)
@XmlEnum
public enum CDDAYPERIODvalues {
   @XmlEnumValue("afterbreakfast")
   AFTERBREAKFAST("afterbreakfast"),
   @XmlEnumValue("afterdinner")
   AFTERDINNER("afterdinner"),
   @XmlEnumValue("afterlunch")
   AFTERLUNCH("afterlunch"),
   @XmlEnumValue("aftermeal")
   AFTERMEAL("aftermeal"),
   @XmlEnumValue("afternoon")
   AFTERNOON("afternoon"),
   @XmlEnumValue("beforebreakfast")
   BEFOREBREAKFAST("beforebreakfast"),
   @XmlEnumValue("beforedinner")
   BEFOREDINNER("beforedinner"),
   @XmlEnumValue("beforelunch")
   BEFORELUNCH("beforelunch"),
   @XmlEnumValue("betweenbreakfastandlunch")
   BETWEENBREAKFASTANDLUNCH("betweenbreakfastandlunch"),
   @XmlEnumValue("betweendinnerandsleep")
   BETWEENDINNERANDSLEEP("betweendinnerandsleep"),
   @XmlEnumValue("betweenlunchanddinner")
   BETWEENLUNCHANDDINNER("betweenlunchanddinner"),
   @XmlEnumValue("betweenmeals")
   BETWEENMEALS("betweenmeals"),
   @XmlEnumValue("evening")
   EVENING("evening"),
   @XmlEnumValue("morning")
   MORNING("morning"),
   @XmlEnumValue("night")
   NIGHT("night"),
   @XmlEnumValue("thehourofsleep")
   THEHOUROFSLEEP("thehourofsleep"),
   @XmlEnumValue("duringbreakfast")
   DURINGBREAKFAST("duringbreakfast"),
   @XmlEnumValue("duringlunch")
   DURINGLUNCH("duringlunch"),
   @XmlEnumValue("duringdinner")
   DURINGDINNER("duringdinner");

   private final String value;

   private CDDAYPERIODvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDDAYPERIODvalues fromValue(String v) {
      CDDAYPERIODvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDDAYPERIODvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
