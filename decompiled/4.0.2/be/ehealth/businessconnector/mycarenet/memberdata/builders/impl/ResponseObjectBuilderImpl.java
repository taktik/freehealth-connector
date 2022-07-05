package be.ehealth.businessconnector.mycarenet.memberdata.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.v3.mapper.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.memberdata.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.memberdata.domain.MemberDataBuilderResponse;
import be.ehealth.businessconnector.mycarenet.memberdata.signature.MemberDataSignatureVerifier;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;
import oasis.names.tc.saml._2_0.protocol.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);
   private static final String PROP_DUMP_MESSAGES = "be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages";

   public ResponseObjectBuilderImpl() {
   }

   public MemberDataBuilderResponse handleConsultationResponse(MemberDataConsultationResponse consultResponse) throws TechnicalConnectorException {
      BlobType blobType = consultResponse.getReturn().getDetail();
      Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
      if (blob.getContent().length > 0) {
         byte[] data = blob.getContent();
         if (blob.getContentEncryption() != null && !blob.getContentEncryption().isEmpty()) {
            byte[] unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).getContentAsByte();
            EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
            data = encryptedKnownContent.getBusinessContent().getValue();
         }

         if (data != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.mycarenet.memberdatasync.builders.impl.dumpMessages", false)) {
            LOG.debug("ResponseObjectBuilder : Blob content: {}", new String(data));
         }

         try {
            MemberDataSignatureVerifier signatureVerifier = new MemberDataSignatureVerifier();
            Map<String, SignatureVerificationResult> signatureVerificationResults = signatureVerifier.verifyAll(data, (Map)null);
            return new MemberDataBuilderResponse(consultResponse, (Response)ConnectorXmlUtils.toObject(data, Response.class), signatureVerificationResults);
         } catch (Exception var7) {
            LOG.error("Error processing MemberDataConsultationResponse with id {0}", consultResponse.getId(), var7);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, var7, new Object[0]);
         }
      } else {
         return null;
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse.class);
      JaxbContextFactory.initJaxbContext(Response.class);
      JaxbContextFactory.initJaxbContext(EncryptedKnownContent.class);
   }
}
