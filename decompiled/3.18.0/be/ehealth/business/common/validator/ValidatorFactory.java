package be.ehealth.business.common.validator;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.impl.EhealthReplyValidatorImpl;

public final class ValidatorFactory {
   private ValidatorFactory() {
      throw new UnsupportedOperationException();
   }

   public static EhealthReplyValidator getEhealthReplyValidator(String customClassProperty) throws TechnicalConnectorException {
      return (EhealthReplyValidator)getValidator(customClassProperty, EhealthReplyValidatorImpl.class);
   }

   public static <T> T getValidator(String customClassProperty, Class<T> defaultImplClass) throws TechnicalConnectorException {
      return (new ConfigurableFactoryHelper(customClassProperty, defaultImplClass.getName())).getImplementation();
   }
}
