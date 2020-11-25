package be.ehealth.businessconnector.mycarenet.memberdata.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;

public interface MemberDataService {
   MemberDataConsultationResponse consultMemberData(MemberDataConsultationRequest var1) throws TechnicalConnectorException;
}
