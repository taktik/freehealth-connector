package org.taktik.connector.business.tarification.session;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse;

public interface TarificationSessionService {
   TarificationConsultationResponse consultTarification(TarificationConsultationRequest var1) throws TechnicalConnectorException;
}
