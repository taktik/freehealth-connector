package be.ehealth.business.mycarenetcommons.builders.util;

import be.ehealth.business.mycarenetcommons.mapper.v3.SendRequestMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
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
   private static final String MYCARENET = "mycarenet.";
   private static final String MYCARENET_PLATFORM = "mycarenet";
   private static final String NEEDXADES = ".request.needxades";
   private static final String XADESTYPE = ".request.xadestype";

   public static Base64Binary generateXades(BlobType inValue, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      if (projectName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"project name"});
      } else {
         ConfigValidator props = ConfigFactory.getConfigValidator();
         Boolean defaultValue = props.getBooleanProperty("${mycarenet.default.request.needxades}", false);
         if (props.getBooleanProperty("mycarenet." + projectName + ".request.needxades", defaultValue)) {
            return ArrayUtils.isEmpty(furnishedXades) ? generateXades(inValue, projectName) : convertXadesToBinary(furnishedXades);
         } else {
            return null;
         }
      }
   }

   public static Base64Binary generateXades(be.fgov.ehealth.mycarenet.commons.core.v2.BlobType inValue, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      BlobType blobType = new BlobType();
      blobType.setContentEncoding(inValue.getContentEncoding());
      blobType.setContentType(inValue.getContentEncoding());
      blobType.setHashValue(inValue.getHashValue());
      blobType.setId(inValue.getId());
      blobType.setValue(inValue.getValue());
      blobType.setContentType(inValue.getContentEncoding());
      return generateXades(blobType, furnishedXades, projectName);
   }

   public static Base64Binary generateXadesForBlob(Blob blob, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.mapBlobToBlobType(blob);
      return generateXades(blobForXades, furnishedXades, projectName);
   }

   public static byte[] generateXades(Blob blob, String projectName, String platformName) throws TechnicalConnectorException {
      Base64Binary base64Binary = generateXadesForBlob(blob, projectName, platformName);
      return base64Binary == null ? null : base64Binary.getValue();
   }

   public static byte[] generateXades(Blob blob, String projectName) throws TechnicalConnectorException {
      Base64Binary base64Binary = generateXadesForBlob(blob, projectName, "mycarenet");
      return base64Binary == null ? null : base64Binary.getValue();
   }

   public static Base64Binary generateXades(BlobType inValue) throws TechnicalConnectorException {
      return generateXades(inValue, "default");
   }

   public static Base64Binary generateXadesForBlob(Blob inValue) throws TechnicalConnectorException {
      return generateXadesForBlob(inValue, "default");
   }

   public static Base64Binary generateXadesForBlob(Blob blob, String projectName) throws TechnicalConnectorException {
      return generateXadesForBlob(blob, projectName, "mycarenet");
   }

   public static Base64Binary generateXadesForBlob(Blob blob, String projectName, String platformName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.mapBlobToBlobType(blob);
      return generateXades(blobForXades, projectName, platformName);
   }

   public static Base64Binary generateXades(BlobType inValue, String projectName, String platformName) throws TechnicalConnectorException {
      return generateXades(inValue, inValue.getId(), inValue.getContentEncoding(), inValue.getContentType(), projectName, platformName);
   }

   private static Base64Binary generateXades(Object inValue, String id, String contentEncoding, String contentType, String projectName, String platformName) throws TechnicalConnectorException {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      String xadesTypePropertyKey = platformName + "." + projectName + ".request.xadestype";
      String propValue = props.getProperty(xadesTypePropertyKey, "${" + platformName + ".default" + ".request.xadestype" + "}");
      if (!"none".equals(propValue)) {
         if (!"xades".equals(propValue) && !"xadest".equals(propValue)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Property " + xadesTypePropertyKey + " with value " + propValue + " is not a supported value"});
         } else {
            Map<String, Object> options = new HashMap();
            options.put("baseURI", id);
            List<String> transformList = new ArrayList();
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

            byte[] xadesValue = SignatureBuilderFactory.getSignatureBuilder(xadesType).sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray(inValue), options);
            return convertXadesToBinary(xadesValue);
         }
      } else {
         return null;
      }
   }

   public static Base64Binary generateXades(BlobType inValue, String projectName) throws TechnicalConnectorException {
      return generateXades(inValue, projectName, "mycarenet");
   }

   public static Base64Binary generateXades(be.fgov.ehealth.mycarenet.commons.core.v2.BlobType inValue, String projectName) throws TechnicalConnectorException {
      return generateXades(inValue, inValue.getId(), inValue.getContentEncoding(), inValue.getContentType(), projectName, "mycarenet");
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
