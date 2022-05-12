package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokeTherapeuticExclusionRequestType",
   propOrder = {"request", "therapeuticexclusion"}
)
@XmlRootElement(
   name = "RevokeTherapeuticExclusionRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class RevokeTherapeuticExclusionRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected TherapeuticExclusionType therapeuticexclusion;

   public RevokeTherapeuticExclusionRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public TherapeuticExclusionType getTherapeuticexclusion() {
      return this.therapeuticexclusion;
   }

   public void setTherapeuticexclusion(TherapeuticExclusionType value) {
      this.therapeuticexclusion = value;
   }
}
