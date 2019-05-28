package be.ehealth.business.mycarenetdomaincommons.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class BlobBuilderFactory {
   private static final String PROP_BLOBBUILDER_CLASS = "mycarenet.blobbuilder.class";
   private static final String DEFAULT_BLOBBUILDER_CLASS = "be.ehealth.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl";
   private static ConfigurableFactoryHelper<BlobBuilder> blobBuilderFactory = new ConfigurableFactoryHelper("mycarenet.blobbuilder.class", "be.ehealth.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl");

   public static BlobBuilder getBlobBuilder(String projectName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("projectName", projectName);
      return (BlobBuilder)blobBuilderFactory.getImplementation(configParameters);
   }

   public static BlobBuilder getBlobBuilder(String platformName, String projectName, String messageName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("platformName", platformName);
      configParameters.put("projectName", projectName);
      configParameters.put("messageName", messageName);
      return (BlobBuilder)blobBuilderFactory.getImplementation(configParameters);
   }
}
