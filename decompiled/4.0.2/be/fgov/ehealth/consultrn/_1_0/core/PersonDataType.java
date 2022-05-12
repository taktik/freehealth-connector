package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonDataType",
   propOrder = {"birth", "name", "gender", "nationality", "civilstate", "decease", "address"}
)
public class PersonDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Birth"
   )
   protected BirthDeceaseType birth;
   @XmlElement(
      name = "Name"
   )
   protected NameType name;
   @XmlElement(
      name = "Gender"
   )
   protected GenderType gender;
   @XmlElement(
      name = "Nationality"
   )
   protected NationalityType nationality;
   @XmlElement(
      name = "Civilstate"
   )
   protected CivilStateType civilstate;
   @XmlElement(
      name = "Decease"
   )
   protected BirthDeceaseType decease;
   @XmlElement(
      name = "Address"
   )
   protected AddressType address;

   public PersonDataType() {
   }

   public BirthDeceaseType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthDeceaseType value) {
      this.birth = value;
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

   public NationalityType getNationality() {
      return this.nationality;
   }

   public void setNationality(NationalityType value) {
      this.nationality = value;
   }

   public CivilStateType getCivilstate() {
      return this.civilstate;
   }

   public void setCivilstate(CivilStateType value) {
      this.civilstate = value;
   }

   public BirthDeceaseType getDecease() {
      return this.decease;
   }

   public void setDecease(BirthDeceaseType value) {
      this.decease = value;
   }

   public AddressType getAddress() {
      return this.address;
   }

   public void setAddress(AddressType value) {
      this.address = value;
   }
}
