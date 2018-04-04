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
   propOrder = {"distinguishedName", "signedCredentials"}
)
@XmlRootElement(
   name = "RevokeAndActivateRequest"
)
public class RevokeAndActivateRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DistinguishedName",
      required = true
   )
   protected EhealthDistinguishedNameType distinguishedName;
   @XmlElement(
      name = "SignedCredentials",
      required = true
   )
   protected SignedCredentialsType signedCredentials;

   public EhealthDistinguishedNameType getDistinguishedName() {
      return this.distinguishedName;
   }

   public void setDistinguishedName(EhealthDistinguishedNameType value) {
      this.distinguishedName = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
