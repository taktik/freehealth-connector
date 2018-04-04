package be.fgov.ehealth.certra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"revokeDataRequest"}
)
@XmlRootElement(
   name = "RevokeRequest"
)
public class RevokeRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RevokeDataRequest",
      required = true
   )
   protected byte[] revokeDataRequest;

   public byte[] getRevokeDataRequest() {
      return this.revokeDataRequest;
   }

   public void setRevokeDataRequest(byte[] value) {
      this.revokeDataRequest = value;
   }
}
