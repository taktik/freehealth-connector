package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SITE"
)
@XmlEnum
public enum CDSITE {
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
   RHIP("rhip");

   private final String value;

   private CDSITE(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSITE fromValue(String v) {
      CDSITE[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSITE c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
