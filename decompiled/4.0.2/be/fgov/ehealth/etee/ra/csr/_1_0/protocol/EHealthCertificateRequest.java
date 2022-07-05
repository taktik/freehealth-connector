package be.fgov.ehealth.etee.ra.csr._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"contactData", "additionalSearchCriteria", "csr", "contract", "usagesType", "ehActorQualitiesSignedData"}
)
@XmlRootElement(
   name = "EHealthCertificateRequest"
)
public class EHealthCertificateRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "AdditionalSearchCriterium"
   )
   protected List<SearchCriteriumType> additionalSearchCriteria;
   @XmlElement(
      name = "Csr",
      required = true
   )
   protected byte[] csr;
   @XmlElement(
      name = "Contract",
      required = true
   )
   protected String contract;
   @XmlElement(
      name = "UsagesType"
   )
   protected UsagesType usagesType;
   @XmlElement(
      name = "EHActorQualitiesSignedData",
      required = true
   )
   protected byte[] ehActorQualitiesSignedData;

   public EHealthCertificateRequest() {
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public List<SearchCriteriumType> getAdditionalSearchCriteria() {
      if (this.additionalSearchCriteria == null) {
         this.additionalSearchCriteria = new ArrayList();
      }

      return this.additionalSearchCriteria;
   }

   public byte[] getCsr() {
      return this.csr;
   }

   public void setCsr(byte[] value) {
      this.csr = value;
   }

   public String getContract() {
      return this.contract;
   }

   public void setContract(String value) {
      this.contract = value;
   }

   public UsagesType getUsagesType() {
      return this.usagesType;
   }

   public void setUsagesType(UsagesType value) {
      this.usagesType = value;
   }

   public byte[] getEHActorQualitiesSignedData() {
      return this.ehActorQualitiesSignedData;
   }

   public void setEHActorQualitiesSignedData(byte[] value) {
      this.ehActorQualitiesSignedData = value;
   }
}
