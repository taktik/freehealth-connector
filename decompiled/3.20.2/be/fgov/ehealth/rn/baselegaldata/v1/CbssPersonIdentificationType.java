package be.fgov.ehealth.rn.baselegaldata.v1;

import be.fgov.ehealth.rn.registries.commons.v1.AnomaliesType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CbssPersonIdentificationType",
   propOrder = {"ssin", "name", "birth", "gender", "address", "contactAddress", "anomalies"}
)
public class CbssPersonIdentificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin"
   )
   protected String ssin;
   @XmlElement(
      name = "Name"
   )
   protected MinimalNameInfoType name;
   @XmlElement(
      name = "Birth"
   )
   protected MinimalBirthInfoType birth;
   @XmlElement(
      name = "Gender"
   )
   protected MinimalGenderInfoType gender;
   @XmlElement(
      name = "Address"
   )
   protected AddressCbssBaseType address;
   @XmlElement(
      name = "ContactAddress"
   )
   protected ContactAddressBaseType contactAddress;
   @XmlElement(
      name = "Anomalies"
   )
   protected AnomaliesType anomalies;
   @XmlAttribute(
      name = "Register"
   )
   protected String register;
   @XmlAttribute(
      name = "Deceased"
   )
   protected Boolean deceased;

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }

   public MinimalNameInfoType getName() {
      return this.name;
   }

   public void setName(MinimalNameInfoType value) {
      this.name = value;
   }

   public MinimalBirthInfoType getBirth() {
      return this.birth;
   }

   public void setBirth(MinimalBirthInfoType value) {
      this.birth = value;
   }

   public MinimalGenderInfoType getGender() {
      return this.gender;
   }

   public void setGender(MinimalGenderInfoType value) {
      this.gender = value;
   }

   public AddressCbssBaseType getAddress() {
      return this.address;
   }

   public void setAddress(AddressCbssBaseType value) {
      this.address = value;
   }

   public ContactAddressBaseType getContactAddress() {
      return this.contactAddress;
   }

   public void setContactAddress(ContactAddressBaseType value) {
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

   public Boolean isDeceased() {
      return this.deceased;
   }

   public void setDeceased(Boolean value) {
      this.deceased = value;
   }
}
