package org.taktik.connector.business.consultrn.service.impl;

import org.taktik.connector.business.common.helper.EhealthServiceHelper;
import org.taktik.connector.business.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import org.taktik.connector.business.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import org.taktik.connector.business.consultrn.service.ConsultrnService;
import org.taktik.connector.business.consultrn.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.SoaErrorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultrnServiceImpl implements ConsultrnService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ConsultrnServiceImpl.class);
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public ConsultrnServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
   }

   public ConsultrnServiceImpl() {
      LOG.debug("creating ConsultrnServiceImpl for ModuleBootstrapHook");
   }

   public SearchBySSINReply search(SAMLToken token, SearchBySSINRequest request) throws ConsultrnIdentifyPersonException, TechnicalConnectorException {
      try {
         return (SearchBySSINReply)EhealthServiceHelper.callEhealthService_1_0(token, ServiceFactory.getConsultrnIdentifyPersonService(token), request, SearchBySSINReply.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var4) {
         throw new ConsultrnIdentifyPersonException((SearchBySSINReply)var4.getResponseTypeV1_0());
      }
   }

   public SearchPhoneticReply search(SAMLToken token, SearchPhoneticRequest request) throws ConsultrnPhoneticSearchException, TechnicalConnectorException {
      try {
         return (SearchPhoneticReply)EhealthServiceHelper.callEhealthService_1_0(token, ServiceFactory.getConsultrnPhoneticSearchService(token), request, SearchPhoneticReply.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var4) {
         throw new ConsultrnPhoneticSearchException((SearchPhoneticReply)var4.getResponseTypeV1_0());
      }
   }

   public RegisterPersonResponse registerPerson(SAMLToken token, RegisterPersonRequest request) throws TechnicalConnectorException, ConsultrnRegisterPersonException, ConsultrnRegisterExistingPersonException {
      try {
         return (RegisterPersonResponse)EhealthServiceHelper.callEhealthServiceV2(token, ServiceFactory.getConsultrnManagePersonService(token, "urn:be:fgov:ehealth:consultrn:manageperson:protocol:v2:RegisterPerson"), request, RegisterPersonResponse.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var5) {
         RegisterPersonResponse response = (RegisterPersonResponse)var5.getResponseTypeV2();
         if (response.getResult() != null && response.getResult().getExistingPersons() != null) {
            throw new ConsultrnRegisterExistingPersonException(response);
         } else {
            throw new ConsultrnRegisterPersonException(response);
         }
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SearchBySSINRequest.class);
      JaxbContextFactory.initJaxbContext(SearchPhoneticRequest.class);
      JaxbContextFactory.initJaxbContext(SearchBySSINReply.class);
      JaxbContextFactory.initJaxbContext(SearchPhoneticReply.class);
   }
}
