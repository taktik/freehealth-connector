package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.AuthorRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeleteUrgentMedicalAidAttestationRequestType",
   propOrder = {"beneficiarySsin", "attestationNumber"}
)
@XmlRootElement(
   name = "DeleteUrgentMedicalAidAttestationRequest"
)
public class DeleteUrgentMedicalAidAttestationRequest extends AuthorRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeneficiarySsin",
      required = true
   )
   protected String beneficiarySsin;
   @XmlElement(
      name = "AttestationNumber",
      required = true
   )
   protected String attestationNumber;

   public DeleteUrgentMedicalAidAttestationRequest() {
   }

   public String getBeneficiarySsin() {
      return this.beneficiarySsin;
   }

   public void setBeneficiarySsin(String value) {
      this.beneficiarySsin = value;
   }

   public String getAttestationNumber() {
      return this.attestationNumber;
   }

   public void setAttestationNumber(String value) {
      this.attestationNumber = value;
   }
}
