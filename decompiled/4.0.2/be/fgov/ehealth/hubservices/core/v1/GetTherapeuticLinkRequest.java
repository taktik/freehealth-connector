package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetTherapeuticLinkRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetTherapeuticLinkRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class GetTherapeuticLinkRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectGetHCPartyPatientConsentType select;

   public GetTherapeuticLinkRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectGetHCPartyPatientConsentType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetHCPartyPatientConsentType value) {
      this.select = value;
   }
}
