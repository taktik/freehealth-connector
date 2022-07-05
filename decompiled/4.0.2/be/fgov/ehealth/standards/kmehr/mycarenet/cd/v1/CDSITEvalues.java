package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SITEvalues"
)
@XmlEnum
public enum CDSITEvalues {
   BE("BE"),
   @XmlEnumValue("blood")
   BLOOD("blood"),
   BN("BN"),
   BU("BU"),
   CT("CT"),
   LA("LA"),
   LAC("LAC"),
   LACF("LACF"),
   LD("LD"),
   LE("LE"),
   LEJ("LEJ"),
   LF("LF"),
   LG("LG"),
   LH("LH"),
   LIJ("LIJ"),
   LLAQ("LLAQ"),
   LLFA("LLFA"),
   LMFA("LMFA"),
   LN("LN"),
   LPC("LPC"),
   LSC("LSC"),
   LT("LT"),
   LUA("LUA"),
   LUAQ("LUAQ"),
   LUFA("LUFA"),
   LVG("LVG"),
   LVL("LVL"),
   NB("NB"),
   OD("OD"),
   OS("OS"),
   OU("OU"),
   PA("PA"),
   PERIN("PERIN"),
   RA("RA"),
   RAC("RAC"),
   RACF("RACF"),
   RD("RD"),
   RE("RE"),
   REJ("REJ"),
   RF("RF"),
   RG("RG"),
   RH("RH"),
   RIJ("RIJ"),
   RLAQ("RLAQ"),
   RLFA("RLFA"),
   RMFA("RMFA"),
   RN("RN"),
   RPC("RPC"),
   RSC("RSC"),
   RT("RT"),
   RUA("RUA"),
   RUAQ("RUAQ"),
   RUFA("RUFA"),
   RVG("RVG"),
   RVL("RVL"),
   @XmlEnumValue("lk")
   LK("lk"),
   @XmlEnumValue("rk")
   RK("rk"),
   @XmlEnumValue("lhip")
   LHIP("lhip"),
   @XmlEnumValue("rhip")
   RHIP("rhip"),
   @XmlEnumValue("lfem")
   LFEM("lfem"),
   @XmlEnumValue("rfem")
   RFEM("rfem"),
   @XmlEnumValue("ltib")
   LTIB("ltib"),
   @XmlEnumValue("rtib")
   RTIB("rtib");

   private final String value;

   private CDSITEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSITEvalues fromValue(String v) {
      CDSITEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDSITEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
