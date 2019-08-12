package be.ehealth.businessconnector.ehbox.v3.validator;

import be.ehealth.businessconnector.ehbox.v3.validator.impl.EhboxReplyValidatorImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class ValidatorFactory {
   private static final String PROP_EHBOX_VALIDATOR = "be.ehealth.businessconnector.ehbox.v3.validator";
   private static ConfigurableFactoryHelper<EhboxReplyValidator> factory = new ConfigurableFactoryHelper("be.ehealth.businessconnector.ehbox.v3.validator", EhboxReplyValidatorImpl.class.getName());

   private ValidatorFactory() {
      throw new UnsupportedOperationException();
   }

   public static EhboxReplyValidator getValidator() throws TechnicalConnectorException {
      return (EhboxReplyValidator)factory.getImplementation();
   }
}
