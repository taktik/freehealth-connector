package be.ehealth.business.mycarenetdomaincommons.builders;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class RequestBuilderFactory {
   private static final String USE_DEFAULT_PROPERTIES = ".usedefaultproperties";
   private static final String MYCARENET = "mycarenet.";
   private static final String PROP_BLOBBUILDER_CLASS = "mycarenet.blobbuilder.class";
   private static final String DEFAULT_BLOBBUILDER_CLASS = "be.ehealth.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl";
   private static ConfigurableFactoryHelper<BlobBuilder> blobBuilderFactory = new ConfigurableFactoryHelper("mycarenet.blobbuilder.class", "be.ehealth.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl");
   private static final String PROP_COMMONBUILDER_CLASS = "mycarenet.commonbuilder.class";
   private static final String DEFAULT_COMMONBUILDER_CLASS = "be.ehealth.business.mycarenetdomaincommons.builders.impl.GenericCommonBuilderImpl";
   private static ConfigurableFactoryHelper<CommonBuilder> commonBuilderFactory = new ConfigurableFactoryHelper("mycarenet.commonbuilder.class", "be.ehealth.business.mycarenetdomaincommons.builders.impl.GenericCommonBuilderImpl");

   private RequestBuilderFactory() {
   }

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
         if (props.getBooleanProperty(useDefaultProperties, true)) {
            verifiedProjectName = "default";
         }

         Map<String, Object> configParameters = new HashMap();
         configParameters.put("projectName", verifiedProjectName);
         return (CommonBuilder)commonBuilderFactory.getImplementation(configParameters);
      }
   }
}
