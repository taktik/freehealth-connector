package be.ehealth.businessconnector.ehbox.v3.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.MalformedURLException;

public final class ServiceFactory {
   private static ConfigValidator config = ConfigFactory.getConfigValidator();
   public static final String PROP_ENDPOINT_CONSULTATION_V3 = "endpoint.ehbox.consultation.v3";
   public static final String PROP_ENDPOINT_PUBLICATION_V3 = "endpoint.ehbox.publication.v3";
   public static final String PROP_ENDPOINT_EH2EBOX_PUBLICATION_V3 = "endpoint.eh2ebox.publication.v3";
   public static final String PROP_VALIDATION_INCOMING_EHBOX_V3 = "validation.incoming.ehbox.v3.message";
   public static final String EHBOX_CONS_PROT = "/ehealth-ehbox/XSD/ehealth-ehBox-consultation-schema-protocol-3_0.xsd";
   public static final String EHBOX_PUB_PROT = "/ehealth-ehbox/XSD/ehealth-ehBox-publication-schema-protocol-3_0.xsd";

   private ServiceFactory() {
   }

   public static GenericRequest getConsultationService(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, EhboxBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.ehbox.consultation.v3", "$uddi{uddi:ehealth-fgov-be:business:ehboxconsultation:v3}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.ehbox.v3.message", "/ehealth-ehbox/XSD/ehealth-ehBox-consultation-schema-protocol-3_0.xsd"));
      return genReq;
   }

   public static GenericRequest getPublicationService(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, EhboxBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.ehbox.publication.v3", "$uddi{uddi:ehealth-fgov-be:business:ehboxpublication:v3}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.ehbox.v3.message", "/ehealth-ehbox/XSD/ehealth-ehBox-publication-schema-protocol-3_0.xsd"));
      return genReq;
   }

   public static GenericRequest getEh2EboxPublicationService(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, EhboxBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.eh2ebox.publication.v3", "$uddi{uddi:ehealth-fgov-be:business:eh2eboxpublication:v3}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.ehbox.v3.message", "/ehealth-ehbox/XSD/ehealth-ehBox-publication-schema-protocol-3_0.xsd"));
      return genReq;
   }
}
