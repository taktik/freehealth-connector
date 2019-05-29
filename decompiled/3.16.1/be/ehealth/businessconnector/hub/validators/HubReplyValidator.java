package be.ehealth.businessconnector.hub.validators;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.business.intrahubcommons.exception.KmehrBusinessConnectorException;
import be.fgov.ehealth.hubservices.core.v1.AcknowledgeType;

public interface HubReplyValidator {
   void validate(AcknowledgeType var1) throws IntraHubBusinessConnectorException, KmehrBusinessConnectorException;
}
