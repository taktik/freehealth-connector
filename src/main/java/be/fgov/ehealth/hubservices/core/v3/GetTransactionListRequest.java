package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetTransactionListRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "GetTransactionListRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetTransactionListRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestListType request;
   @XmlElement(
      required = true
   )
   protected SelectGetTransactionListType select;

   public RequestListType getRequest() {
      return this.request;
   }

   public void setRequest(RequestListType value) {
      this.request = value;
   }

   public SelectGetTransactionListType getSelect() {
      return this.select;
   }

   public void setSelect(SelectGetTransactionListType value) {
      this.select = value;
   }
}
