package be.fgov.ehealth.mycarenet.registration.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendRequestType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "RegisterToMycarenetServiceRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:registration:protocol:v1"
)
public class RegisterToMycarenetServiceRequest extends SendRequestType {
   private static final long serialVersionUID = -6622884767890612976L;
}
