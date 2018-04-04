package org.taktik.connector.technical.service.timestamp.impl;

import be.behealth.webservices.tsa.TSConsultRequest;
import be.behealth.webservices.tsa.TSConsultResponse;
import be.behealth.webservices.tsa.TSConsultTSBagRequest;
import be.behealth.webservices.tsa.TSConsultTSBagResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.timestamp.ConsultService;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class ConsultServiceImpl extends AbstractConsultationServiceImpl<TSConsultTSBagRequest, TSConsultTSBagResponse, TSConsultRequest, TSConsultResponse> implements ConsultService {
   public ConsultServiceImpl() {
      super(TSConsultTSBagResponse.class, TSConsultResponse.class);
   }

   public TSConsultTSBagResponse getTimestamp(X509Certificate certificate, PrivateKey privateKey, TSConsultTSBagRequest consultRequest) throws TechnicalConnectorException {
      return (TSConsultTSBagResponse)this.obtainTimestamp(certificate, privateKey, consultRequest);
   }

   public TSConsultResponse checkCompleteness(X509Certificate certificate, PrivateKey privateKey, TSConsultRequest consultRequest) throws TechnicalConnectorException {
      return (TSConsultResponse)this.obtainCompleteness(certificate, privateKey, consultRequest);
   }
}
