package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.CertificateInfoType;
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
   name = "GetCertificateInfoForCitizenResponseType",
   propOrder = {"certificateInfos"}
)
@XmlRootElement(
   name = "GetCertificateInfoForCitizenResponse"
)
public class GetCertificateInfoForCitizenResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertificateInfo"
   )
   protected List<CertificateInfoType> certificateInfos;

   public List<CertificateInfoType> getCertificateInfos() {
      if (this.certificateInfos == null) {
         this.certificateInfos = new ArrayList();
      }

      return this.certificateInfos;
   }
}
