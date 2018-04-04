package org.taktik.connector.business.genericasync.session;

import org.taktik.connector.business.genericasync.session.impl.GenAsyncServiceImpl;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class GenAsyncSessionServiceFactory extends AbstractSessionServiceFactory {
   private static GenAsyncServiceImplementationFactory implementationClassFactory = new GenAsyncServiceImplementationFactory();

   public static GenAsyncService getGenAsyncService(String serviceName) throws ConnectorException {
      return (GenAsyncService)getService(GenAsyncServiceImpl.class, implementationClassFactory, new String[]{serviceName});
   }
}
