package be.fgov.ehealth.etkra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signedCertificate", "etk"}
)
@XmlRootElement(
   name = "RevokeAndActivateResponse"
)
public class RevokeAndActivateResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedCertificate"
   )
   protected byte[] signedCertificate;
   @XmlElement(
      name = "ETK"
   )
   protected byte[] etk;

   public byte[] getSignedCertificate() {
      return this.signedCertificate;
   }

   public void setSignedCertificate(byte[] value) {
      this.signedCertificate = value;
   }

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }
}
