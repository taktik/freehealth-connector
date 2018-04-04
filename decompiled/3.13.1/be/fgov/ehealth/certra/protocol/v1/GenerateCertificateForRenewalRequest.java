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
   propOrder = {"ehcsr"}
)
@XmlRootElement(
   name = "GenerateCertificateForRenewalRequest"
)
public class GenerateCertificateForRenewalRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected byte[] ehcsr;

   public byte[] getEhcsr() {
      return this.ehcsr;
   }

   public void setEhcsr(byte[] value) {
      this.ehcsr = value;
   }
}
