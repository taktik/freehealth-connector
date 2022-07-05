package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetHCPartyRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetHCPartyRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class GetHCPartyRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectGetHCPartyType select;

   public GetHCPartyRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectGetHCPartyType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetHCPartyType value) {
      this.select = value;
   }
}
