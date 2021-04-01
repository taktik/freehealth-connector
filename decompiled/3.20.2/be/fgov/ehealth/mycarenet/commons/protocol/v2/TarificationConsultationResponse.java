package be.fgov.ehealth.mycarenet.commons.protocol.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "TarificationConsultationResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1"
)
public class TarificationConsultationResponse extends SendResponseType {
   private static final long serialVersionUID = -1823503051478209431L;
}
