package org.taktik.connector.business.hubv3.validators;

import org.taktik.connector.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.fgov.ehealth.hubservices.core.v3.AcknowledgeType;

public interface HubReplyValidator {
   void validate(AcknowledgeType var1) throws IntraHubBusinessConnectorException;
}
