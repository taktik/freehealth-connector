package be.ehealth.businessconnector.mediprima.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mediprima.protocol.v1.ConsultCarmedInterventionRequest;
import be.fgov.ehealth.mediprima.protocol.v1.ConsultCarmedInterventionResponse;

public interface MediprimaConsultationSessionService {
   ConsultCarmedInterventionResponse consultCarmedIntervention(ConsultCarmedInterventionRequest var1) throws TechnicalConnectorException;
}
