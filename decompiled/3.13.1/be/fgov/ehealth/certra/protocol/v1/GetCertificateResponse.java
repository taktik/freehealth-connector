package be.fgov.ehealth.certra.protocol.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"certificate", "caCertificates"}
)
@XmlRootElement(
   name = "GetCertificateResponse"
)
public class GetCertificateResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected byte[] certificate;
   protected List<byte[]> caCertificates;

   public byte[] getCertificate() {
      return this.certificate;
   }

   public void setCertificate(byte[] value) {
      this.certificate = value;
   }

   public List<byte[]> getCaCertificates() {
      if (this.caCertificates == null) {
         this.caCertificates = new ArrayList();
      }

      return this.caCertificates;
   }
}
