package org.taktik.connector.business.mycarenetcommons.builders.util;

import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v2.BlobType;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.w3._2005._05.xmlmime.Base64Binary;

public class BlobUtil implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public static Base64Binary generateXades(BlobType inValue, Credential credential, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      if (projectName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"project name"});
      } else {
         ConfigValidator props = ConfigFactory.getConfigValidator();
         Boolean defaultValue = props.getBooleanProperty("${mycarenet.default.request.needxades}", false);
         if (props.getBooleanProperty("mycarenet." + projectName + ".request.needxades", defaultValue)) {
            return ArrayUtils.isEmpty(furnishedXades) ? generateXades(inValue, credential, projectName) : convertXadesToBinary(furnishedXades);
         } else {
            return null;
         }
      }
   }

   public static Base64Binary generateXadesForBlob(Blob blob, Credential credential, byte[] furnishedXades, String projectName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.mapBlobToBlobType(blob);
      return generateXades(blobForXades, credential, furnishedXades, projectName);
   }

   public static Base64Binary generateXades(BlobType inValue, Credential credential) throws TechnicalConnectorException {
      return generateXades(inValue, credential,"default");
   }

   public static Base64Binary generateXadesForBlob(Blob inValue, Credential credential) throws TechnicalConnectorException {
      return generateXadesForBlob(inValue, credential, "default");
   }

   public static Base64Binary generateXadesForBlob(Blob blob, Credential credential, String projectName) throws TechnicalConnectorException {
      BlobType blobForXades = SendRequestMapper.mapBlobToBlobType(blob);
      return generateXades(blobForXades, credential, projectName);
   }

   public static Base64Binary generateXades(BlobType inValue, Credential credential, String projectName) throws TechnicalConnectorException {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      String propValue = props.getProperty("mycarenet." + projectName + ".request.xadestype", "${mycarenet.default.request.xadestype}");
      if (!"xades".equals(propValue) && !"xadest".equals(propValue)) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, "Property mycarenet." + projectName + ".request.xadestype" + " with value " + propValue + " is not a supported value");
      } else {
         Map<String, Object> options = new HashMap<>();
         options.put("baseURI", inValue.getId());
         List<String> transformList = new ArrayList<>();
         transformList.add("http://www.w3.org/2000/09/xmldsig#base64");
         if ("deflate".equals(inValue.getContentEncoding())) {
            transformList.add("urn:nippin:xml:sig:transform:optional-deflate");
         }

         if ("text/xml".equals(inValue.getContentType())) {
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
