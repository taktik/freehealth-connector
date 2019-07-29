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
   name = "GetTherapeuticLinkRequestType",
   propOrder = {"request", "select", "prooves"}
)
@XmlRootElement(
   name = "GetTherapeuticLinkRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class GetTherapeuticLinkRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected GetTherapeuticLinkSelectType select;
   @XmlElement(
      name = "proof"
   )
   protected List<ProofType> prooves;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public GetTherapeuticLinkSelectType getSelect() {
      return this.select;
   }

   public void setSelect(GetTherapeuticLinkSelectType value) {
      this.select = value;
   }

   public List<ProofType> getProoves() {
      if (this.prooves == null) {
         this.prooves = new ArrayList();
      }

      return this.prooves;
   }
}
