package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetHCPartyConsentRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetHCPartyConsentRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetHCPartyConsentRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectGetHCPartyConsentType select;

   public GetHCPartyConsentRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectGetHCPartyConsentType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetHCPartyConsentType value) {
      this.select = value;
   }
}
