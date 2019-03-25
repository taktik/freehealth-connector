package be.ehealth.businessconnector.consultrn.session;

import be.ehealth.businessconnector.consultrn.session.impl.ConsultrnImplementationClassFactory;
import be.ehealth.businessconnector.consultrn.session.impl.ConsultrnSessionServiceImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class ConsultrnSessionServiceFactory extends AbstractSessionServiceFactory {
   public static ConsultrnService getConsultrnSession() throws ConnectorException {
      return (ConsultrnService)getService(ConsultrnSessionServiceImpl.class, new ConsultrnImplementationClassFactory(), new String[0]);
   }
}
