package be.ehealth.businessconnector.mycarenet.attestv2.helper;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.kmehrcommons.mycarenet.McnHcPartyUtil;
import be.ehealth.business.mycarenetcommons.mapper.v3.BlobMapper;
import be.ehealth.business.mycarenetcommons.mapper.v3.CommonInputMapper;
import be.ehealth.business.mycarenetcommons.mapper.v3.RoutingMapper;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.mycarenet.attestv2.security.AttestEncryptionUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.messageservices.mycarenet.core.v1.RequestType;
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage;
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

   public static CancelAttestationRequest buildSendRequestTypeWithXades(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Blob blob) throws TechnicalConnectorException {
      CancelAttestationRequest cancelAttestRequest = (CancelAttestationRequest)buildSendRequestType(isTest, references, patientSsin, referenceDate, blob, "attestv2", CancelAttestationRequest.class);
      Base64Binary base64Binary = buildXades(blob, cancelAttestRequest);
      cancelAttestRequest.setXades(base64Binary);
      return cancelAttestRequest;
   }

   public static <T> SendRequestType buildSendRequestType(boolean isTest, InputReference references, Ssin patientSsin, DateTime referenceDate, Blob blob, String projectIdentifier, Class<T> clazz) throws TechnicalConnectorException {
      SendRequestType sendRequestType;
      try {
         sendRequestType = (SendRequestType)clazz.newInstance();
      } catch (Exception var11) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var11, new Object[0]);
      }

      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder(projectIdentifier);
      Patient patient = (new Patient.Builder()).withInss(patientSsin.getValue()).build();
      CommonInputType mapCommonInputType = CommonInputMapper.mapCommonInputType(commonBuilder.createCommonInput(McnConfigUtil.retrievePackageInfo(projectIdentifier), isTest, references.getInputReference()));
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
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY_VALUE, new Object[]{messageProtocoleSchemaVersion + " is not a valid message protocole schema decimal value"});
         }
      }

   }

   public static Base64Binary buildXades(Blob blob, SendRequestType sendRequestType) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      options.put("transformerList", tranforms);
      options.put("baseURI", blob.getId());
      byte[] sign = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray((Object)sendRequestType), options);
      return DomainBlobMapper.mapB64fromByte(sign);
   }

   public static RequestType buildRequest(InputReference references) throws TechnicalConnectorException {
      RequestType req = new RequestType();
      req.setId(McnHcPartyUtil.createKmehrId("attestv2", references.getInputReference()));
      req.setAuthor(McnHcPartyUtil.createAuthor("attestv2"));
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
         throw new AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public static Blob buildBlobWithEncryptedKnownContent(String id, EncryptedKnownContent encryptedKnownContent, String encodingType, String contentType, String messageName, String contentEncryption, String projectIdentifier) throws TechnicalConnectorException {
      byte[] xmlByteArray = (new AttestEncryptionUtil()).handleEncryptionSendAttestation(encryptedKnownContent, SessionUtil.getHolderOfKeyCrypto(), id);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.attestv2.builders.impl.dumpMessages", false)) {
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

   public static EncryptedKnownContent buildEncryptedKnownContent(BusinessContent businessContent) throws TechnicalConnectorException {
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      encryptedKnownContent.setBusinessContent(businessContent);
      return encryptedKnownContent;
   }
}
