package org.taktik.connector.technical.service.timestamp.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.timestamp.ConsultServiceV2;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultRequest;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultResponse;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultTSBagRequest;
import be.fgov.ehealth.timestamping.protocol.v2.TSConsultTSBagResponse;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class ConsultServiceV2Impl extends AbstractConsultationServiceImpl<TSConsultTSBagRequest, TSConsultTSBagResponse, TSConsultRequest, TSConsultResponse> implements ConsultServiceV2 {
   public ConsultServiceV2Impl() {
      super(TSConsultTSBagResponse.class, TSConsultResponse.class);
   }

   public TSConsultTSBagResponse getTimestamp(X509Certificate certificate, PrivateKey privateKey, TSConsultTSBagRequest consultRequest) throws TechnicalConnectorException {
      return (TSConsultTSBagResponse)this.obtainTimestamp(certificate, privateKey, consultRequest);
   }

   public TSConsultResponse checkCompleteness(X509Certificate certificate, PrivateKey privateKey, TSConsultRequest consultRequest) throws TechnicalConnectorException {
      return (TSConsultResponse)this.obtainCompleteness(certificate, privateKey, consultRequest);
   }
}
