package be.ehealth.businessconnector.mycarenet.attest.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.mycarenet.attest.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.mycarenet.attest.domain.AttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attest.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.attest.exception.AttestBusinessConnectorException;
import be.ehealth.businessconnector.mycarenet.attest.exception.AttestBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.mycarenet.attest.mappers.BlobMapper;
import be.ehealth.businessconnector.mycarenet.attest.mappers.CommonInputMapper;
import be.ehealth.businessconnector.mycarenet.attest.mappers.RoutingMapper;
import be.ehealth.businessconnector.mycarenet.attest.security.AttestEncryptionUtil;
import be.ehealth.businessconnector.mycarenet.attest.validators.impl.AttestXmlValidatorImpl;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.core.v1.RequestType;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);
   private static final Configuration config = ConfigFactory.getConfigValidator();

   public AttestBuilderRequest buildSendAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg) throws TechnicalConnectorException, AttestBusinessConnectorException, JAXBException, TransformerException, UnsupportedEncodingException {
      this.checkInputParameters(references, patientSsin, referenceDate);
      SendAttestationRequest sendAttestationRequest = new SendAttestationRequest();
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      SendTransactionRequest request = new SendTransactionRequest();
      request.setRequest(this.buildRequest(references));
      request.setKmehrmessage(msg);
      this.setMessageProtocoleSchemaVersion(request);
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      BusinessContent businessContent = new BusinessContent();
      businessContent.setId(detailId);
      MarshallerHelper<SendTransactionRequest, SendTransactionRequest> kmehrMarshallHelper = new MarshallerHelper(SendTransactionRequest.class, SendTransactionRequest.class);
      businessContent.setValue(kmehrMarshallHelper.toXMLByteArray(request));
      encryptedKnownContent.setBusinessContent(businessContent);
      byte[] xmlByteArray = (new AttestEncryptionUtil()).handleEncryption(encryptedKnownContent, SessionUtil.getHolderOfKeyCrypto(), detailId);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.attest.builders.impl.dumpMessages", false)) {
         LOG.debug("RequestObjectBuilder : created blob content: " + new String(xmlByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder("attest").build(xmlByteArray, "none", detailId, "text/xml", (String)null, "encryptedForKnownCINNIC");
      blob.setMessageName("E-ATTEST");
      this.buildAttestationRequest(isTest, references, patientSsin, referenceDate, sendAttestationRequest, blob);
      (new AttestXmlValidatorImpl()).validate(sendAttestationRequest);
      return new AttestBuilderRequest(sendAttestationRequest, request);
   }

   private void setMessageProtocoleSchemaVersion(SendTransactionRequest request) throws TechnicalConnectorException {
      String messageProtocoleSchemaVersion = config.getProperty("mycarenet.attest.message.protocole.schema.version");
      if (messageProtocoleSchemaVersion != null) {
         try {
            request.setMessageProtocoleSchemaVersion(new BigDecimal(messageProtocoleSchemaVersion));
         } catch (NumberFormatException var4) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY_VALUE, new Object[]{messageProtocoleSchemaVersion + " is not a valid message protocole schema decimal value"});
         }
      }

   }

   private void buildAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, SendAttestationRequest sendAttestationRequest, Blob blob) throws TechnicalConnectorException {
      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder("attest");
      sendAttestationRequest.setCommonInput(CommonInputMapper.mapCommonInputType(commonBuilder.createCommonInput(McnConfigUtil.retrievePackageInfo("attest"), isTest, references.getInputReference())));
      sendAttestationRequest.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendAttestationRequest.setIssueInstant(new DateTime());
      Patient patient = (new Patient.Builder()).withInss(patientSsin.getValue()).build();
      sendAttestationRequest.setRouting(RoutingMapper.mapRoutingType(commonBuilder.createRouting(patient, referenceDate)));
      sendAttestationRequest.setDetail(BlobMapper.mapBlobTypefromBlob(blob));
   }

   private RequestType buildRequest(InputReference references) throws TechnicalConnectorException {
      RequestType req = new RequestType();
      req.setId(HcPartyUtil.createKmehrId("attest", references.getInputReference()));
      req.setAuthor(HcPartyUtil.createAuthor("attest"));
      req.setDate(new DateTime());
      req.setTime(new DateTime());
      return req;
   }

   private void checkInputParameters(InputReference inputReference, Ssin patientSsin, DateTime referenceDate) throws AttestBusinessConnectorException {
      this.checkParameterNotNull(inputReference, "InputReference");
      this.checkParameterNotNull(inputReference.getInputReference(), "Input reference");
      this.checkParameterNotNull(referenceDate, "Reference date");
      this.checkParameterNotNull(patientSsin, "patientSsin");
      this.checkParameterNotNull(patientSsin, "value of patientSsin");
   }

   private void checkParameterNotNull(Object references, String parameterName) throws AttestBusinessConnectorException {
      if (references == null) {
         throw new AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
      JaxbContextFactory.initJaxbContext(RoutingType.class);
   }
}
