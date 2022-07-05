package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-STENT-SEGMENTvalues"
)
@XmlEnum
public enum CDSTENTSEGMENTvalues {
   @XmlEnumValue("proxrca")
   PROXRCA("proxrca"),
   @XmlEnumValue("midrca")
   MIDRCA("midrca"),
   @XmlEnumValue("distrca")
   DISTRCA("distrca"),
   @XmlEnumValue("rightposteriordescending")
   RIGHTPOSTERIORDESCENDING("rightposteriordescending"),
   @XmlEnumValue("leftmain")
   LEFTMAIN("leftmain"),
   @XmlEnumValue("proxlad")
   PROXLAD("proxlad"),
   @XmlEnumValue("midlad")
   MIDLAD("midlad"),
   @XmlEnumValue("distlad")
   DISTLAD("distlad"),
   @XmlEnumValue("d1")
   D_1("d1"),
   @XmlEnumValue("d2")
   D_2("d2"),
   @XmlEnumValue("d3")
   D_3("d3"),
   @XmlEnumValue("proxcx")
   PROXCX("proxcx"),
   @XmlEnumValue("intermediatebissectrice")
   INTERMEDIATEBISSECTRICE("intermediatebissectrice"),
   @XmlEnumValue("m1")
   M_1("m1"),
   @XmlEnumValue("m2")
   M_2("m2"),
   @XmlEnumValue("distcx")
   DISTCX("distcx"),
   @XmlEnumValue("pl1")
   PL_1("pl1"),
   @XmlEnumValue("pl2")
   PL_2("pl2"),
   @XmlEnumValue("pl3")
   PL_3("pl3"),
   @XmlEnumValue("leftposteriordescending")
   LEFTPOSTERIORDESCENDING("leftposteriordescending"),
   @XmlEnumValue("rv")
   RV("rv"),
   @XmlEnumValue("rightpl2")
   RIGHTPL_2("rightpl2"),
   @XmlEnumValue("rightpl3")
   RIGHTPL_3("rightpl3"),
   @XmlEnumValue("pl4")
   PL_4("pl4");

   private final String value;

   private CDSTENTSEGMENTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSTENTSEGMENTvalues fromValue(String v) {
      CDSTENTSEGMENTvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDSTENTSEGMENTvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
