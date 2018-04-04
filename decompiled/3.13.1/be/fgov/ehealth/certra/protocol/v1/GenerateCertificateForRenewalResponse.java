package be.fgov.ehealth.certra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"requestId"}
)
@XmlRootElement(
   name = "GenerateCertificateForRenewalResponse"
)
public class GenerateCertificateForRenewalResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String requestId;

   public String getRequestId() {
      return this.requestId;
   }

   public void setRequestId(String value) {
      this.requestId = value;
   }
}
