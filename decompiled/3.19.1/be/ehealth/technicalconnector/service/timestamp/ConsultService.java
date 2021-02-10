package be.ehealth.technicalconnector.service.timestamp;

import be.behealth.webservices.tsa.TSConsultRequest;
import be.behealth.webservices.tsa.TSConsultResponse;
import be.behealth.webservices.tsa.TSConsultTSBagRequest;
import be.behealth.webservices.tsa.TSConsultTSBagResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/** @deprecated */
@Deprecated
public interface ConsultService {
   TSConsultTSBagResponse getTimestamp(X509Certificate var1, PrivateKey var2, TSConsultTSBagRequest var3) throws TechnicalConnectorException;

   TSConsultResponse checkCompleteness(X509Certificate var1, PrivateKey var2, TSConsultRequest var3) throws TechnicalConnectorException;
}
