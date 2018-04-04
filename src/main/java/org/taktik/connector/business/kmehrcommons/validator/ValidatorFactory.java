package org.taktik.connector.business.kmehrcommons.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class ValidatorFactory {
   public static final String PROP_KMEHRVALIDATOR_CLASS = "kmehrvalidator.class";
   public static final String DEFAULT_KMEHRVALIDATOR_CLASS = "org.taktik.connector.business.kmehrcommons.validator.impl.KmehrValidatorImpl";
   private static ConfigurableFactoryHelper<KmehrValidator> helperFactoryKmehrValidator = new ConfigurableFactoryHelper("kmehrvalidator.class", "org.taktik.connector.business.kmehrcommons.validator.impl.KmehrValidatorImpl");

   private ValidatorFactory() {
      throw new UnsupportedOperationException();
   }

   public static KmehrValidator getKmehrValidator() throws TechnicalConnectorException {
      return (KmehrValidator)helperFactoryKmehrValidator.getImplementation();
   }
}
