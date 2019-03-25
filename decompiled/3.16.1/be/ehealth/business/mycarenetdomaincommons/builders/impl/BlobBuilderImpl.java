package be.ehealth.business.mycarenetdomaincommons.builders.impl;

import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilder;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorExceptionValues;
import be.ehealth.business.mycarenetdomaincommons.util.PropertyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlobBuilderImpl implements BlobBuilder {
   private static final String PROPERTYBEGINNING = ".blobbuilder.";
   private String projectName;
   private String platformName;
   private String messageName;

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectName")) {
         this.platformName = (String)parameterMap.get("platformName") == null ? "mycarenet" : (String)parameterMap.get("platformName");
         this.projectName = (String)parameterMap.get("projectName");
         this.messageName = (String)parameterMap.get("messageName");
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of BlobBuilder , check factory method call"});
      }
   }

   public Blob build(byte[] input) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String id = this.platformName + ".blobbuilder." + usedProjectName + ".id";
      ConfigValidator validator = this.getProperties(id);
      return this.build(input, validator.getProperty(id));
   }

   public Blob build(byte[] input, String id) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String baseProperty = this.platformName + ".blobbuilder." + usedProjectName;
      String basePropertyWithMessageName = baseProperty + (this.messageName == null ? "" : "." + this.messageName);
      String projectDefaultEncodingTypeProperty = baseProperty + ".encodingtype";
      String projectDefaultContentTypeProperty = baseProperty + ".contenttype";
      String projectDefaultContentEncryptionProperty = baseProperty + ".contentencryption";
      String encodingTypePropertyWithMessageName = basePropertyWithMessageName + ".encodingtype";
      String contentTypePropertyWithMessageName = basePropertyWithMessageName + ".contenttype";
      String contentEncryptionPropertyWithMessageName = basePropertyWithMessageName + ".contentencryption";
      ConfigValidator validator = this.getProperties();
      String encodingType = validator.getProperty(encodingTypePropertyWithMessageName);
      String contentType = validator.getProperty(contentTypePropertyWithMessageName);
      String contentEncryption = validator.getProperty(contentEncryptionPropertyWithMessageName);
      if (encodingType == null) {
         encodingType = validator.getProperty(projectDefaultEncodingTypeProperty);
      }

      if (contentType == null) {
         contentType = validator.getProperty(projectDefaultContentTypeProperty);
      }

      if (contentEncryption == null) {
         contentEncryption = validator.getProperty(projectDefaultContentEncryptionProperty);
      }

      return this.build(input, encodingType, id, contentType, (String)null, contentEncryption);
   }

   public Blob build(byte[] input, String encodingType, String id, String contentType) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, encodingType, id, contentType, (String)null);
   }

   public Blob build(byte[] input, String encodingType, String id, String contentType, String messageName) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, encodingType, id, contentType, messageName, (String)null);
   }

   public Blob build(byte[] input, String encodingType, String id, String contentType, String messageName, String contentEncryption) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      if (input != null && input.length != 0) {
         if (contentType != null && !contentType.isEmpty()) {
            Blob newBlob = new Blob();
            newBlob.setContentEncoding("none");
            byte[] buff = input;
            if (encodingType.equals("deflate")) {
               newBlob.setContentEncoding(encodingType);
               buff = ConnectorIOUtils.compress(input, "deflate");
            }

            newBlob.setContent(buff);
            newBlob.setContentType(contentType);
            newBlob.setId(id);
            newBlob.setMessageName(messageName);
            newBlob.setHashValue((byte[])null);
            newBlob.setContentEncryption(contentEncryption);
            return newBlob;
         } else {
            throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, (Blob)null, new Object[]{"String contentType"});
         }
      } else {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, (Blob)null, new Object[]{"byte[] input"});
      }
   }

   public byte[] checkAndRetrieveContent(Blob blob) throws TechnicalConnectorException {
      if (blob == null) {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, (Blob)null, new Object[]{"Blob blob"});
      } else {
         return BuilderUtils.checkAndDecompress(blob.getContent(), blob.getContentEncoding(), blob.getHashValue(), blob.isHashTagRequired());
      }
   }

   private String getProjectPropertiesValue() {
      return PropertyUtil.retrieveProjectNameToUse(this.projectName, this.platformName + ".blobbuilder.");
   }

   private ConfigValidator getProperties(String... neededProperties) {
      List<String> expectedProps = new ArrayList();
      String[] arr$ = neededProperties;
      int len$ = neededProperties.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String neededPropertie = arr$[i$];
         expectedProps.add(neededPropertie);
      }

      return ConfigFactory.getConfigValidator(expectedProps);
   }
}
