package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutTherapeuticLinkBulkRequestType",
   propOrder = {"request", "therapeuticlinkrequests"}
)
@XmlRootElement(
   name = "PutTherapeuticLinkBulkRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class PutTherapeuticLinkBulkRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      name = "therapeuticlinkrequest",
      required = true
   )
   protected List<Therapeuticlinkrequest> therapeuticlinkrequests;

   public PutTherapeuticLinkBulkRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public List<Therapeuticlinkrequest> getTherapeuticlinkrequests() {
      if (this.therapeuticlinkrequests == null) {
         this.therapeuticlinkrequests = new ArrayList();
      }

      return this.therapeuticlinkrequests;
   }
}
