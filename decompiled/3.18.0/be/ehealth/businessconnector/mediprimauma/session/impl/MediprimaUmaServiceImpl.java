package be.ehealth.businessconnector.mediprimauma.session.impl;

import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaDeleteException;
import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaSearchException;
import be.ehealth.businessconnector.mediprimauma.exception.MediprimaUmaSendException;
import be.ehealth.businessconnector.mediprimauma.session.MediprimaUmaService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mediprima.uma.protocol.v1.DeleteUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.DeleteUrgentMedicalAidAttestationResponse;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SearchUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SearchUrgentMedicalAidAttestationResponse;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SendUrgentMedicalAidAttestationRequest;
import be.fgov.ehealth.mediprima.uma.protocol.v1.SendUrgentMedicalAidAttestationResponse;

public class MediprimaUmaServiceImpl implements MediprimaUmaService {
   private be.ehealth.businessconnector.mediprimauma.service.MediprimaUmaService mediprimaUmaService;

   public MediprimaUmaServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.mediprimaUmaService = new be.ehealth.businessconnector.mediprimauma.service.impl.MediprimaUmaServiceImpl(sessionValidator, replyValidator);
   }

   public DeleteUrgentMedicalAidAttestationResponse deleteUrgentMedicalAidAttestation(DeleteUrgentMedicalAidAttestationRequest request) throws TechnicalConnectorException, MediprimaUmaDeleteException {
      Session.validateSession();
      return this.mediprimaUmaService.deleteUrgentMedicalAidAttestation(Session.getSAMLToken(), request);
   }

   public SearchUrgentMedicalAidAttestationResponse searchUrgentMedicalAidAttestation(SearchUrgentMedicalAidAttestationRequest request) throws TechnicalConnectorException, MediprimaUmaSearchException {
      Session.validateSession();
      return this.mediprimaUmaService.searchUrgentMedicalAidAttestation(Session.getSAMLToken(), request);
   }

   public SendUrgentMedicalAidAttestationResponse sendUrgentMedicalAidAttestation(SendUrgentMedicalAidAttestationRequest request) throws TechnicalConnectorException, MediprimaUmaSendException {
      Session.validateSession();
      return this.mediprimaUmaService.sendUrgentMedicalAidAttestation(Session.getSAMLToken(), request);
   }
}
