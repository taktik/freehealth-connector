package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"value"}
)
public class Text implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;

   public Text() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }
}
