package be.ehealth.businessconnector.mycarenet.attestv3.builders.impl;

import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedEncryptedResponseHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedResponseHolder;
import be.ehealth.business.mycarenetcommons.v4.builders.ResponseObjectBuilderHelper;
import be.ehealth.businessconnector.mycarenet.attestv3.builders.ResponseObjectBuilder;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private ResponseObjectBuilderHelper responseObjectBuilderHelper = new ResponseObjectBuilderHelper();

   public ResponseObjectBuilderImpl() {
   }

   public SignedEncryptedResponseHolder handleSendAttestionResponse(SendResponseType sendResponse, EncryptedRequestHolder sendAttestationRequest) throws TechnicalConnectorException {
      return this.responseObjectBuilderHelper.build(sendResponse, sendAttestationRequest);
   }

   public SignedResponseHolder handleCancelAttestationResponse(SendResponseType sendResponse, SendRequestType cancelAttestationRequest) throws TechnicalConnectorException {
      return this.responseObjectBuilderHelper.build(sendResponse, cancelAttestationRequest);
   }

   public void bootstrap() {
   }
}
