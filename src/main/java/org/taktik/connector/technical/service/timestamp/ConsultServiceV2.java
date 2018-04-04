package org.taktik.connector.technical.service.timestamp;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultRequest;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultResponse;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultTSBagRequest;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultTSBagResponse;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public interface ConsultServiceV2 {
   TSConsultTSBagResponse getTimestamp(X509Certificate var1, PrivateKey var2, TSConsultTSBagRequest var3) throws TechnicalConnectorException;

   TSConsultResponse checkCompleteness(X509Certificate var1, PrivateKey var2, TSConsultRequest var3) throws TechnicalConnectorException;
}
