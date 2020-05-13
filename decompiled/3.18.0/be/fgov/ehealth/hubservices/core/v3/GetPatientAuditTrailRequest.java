package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPatientAuditTrailRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetPatientAuditTrailRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetPatientAuditTrailRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestListType request;
   @XmlElement(
      required = true
   )
   protected SelectGetPatientAuditTrailType select;

   public RequestListType getRequest() {
      return this.request;
   }

   public void setRequest(RequestListType value) {
      this.request = value;
   }

   public SelectGetPatientAuditTrailType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetPatientAuditTrailType value) {
      this.select = value;
   }
}
