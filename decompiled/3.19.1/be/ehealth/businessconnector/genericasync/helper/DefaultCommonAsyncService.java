package be.ehealth.businessconnector.genericasync.helper;

import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.TAckResponse;
import be.cin.types.v1.Blob;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedTAckResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DefaultCommonAsyncService implements CommonAsyncService {
   private GetHelper getHelper;

   public DefaultCommonAsyncService(String projectName) {
      this.getHelper = new GetHelper(projectName);
   }

   public ConfirmResponse confirmAll(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.getHelper.confirmAll(getResponse);
   }

   public ConfirmResponse confirmAllTAcks(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.getHelper.confirmWithTAckReferences(getResponse);
   }

   public ConfirmResponse confirmAllMessages(ProcessedGetResponse getResponse) throws ConnectorException {
      return this.getHelper.confirmWithMessageReferences(getResponse);
   }

   public ConfirmResponse confirmTAck(ProcessedTAckResponse tAckResponse) throws ConnectorException {
      return this.getHelper.confirmTAckWithReference(tAckResponse);
   }

   public ConfirmResponse confirmMessage(ProcessedMsgResponse msgResponse) throws ConnectorException {
      return this.getHelper.confirmMessageWithReference(msgResponse);
   }

   public SignatureVerificationResult validateXadesTWithManifest(TAckResponse tAckResponse, Blob postedBlob, byte[] xadesT) throws GenAsyncBusinessConnectorException {
      Validate.notNull(tAckResponse);
      Validate.notNull(postedBlob);
      Validate.notNull(xadesT);
      Element blobElement = ConnectorXmlUtils.toDocument((Object)postedBlob).getDocumentElement();
      Document signedContent = ConnectorXmlUtils.toDocument((Object)tAckResponse);
      signedContent.getFirstChild().appendChild(signedContent.importNode(blobElement, true));
      byte[] signedByteArray = ConnectorXmlUtils.toByteArray((Node)signedContent);
      Map<String, Object> options = new HashMap();
      options.put("followNestedManifest", true);

      try {
         return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify(signedByteArray, xadesT, options);
      } catch (TechnicalConnectorException var9) {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, var9, new Object[]{var9.getMessage()});
      }
   }
}
