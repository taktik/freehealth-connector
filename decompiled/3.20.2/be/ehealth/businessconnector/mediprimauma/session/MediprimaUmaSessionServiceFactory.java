package be.ehealth.businessconnector.mediprimauma.session;

import be.ehealth.businessconnector.mediprimauma.session.impl.MediprimaUmaImplementationClassFactory;
import be.ehealth.businessconnector.mediprimauma.session.impl.MediprimaUmaServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MediprimaUmaSessionServiceFactory extends AbstractSessionServiceFactory {
   private MediprimaUmaSessionServiceFactory() {
   }

   public static MediprimaUmaService getMediprimaUmaSession() throws ConnectorException {
      return (MediprimaUmaService)getService(MediprimaUmaServiceImpl.class, new MediprimaUmaImplementationClassFactory(), new String[0]);
   }
}
