package be.ehealth.businessconnector.mycarenet.memberdata.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   private static final String PROP_ENCRYPTEDREQUESTBUILDER_CLASS = "memberdata.encryptedrequestobjectbuilder.class";
   private static final String PROP_NOTENCRYPTEDREQUESTBUILDER_CLASS = "memberdata.notencryptedrequestobjectbuilder.class";
   private static final String DEFAULT_ENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.EncryptedRequestObjectBuilderImpl";
   private static final String DEFAULT_NOTENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.NotEncryptedRequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryERequestBuilder = new ConfigurableFactoryHelper("memberdata.encryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.EncryptedRequestObjectBuilderImpl");
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryNERequestBuilder = new ConfigurableFactoryHelper("memberdata.notencryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.NotEncryptedRequestObjectBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryERequestBuilder.getImplementation();
   }

   public static RequestObjectBuilder getNotEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryNERequestBuilder.getImplementation();
   }
}
