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
   propOrder = {"toBeRegistered", "signedCredentials"}
)
@XmlRootElement(
   name = "CompleteEtkRegistrationRequest"
)
public class CompleteEtkRegistrationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ToBeRegistered",
      required = true
   )
   protected byte[] toBeRegistered;
   @XmlElement(
      name = "SignedCredentials"
   )
   protected SignedCredentialsType signedCredentials;

   public byte[] getToBeRegistered() {
      return this.toBeRegistered;
   }

   public void setToBeRegistered(byte[] value) {
      this.toBeRegistered = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
