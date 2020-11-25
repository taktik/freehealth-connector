package be.ehealth.businessconnector.mycarenet.memberdatav2.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   private static final String PROP_RESPONSEBUILDER_CLASS = "memberdata.responseobjectbuilder.class";
   private static final String DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("memberdata.responseobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.ResponseObjectBuilderImpl");

   private ResponseObjectBuilderFactory() {
   }

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }
}
