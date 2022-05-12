package be.ehealth.technicalconnector.service.ws;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.SchemaValidatorHandler;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public final class ServiceFactory {
   static final String IDSUPPORT_XSD = "/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd";
   private static final String PROP_ENDPOINT_SEALS_V1 = "endpoint.seals.v1";
   private static final String PROP_ENDPOINT_ETK = "endpoint.etk";
   private static final String PROP_ENDPOINT_TSAUTHORITY = "endpoint.ts.authority";
   private static final String PROP_ENDPOINT_TSCONSULT = "endpoint.ts.consult";
   private static final String PROP_ENDPOINT_KGSS = "endpoint.kgss";
   private static final String PROP_ENDPOINT_STS = "endpoint.sts";
   private static final String PROP_ENDPOINT_IDSUPPORT_V2 = "endpoint.idsupport.v2";

   private ServiceFactory() {
   }

   public static GenericRequest getSealsService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.seals.v1")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.seals.v1"});
      } else {
         return getX509SecuredRequest(certificate, privateKey, ConfigFactory.getConfigValidator().getProperty("endpoint.seals.v1"));
      }
   }

   public static GenericRequest getETKService() throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.etk")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.etk"});
      } else {
         return getUnSecuredRequest(ConfigFactory.getConfigValidator().getProperty("endpoint.etk"));
      }
   }

   public static GenericRequest getSTSService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.sts")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.sts"});
      } else {
         return getX509SecuredRequest(certificate, privateKey, ConfigFactory.getConfigValidator().getProperty("endpoint.sts"));
      }
   }

   public static GenericRequest getKGSSService() throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.kgss")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.kgss"});
      } else {
         return getUnSecuredRequest(ConfigFactory.getConfigValidator().getProperty("endpoint.kgss"));
      }
   }

   public static GenericRequest getKGSSServiceSecured(SAMLToken token) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.kgss")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.kgss"});
      } else {
         return getSAMLSecuredRequest(token, ConfigFactory.getConfigValidator().getProperty("endpoint.kgss"));
      }
   }

   public static GenericRequest getTSAuthorityService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.ts.authority")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.ts.authority"});
      } else {
         return getX509SecuredRequest(certificate, privateKey, ConfigFactory.getConfigValidator().getProperty("endpoint.ts.authority"));
      }
   }

   public static GenericRequest getTSConsultService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.ts.consult")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.ts.consult"});
      } else {
         return getX509SecuredRequest(certificate, privateKey, ConfigFactory.getConfigValidator().getProperty("endpoint.ts.consult"));
      }
   }

   public static GenericRequest getIdSupportV2Service(SAMLToken token) throws TechnicalConnectorException {
      if (!ConfigFactory.getConfigValidator().containsKey("endpoint.idsupport.v2")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, new Object[]{"endpoint.idsupport.v2"});
      } else {
         GenericRequest request = getSAMLSecuredRequest(token, ConfigFactory.getConfigValidator().getProperty("endpoint.idsupport.v2"));
         request.setSoapAction("urn:be:fgov:ehealth:idsupport:protocol:v2:verifyId");
         HandlerChain handlers = new HandlerChain();
         handlers.register(HandlerPosition.BEFORE, new SchemaValidatorHandler(3, new String[]{"/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd"}));
         request.addHandlerChain(handlers);
         return request;
      }
   }

   private static GenericRequest getX509SecuredRequest(X509Certificate certificate, PrivateKey privateKey, String endpoint) throws TechnicalConnectorException {
      GenericRequest request = getUnSecuredRequest(endpoint);
      request.setCertificateSecured(certificate, privateKey);
      return request;
   }

   private static GenericRequest getSAMLSecuredRequest(SAMLToken token, String endpoint) throws TechnicalConnectorException {
      GenericRequest request = getUnSecuredRequest(endpoint);
      request.setCredential(token, TokenType.SAML);
      return request;
   }

   private static GenericRequest getUnSecuredRequest(String endpoint) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setEndpoint(endpoint);
      return request;
   }
}
