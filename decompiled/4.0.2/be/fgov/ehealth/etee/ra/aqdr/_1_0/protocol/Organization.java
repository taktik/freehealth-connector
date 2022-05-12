package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"nameFr", "nameNl", "identifier"}
)
public class Organization implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NameFr",
      required = true
   )
   protected String nameFr;
   @XmlElement(
      name = "NameNl",
      required = true
   )
   protected String nameNl;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   protected Identifier identifier;

   public Organization() {
   }

   public String getNameFr() {
      return this.nameFr;
   }

   public void setNameFr(String value) {
      this.nameFr = value;
   }

   public String getNameNl() {
      return this.nameNl;
   }

   public void setNameNl(String value) {
      this.nameNl = value;
   }

   public Identifier getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(Identifier value) {
      this.identifier = value;
   }
}
