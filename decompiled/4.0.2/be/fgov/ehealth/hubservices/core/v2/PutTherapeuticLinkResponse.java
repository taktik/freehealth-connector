package be.fgov.ehealth.hubservices.core.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "PutTherapeuticLinkResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class PutTherapeuticLinkResponse extends PutTherapeuticLinkResponseType {
   private static final long serialVersionUID = 1L;

   public PutTherapeuticLinkResponse() {
   }
}
