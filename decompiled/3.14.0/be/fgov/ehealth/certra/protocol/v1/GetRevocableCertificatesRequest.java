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
   propOrder = {"revocableCertificatesDataRequest"}
)
@XmlRootElement(
   name = "GetRevocableCertificatesRequest"
)
public class GetRevocableCertificatesRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RevocableCertificatesDataRequest",
      required = true
   )
   protected byte[] revocableCertificatesDataRequest;

   public byte[] getRevocableCertificatesDataRequest() {
      return this.revocableCertificatesDataRequest;
   }

   public void setRevocableCertificatesDataRequest(byte[] value) {
      this.revocableCertificatesDataRequest = value;
   }
}
