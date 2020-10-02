package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthDeceaseType",
   propOrder = {"date", "localisation"}
)
public class BirthDeceaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Date",
      required = true
   )
   protected String date;
   @XmlElement(
      name = "Localisation"
   )
   protected WhereType localisation;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public String getDate() {
      return this.date;
   }

   public void setDate(String value) {
      this.date = value;
   }

   public WhereType getLocalisation() {
      return this.localisation;
   }

   public void setLocalisation(WhereType value) {
      this.localisation = value;
   }

   public String getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(String value) {
      this.modificationDate = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }
}
