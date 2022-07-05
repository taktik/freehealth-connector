package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeclareTransactionRequestType",
   propOrder = {"request", "kmehrheader"}
)
@XmlRootElement(
   name = "DeclareTransactionRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class DeclareTransactionRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected KmehrHeaderDeclareTransaction kmehrheader;

   public DeclareTransactionRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public KmehrHeaderDeclareTransaction getKmehrheader() {
      return this.kmehrheader;
   }

   public void setKmehrheader(KmehrHeaderDeclareTransaction value) {
      this.kmehrheader = value;
   }
}
