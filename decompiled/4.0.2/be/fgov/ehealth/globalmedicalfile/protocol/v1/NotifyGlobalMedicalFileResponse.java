package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "NotifyGlobalMedicalFileResponse",
   namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1"
)
public class NotifyGlobalMedicalFileResponse extends SendResponseType {
   private static final long serialVersionUID = 7474695658294730459L;

   public NotifyGlobalMedicalFileResponse() {
   }
}
