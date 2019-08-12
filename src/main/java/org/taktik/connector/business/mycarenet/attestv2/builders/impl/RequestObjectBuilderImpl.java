package org.taktik.connector.business.mycarenet.attestv2.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.business.mycarenet.attestv2.builders.RequestObjectBuilder;
import org.taktik.connector.business.mycarenet.attestv2.domain.CancelAttestBuilderRequest;
import org.taktik.connector.business.mycarenet.attestv2.domain.InputReference;
import org.taktik.connector.business.mycarenet.attestv2.domain.SendAttestBuilderRequest;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import org.taktik.connector.business.mycarenet.attestv2.helper.RequestObjectBuilderHelper;
import org.taktik.connector.business.mycarenet.attestv2.validators.impl.AttestXmlValidatorImpl;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import org.joda.time.DateTime;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public SendAttestBuilderRequest buildSendAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg, Credential credential, String mcnLicense, String mcnPassword) throws AttestBusinessConnectorException, TechnicalConnectorException {
      RequestObjectBuilderHelper.checkInputParameters(references, patientSsin, referenceDate);
      SendTransactionRequest request = RequestObjectBuilderHelper.buildSendTransactionRequest(references, msg);
      RequestObjectBuilderHelper.setMessageProtocoleSchemaVersion(request, "mycarenet.attest.v2.message.protocole.schema.version");
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      BusinessContent businessContent = RequestObjectBuilderHelper.buildBusinessContent(request, detailId);
      EncryptedKnownContent encryptedKnownContent = RequestObjectBuilderHelper.buildEncryptedKnownContent(businessContent, credential);

      try {
         Blob blob = RequestObjectBuilderHelper.buildBlobWithEncryptedKnownContent(detailId, encryptedKnownContent, "none", "text/xml", "E-ATTEST-V2", "encryptedForKnownBED", "attest");
         SendAttestationRequest sendAttestationRequest = (SendAttestationRequest)RequestObjectBuilderHelper.buildSendRequestType(isTest, references, patientSsin, referenceDate, blob, "attest", SendAttestationRequest.class, mcnLicense, mcnPassword);
         (new AttestXmlValidatorImpl()).validate(sendAttestationRequest);
         return new SendAttestBuilderRequest(sendAttestationRequest, request);
      } catch (Exception var12) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var12, new Object[0]);
      }
   }

   public CancelAttestBuilderRequest buildCancelAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg, String mcnLicense, String mcnPassword) throws AttestBusinessConnectorException, TechnicalConnectorException {
      RequestObjectBuilderHelper.checkInputParameters(references, patientSsin, referenceDate);
      SendTransactionRequest request = RequestObjectBuilderHelper.buildSendTransactionRequest(references, msg);
      RequestObjectBuilderHelper.setMessageProtocoleSchemaVersion(request, "mycarenet.attest.v2.message.protocole.schema.version");
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      Blob blob = RequestObjectBuilderHelper.buildBlobWithRequestEncrypted(detailId, request, "none", "E-ATTEST-CANCEL", "text/xml", "attest");

      CancelAttestationRequest cancelAttestRequest;
      try {
         cancelAttestRequest = RequestObjectBuilderHelper.buildSendRequestTypeWithXades(isTest, references, patientSsin, referenceDate, blob, mcnLicense, mcnPassword);
      } catch (Exception var11) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var11, new Object[0]);
      }

      (new AttestXmlValidatorImpl()).validate(cancelAttestRequest);
      return new CancelAttestBuilderRequest(cancelAttestRequest, request);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
      JaxbContextFactory.initJaxbContext(RoutingType.class);
   }
}
