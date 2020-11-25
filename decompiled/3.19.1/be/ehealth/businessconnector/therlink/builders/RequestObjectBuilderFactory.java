package be.ehealth.businessconnector.therlink.builders;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class RequestObjectBuilderFactory {
   private static final String PROP_REQUESTOBJECTBUILDER_CLASS = "therlink.requestobjectbuilder.class";
   private static final String PROP_COMMONOBJECTBUILDER_CLASS = "therlink.commonobjectbuilder.class";
   private static final String PROP_PROOFBUILDER_CLASS = "therlink.proofbuilder.class";
   private static final String DEFAULT_REQUESTOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.therlink.builders.impl.GenericRequestObjectBuilder";
   private static final String DEFAULT_PROOFBUILDER_CLASS = "be.ehealth.businessconnector.therlink.builders.impl.ProofBuilderImpl";
   private static final String DEFAULT_COMMONOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.therlink.builders.impl.CommonObjectBuilderImpl";
   private static ConfigurableFactoryHelper<CommonObjectBuilder> helperFactoryCommonBuilder = new ConfigurableFactoryHelper("therlink.commonobjectbuilder.class", "be.ehealth.businessconnector.therlink.builders.impl.CommonObjectBuilderImpl");
   private static ConfigurableFactoryHelper<ProofBuilder> helperFactoryProofBuilder = new ConfigurableFactoryHelper("therlink.proofbuilder.class", "be.ehealth.businessconnector.therlink.builders.impl.ProofBuilderImpl");
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryRequestObjectBuilder = new ConfigurableFactoryHelper("therlink.requestobjectbuilder.class", "be.ehealth.businessconnector.therlink.builders.impl.GenericRequestObjectBuilder");
   private static boolean securityProviderAdded;

   private RequestObjectBuilderFactory() {
   }

   public static CommonObjectBuilder getCommonBuilder() throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
      return (CommonObjectBuilder)helperFactoryCommonBuilder.getImplementation();
   }

   public static ProofBuilder getProofBuilder() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      if (!securityProviderAdded) {
         securityProviderAdded = true;
         Security.addProvider(new BouncyCastleProvider());
      }

      return (ProofBuilder)helperFactoryProofBuilder.getImplementation();
   }

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return (RequestObjectBuilder)helperFactoryRequestObjectBuilder.getImplementation();
   }
}
