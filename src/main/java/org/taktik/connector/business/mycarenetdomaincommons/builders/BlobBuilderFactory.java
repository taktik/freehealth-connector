package org.taktik.connector.business.mycarenetdomaincommons.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class BlobBuilderFactory {
   private static ConfigurableFactoryHelper<BlobBuilder> blobBuilderFactory = new ConfigurableFactoryHelper("mycarenet.blobbuilder.class", "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl");

   public static BlobBuilder getBlobBuilder(String projectName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap<>();
      configParameters.put("projectName", projectName);
      return blobBuilderFactory.getImplementation(configParameters);
   }

   public static BlobBuilder getBlobBuilder(String platformName, String projectName, String messageName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap<>();
      configParameters.put("platformName", platformName);
      configParameters.put("projectName", projectName);
      configParameters.put("messageName", messageName);
      return blobBuilderFactory.getImplementation(configParameters);
   }

}
