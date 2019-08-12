package be.ehealth.businessconnector.consultrn.service.impl;

import be.ehealth.business.common.helper.EhealthServiceHelper;
import be.ehealth.businessconnector.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import be.ehealth.businessconnector.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import be.ehealth.businessconnector.consultrn.service.ConsultrnService;
import be.ehealth.businessconnector.consultrn.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
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
