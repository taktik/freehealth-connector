package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-DAYPERIOD"
)
@XmlEnum
public enum CDDAYPERIOD {
   AFTERBREAKFAST,
   AFTERDINNER,
   AFTERLUNCH,
   AFTERMEAL,
   AFTERNOON,
   BEFOREBREAKFAST,
   BEFOREDINNER,
   BEFORELUNCH,
   BETWEENBREAKFASTANDLUNCH,
   BETWEENDINNERANDSLEEP,
   BETWEENLUNCHANDDINNER,
   BETWEENMEALS,
   DURINGBREAKFAST,
   DURINGDINNER,
   DURINGLUNCH,
   EVENING,
   MORNING,
   NIGHT,
   THEHOUROFSLEEP;

   public String value() {
      return this.name();
   }

   public static CDDAYPERIOD fromValue(String v) {
      return valueOf(v);
   }
}
