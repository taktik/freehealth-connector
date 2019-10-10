package be.ehealth.businessconnector.genins.builders;

import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorExceptionValues;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RequestObjectBuilderFactory {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderFactory.class);
   private static final String PROP_REQUESTOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.genins.builders.RequestObjectBuilder.class";
   private static final String DEFAULT_REQUESTOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.genins.builders.impl.RequestObjectBuilderImpl";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static RequestObjectBuilder cachedBuilder;

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException, GenInsBusinessConnectorException, InstantiationException {
      if (cachedBuilder != null) {
         return cachedBuilder;
      } else {
         String headerClassName = config.getProperty("be.ehealth.businessconnector.genins.builders.RequestObjectBuilder.class", "be.ehealth.businessconnector.genins.builders.impl.RequestObjectBuilderImpl");

         String msg;
         try {
            Class<?> provider = Class.forName(headerClassName);
            if (provider != null) {
               Object providerObject = provider.newInstance();
               if (providerObject instanceof RequestObjectBuilder) {
                  cachedBuilder = (RequestObjectBuilder)providerObject;
                  return cachedBuilder;
               } else {
                  msg = "Class with name [" + headerClassName + "] is not an instance of RevocationStatusChecker, but an instance of [" + providerObject.getClass() + "]";
                  LOG.warn(msg);
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HEADER_INSTANCIATION, new Object[]{msg});
               }
            } else {
               String msg = "Specified class [" + headerClassName + "] can't be found";
               LOG.warn(msg);
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HEADER_INSTANCIATION, new Object[]{msg});
            }
         } catch (ClassNotFoundException var4) {
            msg = "Class could not be found : " + headerClassName;
            LOG.error(msg);
            throw new GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues.OBJECTBUILDER_INSTANCIATION_ERROR, var4, msg);
         } catch (IllegalAccessException var5) {
            msg = "Illegal acces exception while instanciation of " + headerClassName;
            LOG.error(msg);
            throw new GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues.OBJECTBUILDER_INSTANCIATION_ERROR, var5, msg);
         } catch (SecurityException var6) {
            msg = "Security exception while instanciation of " + headerClassName;
            LOG.error(msg);
            throw new GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues.OBJECTBUILDER_INSTANCIATION_ERROR, var6, msg);
         } catch (IllegalArgumentException var7) {
            msg = "Illegal argument exception while instanciation of " + headerClassName;
            LOG.error(msg);
            throw new GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues.OBJECTBUILDER_INSTANCIATION_ERROR, var7, msg);
         }
      }
   }
}
