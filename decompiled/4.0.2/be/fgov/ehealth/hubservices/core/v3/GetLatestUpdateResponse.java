package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetLatestUpdateResponseType",
   propOrder = {"response", "acknowledge", "latestupdatelist"}
)
@XmlRootElement(
   name = "GetLatestUpdateResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetLatestUpdateResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   @XmlElement(
      required = true
   )
   protected LatestUpdateListType latestupdatelist;

   public GetLatestUpdateResponse() {
   }

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public LatestUpdateListType getLatestupdatelist() {
      return this.latestupdatelist;
   }

   public void setLatestupdatelist(LatestUpdateListType value) {
      this.latestupdatelist = value;
   }
}
