package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultGlobalMedicalFileResponse",
   namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1"
)
public class ConsultGlobalMedicalFileResponse extends SendResponseType {
   private static final long serialVersionUID = -7747596132459460947L;
}
