package be.ehealth.businessconnector.genins.builders;

import be.ehealth.businessconnector.genins.domain.RequestParameters;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;

public interface RequestObjectBuilder {
   GetInsurabilityAsXmlOrFlatRequestType createGetInsurabilityRequest(RequestParameters var1, boolean var2) throws TechnicalConnectorException, GenInsBusinessConnectorException, InstantiationException;
}
