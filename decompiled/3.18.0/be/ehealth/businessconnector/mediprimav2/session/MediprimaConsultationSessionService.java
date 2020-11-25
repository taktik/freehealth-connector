package be.ehealth.businessconnector.mediprimav2.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequest;
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse;

public interface MediprimaConsultationSessionService {
   ConsultCarmedInterventionResponse consultCarmedIntervention(ConsultCarmedInterventionRequest var1) throws TechnicalConnectorException;
}
