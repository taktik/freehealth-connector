package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.uma.core.v1.AttestationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendUrgentMedicalAidAttestationResponseType",
   propOrder = {"attestion"}
)
@XmlRootElement(
   name = "SendUrgentMedicalAidAttestationResponse"
)
public class SendUrgentMedicalAidAttestationResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Attestion"
   )
   protected AttestationType attestion;

   public SendUrgentMedicalAidAttestationResponse() {
   }

   public AttestationType getAttestion() {
      return this.attestion;
   }

   public void setAttestion(AttestationType value) {
      this.attestion = value;
   }
}
