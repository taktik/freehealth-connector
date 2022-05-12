package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetHistoryRequest"
)
public class GetHistoryRequest extends MessageRequestType {
   private static final long serialVersionUID = -4886737250289087877L;

   public GetHistoryRequest() {
   }
}
