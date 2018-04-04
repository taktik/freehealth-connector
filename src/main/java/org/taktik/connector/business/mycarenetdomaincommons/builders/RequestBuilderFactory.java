package org.taktik.connector.business.mycarenetdomaincommons.builders;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class RequestBuilderFactory {
   private static final String USE_DEFAULT_PROPERTIES = ".usedefaultproperties";
   private static final String MYCARENET = "mycarenet.";
   private static final String PROP_BLOBBUILDER_CLASS = "mycarenet.blobbuilder.class";
   private static final String DEFAULT_BLOBBUILDER_CLASS = "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl";
   private static ConfigurableFactoryHelper<BlobBuilder> blobBuilderFactory = new ConfigurableFactoryHelper("mycarenet.blobbuilder.class", "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl");
   private static final String PROP_COMMONBUILDER_CLASS = "mycarenet.commonbuilder.class";
   private static final String DEFAULT_COMMONBUILDER_CLASS = "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.GenericCommonBuilderImpl";
   private static ConfigurableFactoryHelper<CommonBuilder> commonBuilderFactory = new ConfigurableFactoryHelper("mycarenet.commonbuilder.class", "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.GenericCommonBuilderImpl");

   /** @deprecated */
   @Deprecated
   public static BlobBuilder getBlobBuilder(String projectName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("projectName", projectName);
      return (BlobBuilder)blobBuilderFactory.getImplementation(configParameters);
   }

   public static CommonBuilder getCommonBuilder(String projectName) throws TechnicalConnectorException {
      String verifiedProjectName = projectName;
      if (projectName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"project name"});
      } else {
         String useDefaultProperties = "mycarenet." + projectName + ".usedefaultproperties";
         ConfigValidator props = ConfigFactory.getConfigValidator();
         if (props.getBooleanProperty(useDefaultProperties, true).booleanValue()) {
            verifiedProjectName = "default";
         }

         Map<String, Object> configParameters = new HashMap();
         configParameters.put("projectName", verifiedProjectName);
         return (CommonBuilder)commonBuilderFactory.getImplementation(configParameters);
      }
   }
}
