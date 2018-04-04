package org.taktik.connector.business.mycarenetcommons.builders.impl;

import org.taktik.connector.business.mycarenetcommons.builders.ResponseBuilder;
import org.taktik.connector.business.mycarenetcommons.builders.util.PropertyUtil;
import org.taktik.connector.business.mycarenetcommons.validator.CommonOutputValidator;
import org.taktik.connector.business.mycarenetdomaincommons.builders.impl.BuilderUtils;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorExceptionValues;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v2.BlobType;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendResponseType;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import oasis.names.tc.dss._1_0.core.schema.SignRequest;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;
import org.apache.commons.lang.ArrayUtils;
import org.etsi.uri._01903.v1_3.QualifyingProperties;
import org.etsi.uri._01903.v1_3.UnsignedProperties;
import org.w3._2005._05.xmlmime.Base64Binary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class ResponseBuilderImpl implements ResponseBuilder, ConfigurableImplementation, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String XADES_LEVEL_XADES = "xades";
   private static final String XADES_LEVEL_XADEST = "xadest";
   private static final String XADES_LEVEL_NONE = "none";
   private static ResponseBuilderImpl instance;
   private String projectName;

   public static ResponseBuilder getInstance() {
      if (instance == null) {
         instance = new ResponseBuilderImpl();
      }

      return instance;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectname")) {
         this.projectName = (String)parameterMap.get("projectname");
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of CommonBuilder , check factory method call"});
      }
   }

   public void validateHash(Blob blob) throws TechnicalConnectorException {
      this.checkIfInitialized();
      byte[] decompressedBlob = BuilderUtils.decompressBlob(blob.getContent(), blob.getContentEncoding());
      BuilderUtils.checkHash(blob.getHashValue(), decompressedBlob);
   }

   private void checkIfInitialized() throws IllegalStateException {
      if (this.projectName == null) {
         throw new IllegalStateException("method on " + this.getClass().getCanonicalName() + " called while it was not initialized yet ");
      }
   }

   /** @deprecated */
   @Deprecated
   public void validateXades(SendResponseType responseType, Blob blob) throws TechnicalConnectorException {
      this.commonValidateMethod(responseType, blob);
   }

   private void commonValidateMethod(SendResponseType responseType, Blob blob) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      this.checkIfInitialized();
      String neededXadesLevel = this.retrieveResponseXadesProperty();
      Base64Binary xades = null;
      if (responseType != null && responseType.getReturn() != null) {
         xades = responseType.getReturn().getXadesT();
      }

      if (xades != null && xades.getValue() != null && !ArrayUtils.isEmpty(xades.getValue())) {
         byte[] xadesByteArray = ArrayUtils.clone(xades.getValue());
         AdvancedElectronicSignatureEnumeration xadesSignatureType = this.convertToSignatureType(neededXadesLevel);
         SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(xadesSignatureType);
         Element sigElement = ConnectorXmlUtils.toElement(xadesByteArray);
         Map<String, Object> optionMap = new HashMap();
         SignatureVerificationResult result = builder.verify(this.reassemblyMessage(responseType, sigElement), xadesByteArray, optionMap);
         if (result.getErrors().size() != 0) {
            this.createInvalidBlobContentConnectorException(blob, result, xades);
         }
      } else if (!"none".equals(neededXadesLevel)) {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.XADESVALUE_NULL, blob);
      }

   }

   public void createInvalidBlobContentConnectorException(Blob blob, SignatureVerificationResult result, Base64Binary xades) throws InvalidBlobContentConnectorException {
      StringBuilder errorMessageBuilder = new StringBuilder();
      Iterator i$ = result.getErrors().iterator();

      while(i$.hasNext()) {
         SignatureVerificationError verificationError = (SignatureVerificationError)i$.next();
         errorMessageBuilder.append("error: ").append(verificationError.getErrorName()).append(":[").append(verificationError.getMessage()).append("]");
      }

      String receivedXadesString = xades != null && xades.getValue() != null ? new String(xades.getValue()) : "";
      throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.XADESVALUES_DIFFERENT, blob, new Object[]{errorMessageBuilder.toString(), receivedXadesString});
   }

   public void validateXades(SendResponseType responseType) throws TechnicalConnectorException {
      Blob blob = this.mapBlobTypeToBlob(responseType.getReturn().getDetail());
      this.commonValidateMethod(responseType, blob);
   }

   private AdvancedElectronicSignatureEnumeration convertToSignatureType(String xadesRequiredProperty) {
      if ("xadest".equals(xadesRequiredProperty)) {
         return AdvancedElectronicSignatureEnumeration.XAdES_T;
      } else if ("xades".equals(xadesRequiredProperty)) {
         return AdvancedElectronicSignatureEnumeration.XAdES;
      } else {
         String projectNameToUse = PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet.");
         throw new IllegalArgumentException("no AdvancedElectronicSignatureEnumeration known for config parameter 'mycarenet." + projectNameToUse + ".response.requiredxades' with value " + xadesRequiredProperty);
      }
   }

   public String retrieveResponseXadesProperty() {
      return ConfigFactory.getConfigValidator().getProperty("mycarenet." + this.projectName + ".response.neededxadeslevel", "xades");
   }

   public String getResponse(SendResponseType responseType) throws TechnicalConnectorException {
      return this.getResponse(responseType, true);
   }

   public String getResponse(SendResponseType responseType, boolean validate) throws TechnicalConnectorException {
      Blob blob = this.mapBlobTypeToBlob(responseType.getReturn().getDetail());
      if (validate) {
         CommonOutputValidator.validate(responseType.getReturn().getCommonOutput());
         this.validateHash(blob);
         this.commonValidateMethod(responseType, blob);
      }

      return new String(BuilderUtils.decompressBlob(blob.getContent(), blob.getContentEncoding()));
   }

   public Blob mapBlobTypeToBlob(BlobType inBlob) {
      Blob blob = new Blob();
      blob.setId(inBlob.getId());
      blob.setContent(inBlob.getValue());
      blob.setHashValue(inBlob.getHashValue());
      blob.setContentEncoding(inBlob.getContentEncoding());
      blob.setContentType(inBlob.getContentType());
      return blob;
   }

   private byte[] reassemblyMessage(Object responseObject, Element sigElement) throws TechnicalConnectorException {
      MarshallerHelper<Object, Object> responseMarshaller = new MarshallerHelper(responseObject.getClass(), responseObject.getClass());
      Document explodedDoc = responseMarshaller.toDocument(responseObject);
      explodedDoc.adoptNode(sigElement);
      Element el = explodedDoc.createElement("XadesT-Signature");
      el.appendChild(sigElement);
      ConnectorXmlUtils.getFirstChildElement(explodedDoc).appendChild(el);
      return ConnectorXmlUtils.toByteArray((Node)explodedDoc);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(SendResponseType.class);
      JaxbContextFactory.initJaxbContext(SendRequestType.class);
      JaxbContextFactory.initJaxbContext(SignResponse.class);
      JaxbContextFactory.initJaxbContext(UnsignedProperties.class);
      JaxbContextFactory.initJaxbContext(SignRequest.class);
      JaxbContextFactory.initJaxbContext(QualifyingProperties.class);
   }
}
