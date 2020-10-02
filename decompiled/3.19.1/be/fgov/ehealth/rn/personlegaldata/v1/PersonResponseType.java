package be.fgov.ehealth.rn.personlegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.rn.baselegaldata.v1.AddressBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.AdministratorBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.CivilStatesBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.DeceaseInfoBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.LegalCohabitationBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.NobilityTitleBaseType;
import be.fgov.ehealth.rn.baselegaldata.v1.SubregisterBaseType;
import be.fgov.ehealth.rn.registries.commons.v1.AnomaliesType;
import be.fgov.ehealth.rn.registries.commons.v1.PersonRegisterType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonResponseType",
   propOrder = {"ssin", "nobilityTitle", "name", "nationalities", "birth", "decease", "gender", "civilStates", "address", "contactAddress", "administrator", "subregister", "legalCohabitation", "anomalies"}
)
public class PersonResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin"
   )
   protected String ssin;
   @XmlElement(
      name = "NobilityTitle"
   )
   protected NobilityTitleBaseType nobilityTitle;
   @XmlElement(
      name = "Name"
   )
   protected NameInfoBaseType name;
   @XmlElement(
      name = "Nationalities"
   )
   protected NationalitiesBaseType nationalities;
   @XmlElement(
      name = "Birth"
   )
   protected BirthInfoBaseType birth;
   @XmlElement(
      name = "Decease"
   )
   protected DeceaseInfoBaseType decease;
   @XmlElement(
      name = "Gender"
   )
   protected GenderInfoBaseType gender;
   @XmlElement(
      name = "CivilStates"
   )
   protected CivilStatesBaseType civilStates;
   @XmlElement(
      name = "Address"
   )
   protected AddressBaseType address;
   @XmlElement(
      name = "ContactAddress"
   )
   protected ContactAddressBaseType contactAddress;
   @XmlElement(
      name = "Administrator"
   )
   protected AdministratorBaseType administrator;
   @XmlElement(
      name = "Subregister"
   )
   protected SubregisterBaseType subregister;
   @XmlElement(
      name = "LegalCohabitation"
   )
   protected LegalCohabitationBaseType legalCohabitation;
   @XmlElement(
      name = "Anomalies"
   )
   protected AnomaliesType anomalies;
   @XmlAttribute(
      name = "Register"
   )
   protected PersonRegisterType register;
   @XmlAttribute(
      name = "RegisterInceptionDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime registerInceptionDate;

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }

   public NobilityTitleBaseType getNobilityTitle() {
      return this.nobilityTitle;
   }

   public void setNobilityTitle(NobilityTitleBaseType value) {
      this.nobilityTitle = value;
   }

   public NameInfoBaseType getName() {
      return this.name;
   }

   public void setName(NameInfoBaseType value) {
      this.name = value;
   }

   public NationalitiesBaseType getNationalities() {
      return this.nationalities;
   }

   public void setNationalities(NationalitiesBaseType value) {
      this.nationalities = value;
   }

   public BirthInfoBaseType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthInfoBaseType value) {
      this.birth = value;
   }

   public DeceaseInfoBaseType getDecease() {
      return this.decease;
   }

   public void setDecease(DeceaseInfoBaseType value) {
      this.decease = value;
   }

   public GenderInfoBaseType getGender() {
      return this.gender;
   }

   public void setGender(GenderInfoBaseType value) {
      this.gender = value;
   }

   public CivilStatesBaseType getCivilStates() {
      return this.civilStates;
   }

   public void setCivilStates(CivilStatesBaseType value) {
      this.civilStates = value;
   }

   public AddressBaseType getAddress() {
      return this.address;
   }

   public void setAddress(AddressBaseType value) {
      this.address = value;
   }

   public ContactAddressBaseType getContactAddress() {
      return this.contactAddress;
   }

   public void setContactAddress(ContactAddressBaseType value) {
      this.contactAddress = value;
   }

   public AdministratorBaseType getAdministrator() {
      return this.administrator;
   }

   public void setAdministrator(AdministratorBaseType value) {
      this.administrator = value;
   }

   public SubregisterBaseType getSubregister() {
      return this.subregister;
   }

   public void setSubregister(SubregisterBaseType value) {
      this.subregister = value;
   }

   public LegalCohabitationBaseType getLegalCohabitation() {
      return this.legalCohabitation;
   }

   public void setLegalCohabitation(LegalCohabitationBaseType value) {
      this.legalCohabitation = value;
   }

   public AnomaliesType getAnomalies() {
      return this.anomalies;
   }

   public void setAnomalies(AnomaliesType value) {
      this.anomalies = value;
   }

   public PersonRegisterType getRegister() {
      return this.register;
   }

   public void setRegister(PersonRegisterType value) {
      this.register = value;
   }

   public DateTime getRegisterInceptionDate() {
      return this.registerInceptionDate;
   }

   public void setRegisterInceptionDate(DateTime value) {
      this.registerInceptionDate = value;
   }
}
