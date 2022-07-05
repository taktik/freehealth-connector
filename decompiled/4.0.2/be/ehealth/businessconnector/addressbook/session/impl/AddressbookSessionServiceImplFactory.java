package be.ehealth.businessconnector.addressbook.session.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class AddressbookSessionServiceImplFactory extends ImplementationClassFactory {
   public AddressbookSessionServiceImplFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(AddressbookSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new AddressbookSessionServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported or the number of additionalParameters was not correct ");
      }
   }
}
