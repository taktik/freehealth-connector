package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonResponseType",
   propOrder = {"ssin", "nobilityTitle", "name", "nationalities", "birth", "decease", "gender", "civilStates", "address"}
)
public class PersonResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "NobilityTitle"
   )
   protected NobilityTitleType nobilityTitle;
   @XmlElement(
      name = "Name"
   )
   protected PersonNameResponseType name;
   @XmlElement(
      name = "Nationalities"
   )
   protected NationalitiesType nationalities;
   @XmlElement(
      name = "Birth"
   )
   protected BirthResponseType birth;
   @XmlElement(
      name = "Decease"
   )
   protected DeceaseResponseType decease;
   @XmlElement(
      name = "Gender"
   )
   protected GenderType gender;
   @XmlElement(
      name = "CivilStates"
   )
   protected CivilStatesResponseType civilStates;
   @XmlElement(
      name = "Address"
   )
   protected AddressType address;
   @XmlAttribute(
      name = "register"
   )
   protected String register;

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }

   public NobilityTitleType getNobilityTitle() {
      return this.nobilityTitle;
   }

   public void setNobilityTitle(NobilityTitleType value) {
      this.nobilityTitle = value;
   }

   public PersonNameResponseType getName() {
      return this.name;
   }

   public void setName(PersonNameResponseType value) {
      this.name = value;
   }

   public NationalitiesType getNationalities() {
      return this.nationalities;
   }

   public void setNationalities(NationalitiesType value) {
      this.nationalities = value;
   }

   public BirthResponseType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthResponseType value) {
      this.birth = value;
   }

   public DeceaseResponseType getDecease() {
      return this.decease;
   }

   public void setDecease(DeceaseResponseType value) {
      this.decease = value;
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType value) {
      this.gender = value;
   }

   public CivilStatesResponseType getCivilStates() {
      return this.civilStates;
   }

   public void setCivilStates(CivilStatesResponseType value) {
      this.civilStates = value;
   }

   public AddressType getAddress() {
      return this.address;
   }

   public void setAddress(AddressType value) {
      this.address = value;
   }

   public String getRegister() {
      return this.register;
   }

   public void setRegister(String value) {
      this.register = value;
   }
}
