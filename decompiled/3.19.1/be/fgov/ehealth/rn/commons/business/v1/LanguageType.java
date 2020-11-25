package be.fgov.ehealth.rn.commons.business.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "LanguageType"
)
@XmlEnum
public enum LanguageType {
   NL,
   FR,
   DE;

   public String value() {
      return this.name();
   }

   public static LanguageType fromValue(String v) {
      return valueOf(v);
   }
}
