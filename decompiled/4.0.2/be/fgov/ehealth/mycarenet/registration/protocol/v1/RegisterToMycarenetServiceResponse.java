package be.fgov.ehealth.mycarenet.registration.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "RegisterToMycarenetServiceResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:registration:protocol:v1"
)
public class RegisterToMycarenetServiceResponse extends SendResponseType {
   private static final long serialVersionUID = -6622884767890612976L;

   public RegisterToMycarenetServiceResponse() {
   }
}
