package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutTherapeuticLinkRequestType",
   propOrder = {"request", "therapeuticlink"}
)
@XmlRootElement(
   name = "PutTherapeuticLinkRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class PutTherapeuticLinkRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected TherapeuticLinkType therapeuticlink;

   public PutTherapeuticLinkRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public TherapeuticLinkType getTherapeuticlink() {
      return this.therapeuticlink;
   }

   public void setTherapeuticlink(TherapeuticLinkType value) {
      this.therapeuticlink = value;
   }
}
