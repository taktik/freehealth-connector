package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-DIAGNOSISvalues"
)
@XmlEnum
public enum CDORTHODIAGNOSISvalues {
   @XmlEnumValue("primaryarthrosis")
   PRIMARYARTHROSIS("primaryarthrosis"),
   @XmlEnumValue("necrosisavascular")
   NECROSISAVASCULAR("necrosisavascular"),
   @XmlEnumValue("fracture")
   FRACTURE("fracture"),
   @XmlEnumValue("inflamatory")
   INFLAMATORY("inflamatory"),
   @XmlEnumValue("posttraumaticarthrosis")
   POSTTRAUMATICARTHROSIS("posttraumaticarthrosis"),
   @XmlEnumValue("arthrosisafterinfection")
   ARTHROSISAFTERINFECTION("arthrosisafterinfection"),
   @XmlEnumValue("secondaryarthrosis")
   SECONDARYARTHROSIS("secondaryarthrosis"),
   @XmlEnumValue("rheumatoidarthritis")
   RHEUMATOIDARTHRITIS("rheumatoidarthritis"),
   @XmlEnumValue("tumor")
   TUMOR("tumor"),
   @XmlEnumValue("hipdysplasia")
   HIPDYSPLASIA("hipdysplasia"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("asepticloosening")
   ASEPTICLOOSENING("asepticloosening"),
   @XmlEnumValue("infection")
   INFECTION("infection"),
   @XmlEnumValue("instability")
   INSTABILITY("instability"),
   @XmlEnumValue("periprostheticfracture")
   PERIPROSTHETICFRACTURE("periprostheticfracture"),
   @XmlEnumValue("pain")
   PAIN("pain"),
   @XmlEnumValue("wearpolyethylene")
   WEARPOLYETHYLENE("wearpolyethylene"),
   @XmlEnumValue("wrongalignment")
   WRONGALIGNMENT("wrongalignment"),
   @XmlEnumValue("fractureofimplant")
   FRACTUREOFIMPLANT("fractureofimplant"),
   @XmlEnumValue("progressionarthrosis")
   PROGRESSIONARTHROSIS("progressionarthrosis"),
   @XmlEnumValue("rigidity")
   RIGIDITY("rigidity"),
   @XmlEnumValue("wear")
   WEAR("wear"),
   @XmlEnumValue("inflammatory")
   INFLAMMATORY("inflammatory");

   private final String value;

   private CDORTHODIAGNOSISvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHODIAGNOSISvalues fromValue(String v) {
      CDORTHODIAGNOSISvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDORTHODIAGNOSISvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
