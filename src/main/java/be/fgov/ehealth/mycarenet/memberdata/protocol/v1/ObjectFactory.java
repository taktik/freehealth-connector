package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public MemberDataConsultationRequest createMemberDataConsultationRequest() {
      return new MemberDataConsultationRequest();
   }

   public MemberDataConsultationResponse createMemberDataConsultationResponse() {
      return new MemberDataConsultationResponse();
   }
}
