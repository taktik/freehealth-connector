package be.ehealth.businessconnector.addressbook.session;

import be.ehealth.businessconnector.addressbook.session.impl.AddressbookSessionServiceImpl;
import be.ehealth.businessconnector.addressbook.session.impl.AddressbookSessionServiceImplFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class AddressbookSessionServiceFactory extends AbstractSessionServiceFactory {
   private AddressbookSessionServiceFactory() {
      throw new UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used");
   }

   public static AddressbookSessionService getAddressbookSessionService() throws ConnectorException {
      return (AddressbookSessionService)getService(AddressbookSessionServiceImpl.class, new AddressbookSessionServiceImplFactory(), new String[0]);
   }
}
