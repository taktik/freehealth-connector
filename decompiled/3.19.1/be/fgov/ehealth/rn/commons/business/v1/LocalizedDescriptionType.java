package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalizedDescriptionType",
   propOrder = {"value"}
)
public class LocalizedDescriptionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Language"
   )
   protected LanguageType language;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public LanguageType getLanguage() {
      return this.language;
   }

   public void setLanguage(LanguageType value) {
      this.language = value;
   }
}
