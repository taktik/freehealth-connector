package be.ehealth.businessconnector.mediprima.session;

import be.ehealth.businessconnector.mediprima.session.impl.MediprimaConsultationSessionServiceImpl;
import be.ehealth.businessconnector.mediprima.session.impl.MediprimaImplementationClassFactory;
import be.ehealth.businessconnector.mediprima.session.impl.MediprimaTarificationSessionServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MediprimaSessionServiceFactory extends AbstractSessionServiceFactory {
   private MediprimaSessionServiceFactory() {
   }

   public static MediprimaConsultationSessionService getConsultationMediprimaSession() throws ConnectorException {
      return (MediprimaConsultationSessionService)getService(MediprimaConsultationSessionServiceImpl.class, new MediprimaImplementationClassFactory(), new String[0]);
   }

   public static MediprimaTarificationSessionService getTarificationMediprimaSession() throws ConnectorException {
      return (MediprimaTarificationSessionService)getService(MediprimaTarificationSessionServiceImpl.class, new MediprimaImplementationClassFactory(), new String[0]);
   }
}
