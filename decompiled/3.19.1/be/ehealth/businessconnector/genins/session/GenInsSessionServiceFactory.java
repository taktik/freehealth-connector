package be.ehealth.businessconnector.genins.session;

import be.ehealth.businessconnector.genins.session.impl.GenInsServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class GenInsSessionServiceFactory extends AbstractSessionServiceFactory {
   private GenInsSessionServiceFactory() {
   }

   public static GenInsService getGenInsService() throws ConnectorException {
      return (GenInsService)getService(GenInsServiceImpl.class, new GenInsServiceImplementationFactory(), new String[0]);
   }
}
