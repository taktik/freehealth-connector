package org.taktik.connector.business.registration.session;

import org.taktik.connector.business.registration.session.impl.RegistrationSessionImpl;
import org.taktik.connector.business.registration.session.impl.RegsitrationImplementationClassFactory;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class RegistrationSessionFactory extends AbstractSessionServiceFactory {
   public static RegistrationSession getRegistrationSession() throws ConnectorException {
      return (RegistrationSession)getService(RegistrationSessionImpl.class, new RegsitrationImplementationClassFactory(), new String[0]);
   }
}
