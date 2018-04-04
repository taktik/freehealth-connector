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
   propOrder = {"revocableCertificatesDataResponse"}
)
@XmlRootElement(
   name = "GetRevocableCertificatesResponse"
)
public class GetRevocableCertificatesResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RevocableCertificatesDataResponse"
   )
   protected byte[] revocableCertificatesDataResponse;

   public byte[] getRevocableCertificatesDataResponse() {
      return this.revocableCertificatesDataResponse;
   }

   public void setRevocableCertificatesDataResponse(byte[] value) {
      this.revocableCertificatesDataResponse = value;
   }
}
