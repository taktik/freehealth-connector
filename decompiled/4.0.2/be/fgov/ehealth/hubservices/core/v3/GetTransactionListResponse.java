package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetTransactionListResponseType",
   propOrder = {"response", "acknowledge", "kmehrheader"}
)
@XmlRootElement(
   name = "GetTransactionListResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetTransactionListResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseListType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected KmehrHeaderGetTransactionList kmehrheader;

   public GetTransactionListResponse() {
   }

   public ResponseListType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseListType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public KmehrHeaderGetTransactionList getKmehrheader() {
      return this.kmehrheader;
   }

   public void setKmehrheader(KmehrHeaderGetTransactionList value) {
      this.kmehrheader = value;
   }
}
