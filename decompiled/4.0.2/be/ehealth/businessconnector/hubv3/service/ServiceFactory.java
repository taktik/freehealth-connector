package be.ehealth.businessconnector.hubv3.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.business.intrahubcommons.security.HubDecryptionHandler;
import be.ehealth.businessconnector.hubv3.service.impl.HubTokenServiceImpl;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;

public final class ServiceFactory {
   public static final String INTRAHUB_PROTOCOL = "/ehealth-hubservices/XSD/hubservices_protocol-3_5.xsd";
   public static final String PROP_HUBID = "hubv3.id";
   public static final String PROP_HUBAPPID = "hubv3.application";
   public static final String PROP_ENDPOINT_INTRAHUB = "endpoint.hubv3.intra";
   private static final String PROP_VALIDATION_INCOMING_INTRAHUB = "validation.incoming.intrahubv3.message";
   private static final List<String> expectedProps = new ArrayList();
   private static final Configuration config;

   private ServiceFactory() {
   }

   public static GenericRequest getIntraHubPort(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      Validate.notNull(soapAction, "Required parameter SOAPAction is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.hubv3.intra")).setSoapAction(soapAction).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(addHubServiceHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.intrahubv3.message", "/ehealth-hubservices/XSD/hubservices_protocol-3_5.xsd")));
   }

   public static HubTokenService getIntraHubService() {
      return new HubTokenServiceImpl();
   }

   private static HandlerChain addHubServiceHandlerChain(HandlerChain chain) throws TechnicalConnectorException {
      if (SessionUtil.getEncryptionCrypto() != null) {
         chain.register(HandlerPosition.BEFORE, new HubDecryptionHandler(SessionUtil.getEncryptionCrypto()));
      }

      return chain;
   }

   static {
      expectedProps.add("endpoint.hubv3.intra");
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
