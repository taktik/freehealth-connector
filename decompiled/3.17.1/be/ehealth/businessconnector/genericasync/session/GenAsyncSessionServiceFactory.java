package be.ehealth.businessconnector.genericasync.session;

import be.ehealth.businessconnector.genericasync.session.impl.GenAsyncServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class GenAsyncSessionServiceFactory extends AbstractSessionServiceFactory {
   private static GenAsyncServiceImplementationFactory implementationClassFactory = new GenAsyncServiceImplementationFactory();

   public static GenAsyncService getGenAsyncService(String serviceName) throws ConnectorException {
      return (GenAsyncService)getService(GenAsyncServiceImpl.class, implementationClassFactory, new String[]{serviceName});
   }
}
