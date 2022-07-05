package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPatientConsentRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetPatientConsentRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class GetPatientConsentRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectGetPatientConsentType select;

   public GetPatientConsentRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectGetPatientConsentType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetPatientConsentType value) {
      this.select = value;
   }
}
