package be.ehealth.technicalconnector.generic.session;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.generic.session.impl.GenericServiceImpl;
import be.ehealth.technicalconnector.generic.session.impl.GenericServiceImplementationFactory;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class GenericSessionServiceFactory extends AbstractSessionServiceFactory {
   private GenericSessionServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenericService getGenericService() throws ConnectorException {
      return (GenericService)getService(GenericServiceImpl.class, new GenericServiceImplementationFactory(), new String[0]);
   }
}
