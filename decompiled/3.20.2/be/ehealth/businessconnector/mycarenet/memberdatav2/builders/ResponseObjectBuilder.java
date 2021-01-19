package be.ehealth.businessconnector.mycarenet.memberdatav2.builders;

import be.ehealth.businessconnector.mycarenet.memberdatav2.domain.MemberDataBuilderResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;

public interface ResponseObjectBuilder {
   MemberDataBuilderResponse handleConsultationResponse(MemberDataConsultationResponse var1) throws TechnicalConnectorException;
}
