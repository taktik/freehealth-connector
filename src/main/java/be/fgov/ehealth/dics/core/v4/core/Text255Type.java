package be.fgov.ehealth.dics.core.v4.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Text255Type",
   propOrder = {"fr", "nl", "de", "en"}
)
public class Text255Type implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Fr",
      required = true
   )
   protected String fr;
   @XmlElement(
      name = "Nl",
      required = true
   )
   protected String nl;
   @XmlElement(
      name = "De"
   )
   protected String de;
   @XmlElement(
      name = "En"
   )
   protected String en;

   public String getFr() {
      return this.fr;
   }

   public void setFr(String value) {
      this.fr = value;
   }

   public String getNl() {
      return this.nl;
   }

   public void setNl(String value) {
      this.nl = value;
   }

   public String getDe() {
      return this.de;
   }

   public void setDe(String value) {
      this.de = value;
   }

   public String getEn() {
      return this.en;
   }

   public void setEn(String value) {
      this.en = value;
   }
}
