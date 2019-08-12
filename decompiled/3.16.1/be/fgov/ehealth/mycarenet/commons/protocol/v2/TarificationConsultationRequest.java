package be.fgov.ehealth.mycarenet.commons.protocol.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "TarificationConsultationRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1"
)
public class TarificationConsultationRequest extends SendRequestType {
   private static final long serialVersionUID = -5623378735605620717L;
}
