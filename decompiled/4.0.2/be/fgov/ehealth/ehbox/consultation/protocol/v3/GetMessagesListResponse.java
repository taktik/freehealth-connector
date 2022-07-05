package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetMessagesListResponse"
)
public class GetMessagesListResponse extends GetMessageListResponseType {
   private static final long serialVersionUID = 1L;

   public GetMessagesListResponse() {
   }
}
