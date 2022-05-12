package be.ehealth.businessconnector.genins.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.service.impl.GenInsServiceImpl;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.MalformedURLException;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_GENINS_V1 = "endpoint.genins";
   private static final String PROP_VALIDATION_INCOMING_GENINS = "validation.incoming.genins.message";
   private static final String GENINS_PROT = "/ehealth-mycarenet-genins/XSD/ehealth-genins-protocol-1_1.xsd";
   private static GenInsService service;
   private static Configuration config = ConfigFactory.getConfigValidator();
   public static final String[] GENINS_XSD = new String[]{"/ehealth-mycarenet-genins/XSD/ehealth-genins-protocol-1_1.xsd"};

   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenInsService getGeninsService() {
      if (service == null) {
         service = new GenInsServiceImpl();
      }

      return service;
   }

   public static GenericRequest getGeninsPort(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, GenInsBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.genins", "$uddi{uddi:ehealth-fgov-be:business:genericinsurability:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.genins.message", GENINS_XSD));
      return genReq;
   }
}
