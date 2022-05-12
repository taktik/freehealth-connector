package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetCertificateResponseType",
   propOrder = {"x509Certificates"}
)
@XmlRootElement(
   name = "GetCertificateResponse"
)
public class GetCertificateResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "X509Certificate"
   )
   protected List<byte[]> x509Certificates;

   public GetCertificateResponse() {
   }

   public List<byte[]> getX509Certificates() {
      if (this.x509Certificates == null) {
         this.x509Certificates = new ArrayList();
      }

      return this.x509Certificates;
   }
}
