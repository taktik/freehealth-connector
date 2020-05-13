package org.taktik.connector.business.mycarenetcommons.builders.util;

import org.taktik.connector.business.mycarenetcommons.mapper.v3.SendRequestMapper;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.w3._2005._05.xmlmime.Base64Binary;

public class BlobUtil implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public static Base64Binary generateXades(Credential credential, BlobType inValue, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      if (projectName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, "project name");
      } else {
         ConfigValidator props = ConfigFactory.getConfigValidator();
         Boolean defaultValue = props.getBooleanProperty("${mycarenet.default.request.needxades}", false);
         if (props.getBooleanProperty("mycarenet." + projectName + ".request.needxades", defaultValue)) {
            return ArrayUtils.isEmpty(furnishedXades) ? generateXades(credential, inValue, projectName) : convertXadesToBinary(furnishedXades);
         } else {
            return null;
         }
      }
   }

   public static Base64Binary generateXades(Credential credential, be.fgov.ehealth.mycarenet.commons.core.v2.BlobType inValue, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      BlobType blobType = new BlobType();
      blobType.setContentEncoding(inValue.getContentEncoding());
      blobType.setContentType(inValue.getContentEncoding());
      blobType.setHashValue(inValue.getHashValue());
      blobType.setId(inValue.getId());
      blobType.setValue(inValue.getValue());
      blobType.setContentType(inValue.getContentEncoding());
      return generateXades(credential, blobType, furnishedXades, projectName);
   }

   public static Base64Binary generateXadesForBlob(Credential credential, Blob blob, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.Companion.mapBlobToBlobType(blob);
      return generateXades(credential, blobForXades, furnishedXades, projectName);
   }

   public static byte[] generateXades(Credential credential, Blob blob, String projectName, String platformName) throws TechnicalConnectorException {
      Base64Binary base64Binary = generateXadesForBlob(credential, blob, projectName, platformName);
      return base64Binary == null ? null : base64Binary.getValue();
   }

   public static byte[] generateXades(Credential credential, Blob blob, String projectName) throws TechnicalConnectorException {
      Base64Binary base64Binary = generateXadesForBlob(credential, blob, projectName, "mycarenet");
      return base64Binary == null ? null : base64Binary.getValue();
   }

   public static Base64Binary generateXades(Credential credential, BlobType inValue) throws TechnicalConnectorException {
      return generateXades(credential, inValue, "default");
   }

   public static Base64Binary generateXadesForBlob(Credential credential, Blob inValue) throws TechnicalConnectorException {
      return generateXadesForBlob(credential, inValue, "default");
   }

   public static Base64Binary generateXadesForBlob(Credential credential, Blob blob, String projectName) throws TechnicalConnectorException {
      return generateXadesForBlob(credential, blob, projectName, "mycarenet");
   }

   public static Base64Binary generateXadesForBlob(Credential credential, Blob blob, String projectName, String platformName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.Companion.mapBlobToBlobType(blob);
      return generateXades(credential, blobForXades, projectName, platformName);
   }

   public static Base64Binary generateXades(Credential credential, BlobType inValue, String projectName, String platformName) throws TechnicalConnectorException {
      return generateXades(credential, inValue, inValue.getId(), inValue.getContentEncoding(), inValue.getContentType(), projectName, platformName);
   }

   private static Base64Binary generateXades(Credential credential, Object inValue, String id, String contentEncoding, String contentType, String projectName, String platformName) throws TechnicalConnectorException {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      String xadesTypePropertyKey = platformName + "." + projectName + ".request.xadestype";
      String propValue = props.getProperty(xadesTypePropertyKey, "${" + platformName + ".default" + ".request.xadestype" + "}");
      if (!"none".equals(propValue)) {
      if (!"xades".equals(propValue) && !"xadest".equals(propValue)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Property " + xadesTypePropertyKey + " with value " + propValue + " is not a supported value"});
      } else {
         Map<String, Object> options = new HashMap<>();
         options.put("baseURI", id);
         List<String> transformList = new ArrayList<>();
         transformList.add("http://www.w3.org/2000/09/xmldsig#base64");
         if ("deflate".equals(contentEncoding)) {
            transformList.add("urn:nippin:xml:sig:transform:optional-deflate");
         }
         if ("text/xml".equals(contentType)) {
            transformList.add("http://www.w3.org/2001/10/xml-exc-c14n#");
         }

         options.put("transformerList", transformList);
         AdvancedElectronicSignatureEnumeration xadesType;
         if ("xadest".equals(propValue)) {
            xadesType = AdvancedElectronicSignatureEnumeration.XAdES_T;
         } else {
            xadesType = AdvancedElectronicSignatureEnumeration.XAdES;
         }

         byte[] xadesValue = SignatureBuilderFactory.getSignatureBuilder(xadesType).sign(credential, ConnectorXmlUtils.toByteArray(inValue), options);
         return convertXadesToBinary(xadesValue);
      }
      } else {
         return null;
      }
   }

   public static Base64Binary generateXades(Credential credential, BlobType inValue, String projectName) throws TechnicalConnectorException {
      return generateXades(credential, inValue, projectName, "mycarenet");
   }

   public static Base64Binary generateXades(Credential credential, be.fgov.ehealth.mycarenet.commons.core.v2.BlobType inValue, String projectName) throws TechnicalConnectorException {
      return generateXades(credential, inValue, inValue.getId(), inValue.getContentEncoding(), inValue.getContentType(), projectName, "mycarenet");
   }

   private static Base64Binary convertXadesToBinary(byte[] xadesValue) {
      Base64Binary value = null;
      if (xadesValue != null) {
         value = new Base64Binary();
         value.setValue(xadesValue);
         value.setContentType("text/xml");
      }

      return value;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
   }
}
