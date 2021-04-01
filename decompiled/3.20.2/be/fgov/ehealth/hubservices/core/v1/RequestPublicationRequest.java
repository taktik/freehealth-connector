package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestPublicationRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "RequestPublicationRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class RequestPublicationRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectRequestPublicationType select;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectRequestPublicationType getSelect() {
      return this.select;
   }

   public void setSelect(SelectRequestPublicationType value) {
      this.select = value;
   }
}
