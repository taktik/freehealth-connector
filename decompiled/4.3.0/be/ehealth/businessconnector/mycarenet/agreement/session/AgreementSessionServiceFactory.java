package be.ehealth.businessconnector.mycarenet.agreement.session;

import be.ehealth.businessconnector.mycarenet.agreement.session.impl.AgreementServiceImpl;
import be.ehealth.businessconnector.mycarenet.agreement.session.impl.AgreementServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class AgreementSessionServiceFactory extends AbstractSessionServiceFactory {
   private AgreementSessionServiceFactory() {
   }

   public static AgreementService getAgreementService() throws ConnectorException {
      return (AgreementService)getService(AgreementServiceImpl.class, new AgreementServiceImplementationFactory(), new String[0]);
   }
}
