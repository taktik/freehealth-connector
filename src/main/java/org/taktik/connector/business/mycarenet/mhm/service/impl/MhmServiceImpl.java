package org.taktik.connector.business.mycarenet.mhm.service.impl;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest;
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.mycarenet.mhm.service.MhmService;
import org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;

import javax.xml.soap.SOAPException;

public class MhmServiceImpl implements MhmService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(MhmServiceImpl.class);

   public MhmServiceImpl(EhealthReplyValidator replyValidator) { }

   public MhmServiceImpl() {
      LOG.debug("creating AttestServiceImpl for bootstrapping purposes");
   }

   public final SendSubscriptionResponse sendSubscription(SAMLToken token, SendRequestType request) throws TechnicalConnectorException {
      try {
         GenericRequest service = ServiceFactory.getSubscriptionPort(token);
         service.setPayload(request);
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         return xmlResponse.asObject(SendSubscriptionResponse.class);
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.getMessage());
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendSubscriptionRequest.class);
      JaxbContextFactory.initJaxbContext(SendSubscriptionResponse.class);
   }
}
