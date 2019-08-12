package be.ehealth.businessconnector.mycarenet.attest.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "attest.responseobjectbuilder.class";
   public static final String DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.attest.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("attest.responseobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.attest.builders.impl.ResponseObjectBuilderImpl");

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }
}
