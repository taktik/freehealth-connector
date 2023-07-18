package org.taktik.connector.business.mycarenet.agreement.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.mycarenet.agreement.builders.RequestObjectBuilder;
import org.taktik.connector.business.mycarenet.agreement.domain.AgreementBuilderRequest;
import org.taktik.connector.business.mycarenet.agreement.domain.AskAgreementBuilderRequest;
import org.taktik.connector.business.mycarenet.agreement.domain.ConsultAgreementBuilderRequest;
import org.taktik.connector.business.mycarenet.agreement.exception.AgreementBusinessConnectorException;
import org.taktik.connector.business.mycarenet.agreement.security.AgreementEncryptionUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.InputReference;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;

public class EncryptedRequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   KeyDepotManager keyDepotManager;
   Log log = LogFactory.getLog(this.getClass());

   public EncryptedRequestObjectBuilderImpl(KeyDepotService keyDepotService) {
      this.keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService);
   }

   public boolean equals(Object obj) {
      return super.equals(obj);
   }

   public AskAgreementBuilderRequest buildAskAgreementRequest(boolean isTest, InputReference reference, Patient patientInfo, DateTime refDate, byte[] messageFHIR) throws TechnicalConnectorException, AgreementBusinessConnectorException {
      AskAgreementBuilderRequest requestBuilder = new AskAgreementBuilderRequest();
      this.build(isTest, reference, patientInfo, refDate, messageFHIR, requestBuilder);
      return requestBuilder;
   }

   public ConsultAgreementBuilderRequest buildConsultAgreementRequest(boolean isTest, InputReference reference, Patient patientInfo, DateTime refDate, byte[] messageFHIR) throws TechnicalConnectorException, AgreementBusinessConnectorException {
      ConsultAgreementBuilderRequest requestBuilder = new ConsultAgreementBuilderRequest();
      this.build(isTest, reference, patientInfo, refDate, messageFHIR, requestBuilder);
      return requestBuilder;
   }

   public <T extends AgreementBuilderRequest> void build(boolean isTest, InputReference references, Patient patientInfo, DateTime refDate, byte[] messageFHIR, T requestBuilder) throws TechnicalConnectorException, AgreementBusinessConnectorException {
      this.checkParameterNotNull(references, "InputReference");
      this.checkParameterNotNull(references.getInputReference(), "Input reference");
      String detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
      BlobBuilder blobBuilder = BlobBuilderFactory.getBlobBuilder("agreement");
      BusinessContent businessContent = new BusinessContent();
      businessContent.setId(detailId);
      businessContent.setValue(messageFHIR);
      requestBuilder.setBusinessContent(businessContent);
      EncryptedKnownContent encryptedKnownContent = new EncryptedKnownContent();
      encryptedKnownContent.setReplyToEtk(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY).getEncoded());
      encryptedKnownContent.setBusinessContent(businessContent);
      encryptedKnownContent.setXades(buildXades(detailId, ConnectorXmlUtils.toByteArray(businessContent)));
      if (log.isDebugEnabled()) {
         ConnectorXmlUtils.dump(encryptedKnownContent);
      }

      byte[] payload = null;
      try {
         payload = (new AgreementEncryptionUtil()).handleEncryption(encryptedKnownContent, SessionUtil.getHolderOfKeyCrypto());
         if (payload != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.agreement.builders.impl.dumpMessages", false)) {
            log.debug("EncryptedRequestObjectBuilder : Created blob content: " + Base64.encodeBase64String(payload));
         }
      } catch (Exception var15) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, var15, new Object[0]);
      }

      Blob blob = blobBuilder.build(payload, "none", detailId, "text/xml", (String)null, "encryptedForKnownBED");
      SendRequestType request = requestBuilder.getRequest();
      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder("agreement");
      request.setCommonInput(((CommonInputMapper)Mappers.getMapper(CommonInputMapper.class)).map(commonBuilder.createCommonInput(McnConfigUtil.retrievePackageInfo("agreement"), isTest, references.getInputReference())));
      request.setRouting(((RoutingMapper)Mappers.getMapper(RoutingMapper.class)).map(commonBuilder.createRouting(patientInfo, refDate)));
      request.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      request.setIssueInstant(new DateTime());
      request.setDetail(BlobMapper.mapBlobTypefromBlob(blob));
      (new AgreementXmlValidatorImpl()).validate(request);
   }

   private void checkParameterNotNull(Object references, String parameterName) throws AgreementBusinessConnectorException {
      if (references == null) {
         throw new AgreementBusinessConnectorException(AgreementBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public static byte[] buildXades(String id, byte[] contentToSign) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      options.put("transformerList", tranforms);
      options.put("baseURI", id);
      return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(SessionUtil.getEncryptionCredential(), contentToSign, options);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(new Class[]{AskAgreementRequest.class});
      JaxbContextFactory.initJaxbContext(new Class[]{ConsultAgreementRequest.class});
      JaxbContextFactory.initJaxbContext(new Class[]{SendTransactionRequest.class});
      JaxbContextFactory.initJaxbContext(new Class[]{CommonInputType.class});
      JaxbContextFactory.initJaxbContext(new Class[]{RoutingType.class});
   }
}
