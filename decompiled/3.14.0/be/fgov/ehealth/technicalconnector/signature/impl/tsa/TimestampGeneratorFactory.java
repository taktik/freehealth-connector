package be.fgov.ehealth.technicalconnector.signature.impl.tsa;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.signature.impl.tsa.impl.TimeStampGeneratorImpl;
import java.util.Map;

public final class TimestampGeneratorFactory {
   private static final String PROP_TSGEN_CLASS = "be.fgov.ehealth.technicalconnector.signature.timestampgenerator";
   private static final String DEFAULT_TSGEN_CLASS = TimeStampGeneratorImpl.class.getName();
   private static ConfigurableFactoryHelper<TimestampGenerator> factoryHelper;

   private TimestampGeneratorFactory() {
      throw new UnsupportedOperationException();
   }

   public static TimestampGenerator getInstance(Map<String, Object> options) throws TechnicalConnectorException {
      return (TimestampGenerator)factoryHelper.getImplementation(options);
   }

   static {
      factoryHelper = new ConfigurableFactoryHelper("be.fgov.ehealth.technicalconnector.signature.timestampgenerator", DEFAULT_TSGEN_CLASS);
   }
}
