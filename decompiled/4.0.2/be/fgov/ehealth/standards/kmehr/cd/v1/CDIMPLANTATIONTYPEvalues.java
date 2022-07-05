package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-IMPLANTATION-TYPEvalues"
)
@XmlEnum
public enum CDIMPLANTATIONTYPEvalues {
   @XmlEnumValue("unimedial")
   UNIMEDIAL("unimedial"),
   @XmlEnumValue("unilateral")
   UNILATERAL("unilateral"),
   @XmlEnumValue("bicompartimental")
   BICOMPARTIMENTAL("bicompartimental"),
   @XmlEnumValue("femoropatellar")
   FEMOROPATELLAR("femoropatellar"),
   @XmlEnumValue("totalcr")
   TOTALCR("totalcr"),
   @XmlEnumValue("totalps")
   TOTALPS("totalps"),
   @XmlEnumValue("totalcck")
   TOTALCCK("totalcck"),
   @XmlEnumValue("hinge")
   HINGE("hinge"),
   @XmlEnumValue("totaluc")
   TOTALUC("totaluc"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("totalprosthesis")
   TOTALPROSTHESIS("totalprosthesis"),
   @XmlEnumValue("totalprosthesisdualmobility")
   TOTALPROSTHESISDUALMOBILITY("totalprosthesisdualmobility"),
   @XmlEnumValue("hemiprosthesismonoblock")
   HEMIPROSTHESISMONOBLOCK("hemiprosthesismonoblock"),
   @XmlEnumValue("hemiprosthesisbipolar")
   HEMIPROSTHESISBIPOLAR("hemiprosthesisbipolar"),
   @XmlEnumValue("bicruciateretaining")
   BICRUCIATERETAINING("bicruciateretaining"),
   @XmlEnumValue("hemiprosthesismodular")
   HEMIPROSTHESISMODULAR("hemiprosthesismodular"),
   @XmlEnumValue("resurfacingfemhemi")
   RESURFACINGFEMHEMI("resurfacingfemhemi"),
   @XmlEnumValue("resurfacingfemcup")
   RESURFACINGFEMCUP("resurfacingfemcup"),
   @XmlEnumValue("resurfacingpartial")
   RESURFACINGPARTIAL("resurfacingpartial"),
   @XmlEnumValue("resurfacingpartialfemcondyle")
   RESURFACINGPARTIALFEMCONDYLE("resurfacingpartialfemcondyle");

   private final String value;

   private CDIMPLANTATIONTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDIMPLANTATIONTYPEvalues fromValue(String v) {
      CDIMPLANTATIONTYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDIMPLANTATIONTYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
