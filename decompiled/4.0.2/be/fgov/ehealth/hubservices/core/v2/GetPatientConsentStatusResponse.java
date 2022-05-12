package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPatientConsentStatusResponseType",
   propOrder = {"response", "acknowledge", "consent"}
)
@XmlRootElement(
   name = "GetPatientConsentStatusResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class GetPatientConsentStatusResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected ConsentWithStatusType consent;

   public GetPatientConsentStatusResponse() {
   }

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public ConsentWithStatusType getConsent() {
      return this.consent;
   }

   public void setConsent(ConsentWithStatusType value) {
      this.consent = value;
   }
}
