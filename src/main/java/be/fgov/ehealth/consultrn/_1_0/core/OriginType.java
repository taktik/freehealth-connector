package be.fgov.ehealth.consultrn._1_0.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "OriginType"
)
@XmlEnum
public enum OriginType {
   BCSS_KSZ,
   RN_RR;

   public String value() {
      return this.name();
   }

   public static OriginType fromValue(String v) {
      return valueOf(v);
   }
}
