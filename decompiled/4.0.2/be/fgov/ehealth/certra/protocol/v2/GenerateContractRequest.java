package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.CertificateIdentifierType;
import be.fgov.ehealth.certra.core.v2.ContactDataType;
import be.fgov.ehealth.commons.core.v2.ActorType;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateContractRequestType",
   propOrder = {"contactData", "signer", "certificateIdentifier"}
)
@XmlRootElement(
   name = "GenerateContractRequest"
)
public class GenerateContractRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "Signer",
      required = true
   )
   protected ActorType signer;
   @XmlElement(
      name = "CertificateIdentifier",
      required = true
   )
   protected CertificateIdentifierType certificateIdentifier;

   public GenerateContractRequest() {
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public ActorType getSigner() {
      return this.signer;
   }

   public void setSigner(ActorType value) {
      this.signer = value;
   }

   public CertificateIdentifierType getCertificateIdentifier() {
      return this.certificateIdentifier;
   }

   public void setCertificateIdentifier(CertificateIdentifierType value) {
      this.certificateIdentifier = value;
   }
}
