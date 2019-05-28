package org.taktik.connector.business.mycarenet.attestv2.session;

import org.taktik.connector.business.mycarenet.attestv2.session.impl.AttestServiceImpl;
import org.taktik.connector.business.mycarenet.attestv2.session.impl.AttestServiceImplementationFactory;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class AttestSessionServiceFactory extends AbstractSessionServiceFactory {
   private AttestSessionServiceFactory() {
   }

   public static AttestService getAttestService() throws ConnectorException {
      return (AttestService)getService(AttestServiceImpl.class, new AttestServiceImplementationFactory(), new String[0]);
   }
}
