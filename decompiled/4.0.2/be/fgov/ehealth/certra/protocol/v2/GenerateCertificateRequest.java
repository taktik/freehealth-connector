package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.EHealthCertificateSigningRequestType;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateCertificateRequestType",
   propOrder = {"eHealthCSR", "signature"}
)
@XmlRootElement(
   name = "GenerateCertificateRequest"
)
public class GenerateCertificateRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHealthCSR",
      required = true
   )
   protected EHealthCertificateSigningRequestType eHealthCSR;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected Signature signature;

   public GenerateCertificateRequest() {
   }

   public EHealthCertificateSigningRequestType getEHealthCSR() {
      return this.eHealthCSR;
   }

   public void setEHealthCSR(EHealthCertificateSigningRequestType value) {
      this.eHealthCSR = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }
}
