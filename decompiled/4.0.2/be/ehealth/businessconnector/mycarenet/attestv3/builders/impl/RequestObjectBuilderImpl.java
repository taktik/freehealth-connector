package be.ehealth.businessconnector.mycarenet.attestv3.builders.impl;

import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.business.mycarenetcommons.v4.builders.EncryptedRequestObjectBuilder;
import be.ehealth.business.mycarenetcommons.v4.builders.RequestObjectBuilderHelper;
import be.ehealth.businessconnector.mycarenet.attestv3.builders.CancelAttestationRequestInput;
import be.ehealth.businessconnector.mycarenet.attestv3.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.mycarenet.attestv3.builders.SendAttestationRequestInput;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationRequest;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestObjectBuilderHelper helper = new RequestObjectBuilderHelper();

   public RequestObjectBuilderImpl() {
   }

   public EncryptedRequestHolder<SendAttestationRequest> buildSendAttestationRequest(SendAttestationRequestInput sendAttestationRequestInput) throws TechnicalConnectorException {
      this.helper.checkParameterNotNull(sendAttestationRequestInput, "sendAttestationRequestInput");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getInputReference(), "inputReference");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getInputReference().getInputReference(), "inputReference.inputReference");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getReferenceDate(), "referenceDate");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getPatientSsin(), "patientSsin");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getPatientSsin().getValue(), "patientSsin.value");
      this.helper.checkParameterNotNull(sendAttestationRequestInput.getKmehrmessage(), "sendAttestationRequestInput.kmehrmessage");
      return EncryptedRequestObjectBuilder.builder().routing(new RequestObjectBuilderHelper.WithRouting(sendAttestationRequestInput.getPatientSsin(), sendAttestationRequestInput.getReferenceDate(), "attestv3")).build().buildRequest(EncryptedRequestObjectBuilder.Input.builder().isTest(sendAttestationRequestInput.isTest()).inputReference(sendAttestationRequestInput.getInputReference()).detailContent(sendAttestationRequestInput.getKmehrmessage()).attributes(sendAttestationRequestInput.getCommonInputAttributes()).issuer(sendAttestationRequestInput.getIssuer()).messageVersion(sendAttestationRequestInput.getMessageVersion()).projectIdentifier("attestv3").build(), SendAttestationRequest.class);
   }

   public CancelAttestationRequest buildCancelAttestationRequest(CancelAttestationRequestInput cancelAttestationRequestInput) throws TechnicalConnectorException {
      this.helper.checkParameterNotNull(cancelAttestationRequestInput, "cancelAttestationRequestInput");
      this.helper.checkParameterNotNull(cancelAttestationRequestInput.getInputReference(), "inputReference");
      this.helper.checkParameterNotNull(cancelAttestationRequestInput.getInputReference().getInputReference(), "inputReference.inputReference");
      this.helper.checkParameterNotNull(cancelAttestationRequestInput.getKmehrmessage(), "sendAttestationRequestInput.kmehrmessage");
      return (CancelAttestationRequest)be.ehealth.business.mycarenetcommons.v4.builders.RequestObjectBuilder.builder().routing(new RequestObjectBuilderHelper.WithoutRouting()).build().buildRequest(be.ehealth.business.mycarenetcommons.v4.builders.RequestObjectBuilder.Input.builder().isTest(cancelAttestationRequestInput.isTest()).inputReference(cancelAttestationRequestInput.getInputReference()).detailContent(cancelAttestationRequestInput.getKmehrmessage()).attributes(cancelAttestationRequestInput.getCommonInputAttributes()).issuer(cancelAttestationRequestInput.getIssuer()).messageVersion(cancelAttestationRequestInput.getMessageVersion()).projectIdentifier("attestv3").build(), CancelAttestationRequest.class);
   }

   public void bootstrap() {
   }
}
