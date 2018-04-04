package be.fgov.ehealth.certra.protocol.v1;

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
   propOrder = {"signedDn", "dn"}
)
@XmlRootElement(
   name = "ValidateRenewRequest"
)
public class ValidateRenewRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected byte[] signedDn;
   @XmlElement(
      required = true
   )
   protected String dn;

   public byte[] getSignedDn() {
      return this.signedDn;
   }

   public void setSignedDn(byte[] value) {
      this.signedDn = value;
   }

   public String getDn() {
      return this.dn;
   }

   public void setDn(String value) {
      this.dn = value;
   }
}
