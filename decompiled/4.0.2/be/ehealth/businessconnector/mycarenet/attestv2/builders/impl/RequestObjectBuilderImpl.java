package be.ehealth.businessconnector.mycarenet.attestv2.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetcommons.v3.mapper.RoutingMapper;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.businessconnector.mycarenet.attestv2.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.CancelAttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SendAttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.ehealth.businessconnector.mycarenet.attestv2.helper.RequestObjectBuilderHelper;
import be.ehealth.businessconnector.mycarenet.attestv2.validators.impl.AttestXmlValidatorImpl;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage;
import org.joda.time.DateTime;
import org.mapstruct.factory.Mappers;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public RequestObjectBuilderImpl() {
   }

   public SendAttestBuilderRequest buildSendAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg) throws AttestBusinessConnectorException, TechnicalConnectorException {
      RequestObjectBuilderHelper.checkInputParameters(references, patientSsin, referenceDate);
      SendTransactionRequest request = RequestObjectBuilderHelper.buildSendTransactionRequest(references, msg);
      RequestObjectBuilderHelper.setMessageProtocoleSchemaVersion(request, "mycarenet.attest.v2.message.protocole.schema.version");
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      BusinessContent businessContent = RequestObjectBuilderHelper.buildBusinessContent(request, detailId);
      EncryptedKnownContent encryptedKnownContent = RequestObjectBuilderHelper.buildEncryptedKnownContent(businessContent);

      try {
         Blob blob = RequestObjectBuilderHelper.buildBlobWithEncryptedKnownContent(detailId, encryptedKnownContent, "none", "text/xml", "E-ATTEST-V2", "encryptedForKnownBED", "attestv2");
         SendAttestationRequest sendAttestationRequest = (SendAttestationRequest)RequestObjectBuilderHelper.buildSendRequestType(isTest, references, blob, "attestv2", SendAttestationRequest.class);
         CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder("attestv2");
         Patient patient = (new Patient.Builder()).withInss(patientSsin.getValue()).build();
         RoutingMapper mapper = (RoutingMapper)Mappers.getMapper(RoutingMapper.class);
         sendAttestationRequest.setRouting(mapper.map(commonBuilder.createRouting(patient, referenceDate)));
         (new AttestXmlValidatorImpl()).validate(sendAttestationRequest);
         return new SendAttestBuilderRequest(sendAttestationRequest, request, businessContent);
      } catch (Exception var15) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var15, new Object[0]);
      }
   }

   public CancelAttestBuilderRequest buildCancelAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg) throws AttestBusinessConnectorException, TechnicalConnectorException {
      RequestObjectBuilderHelper.checkInputParameters(references, patientSsin, referenceDate);
      SendTransactionRequest request = RequestObjectBuilderHelper.buildSendTransactionRequest(references, msg);
      RequestObjectBuilderHelper.setMessageProtocoleSchemaVersion(request, "mycarenet.attest.v2.message.protocole.schema.version");
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      Blob blob = RequestObjectBuilderHelper.buildBlobWithRequestEncrypted(detailId, request, "none", "E-ATTEST-CANCEL", "text/xml", "attestv2");

      CancelAttestationRequest cancelAttestRequest;
      try {
         cancelAttestRequest = RequestObjectBuilderHelper.buildSendRequestTypeWithXades(isTest, references, blob);
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
