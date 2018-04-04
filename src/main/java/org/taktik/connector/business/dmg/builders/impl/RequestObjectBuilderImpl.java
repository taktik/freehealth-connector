package org.taktik.connector.business.dmg.builders.impl;

import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.kmehrcommons.HcPartyUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.CommonBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.business.dmg.builders.RequestObjectBuilder;
import org.taktik.connector.business.dmg.domain.DMGReferences;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorExceptionValues;
import org.taktik.connector.business.dmg.mappers.BlobMapper;
import org.taktik.connector.business.dmg.mappers.CommonInputMapper;
import org.taktik.connector.business.dmg.mappers.RoutingMapper;
import org.taktik.connector.business.dmg.validators.impl.DmgXmlValidatorImpl;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.globalmedicalfile.core.v1.CommonInputType;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendRequestType;
import be.fgov.ehealth.messageservices.core.v1.RequestType;
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionRequest;
import be.fgov.ehealth.messageservices.core.v1.SelectRetrieveTransaction;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005._05.xmlmime.Base64Binary;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);

   private <T extends SendRequestType> T fillSendRequest(T sendRequestT, FillSendRequestParameter parameterObject, byte[] xades, boolean generatedXades) throws TechnicalConnectorException, DmgBusinessConnectorException {
      CommonBuilder cb = RequestBuilderFactory.getCommonBuilder("dmg");
      this.checkInputParameters(parameterObject.getReferenceId(), parameterObject.getPatientInfo(), parameterObject.getReferenceDate(), parameterObject.getBlob());
      sendRequestT.setCommonInput(CommonInputMapper.mapCommonInputType(cb.createCommonInput(McnConfigUtil.retrievePackageInfo("dmg"), parameterObject.isTest(), parameterObject.getReferenceId())));
      sendRequestT.setRouting(RoutingMapper.mapRoutingType(cb.createRouting(parameterObject.getPatientInfo(), parameterObject.getReferenceDate())));
      sendRequestT.setDetail(BlobMapper.mapBlobTypefromBlob(parameterObject.getBlob()));
      this.setXades(sendRequestT, xades, generatedXades);
      return sendRequestT;
   }

   private <T extends SendRequestType> void setXades(T sendRequestT, byte[] xades, boolean generatedXades) throws TechnicalConnectorException {
      byte[] xadesValue = null;
      if (ArrayUtils.isEmpty(xades) && generatedXades) {
         Map<String, Object> options = new HashMap();
         options.put("baseURI", sendRequestT.getDetail().getId());
         List<String> transformList = new ArrayList();
         transformList.add("http://www.w3.org/2000/09/xmldsig#base64");
         options.put("transformerList", transformList);
         xadesValue = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray((Object)sendRequestT), options);
      } else {
         xadesValue = ArrayUtils.clone(xades);
      }

      if (!ArrayUtils.isEmpty(xadesValue)) {
         Base64Binary value = new Base64Binary();
         value.setValue(xadesValue);
         value.setContentType("text/xml");
         sendRequestT.setXadesT(value);
      }

   }

   private void checkInputParameters(String referenceId, Patient patientInfo, DateTime referenceDate, Blob blob) throws DmgBusinessConnectorException {
      this.checkParameterNotNull(referenceId, "DmgReferences");
      if (blob != null && blob.getContent() != null && blob.getContent().length != 0) {
         this.checkStringParameterNotNullOrEmpty(blob.getContentType(), "Blob contentType");
         this.checkStringParameterNotNullOrEmpty(blob.getId(), "Blob id");
         this.checkParameterNotNull(referenceDate, "Reference date");
         this.checkParameterNotNull(patientInfo, "Patient info");
         if (patientInfo.getInss() == null || patientInfo.getInss().isEmpty()) {
            if (patientInfo.getMutuality() == null || patientInfo.getMutuality().isEmpty()) {
               throw new DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"Ssin and mutuality (No valid patient information)"});
            }

            if (patientInfo.getRegNrWithMut() == null || patientInfo.getRegNrWithMut().isEmpty()) {
               throw new DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"Ssin and registration number (No valid patient information)"});
            }
         }

      } else {
         throw new DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"Blob Content"});
      }
   }

   private void checkStringParameterNotNullOrEmpty(String contentType, String parameterName) throws DmgBusinessConnectorException {
      if (contentType == null || contentType.isEmpty()) {
         throw new DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   private void checkParameterNotNull(Object references, String parameterName) throws DmgBusinessConnectorException {
      if (references == null) {
         throw new DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public final ConsultGlobalMedicalFileRequest buildSendConsultRequest(boolean isTest, String referenceId, Patient patientInfo, DateTime referenceDate, Blob blob, byte[] xades) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException {
      if (blob != null && blob.getContent() != null && ConfigFactory.getConfigValidator().getBooleanProperty("org.taktik.connector.business.dmg.builders.impl.dumpMessages", false).booleanValue()) {
         LOG.debug("RequestObjectBuilder : called with blob content: " + new String(blob.getContent()));
      }

      ConsultGlobalMedicalFileRequest result = new ConsultGlobalMedicalFileRequest();
      result = (ConsultGlobalMedicalFileRequest)this.fillSendRequest(result, new FillSendRequestParameter(isTest, referenceId, patientInfo, referenceDate, blob), xades, false);
      DmgXmlValidatorImpl validator = new DmgXmlValidatorImpl();
      validator.validate(result);
      return result;
   }

   public ConsultGlobalMedicalFileRequest buildSendConsultRequest(boolean isTest, DMGReferences references, Patient patientInfo, DateTime referenceDate, SelectRetrieveTransaction request) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException {
      ConsultGlobalMedicalFileRequest result = new ConsultGlobalMedicalFileRequest();
      RetrieveTransactionRequest req = new RetrieveTransactionRequest();
      req.setRequest(this.generatedReq(references));
      req.setSelect(request);
      MarshallerHelper<RetrieveTransactionRequest, RetrieveTransactionRequest> kmehrRequestMarshaller = new MarshallerHelper(RetrieveTransactionRequest.class, RetrieveTransactionRequest.class);
      byte[] xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(req);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("org.taktik.connector.business.dmg.builders.impl.dumpMessages", false).booleanValue()) {
         LOG.debug("RequestObjectBuilder : created blob content: " + new String(xmlByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder("dmg").build(xmlByteArray, "none", "_" + references.getBlobId(), "text/xml");
      blob.setMessageName("GMD-CONSULT-HCP");
      result = (ConsultGlobalMedicalFileRequest)this.fillSendRequest(result, new FillSendRequestParameter(isTest, references.getInputReference(), patientInfo, referenceDate, blob), ArrayUtils.EMPTY_BYTE_ARRAY, false);
      DmgXmlValidatorImpl validator = new DmgXmlValidatorImpl();
      validator.validate(result);
      return result;
   }

   public final NotifyGlobalMedicalFileRequest buildSendNotifyRequest(boolean isTest, String referenceId, Patient patientInfo, DateTime referenceDate, Blob blob, byte[] xades) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException {
      NotifyGlobalMedicalFileRequest result = new NotifyGlobalMedicalFileRequest();
      result = (NotifyGlobalMedicalFileRequest)this.fillSendRequest(result, new FillSendRequestParameter(isTest, referenceId, patientInfo, referenceDate, blob), xades, true);
      DmgXmlValidatorImpl validator = new DmgXmlValidatorImpl();
      validator.validate(result);
      return result;
   }

   public NotifyGlobalMedicalFileRequest buildSendNotifyRequest(boolean isTest, DMGReferences references, Patient patientInfo, DateTime referenceDate, Kmehrmessage msg) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException {
      SendTransactionRequest request = new SendTransactionRequest();
      request.setRequest(this.generatedReq(references));
      request.setKmehrmessage(msg);
      MarshallerHelper<SendTransactionRequest, SendTransactionRequest> kmehrRequestMarshaller = new MarshallerHelper(SendTransactionRequest.class, SendTransactionRequest.class);
      byte[] xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(request);
      if (xmlByteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("org.taktik.connector.business.dmg.builders.impl.dumpMessages", false).booleanValue()) {
         LOG.debug("RequestObjectBuilder : created blob content: " + new String(xmlByteArray));
      }

      Blob blob = BlobBuilderFactory.getBlobBuilder("dmg").build(xmlByteArray, "none", "_" + references.getBlobId(), "text/xml");
      blob.setMessageName("GMD-CONSULT-HCP");
      return this.buildSendNotifyRequest(isTest, references.getInputReference(), patientInfo, referenceDate, blob, ArrayUtils.EMPTY_BYTE_ARRAY);
   }

   private RequestType generatedReq(DMGReferences references) throws TechnicalConnectorException {
      RequestType req = new RequestType();
      req.setId(HcPartyUtil.createKmehrId("dmg", references.getKmehrIdSuffix()));
      req.setAuthor(HcPartyUtil.createAuthor("dmg"));
      req.setDate(new DateTime());
      req.setTime(new DateTime());
      return req;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SelectRetrieveTransaction.class);
      JaxbContextFactory.initJaxbContext(RetrieveTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(SendTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
      JaxbContextFactory.initJaxbContext(RoutingType.class);
   }
}
