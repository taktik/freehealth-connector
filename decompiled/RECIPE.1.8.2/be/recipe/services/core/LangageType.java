package be.recipe.services.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "LangageType"
)
@XmlEnum
public enum LangageType {
   FR,
   NL,
   EN,
   DE,
   NA;

   public String value() {
      return this.name();
   }

   public static LangageType fromValue(String v) {
      return valueOf(v);
   }
}
