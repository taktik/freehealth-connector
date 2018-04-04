package be.ehealth.business.mycarenetcommons.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class ResponseBuilderFactory {
   private static final String PROP_RESPONSEBUILDER_CLASS = "mycarenet.mcn.responseobjectbuilder.class";
   private static final String DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.business.mycarenetcommons.builders.impl.ResponseBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseBuilder> responseBuilderFactory = new ConfigurableFactoryHelper("mycarenet.mcn.responseobjectbuilder.class", "be.ehealth.business.mycarenetcommons.builders.impl.ResponseBuilderImpl");

   /** @deprecated */
   @Deprecated
   public static ResponseBuilder getResponseBuilder() throws TechnicalConnectorException {
      return getResponseBuilder("default");
   }

   public static ResponseBuilder getResponseBuilder(String projectName) throws TechnicalConnectorException {
      Map<String, Object> configProperties = new HashMap();
      configProperties.put("projectname", projectName);
      return (ResponseBuilder)responseBuilderFactory.getImplementation(configProperties);
   }
}
