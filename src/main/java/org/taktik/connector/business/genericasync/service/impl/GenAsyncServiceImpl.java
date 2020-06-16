package org.taktik.connector.business.genericasync.service.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import org.taktik.connector.business.genericasync.handlers.IncomingSecurityHandler;
import org.taktik.connector.business.genericasync.handlers.SAMLHolderOfKeyHandler;
import org.taktik.connector.business.genericasync.service.GenAsyncService;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.SOAPHeaderLoggerHandler;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import org.taktik.connector.technical.ws.feature.XOPFeature;

import javax.xml.soap.SOAPException;
import java.util.concurrent.TimeUnit;

public class GenAsyncServiceImpl implements GenAsyncService {
   private static final Logger LOG = LoggerFactory.getLogger(GenAsyncServiceImpl.class);
   private static Configuration config = ConfigFactory.getConfigValidator();
   private SessionValidator sessionValidator;
   private String serviceName;
   private int threshold;

   public GenAsyncServiceImpl(String serviceName) {
      this.serviceName = serviceName;
      this.setThreshold();
   }

   public PostResponse postRequest(SAMLToken token, Post request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException {
      try {
         return this.invoke(token, request, header, PostResponse.class);
      } catch (TechnicalConnectorException e) {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.TARGET_SERVICE_ERROR, e, e.getMessage());
      }
   }

   public GetResponse getRequest(SAMLToken token, Get request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return this.invoke(token, request, header, GetResponse.class);
   }

   public ConfirmResponse confirmRequest(SAMLToken token, Confirm request, WsAddressingHeader header) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      return this.invoke(token, request, header, ConfirmResponse.class);
   }

   protected <T> T invoke(SAMLToken token, Object request, WsAddressingHeader header, Class<T> clazz) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      try {
         GenericRequest genReq = build(token, this.serviceName);
         genReq.setPayload(request, new XOPFeature(this.threshold));
         genReq.setWSAddressing(header);
         return ServiceFactory.getGenericWsSender().send(genReq).asObject(clazz);
      } catch (SOAPException e) {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.TARGET_SERVICE_ERROR, e, e.getMessage());
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

   private void setThreshold() {
      this.threshold = ConfigFactory.getConfigValidator().getIntegerProperty("threshold.genericasync." + this.serviceName + ".v1", 81920);
   }

   protected static GenericRequest build(SAMLToken token, String serviceName) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setEndpoint(getProperty("endpoint.genericasync.", serviceName, true));
      HandlerChain chain = HandlerChainUtil.buildChainWithValidator("validation.incoming.message.genasync.", "/mycarenet-genasync/XSD/GenericAsync-V4.xsd");
      chain.register(HandlerPosition.SECURITY, new SAMLHolderOfKeyHandler(token, getDuration("security.outgoing.message.genasync.timestamp.", serviceName, 30L)));
      chain.register(HandlerPosition.SECURITY, new IncomingSecurityHandler(getDuration("security.incoming.message.genasync.timestamp.created.ttl.", serviceName, 30L), getDuration("security.incoming.message.genasync.timestamp.expires.ttl.", serviceName, 30L)));
      chain.register(HandlerPosition.SECURITY, new SOAPHeaderLoggerHandler());
      request.addHandlerChain(chain);
      request.addDefaulHandlerChain();
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
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_CONFIG, key);
      } else {
         return result;
      }
   }

   private static Duration getDuration(String startKey, String serviceName, long defaultDurationInSeconds) throws TechnicalConnectorException {
      String key = startKey + serviceName + ".v1";
      return config.getDurationProperty(key, defaultDurationInSeconds, TimeUnit.SECONDS);
   }
}
