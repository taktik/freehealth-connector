package be.ehealth.businessconnector.mycarenet.memberdata.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.memberdata.exception.MemberDataBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import oasis.names.tc.saml._2_0.protocol.AttributeQuery;

public interface RequestObjectBuilder {
   MemberDataConsultationRequest buildConsultationRequest(boolean var1, InputReference var2, AttributeQuery var3) throws TechnicalConnectorException, MemberDataBusinessConnectorException;
}
