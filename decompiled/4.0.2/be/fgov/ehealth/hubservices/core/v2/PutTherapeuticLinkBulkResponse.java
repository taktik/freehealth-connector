package be.fgov.ehealth.hubservices.core.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "PutTherapeuticLinkBulkResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2"
)
public class PutTherapeuticLinkBulkResponse extends PutTherapeuticLinkResponseType {
   private static final long serialVersionUID = 1L;

   public PutTherapeuticLinkBulkResponse() {
   }
}
