package be.ehealth.businessconnector.mediprimauma.service.impl;

import be.ehealth.business.common.helper.EhealthServiceHelper;
import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaDeleteException;
import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaSearchException;
import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaSendException;
import be.ehealth.businessconnector.mediprimauma.service.MediprimaUmaService;
import be.ehealth.businessconnector.mediprimauma.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mediprima.uma.protocol.v1.DeleteUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.DeleteUrgentMedicalAidAttestationResponse;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SearchUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SearchUrgentMedicalAidAttestationResponse;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SendUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SendUrgentMedicalAidAttestationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediprimaUmaServiceImpl implements MediprimaUmaService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(MediprimaUmaServiceImpl.class);
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public MediprimaUmaServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
   }

   public MediprimaUmaServiceImpl() {
      LOG.debug("creating MediprimaUmaServiceImpl for ModuleBootstrapHook");
   }

   public DeleteUrgentMedicalAidAttestationResponse deleteUrgentMedicalAidAttestation(SAMLToken token, DeleteUrgentMedicalAidAttestationRequest request) throws MediprimaUmaDeleteException, TechnicalConnectorException {
      try {
         return (DeleteUrgentMedicalAidAttestationResponse)EhealthServiceHelper.callEhealthServiceV2(token, ServiceFactory.getMediPrimaUmaService(token, "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:deleteMedicalEmergencyAttestion"), request, DeleteUrgentMedicalAidAttestationResponse.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var4) {
         throw new MediprimaUmaDeleteException((DeleteUrgentMedicalAidAttestationResponse)var4.getResponseTypeV2());
      }
   }

   public SearchUrgentMedicalAidAttestationResponse searchUrgentMedicalAidAttestation(SAMLToken token, SearchUrgentMedicalAidAttestationRequest request) throws MediprimaUmaSearchException, TechnicalConnectorException {
      try {
         return (SearchUrgentMedicalAidAttestationResponse)EhealthServiceHelper.callEhealthServiceV2(token, ServiceFactory.getMediPrimaUmaService(token, "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:searchMedicalEmergencyAttestion"), request, SearchUrgentMedicalAidAttestationResponse.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var4) {
         throw new MediprimaUmaSearchException((SearchUrgentMedicalAidAttestationResponse)var4.getResponseTypeV2());
      }
   }

   public SendUrgentMedicalAidAttestationResponse sendUrgentMedicalAidAttestation(SAMLToken token, SendUrgentMedicalAidAttestationRequest request) throws MediprimaUmaSendException, TechnicalConnectorException {
      try {
         return (SendUrgentMedicalAidAttestationResponse)EhealthServiceHelper.callEhealthServiceV2(token, ServiceFactory.getMediPrimaUmaService(token, "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:sendMedicalEmergencyAttestion"), request, SendUrgentMedicalAidAttestationResponse.class, this.sessionValidator, this.replyValidator);
      } catch (SoaErrorException var4) {
         throw new MediprimaUmaSendException((SendUrgentMedicalAidAttestationResponse)var4.getResponseTypeV2());
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(DeleteUrgentMedicalAidAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(DeleteUrgentMedicalAidAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(SearchUrgentMedicalAidAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SearchUrgentMedicalAidAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(SendUrgentMedicalAidAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendUrgentMedicalAidAttestationResponse.class);
   }
}
