package be.fgov.ehealth.certra.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EHealthCertificateSigningRequestType",
   propOrder = {"contactData", "csr", "contract", "baseServiceUsages"}
)
public class EHealthCertificateSigningRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "CSR",
      required = true
   )
   protected byte[] csr;
   @XmlElement(
      name = "Contract",
      required = true
   )
   protected ContractType contract;
   @XmlElement(
      name = "BaseServiceUsage"
   )
   protected List<String> baseServiceUsages;

   public EHealthCertificateSigningRequestType() {
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public byte[] getCSR() {
      return this.csr;
   }

   public void setCSR(byte[] value) {
      this.csr = value;
   }

   public ContractType getContract() {
      return this.contract;
   }

   public void setContract(ContractType value) {
      this.contract = value;
   }

   public List<String> getBaseServiceUsages() {
      if (this.baseServiceUsages == null) {
         this.baseServiceUsages = new ArrayList();
      }

      return this.baseServiceUsages;
   }
}
