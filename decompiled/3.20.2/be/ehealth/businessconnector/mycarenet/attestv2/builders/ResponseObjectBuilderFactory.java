package be.ehealth.businessconnector.mycarenet.attestv2.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "attestv2.responseobjectbuilder.class";
   public static final String DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.attestv2.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("attestv2.responseobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.attestv2.builders.impl.ResponseObjectBuilderImpl");

   private ResponseObjectBuilderFactory() {
   }

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }
}
