package be.ehealth.businessconnector.chapterIV.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.MalformedURLException;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_CHIV_CONSULTATION_V1 = "endpoint.ch4.consultation.v1";
   private static final String PROP_ENDPOINT_CHIV_ADMISSION_V1 = "endpoint.ch4.admission.v1";
   private static final String PROP_VALIDATION_INCOMING_CONS_CHIV = "validation.incoming.chapterIV.consultation.message";
   private static final String PROP_VALIDATION_INCOMING_ADM_CHIV = "validation.incoming.chapterIV.admission.message";
   public static final String CH4_PROT = "/ehealth-chapteriv/XSD/chap4services_protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getConsultationService(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, ChapterIVBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.ch4.consultation.v1", "$uddi{uddi:ehealth-fgov-be:business:chapterivchapterivagreementconsultation:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.chapterIV.consultation.message", "/ehealth-chapteriv/XSD/chap4services_protocol-1_0.xsd"));
      return genReq;
   }

   public static GenericRequest getAdmissionService(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, ChapterIVBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.ch4.admission.v1", "$uddi{uddi:ehealth-fgov-be:business:chapterivchapterivagreementadmission:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.chapterIV.admission.message", "/ehealth-chapteriv/XSD/chap4services_protocol-1_0.xsd"));
      return genReq;
   }
}
