package org.taktik.connector.business.mycarenet.mhm.service;

import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface MhmService {
   SendSubscriptionResponse sendSubscription(SAMLToken var1, SendRequestType var2) throws TechnicalConnectorException;
}
