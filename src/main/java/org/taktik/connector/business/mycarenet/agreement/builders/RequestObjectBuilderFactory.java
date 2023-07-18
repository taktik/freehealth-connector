package org.taktik.connector.business.mycarenet.agreement.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   private static final String PROP_ENCRYPTEDREQUESTBUILDER_CLASS = "agreement.encryptedrequestobjectbuilder.class";
   private static final String DEFAULT_ENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.agreement.builders.impl.EncryptedRequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryERequestBuilder = new ConfigurableFactoryHelper("agreement.encryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.agreement.builders.impl.EncryptedRequestObjectBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryERequestBuilder.getImplementation();
   }
}
