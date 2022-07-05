package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetFullMessageRequest"
)
public class GetFullMessageRequest extends MessageRequestType {
   private static final long serialVersionUID = 3623678934286152812L;

   public GetFullMessageRequest() {
   }
}
