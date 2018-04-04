package be.fgov.ehealth.etkra.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signedCsrAndContract", "contactData", "signedCredentials"}
)
@XmlRootElement(
   name = "ProcessCsrRequest"
)
public class ProcessCsrRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedCsrAndContract",
      required = true
   )
   protected byte[] signedCsrAndContract;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "SignedCredentials",
      required = true
   )
   protected SignedCredentialsType signedCredentials;

   public byte[] getSignedCsrAndContract() {
      return this.signedCsrAndContract;
   }

   public void setSignedCsrAndContract(byte[] value) {
      this.signedCsrAndContract = value;
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
