package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "LangageType",
   namespace = "urn:be:fgov:ehealth:commons:core:v1"
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

   public static LangageType fromValue(String var0) {
      return valueOf(var0);
   }
}
