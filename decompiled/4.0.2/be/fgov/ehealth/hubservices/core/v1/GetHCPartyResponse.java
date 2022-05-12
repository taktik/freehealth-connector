package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetHCPartyResponseType",
   propOrder = {"response", "acknowledge", "hcparty"}
)
@XmlRootElement(
   name = "GetHCPartyResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class GetHCPartyResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected HCPartyAdaptedType hcparty;

   public GetHCPartyResponse() {
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

   public HCPartyAdaptedType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyAdaptedType value) {
      this.hcparty = value;
   }
}
