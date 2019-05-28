package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InhabitantType",
   propOrder = {"ssin", "name", "gender", "birthDate"}
)
public class InhabitantType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "Name"
   )
   protected NameType name;
   @XmlElement(
      name = "Gender"
   )
   protected GenderType gender;
   @XmlElement(
      name = "BirthDate"
   )
   protected String birthDate;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public NameType getName() {
      return this.name;
   }

   public void setName(NameType value) {
      this.name = value;
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType value) {
      this.gender = value;
   }

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }
}
