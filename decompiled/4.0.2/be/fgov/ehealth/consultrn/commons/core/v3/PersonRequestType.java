package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonRequestType",
   propOrder = {"name", "nationalities", "birth", "decease", "gender", "civilStates", "residentialAddress"}
)
public class PersonRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected PersonNameRequestType name;
   @XmlElement(
      name = "Nationalities"
   )
   protected NationalitiesType nationalities;
   @XmlElement(
      name = "Birth",
      required = true
   )
   protected BirthRequestType birth;
   @XmlElement(
      name = "Decease"
   )
   protected DeceaseRequestType decease;
   @XmlElement(
      name = "Gender"
   )
   protected GenderType gender;
   @XmlElement(
      name = "CivilStates"
   )
   protected CivilStatesRequestType civilStates;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressRequestType residentialAddress;

   public PersonRequestType() {
   }

   public PersonNameRequestType getName() {
      return this.name;
   }

   public void setName(PersonNameRequestType value) {
      this.name = value;
   }

   public NationalitiesType getNationalities() {
      return this.nationalities;
   }

   public void setNationalities(NationalitiesType value) {
      this.nationalities = value;
   }

   public BirthRequestType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthRequestType value) {
      this.birth = value;
   }

   public DeceaseRequestType getDecease() {
      return this.decease;
   }

   public void setDecease(DeceaseRequestType value) {
      this.decease = value;
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType value) {
      this.gender = value;
   }

   public CivilStatesRequestType getCivilStates() {
      return this.civilStates;
   }

   public void setCivilStates(CivilStatesRequestType value) {
      this.civilStates = value;
   }

   public ResidentialAddressRequestType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressRequestType value) {
      this.residentialAddress = value;
   }
}
