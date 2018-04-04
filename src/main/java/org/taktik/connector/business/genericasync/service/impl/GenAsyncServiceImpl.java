package org.taktik.connector.business.genericasync.service.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import org.taktik.connector.business.genericasync.handlers.IncomingSecurityHandler;
import org.taktik.connector.business.genericasync.handlers.SAMLHolderOfKeyHandler;
import org.taktik.connector.business.genericasync.service.GenAsyncService;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.SOAPHeaderLoggerHandler;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import org.taktik.connector.technical.utils.ConfigurableImplementationHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import org.taktik.connector.technical.ws.feature.XOPFeature;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenAsyncServiceImpl implements GenAsyncService, ConfigurationModuleBootstrap.ModuleBootstrapHook, ConfigurableImplementation {
   public static final String SERVICE_NAME = "serviceName";
   public static final String SESSION_VALIDATOR = "sessionValidator";
   protected static final String GENASYNC_XSD = "/mycarenet-genasync/XSD/mycarenet-genasync-v1.xsd";
   private static final String PROP_ENDPOINT_GENASYNC_FIRST_PART = "endpoint.genericasync.";
   private static final String PROP_VALIDATION_INCOMING_GENASYNC = "validation.incoming.message.genasync.";
   private static final String PROP_SECURITY_INCOMING_GENASYNC_CREATE_TTL = "security.incoming.message.genasync.timestamp.created.ttl.";
   private static final String PROP_SECURITY_INCOMING_GENASYNC_EXPIRES_TTL = "security.incoming.message.genasync.timestamp.expires.ttl.";
   private static final String PROP_SECURITY_OUTGOING_GENASYNC_TS = "security.outgoing.message.genasync.timestamp.";
   private static final String PROP_THRESHOLD_GENASYNC_FIRST_PART = "threshold.genericasync.";
   private static final String END_PART_V1 = ".v1";
   private static final int DEFAULT_THRESHOLD = 81920;
   private static final Logger LOG = LoggerFactory.getLogger(GenAsyncServiceImpl.class);
   private static Configuration config = ConfigFactory.getConfigValidator();
   private SessionValidator sessionValidator;
   private String serviceName;
   private int threshold;

   public GenAsyncServiceImpl() {
   }

   /** @deprecated */
   @Deprecated
   public GenAsyncServiceImpl(SessionValidator sessionValidator, String serviceName) {
      this();
      this.sessionValidator = sessionValidator;
      this.serviceName = serviceName;
      this.setThreshold();
   }

   public PostResponse postRequest(SAMLToken token, Post request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException {
      try {
         return (PostResponse)this.invoke(token, request, header, PostResponse.class);
      } catch (TechnicalConnectorException var5) {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.TARGET_SERVICE_ERROR, var5, new Object[]{var5.getMessage()});
      }
   }

   public GetResponse getRequest(SAMLToken token, Get request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return (GetResponse)this.invoke(token, request, header, GetResponse.class);
   }

   public ConfirmResponse confirmRequest(SAMLToken token, Confirm request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return (ConfirmResponse)this.invoke(token, request, header, ConfirmResponse.class);
   }

   protected <T> T invoke(SAMLToken token, Object request, WsAddressingHeader header, Class<T> clazz) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest genReq = build(token, this.serviceName);
         genReq.setPayload(request, new XOPFeature(this.threshold));
         genReq.setWSAddressing(header);
         return ServiceFactory.getGenericWsSender().send(genReq).asObject(clazz);
      } catch (SOAPException var6) {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.TARGET_SERVICE_ERROR, var6, new Object[]{var6.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(Confirm.class);
      JaxbContextFactory.initJaxbContext(ConfirmResponse.class);
      JaxbContextFactory.initJaxbContext(Get.class);
      JaxbContextFactory.initJaxbContext(GetResponse.class);
      JaxbContextFactory.initJaxbContext(Post.class);
      JaxbContextFactory.initJaxbContext(PostResponse.class);
      JaxbContextFactory.initJaxbContext(MsgResponse.class);
      LOG.debug("bootstrapped GenAsyncServiceImpl");
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.serviceName = (String)ConfigurableImplementationHelper.get("serviceName", parameterMap, String.class, true);
      this.sessionValidator = (SessionValidator)ConfigurableImplementationHelper.get("sessionValidator", parameterMap, SessionValidator.class, true);
      this.setThreshold();
   }

   private void setThreshold() {
      this.threshold = ConfigFactory.getConfigValidator().getIntegerProperty("threshold.genericasync." + this.serviceName + ".v1", 81920).intValue();
   }

   protected static GenericRequest build(SAMLToken token, String serviceName) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setEndpoint(getProperty("endpoint.genericasync.", serviceName, true));
      HandlerChain chain = HandlerChainUtil.buildChainWithValidator("validation.incoming.message.genasync.", "/mycarenet-genasync/XSD/mycarenet-genasync-v1.xsd");
      chain.register(HandlerPosition.SECURITY, new SAMLHolderOfKeyHandler(token, getDuration("security.outgoing.message.genasync.timestamp.", serviceName, 30L)));
      chain.register(HandlerPosition.SECURITY, new IncomingSecurityHandler(getDuration("security.incoming.message.genasync.timestamp.created.ttl.", serviceName, 30L), getDuration("security.incoming.message.genasync.timestamp.expires.ttl.", serviceName, 30L)));
      chain.register(HandlerPosition.SECURITY, new SOAPHeaderLoggerHandler());
      request.setHandlerChain(chain);
      request.setDefaultHandlerChain();
      return request;
   }

   private static String getProperty(String startKey, String serviceName, boolean required, String... defaultValue) throws TechnicalConnectorException {
      String key = startKey + serviceName + ".v1";
      String result;
      if (defaultValue.length != 0 && defaultValue[0] != null) {
         result = config.getProperty(key, defaultValue[0]);
      } else {
         result = config.getProperty(key);
      }

      if (required && StringUtils.isEmpty(result)) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_CONFIG, new Object[]{key});
      } else {
         return result;
      }
   }

   private static Duration getDuration(String startKey, String serviceName, long defaultDurationInSeconds) throws TechnicalConnectorException {
      String key = startKey + serviceName + ".v1";
      return config.getDurationProperty(key, defaultDurationInSeconds, TimeUnit.SECONDS);
   }
}
