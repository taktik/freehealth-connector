package be.fgov.ehealth.hubservices.core.v2;

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
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
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
   protected KmehrHeaderDeclareTransactionType kmehrheader;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public KmehrHeaderDeclareTransactionType getKmehrheader() {
      return this.kmehrheader;
   }

   public void setKmehrheader(KmehrHeaderDeclareTransactionType value) {
      this.kmehrheader = value;
   }
}
