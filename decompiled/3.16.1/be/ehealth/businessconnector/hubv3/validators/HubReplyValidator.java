package be.ehealth.businessconnector.hubv3.validators;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.fgov.ehealth.hubservices.core.v3.AcknowledgeType;

public interface HubReplyValidator {
   void validate(AcknowledgeType var1) throws IntraHubBusinessConnectorException;
}
