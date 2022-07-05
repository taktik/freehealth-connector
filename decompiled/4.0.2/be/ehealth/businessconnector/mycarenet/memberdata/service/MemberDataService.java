package be.ehealth.businessconnector.mycarenet.memberdata.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;

public interface MemberDataService {
   MemberDataConsultationResponse consultMemberData(SAMLToken var1, MemberDataConsultationRequest var2) throws TechnicalConnectorException;
}
