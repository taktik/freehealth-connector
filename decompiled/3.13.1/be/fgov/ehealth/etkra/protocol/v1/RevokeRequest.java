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
   propOrder = {"signedContract", "certificateToRevoke", "signedCredentials"}
)
@XmlRootElement(
   name = "RevokeRequest"
)
public class RevokeRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedContract",
      required = true
   )
   protected byte[] signedContract;
   @XmlElement(
      required = true
   )
   protected byte[] certificateToRevoke;
   @XmlElement(
      name = "SignedCredentials",
      required = true
   )
   protected SignedCredentialsType signedCredentials;

   public byte[] getSignedContract() {
      return this.signedContract;
   }

   public void setSignedContract(byte[] value) {
      this.signedContract = value;
   }

   public byte[] getCertificateToRevoke() {
      return this.certificateToRevoke;
   }

   public void setCertificateToRevoke(byte[] value) {
      this.certificateToRevoke = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
