package org.taktik.connector.technical.generic.session;

import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.generic.session.impl.GenericServiceImpl;
import org.taktik.connector.technical.generic.session.impl.GenericServiceImplementationFactory;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class GenericSessionServiceFactory extends AbstractSessionServiceFactory {
   private GenericSessionServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenericService getGenericService() throws ConnectorException {
      return (GenericService)getService(GenericServiceImpl.class, new GenericServiceImplementationFactory(), new String[0]);
   }
}
