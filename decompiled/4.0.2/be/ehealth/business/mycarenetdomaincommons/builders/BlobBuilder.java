package be.ehealth.business.mycarenetdomaincommons.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;

public interface BlobBuilder extends ConfigurableImplementation {
   String PROJECT_NAME_KEY = "projectName";
   String PLATFORM_NAME_KEY = "platformName";
   String MESSAGE_NAME_KEY = "messageName";

   Blob build(byte[] var1) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4, String var5) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4, String var5, String var6) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, BlobAttributeValues var2) throws TechnicalConnectorException;

   byte[] checkAndRetrieveContent(Blob var1) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
}
