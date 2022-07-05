package be.ehealth.businessconnector.mediprima.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse;

public interface MediprimaTarificationSessionService {
   TarificationConsultationResponse consultTarification(TarificationConsultationRequest var1) throws TechnicalConnectorException;
}
