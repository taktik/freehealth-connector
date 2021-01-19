package be.fgov.ehealth.rn.cbsspersonlegaldata.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.rn.baselegaldata.v1.AddressCbssWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.CivilStatesWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.DeceaseInfoWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoWithUpdateStatusType;
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesWithUpdateStatusType;
import be.fgov.ehealth.rn.registries.commons.v1.AnomaliesType;
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
   name = "CbssUpdatePersonResponseType",
   propOrder = {"ssin", "name", "nationalities", "birth", "decease", "gender", "civilStates", "address", "contactAddress", "anomalies"}
)
public class CbssUpdatePersonResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected NameInfoWithUpdateStatusType name;
   @XmlElement(
      name = "Nationalities"
   )
   protected NationalitiesWithUpdateStatusType nationalities;
   @XmlElement(
      name = "Birth"
   )
   protected BirthInfoWithUpdateStatusType birth;
   @XmlElement(
      name = "Decease"
   )
   protected DeceaseInfoWithUpdateStatusType decease;
   @XmlElement(
      name = "Gender"
   )
   protected GenderInfoWithUpdateStatusType gender;
   @XmlElement(
      name = "CivilStates"
   )
   protected CivilStatesWithUpdateStatusType civilStates;
   @XmlElement(
      name = "Address"
   )
   protected AddressCbssWithUpdateStatusType address;
   @XmlElement(
      name = "ContactAddress"
   )
   protected ContactAddressWithUpdateStatusType contactAddress;
   @XmlElement(
      name = "Anomalies"
   )
   protected AnomaliesType anomalies;
   @XmlAttribute(
      name = "Register"
   )
   protected String register;
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

   public NameInfoWithUpdateStatusType getName() {
      return this.name;
   }

   public void setName(NameInfoWithUpdateStatusType value) {
      this.name = value;
   }

   public NationalitiesWithUpdateStatusType getNationalities() {
      return this.nationalities;
   }

   public void setNationalities(NationalitiesWithUpdateStatusType value) {
      this.nationalities = value;
   }

   public BirthInfoWithUpdateStatusType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthInfoWithUpdateStatusType value) {
      this.birth = value;
   }

   public DeceaseInfoWithUpdateStatusType getDecease() {
      return this.decease;
   }

   public void setDecease(DeceaseInfoWithUpdateStatusType value) {
      this.decease = value;
   }

   public GenderInfoWithUpdateStatusType getGender() {
      return this.gender;
   }

   public void setGender(GenderInfoWithUpdateStatusType value) {
      this.gender = value;
   }

   public CivilStatesWithUpdateStatusType getCivilStates() {
      return this.civilStates;
   }

   public void setCivilStates(CivilStatesWithUpdateStatusType value) {
      this.civilStates = value;
   }

   public AddressCbssWithUpdateStatusType getAddress() {
      return this.address;
   }

   public void setAddress(AddressCbssWithUpdateStatusType value) {
      this.address = value;
   }

   public ContactAddressWithUpdateStatusType getContactAddress() {
      return this.contactAddress;
   }

   public void setContactAddress(ContactAddressWithUpdateStatusType value) {
      this.contactAddress = value;
   }

   public AnomaliesType getAnomalies() {
      return this.anomalies;
   }

   public void setAnomalies(AnomaliesType value) {
      this.anomalies = value;
   }

   public String getRegister() {
      return this.register;
   }

   public void setRegister(String value) {
      this.register = value;
   }

   public DateTime getRegisterInceptionDate() {
      return this.registerInceptionDate;
   }

   public void setRegisterInceptionDate(DateTime value) {
      this.registerInceptionDate = value;
   }
}
