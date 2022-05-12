package be.ehealth.technicalconnector.session.renew;

import be.ehealth.technicalconnector.exception.InstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public class RenewStrategyFactory {
   private static final String PROP_STSSERVICE_CLASS = "be.ehealth.technicalconnector.session.renew.RenewStrategyFactory";
   private static final String DEFAULT_RENEWSTRATEGY_CLASS = SlidingWindowRenewStrategy.class.getName();

   public RenewStrategyFactory() {
   }

   public static RenewStrategy get() {
      return RenewStrategyFactory.Singleton.INSTANCE.getStrategy();
   }

   private static enum Singleton {
      INSTANCE;

      private RenewStrategy strategy;

      private Singleton() {
         try {
            ConfigurableFactoryHelper<RenewStrategy> factoryHelper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.session.renew.RenewStrategyFactory", RenewStrategyFactory.DEFAULT_RENEWSTRATEGY_CLASS);
            this.strategy = (RenewStrategy)factoryHelper.getImplementation();
         } catch (TechnicalConnectorException var4) {
            throw new InstantiationException(var4);
         }
      }

      public RenewStrategy getStrategy() {
         return this.strategy;
      }
   }
}
