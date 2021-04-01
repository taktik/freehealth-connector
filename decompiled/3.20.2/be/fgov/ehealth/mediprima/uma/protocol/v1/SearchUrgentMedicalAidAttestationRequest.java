package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.mediprima.uma.core.v1.CriteriaType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchUrgentMedicalAidAttestationRequestType",
   propOrder = {"criteria"}
)
@XmlRootElement(
   name = "SearchUrgentMedicalAidAttestationRequest"
)
public class SearchUrgentMedicalAidAttestationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Criteria",
      required = true
   )
   protected CriteriaType criteria;

   public CriteriaType getCriteria() {
      return this.criteria;
   }

   public void setCriteria(CriteriaType value) {
      this.criteria = value;
   }
}
