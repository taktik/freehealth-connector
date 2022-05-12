package be.ehealth.businessconnector.mediprimav2.session;

import be.ehealth.businessconnector.mediprimav2.session.impl.MediprimaConsultationSessionServiceImpl;
import be.ehealth.businessconnector.mediprimav2.session.impl.MediprimaImplementationClassFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MediprimaSessionServiceFactory extends AbstractSessionServiceFactory {
   private MediprimaSessionServiceFactory() {
   }

   public static MediprimaConsultationSessionService getConsultationMediprimaSession() throws ConnectorException {
      return (MediprimaConsultationSessionService)getService(MediprimaConsultationSessionServiceImpl.class, new MediprimaImplementationClassFactory(), new String[0]);
   }
}
