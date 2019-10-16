package org.taktik.connector.technical.service.ws;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.SchemaValidatorHandler;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import org.taktik.connector.technical.ws.domain.TokenType;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public final class ServiceFactory {
   static final String IDSUPPORT_XSD = "/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd";
   private static final String PROP_ENDPOINT_CODAGE = "endpoint.codage";
   private static final String PROP_ENDPOINT_SEALS_V1 = "endpoint.seals.v1";
   private static final String PROP_ENDPOINT_ETK = "endpoint.etk";
   private static final String PROP_ENDPOINT_TSAUTHORITY = "endpoint.ts.authority";
   private static final String PROP_ENDPOINT_TSCONSULT = "endpoint.ts.consult";
   private static final String PROP_ENDPOINT_KGSS = "endpoint.kgss";
   private static final String PROP_ENDPOINT_STS = "endpoint.sts";
   private static final String PROP_ENDPOINT_IDSUPPORT_V2 = "endpoint.idsupport.v2";
   private static Configuration config = ConfigFactory.getConfigValidator();

   /** @deprecated */
   @Deprecated
   public static GenericRequest getCodageService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.codage")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.codage");
      } else {
         return getX509SecuredRequest(certificate, privateKey, config.getProperty("endpoint.codage"));
      }
   }

   public static GenericRequest getSealsService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.seals.v1")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.seals.v1");
      } else {
         return getX509SecuredRequest(certificate, privateKey, config.getProperty("endpoint.seals.v1"));
      }
   }

   public static GenericRequest getETKService() throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.etk")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.etk");
      } else {
         return getUnSecuredRequest(config.getProperty("endpoint.etk"));
      }
   }

   public static GenericRequest getSTSService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.sts")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.sts");
      } else {
         return getX509SecuredRequest(certificate, privateKey, config.getProperty("endpoint.sts"));
      }
   }

   public static GenericRequest getKGSSService() throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.kgss")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.kgss");
      } else {
         return getUnSecuredRequest(config.getProperty("endpoint.kgss"));
      }
   }

   public static GenericRequest getKGSSServiceSecured(SAMLToken token) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.kgss")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.kgss");
      } else {
         return getSAMLSecuredRequest(token, config.getProperty("endpoint.kgss"));
      }
   }

   public static GenericRequest getTSAuthorityService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.ts.authority")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.ts.authority");
      } else {
         return getX509SecuredRequest(certificate, privateKey, config.getProperty("endpoint.ts.authority"));
      }
   }

   public static GenericRequest getTSConsultService(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.ts.consult")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.ts.consult");
      } else {
         return getX509SecuredRequest(certificate, privateKey, config.getProperty("endpoint.ts.consult"));
      }
   }

   public static GenericRequest getIdSupportV2Service(SAMLToken token) throws TechnicalConnectorException {
      if (!config.containsKey("endpoint.idsupport.v2")) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROPERTY_MISSING, "endpoint.idsupport.v2");
      } else {
         GenericRequest request = getSAMLSecuredRequest(token, config.getProperty("endpoint.idsupport.v2"));
         request.setSoapAction("urn:be:fgov:ehealth:idsupport:protocol:v2:verifyId");
         HandlerChain handlers = new HandlerChain();
         handlers.register(HandlerPosition.BEFORE, new SchemaValidatorHandler(3, "/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd"));
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
