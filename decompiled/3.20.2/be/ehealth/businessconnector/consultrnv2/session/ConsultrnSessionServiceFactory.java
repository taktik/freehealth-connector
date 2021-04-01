package be.ehealth.businessconnector.consultrnv2.session;

import be.ehealth.businessconnector.consultrnv2.session.impl.ConsultrnCBSSPersonServiceImpl;
import be.ehealth.businessconnector.consultrnv2.session.impl.ConsultrnImplementationClassFactory;
import be.ehealth.businessconnector.consultrnv2.session.impl.ConsultrnPersonServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class ConsultrnSessionServiceFactory extends AbstractSessionServiceFactory {
   private static final ConsultrnImplementationClassFactory FACTORY = new ConsultrnImplementationClassFactory();

   public static ConsultrnPersonService getConsultrnPersonService() throws ConnectorException {
      return (ConsultrnPersonService)getService(ConsultrnPersonServiceImpl.class, FACTORY, new String[0]);
   }

   public static ConsultrnCBSSPersonService getConsultrnCBSSPersonService() throws ConnectorException {
      return (ConsultrnCBSSPersonService)getService(ConsultrnCBSSPersonServiceImpl.class, FACTORY, new String[0]);
   }
}
