package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.uma.core.v1.AttestationType;
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
   name = "SearchUrgentMedicalAidAttestationResponseType",
   propOrder = {"attestations"}
)
@XmlRootElement(
   name = "SearchUrgentMedicalAidAttestationResponse"
)
public class SearchUrgentMedicalAidAttestationResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Attestation"
   )
   protected List<AttestationType> attestations;

   public List<AttestationType> getAttestations() {
      if (this.attestations == null) {
         this.attestations = new ArrayList();
      }

      return this.attestations;
   }
}
