package be.fgov.ehealth.mycarenet.agreement.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultAgreementRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1"
)
public class ConsultAgreementRequest extends SendRequestType {
   private static final long serialVersionUID = 1L;

   public ConsultAgreementRequest() {
   }
}
