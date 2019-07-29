package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeleteUrgentMedicalAidAttestationResponseType",
   propOrder = {"attestionNumber"}
)
@XmlRootElement(
   name = "DeleteUrgentMedicalAidAttestationResponse"
)
public class DeleteUrgentMedicalAidAttestationResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttestionNumber"
   )
   protected String attestionNumber;

   public String getAttestionNumber() {
      return this.attestionNumber;
   }

   public void setAttestionNumber(String value) {
      this.attestionNumber = value;
   }
}
