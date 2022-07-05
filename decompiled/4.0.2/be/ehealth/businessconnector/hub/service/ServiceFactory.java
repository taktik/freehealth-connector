package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.business.intrahubcommons.security.HubDecryptionHandler;
import be.ehealth.business.intrahubcommons.security.RequestContextHandler;
import be.ehealth.businessconnector.hub.service.impl.IntraHubServiceImpl;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public final class ServiceFactory {
   public static final String INTRAHUB_PROTOCOL = "/ehealth-hubservices/XSD/hubservices_protocol-1_2.xsd";
   private static final String PROP_ENDPOINT_INTRAHUB = "endpoint.hub.intra";
   private static final String PROP_VALIDATION_INCOMING_INTRAHUB = "validation.incoming.intrahub.message";
   private static List<String> expectedProps = new ArrayList();
   private static Configuration config;

   private ServiceFactory() {
   }

   public static GenericRequest getIntraHubPort(SAMLToken token, String soapAction) throws MalformedURLException, TechnicalConnectorException, IntraHubBusinessConnectorException {
      return getIntraHubPortWithFolderEncryption(token, soapAction);
   }

   /** @deprecated */
   @Deprecated
   public static GenericRequest getIntraHubPortWithFolderEncryption(SAMLToken token, Crypto crypto, String soapAction) throws MalformedURLException, TechnicalConnectorException, IntraHubBusinessConnectorException {
      return getIntraHubPortWithFolderEncryption(token, soapAction);
   }

   public static GenericRequest getIntraHubPortWithFolderEncryption(SAMLToken token, String soapAction) throws MalformedURLException, TechnicalConnectorException, IntraHubBusinessConnectorException {
      GenericRequest request = new GenericRequest();
      request.setEndpoint(config.getProperty("endpoint.hub.intra"));
      request.setSoapAction(soapAction);
      request.setCredential(token, TokenType.SAML);
      HandlerChain chain = HandlerChainUtil.buildChainWithValidator("validation.incoming.intrahub.message", "/ehealth-hubservices/XSD/hubservices_protocol-1_2.xsd");
      if (SessionUtil.getEncryptionCrypto() != null) {
         chain.register(HandlerPosition.BEFORE, new RequestContextHandler("DECRYPTED"));
         chain.register(HandlerPosition.BEFORE, new HubDecryptionHandler(SessionUtil.getEncryptionCrypto()));
      }

      request.addDefaulHandlerChain();
      request.addHandlerChain(chain);
      return request;
   }

   public static IntraHubService getIntraHubService() {
      return new IntraHubServiceImpl();
   }

   static {
      expectedProps.add("endpoint.hub.intra");
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
