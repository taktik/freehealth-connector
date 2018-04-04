package be.ehealth.businessconnector.dmg.builders.impl;

import be.cin.nip.async.generic.MsgResponse;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import be.ehealth.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import be.ehealth.businessconnector.dmg.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.dmg.domain.DmgBuilderResponse;
import be.ehealth.businessconnector.dmg.mappers.BlobMapper;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ByteArrayDatasource;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.globalmedicalfile.core.v1.BlobType;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileResponse;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendResponseType;
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionResponse;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005._05.xmlmime.Base64Binary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);

   public final DmgBuilderResponse handleSendResponseType(SendResponseType sendResponse) throws TechnicalConnectorException {
      String code = sendResponse.getStatus().getCode();
      BlobType blobType = sendResponse.getReturn().getDetail();
      Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
      Base64Binary xadesT = sendResponse.getReturn().getXadesT();
      return this.createDmgBuilderResponse(code, sendResponse, blob, xadesT);
   }

   private DmgBuilderResponse createDmgBuilderResponse(String code, Serializable originalResult, Blob blob, Base64Binary xadesT) throws TechnicalConnectorException, InvalidBlobContentConnectorException {
      Map<String, Object> result = new HashMap();
      result.put("result.ehealth.status", code);
      result.put("result.original", originalResult);
      BlobBuilder blobBuilder = BlobBuilderFactory.getBlobBuilder("dmg");
      Object var7 = null;

      byte[] resultbusiness;
      try {
         resultbusiness = blobBuilder.checkAndRetrieveContent(blob);
      } catch (InvalidBlobContentConnectorException var12) {
         if (var12.getDecompressedBlob() == null) {
            throw var12;
         }

         LOG.error(var12.getMessage());
         resultbusiness = var12.getDecompressedBlob();
      }

      if (xadesT != null && xadesT.getValue() != null && !ArrayUtils.isEmpty(xadesT.getValue())) {
         result.put("result.has.signature", true);
         byte[] xadesTByteArray = org.apache.commons.lang.ArrayUtils.clone(xadesT.getValue());
         SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
         Element sigElement = ConnectorXmlUtils.toElement(xadesTByteArray);
         Map<String, Object> optionMap = new HashMap();
         result.put("result.signature.verification", builder.verify(this.reassemblyMessage(originalResult, sigElement), xadesTByteArray, optionMap));
      } else {
         result.put("result.has.signature", false);
      }

      result.put("result.business.byte", new ByteArrayDatasource(resultbusiness));
      this.putUnmarshalledMessage(originalResult, result, resultbusiness);
      return new DmgBuilderResponse(result);
   }

   private void putUnmarshalledMessage(Object originalResult, Map<String, Object> result, byte[] resultbusiness) throws TechnicalConnectorException {
      if (resultbusiness != null && resultbusiness.length != 0) {
         if (originalResult instanceof ConsultGlobalMedicalFileResponse) {
            result.put("result.business.RetrieveTransactionResponse", this.unmarshallByteContent(resultbusiness, RetrieveTransactionResponse.class));
         } else if (originalResult instanceof SendResponseType) {
            result.put("result.business.SendTransactionResponse", this.unmarshallByteContent(resultbusiness, SendTransactionResponse.class));
         } else {
            if (!(originalResult instanceof MsgResponse)) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"Unsupported SendResponseType or MsgResponse type: " + originalResult.getClass()});
            }

            MsgResponse msgResponse = (MsgResponse)originalResult;
            String messageName = msgResponse.getDetail().getMessageName();
            if ("GMD-CONSULT-HCP".equals(messageName)) {
               result.put("result.business.RetrieveTransactionResponse", this.unmarshallByteContent(resultbusiness, RetrieveTransactionResponse.class));
            } else {
               if (!"GMD-CLOSURE".equals(messageName) && !"GMD-EXTENSION".equals(messageName)) {
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"unsupported messageName for Blob in MsgResponse : messageName=[" + messageName + "]"});
               }

               result.put("result.business.KmehrMessage", this.unmarshallByteContent(resultbusiness, Kmehrmessage.class));
            }
         }

      }
   }

   private <T extends Serializable> Serializable unmarshallByteContent(byte[] byteArray, Class<T> unmarshallClass) {
      MarshallerHelper<T, T> helper = new MarshallerHelper(unmarshallClass, unmarshallClass);
      Serializable jaxbObject = null;

      try {
         if (byteArray != null && ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.businessconnector.dmg.builders.impl.dumpMessages", false).booleanValue()) {
            LOG.debug("unmarshallByteContent: unmarshalling following xml : " + new String(byteArray));
         }

         jaxbObject = (Serializable)helper.toObject(byteArray);
         return jaxbObject;
      } catch (IllegalArgumentException var6) {
         LOG.warn("Unable to create business object. Reason: IllegalArgumentException: " + var6.getMessage());
         return var6;
      }
   }

   public DmgBuilderResponse handleAsyncResponse(MsgResponse asyncMsgResponse) throws TechnicalConnectorException {
      String code = "200";
      Blob blob = DomainBlobMapper.mapToBlob(asyncMsgResponse.getDetail());
      Base64Binary xadesT = asyncMsgResponse.getXadesT();
      return this.createDmgBuilderResponse(code, asyncMsgResponse, blob, xadesT);
   }

   private byte[] reassemblyMessage(Object responseObject, Element sigElement) throws TechnicalConnectorException {
      MarshallerHelper<Object, Object> responseMarshaller = this.getMarshallerHelper(responseObject);
      Document explodedDoc = responseMarshaller.toDocument(responseObject);
      explodedDoc.adoptNode(sigElement);
      Element el = explodedDoc.createElement("XadesT-Signature");
      el.appendChild(sigElement);
      ConnectorXmlUtils.getFirstChildElement(explodedDoc).appendChild(el);
      return ConnectorXmlUtils.toByteArray((Node)explodedDoc);
   }

   private <T> MarshallerHelper<T, T> getMarshallerHelper(Object response) throws TechnicalConnectorException {
      MarshallerHelper<T, T> responseMarshaller = null;
      if (response instanceof ConsultGlobalMedicalFileResponse) {
         responseMarshaller = new MarshallerHelper(ConsultGlobalMedicalFileResponse.class, ConsultGlobalMedicalFileResponse.class);
      } else if (response instanceof NotifyGlobalMedicalFileResponse) {
         responseMarshaller = new MarshallerHelper(NotifyGlobalMedicalFileResponse.class, NotifyGlobalMedicalFileResponse.class);
      } else {
         if (!(response instanceof MsgResponse)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"Unsupported SendResponseType: " + response.getClass()});
         }

         responseMarshaller = new MarshallerHelper(MsgResponse.class, MsgResponse.class);
      }

      return responseMarshaller;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(ConsultGlobalMedicalFileResponse.class);
      JaxbContextFactory.initJaxbContext(NotifyGlobalMedicalFileResponse.class);
      JaxbContextFactory.initJaxbContext(SendResponseType.class);
      JaxbContextFactory.initJaxbContext(RetrieveTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(SendTransactionResponse.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
   }
}
