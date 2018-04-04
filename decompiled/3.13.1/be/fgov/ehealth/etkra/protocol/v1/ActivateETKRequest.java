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
   propOrder = {"signedRequest"}
)
@XmlRootElement(
   name = "ActivateETKRequest"
)
public class ActivateETKRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedRequest",
      required = true
   )
   protected byte[] signedRequest;

   public byte[] getSignedRequest() {
      return this.signedRequest;
   }

   public void setSignedRequest(byte[] value) {
      this.signedRequest = value;
   }
}
