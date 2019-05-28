package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutPatientConsentRequestType",
   propOrder = {"request", "consent"}
)
@XmlRootElement(
   name = "PutPatientConsentRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class PutPatientConsentRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected ConsentType consent;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public ConsentType getConsent() {
      return this.consent;
   }

   public void setConsent(ConsentType value) {
      this.consent = value;
   }
}
