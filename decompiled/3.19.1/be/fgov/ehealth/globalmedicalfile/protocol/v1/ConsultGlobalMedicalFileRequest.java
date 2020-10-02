package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultGlobalMedicalFileRequest",
   namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1"
)
public class ConsultGlobalMedicalFileRequest extends SendRequestType {
   private static final long serialVersionUID = -7747596132459460947L;
}
