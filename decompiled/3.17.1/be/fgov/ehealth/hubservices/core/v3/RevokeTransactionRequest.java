package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokeTransactionRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "RevokeTransactionRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class RevokeTransactionRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectRevokeTransactionType select;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectRevokeTransactionType getSelect() {
      return this.select;
   }

   public void setSelect(SelectRevokeTransactionType value) {
      this.select = value;
   }
}
