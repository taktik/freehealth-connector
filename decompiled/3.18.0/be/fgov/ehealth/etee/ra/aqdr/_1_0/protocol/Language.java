package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "Language"
)
@XmlEnum
public enum Language {
   FR,
   NL,
   EN;

   public String value() {
      return this.name();
   }

   public static Language fromValue(String v) {
      return valueOf(v);
   }
}
