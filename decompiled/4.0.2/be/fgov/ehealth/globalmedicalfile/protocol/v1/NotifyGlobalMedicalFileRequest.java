package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "NotifyGlobalMedicalFileRequest",
   namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1"
)
public class NotifyGlobalMedicalFileRequest extends SendRequestType {
   private static final long serialVersionUID = 7474695658294730459L;

   public NotifyGlobalMedicalFileRequest() {
   }
}
