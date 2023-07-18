package be.fgov.ehealth.agreement.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultAgreementResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1"
)
public class ConsultAgreementResponse extends SendResponseType {
   private static final long serialVersionUID = 1L;

   public ConsultAgreementResponse() {
   }
}
