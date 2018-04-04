package org.taktik.connector.business.mycarenetdomaincommons.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class BlobBuilderFactory {
   private static final String PROP_BLOBBUILDER_CLASS = "mycarenet.blobbuilder.class";
   private static final String DEFAULT_BLOBBUILDER_CLASS = "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl";
   private static ConfigurableFactoryHelper<BlobBuilder> blobBuilderFactory = new ConfigurableFactoryHelper("mycarenet.blobbuilder.class", "org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BlobBuilderImpl");

   public static BlobBuilder getBlobBuilder(String projectName) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("projectName", projectName);
      return (BlobBuilder)blobBuilderFactory.getImplementation(configParameters);
   }
}
