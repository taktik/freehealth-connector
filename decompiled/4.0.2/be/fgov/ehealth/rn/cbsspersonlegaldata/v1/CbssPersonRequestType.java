package be.fgov.ehealth.rn.cbsspersonlegaldata.v1;

import be.fgov.ehealth.rn.baselegaldata.v1.AddressDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.CivilStatesDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.DeceaseInfoDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoDeclarationType;
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesDeclarationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CbssPersonRequestType",
   propOrder = {"name", "nationalities", "birth", "decease", "gender", "civilStates", "address", "contactAddress"}
)
public class CbssPersonRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name"
   )
   protected NameInfoDeclarationType name;
   @XmlElement(
      name = "Nationalities"
   )
   protected NationalitiesDeclarationType nationalities;
   @XmlElement(
      name = "Birth"
   )
   protected BirthInfoDeclarationType birth;
   @XmlElement(
      name = "Decease"
   )
   protected DeceaseInfoDeclarationType decease;
   @XmlElement(
      name = "Gender"
   )
   protected GenderInfoDeclarationType gender;
   @XmlElement(
      name = "CivilStates"
   )
   protected CivilStatesDeclarationType civilStates;
   @XmlElement(
      name = "Address"
   )
   protected AddressDeclarationType address;
   @XmlElement(
      name = "ContactAddress"
   )
   protected ContactAddressDeclarationType contactAddress;

   public CbssPersonRequestType() {
   }

   public NameInfoDeclarationType getName() {
      return this.name;
   }

   public void setName(NameInfoDeclarationType value) {
      this.name = value;
   }

   public NationalitiesDeclarationType getNationalities() {
      return this.nationalities;
   }

   public void setNationalities(NationalitiesDeclarationType value) {
      this.nationalities = value;
   }

   public BirthInfoDeclarationType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthInfoDeclarationType value) {
      this.birth = value;
   }

   public DeceaseInfoDeclarationType getDecease() {
      return this.decease;
   }

   public void setDecease(DeceaseInfoDeclarationType value) {
      this.decease = value;
   }

   public GenderInfoDeclarationType getGender() {
      return this.gender;
   }

   public void setGender(GenderInfoDeclarationType value) {
      this.gender = value;
   }

   public CivilStatesDeclarationType getCivilStates() {
      return this.civilStates;
   }

   public void setCivilStates(CivilStatesDeclarationType value) {
      this.civilStates = value;
   }

   public AddressDeclarationType getAddress() {
      return this.address;
   }

   public void setAddress(AddressDeclarationType value) {
      this.address = value;
   }

   public ContactAddressDeclarationType getContactAddress() {
      return this.contactAddress;
   }

   public void setContactAddress(ContactAddressDeclarationType value) {
      this.contactAddress = value;
   }
}
