package org.taktik.connector.business.mycarenetdomaincommons.builders.impl;

import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorExceptionValues;
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlobBuilderImpl implements BlobBuilder {
   private static final String PROPERTYBEGINNING = "mycarenet.blobbuilder.";
   private String projectName;

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectName")) {
         this.projectName = (String)parameterMap.get("projectName");
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of BlobBuilder , check factory method call"});
      }
   }

   public Blob build(byte[] input) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String id = "mycarenet.blobbuilder." + usedProjectName + ".id";
      ConfigValidator validator = this.getProperties(id);
      return this.build(input, validator.getProperty(id));
   }

   public Blob build(byte[] input, String id) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String encodingType = "mycarenet.blobbuilder." + usedProjectName + ".encodingtype";
      String contentType = "mycarenet.blobbuilder." + usedProjectName + ".contenttype";
      ConfigValidator validator = this.getProperties(encodingType, contentType);
      return this.build(input, validator.getProperty(encodingType), id, validator.getProperty(contentType));
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
      return PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet.blobbuilder.");
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
