package org.taktik.connector.business.mycarenet.attest.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.kmehrcommons.HcPartyUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.CommonBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.business.mycarenet.attest.builders.RequestObjectBuilder;
import org.taktik.connector.business.mycarenet.attest.domain.InputReference;
import org.taktik.connector.business.mycarenet.attest.exception.AttestBusinessConnectorException;
import org.taktik.connector.business.mycarenet.attest.exception.AttestBusinessConnectorExceptionValues;
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper;
import org.taktik.connector.business.mycarenet.attest.mappers.CommonInputMapper;
import org.taktik.connector.business.mycarenet.attest.mappers.RoutingMapper;
import org.taktik.connector.business.mycarenet.attest.security.AttestEncryptionUtil;
import org.taktik.connector.business.mycarenet.attest.validators.impl.AttestXmlValidatorImpl;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.core.v1.RequestType;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);

   public SendAttestationRequest buildSendAttestationRequest(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Kmehrmessage msg) throws TechnicalConnectorException, AttestBusinessConnectorException, JAXBException, TransformerException, UnsupportedEncodingException {
      this.checkInputParameters(references, patientSsin, referenceDate);
      SendAttestationRequest sendAttestationRequest = new SendAttestationRequest();
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      SendTransactionRequest request = new SendTransactionRequest();
      request.setRequest(this.buildRequest(references));
      request.setKmehrmessage(msg);
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      BusinessContent businessContent = new BusinessContent();
      businessContent.setId(detailId);
      MarshallerHelper<SendTransactionRequest, SendTransactionRequest> kmehrMarshallHelper = new MarshallerHelper(SendTransactionRequest.class, SendTransactionRequest.class);
      businessContent.setValue(kmehrMarshallHelper.toXMLByteArray(request));
      encryptedKnownContent.setBusinessContent(businessContent);
      byte[] xmlByteArray = (new AttestEncryptionUtil()).handleEncryption(encryptedKnownContent, SessionUtil.getHolderOfKeyCrypto(), detailId);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("org.taktik.connector.business.attest.builders.impl.dumpMessages", false).booleanValue()) {
         LOG.debug("RequestObjectBuilder : created blob content: " + new String(xmlByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder("attest").build(xmlByteArray, "none", detailId, "text/xml", (String)null, "encryptedForKnownCINNIC");
      blob.setMessageName("E-ATTEST");
      this.buildAttestationRequest(isTest, references, patientSsin, referenceDate, sendAttestationRequest, blob);
      (new AttestXmlValidatorImpl()).validate(sendAttestationRequest);
      return sendAttestationRequest;
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
