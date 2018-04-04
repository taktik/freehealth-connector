package org.taktik.connector.business.mycarenetdomaincommons.util;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3._2005._05.xmlmime.Base64Binary;

public class DomainBlobUtil {
   private static final String MYCARENET = "mycarenet.";
   private static final String XADESTYPE = ".request.xadestype";

   private static Base64Binary convertXadesToBinary(byte[] xadesValue) {
      Base64Binary value = null;
      if (xadesValue != null) {
         value = new Base64Binary();
         value.setValue(xadesValue);
         value.setContentType("text/xml");
      }

      return value;
   }

   public static Base64Binary generateXades(Blob inValue, String projectName) throws TechnicalConnectorException {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      String propValue = props.getProperty("mycarenet." + projectName + ".request.xadestype", "${mycarenet.default.request.xadestype}");
      if (!"xades".equals(propValue) && !"xadest".equals(propValue)) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Property mycarenet." + projectName + ".request.xadestype" + " with value " + propValue + " is not a supported value"});
      } else {
         Map<String, Object> options = new HashMap();
         options.put("baseURI", inValue.getId());
         List<String> transformList = new ArrayList();
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
            options.put("SignatureTimestampEndpointTimestampAuthority", "https://services-acpt.ehealth.fgov.be/TimestampAuthority/v2");
            xadesType = AdvancedElectronicSignatureEnumeration.XAdES_T;
         } else {
            xadesType = AdvancedElectronicSignatureEnumeration.XAdES;
         }

         byte[] xadesValue = SignatureBuilderFactory.getSignatureBuilder(xadesType).sign(Session.getInstance().getSession().getEncryptionCredential(), ConnectorXmlUtils.toByteArray((Object)inValue), options);
         return convertXadesToBinary(xadesValue);
      }
   }

   public static Base64Binary generateXadesForBlob(Blob blob, String projectName) throws TechnicalConnectorException {
      return generateXades(blob, projectName);
   }
}
