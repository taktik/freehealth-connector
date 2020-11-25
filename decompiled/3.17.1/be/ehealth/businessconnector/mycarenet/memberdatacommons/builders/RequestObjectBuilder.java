package be.ehealth.businessconnector.mycarenet.memberdatacommons.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.memberdatacommons.exception.MemberDataBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;

public interface RequestObjectBuilder {
   MemberDataConsultationRequest buildConsultationRequest(boolean var1, InputReference var2, Object var3) throws TechnicalConnectorException, MemberDataBusinessConnectorException;
}
