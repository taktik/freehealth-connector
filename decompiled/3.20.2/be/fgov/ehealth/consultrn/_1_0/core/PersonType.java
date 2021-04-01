package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonType",
   propOrder = {"ssin", "personData"}
)
public class PersonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected EncodedSSINType ssin;
   @XmlElement(
      name = "PersonData",
      required = true
   )
   protected PersonDataType personData;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public EncodedSSINType getSSIN() {
      return this.ssin;
   }

   public void setSSIN(EncodedSSINType value) {
      this.ssin = value;
   }

   public PersonDataType getPersonData() {
      return this.personData;
   }

   public void setPersonData(PersonDataType value) {
      this.personData = value;
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
