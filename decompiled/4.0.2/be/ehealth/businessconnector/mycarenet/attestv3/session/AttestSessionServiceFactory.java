package be.ehealth.businessconnector.mycarenet.attestv3.session;

import be.ehealth.businessconnector.mycarenet.attestv3.session.impl.AttestServiceImpl;
import be.ehealth.businessconnector.mycarenet.attestv3.session.impl.AttestServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class AttestSessionServiceFactory extends AbstractSessionServiceFactory {
   private AttestSessionServiceFactory() {
   }

   public static AttestService getAttestService() throws ConnectorException {
      return (AttestService)getService(AttestServiceImpl.class, new AttestServiceImplementationFactory(), new String[0]);
   }
}
