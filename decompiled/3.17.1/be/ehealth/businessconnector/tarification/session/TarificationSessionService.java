package be.ehealth.businessconnector.tarification.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse;

public interface TarificationSessionService {
   TarificationConsultationResponse consultTarification(TarificationConsultationRequest var1) throws TechnicalConnectorException;
}
