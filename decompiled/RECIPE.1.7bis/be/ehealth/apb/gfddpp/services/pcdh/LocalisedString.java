package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalisedString",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"value"}
)
public class LocalisedString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Lang"
   )
   protected LangageType lang;

   public String getValue() {
      return this.value;
   }

   public void setValue(String var1) {
      this.value = var1;
   }

   public LangageType getLang() {
      return this.lang;
   }

   public void setLang(LangageType var1) {
      this.lang = var1;
   }
}
