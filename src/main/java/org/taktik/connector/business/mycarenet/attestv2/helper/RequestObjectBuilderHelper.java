package org.taktik.connector.business.mycarenet.attestv2.helper;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.kmehrcommons.HcPartyUtil;
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper;
import org.taktik.connector.business.mycarenetcommons.mapper.v3.CommonInputMapper;
import org.taktik.connector.business.mycarenetcommons.mapper.v3.RoutingMapper;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.CommonBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.business.mycarenet.attestv2.domain.InputReference;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorExceptionValues;
import org.taktik.connector.business.mycarenet.attestv2.security.AttestEncryptionUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import be.fgov.ehealth.messageservices.core.v1.RequestType;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005._05.xmlmime.Base64Binary;

public final class RequestObjectBuilderHelper {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderHelper.class);
   private static MarshallerHelper<SendTransactionRequest, SendTransactionRequest> kmehrMarshallHelper = new MarshallerHelper(SendTransactionRequest.class, SendTransactionRequest.class);
   private static final Configuration config = ConfigFactory.getConfigValidator();

   private RequestObjectBuilderHelper() {
      throw new IllegalStateException("Utility class");
   }

   public static CancelAttestationRequest buildSendRequestTypeWithXades(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Blob blob, String mcnLicense, String mcnPassword) throws TechnicalConnectorException {
      CancelAttestationRequest cancelAttestRequest = (CancelAttestationRequest)buildSendRequestType(isTest, references, patientSsin, referenceDate, blob, "attest", CancelAttestationRequest.class, mcnLicense, mcnPassword);
      Base64Binary base64Binary = buildXades(blob, cancelAttestRequest);
      cancelAttestRequest.setXades(base64Binary);
      return cancelAttestRequest;
   }

   public static <T> SendRequestType buildSendRequestType(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Blob blob, String projectIdentifier, Class<T> clazz, String mcnLicense, String mcnPassword) throws TechnicalConnectorException {
      SendRequestType sendRequestType;
      try {
         sendRequestType = (SendRequestType)clazz.newInstance();
      } catch (Exception var11) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var11);
      }

      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder(projectIdentifier);
      Patient patient = (new Patient.Builder()).withInss(patientSsin.getValue()).build();
      CommonInputType mapCommonInputType = CommonInputMapper.mapCommonInputType(commonBuilder.createCommonInput(McnConfigUtil.retrievePackageInfo(projectIdentifier, mcnLicense, mcnPassword), isTest, references.getInputReference()));
      sendRequestType.setCommonInput(mapCommonInputType);
      sendRequestType.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendRequestType.setIssueInstant(new DateTime());
      sendRequestType.setRouting(RoutingMapper.mapRoutingType(commonBuilder.createRouting(patient, referenceDate)));
      sendRequestType.setDetail(BlobMapper.mapBlobTypefromBlob(blob));
      return sendRequestType;
   }

   public static BusinessContent buildBusinessContent(SendTransactionRequest request, String detailId) {
      BusinessContent businessContent = new BusinessContent();
      businessContent.setId(detailId);
      businessContent.setValue(kmehrMarshallHelper.toXMLByteArray(request));
      return businessContent;
   }

   public static SendTransactionRequest buildSendTransactionRequest(InputReference references, Kmehrmessage msg) throws TechnicalConnectorException {
      SendTransactionRequest request = new SendTransactionRequest();
      request.setRequest(buildRequest(references));
      request.setKmehrmessage(msg);
      return request;
   }

   public static void setMessageProtocoleSchemaVersion(SendTransactionRequest request, String schema) throws TechnicalConnectorException {
      String messageProtocoleSchemaVersion = config.getProperty(schema);
      if (messageProtocoleSchemaVersion != null) {
         try {
            request.setMessageProtocoleSchemaVersion(new BigDecimal(messageProtocoleSchemaVersion));
         } catch (NumberFormatException var4) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY_VALUE, messageProtocoleSchemaVersion + " is not a valid message protocole schema decimal value");
         }
      }

   }

   public static Base64Binary buildXades(Blob blob, SendRequestType sendRequestType) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap<>();
      List<String> tranforms = new ArrayList<>();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      options.put("transformerList", tranforms);
      options.put("baseURI", blob.getId());
      byte[] sign = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray(sendRequestType), options);
      return DomainBlobMapper.mapB64fromByte(sign);
   }

   public static RequestType buildRequest(InputReference references) throws TechnicalConnectorException {
      RequestType req = new RequestType();
      req.setId(HcPartyUtil.createKmehrId("attest", references.getInputReference()));
      req.setAuthor(HcPartyUtil.createAuthor("attest"));
      req.setDate(new DateTime());
      req.setTime(new DateTime());
      return req;
   }

   public static void checkInputParameters(InputReference inputReference, Ssin patientSsin, DateTime referenceDate) throws AttestBusinessConnectorException {
      checkParameterNotNull(inputReference, "InputReference");
      checkParameterNotNull(inputReference.getInputReference(), "Input reference");
      checkParameterNotNull(referenceDate, "Reference date");
      checkParameterNotNull(patientSsin, "patientSsin");
      checkParameterNotNull(patientSsin, "value of patientSsin");
   }

   public static void checkParameterNotNull(Object references, String parameterName) throws AttestBusinessConnectorException {
      if (references == null) {
         throw new AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues.PARAMETER_NULL, parameterName);
      }
   }

   public static Blob buildBlobWithEncryptedKnownContent(String id, EncryptedKnownContent encryptedKnownContent, String encodingType, String contentType, String messageName, String contentEncryption, String projectIdentifier) throws TechnicalConnectorException {
      byte[] xmlByteArray = (new AttestEncryptionUtil()).handleEncryptionSendAttestation(encryptedKnownContent, SessionUtil.getHolderOfKeyCrypto(), id);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("org.taktik.connector.business.attestv2.builders.impl.dumpMessages", false)) {
         LOG.debug("RequestObjectBuilder : created blob content: {}", new String(xmlByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder(projectIdentifier).build(xmlByteArray, encodingType, id, contentType, messageName, contentEncryption);
      blob.setMessageName(messageName);
      return blob;
   }

   public static Blob buildBlobWithRequestEncrypted(String id, SendTransactionRequest request, String encodingType, String contentType, String messageName, String projectIdentifier) throws TechnicalConnectorException {
      Blob blob = BlobBuilderFactory.getBlobBuilder(projectIdentifier).build(kmehrMarshallHelper.toXMLByteArray(request), encodingType, id, messageName, messageName);
      blob.setMessageName(contentType);
      return blob;
   }

   public static EncryptedKnownContent buildEncryptedKnownContent(BusinessContent businessContent, Credential credential) throws TechnicalConnectorException {
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(credential).getEncoded());
      encryptedKnownContent.setBusinessContent(businessContent);
      return encryptedKnownContent;
   }
}
